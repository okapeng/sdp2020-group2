#!/usr/bin/env python3

from tcpcom import TCPServer
import queue
import threading

# connection configuration settings
IP = ""
PORT = 4445
REPLY = "Server message"

# base controller configuration settings
EV3_CONNECTED = True
DEFAULT_SPEED = 500
DEFAULT_TIME = 200
DISCONNECTED = "disconnect"
STOP = "stop"
FOLLOW = "follow"
STOP_FOLLOW = "stop-follow"

if EV3_CONNECTED:
    import ev3dev.ev3 as ev3
    from BaseController import *

    usth = 300 #US threshold
    ir = ev3.InfraredSensor('in4') #IR port 2
    bs = ev3.BeaconSeeker(sensor = ir,channel = 1)
    usm = ev3.UltrasonicSensor('in3') #Middle US port 3
    usm.mode = 'US-DIST-CM'
    # usr = ev3.UltrasonicSensor('in1') #Right US
    # usr.mode = 'US-DIST-CM'
    # usl = ev3.UltrasonicSensor('in4') #Left US
    # usl.mode = 'US-DIST-CM'

class BaseCtrlThread(threading.Thread):
    def __init__(self,t_name,commPool):
        threading.Thread.__init__(self,name=t_name)
        self.commPool = commPool
        self.followThread = AutoFollowThread("follow thread") if EV3_CONNECTED else None
    
    def run(self):
        while True:
            command = self.commPool.get(1)
            # print("execute command:", command)
            if command == DISCONNECTED:
                break
            if not EV3_CONNECTED:
                continue
            if command == STOP:
                stop()
            if command == FOLLOW:
                if self.followThread.is_alive():
                    continue
                else:
                    self.followThread.start()
            if command == STOP_FOLLOW:
                print("stop follow")
                self.followThread.stop()
                self.followThread.join()
                self.followThread = AutoFollowThread("follow thread") if EV3_CONNECTED else None
                stop()
            if command == "forward":
                forward(DEFAULT_SPEED, DEFAULT_TIME)
            if command == "back":
                back(DEFAULT_SPEED, DEFAULT_TIME)
            if command == "left":
                left(DEFAULT_SPEED, DEFAULT_TIME)
            if command == "right":
                right(DEFAULT_SPEED, DEFAULT_TIME)
            if command == "rotr":
                rotr(DEFAULT_SPEED, DEFAULT_TIME)
            if command == "rotl":
                rotl(DEFAULT_SPEED, DEFAULT_TIME)
            if command == "dtl":
                fleft(DEFAULT_SPEED, DEFAULT_TIME)
            if command == "dtr":
                fright(DEFAULT_SPEED, DEFAULT_TIME)
            if command == "dbl":
                bleft(DEFAULT_SPEED, DEFAULT_TIME)
            if command == "dbr":
                bright(DEFAULT_SPEED, DEFAULT_TIME)
        clearCommPool()
        print("Base control stop")

class AutoFollowThread(threading.Thread):
    def __init__(self, t_name):
        threading.Thread.__init__(self, name=t_name)
        self.running = True

    def run(self):
        while(self.running):
            if(bs.distance<0):
                stop()
                # print('BEACON NOT FOUND')
            elif(bs.distance >= 100):
                stop()
                # print('OUT OF RANGE')
            elif(usm.value()<usth): # or usr.value()<usth or usl.value()<usth):
                # print('OBJECT DETECTED')
        #    object()
                stop()
            elif(bs.distance>30 and abs(bs.heading)<4):
                forward(750,100)
            elif(bs.heading>2):
                rotl(500,100)
            elif(bs.heading<-2):
                rotr(500,100)
            else:
                # print()
                pass
    
    def stop(self):
        self.running = False

def onStateChanged(state, msg):
    global isConnected
    if state == "LISTENING":
        print("Server:-- Listening...")
    elif state == "CONNECTED":
        isConnected = True
        baseCtrlThread = BaseCtrlThread("Base controller", commPool)
        baseCtrlThread.start()
        print("Server:-- Connected to " + msg)
    elif state == "MESSAGE":
        # print("Server:-- Manual command received: ", msg)
        if msg == FOLLOW or msg == "come" or msg == STOP or msg == STOP_FOLLOW:
            clearCommPool()
        commPool.put(msg)
        
def clearCommPool():
    with commPool.mutex:
        commPool.queue.clear()

def main():
    global serverThread, commPool, baseCtrlThread
    commPool = queue.Queue()
    serverThread = TCPServer(PORT, stateChanged=onStateChanged, endOfBlock=b'\n', isVerbose=False)
    serverThread.join()


if __name__ == '__main__':
    main()
