#!/usr/bin/env python2

from tcpcom import TCPServer
import time
import Queue
import threading
from pimove import *

# connection configuration settings
IP = ""
EV3_PORT = 4444
PORT = 4445
REPLY = "Server message"

# base controller configuration settings
MAX_SPEED = 100
MIN_SPEED = 80
DEFAULT_SPEED = 100
DEFAULT_DISTANCE = 100
MIN_DIS = 300

DISCONNECT = "disconnect"
STOP = "stop"
FOLLOW = "follow"
STOP_FOLLOW = "stop-follow"

COMMAND_LIST = ["forward", "back", "right", "left", "rotr", "rotl"]


class BaseCtrlThread(threading.Thread):
    def __init__(self,t_name,commPool):
        threading.Thread.__init__(self,name=t_name)
        # self.piMove = OurPiMove()
        self.commPool = commPool
    
    def run(self):
        while True:
            command = self.commPool.get(1)
            # print("execute command:", command)
            if command == DISCONNECT:
                break
            if command == STOP:
                self.piMove.stop()
            if command in COMMAND_LIST:
                self.piMove.move(DEFAULT_SPEED, DEFAULT_DISTANCE)
            # if command == "dtl":
            #     self.piMove.fleft(DEFAULT_SPEED, DEFAULT_DISTANCE)
            # if command == "dtr":
            #     self.piMove.fright(DEFAULT_SPEED, DEFAULT_DISTANCE)
            # if command == "dbl":
            #     self.piMove.bleft(DEFAULT_SPEED, DEFAULT_DISTANCE)
            # if command == "dbr":
            #     self.piMove.bright(DEFAULT_SPEED, DEFAULT_DISTANCE)
        clearCommPool()
        print("Base control stop")

class StatusUpdateThread(threading.Thread):
    def __init__(self, t_name):
        threading.Thread.__init__(self, name=t_name)
        self.running = True
        self.battery = 100

    def run(self):
        while(self.running):
            self.battery = self.battery - 1
            appServer.sendMessage("Battery:{}".format(self.battery))
            time.sleep(4)

    def stop(self):
        self.running = False
        
class AutoFollowThread(threading.Thread):
    def __init__(self, t_name):
        threading.Thread.__init__(self, name=t_name)
        self.running = True

    def run(self):
        piMove.stop()
        while(self.running):
            if(ev3Status['bc_dist']<0 or ev3Status['bc_dist'] >= 100):
                piMove.stop()
                appServer.sendMessage('Exception:Beacon lost')
                while ev3Status['bc_dist']<0 or ev3Status['bc_dist'] >= 100
                appServer.sendMessage('Exception:Beacon found')
            elif(usm.value() < MIN_DIS): # or usr.value()<usth or usl.value()<usth):
                piMove.stop()
            elif(ev3Status['bc_dist']>30 and abs(ev3Status['bc_heading'])<4):
                piMove.move("forward", DEFAULT_SPEED, DEFAULT_DISTANCE)
            elif(ev3Status['bc_heading']>2):
                piMove.move("rotl", MIN_SPEED, DEFAULT_DISTANCE)
            elif(ev3Status['bc_heading']<-2):
                piMove.move("rotr", MIN_SPEED, DEFAULT_DISTANCE)
            else:
                pass
    
    def stop(self):
        self.running = False

def onStateChangedApp(state, msg):
    global baseCtrlThread
    if state == "LISTENING":
        print("Server:-- Listening...")
    elif state == "CONNECTED":
        baseCtrlThread = BaseCtrlThread("Base controller", commPool)
        baseCtrlThread.start()
        statusUpdateThread = StatusUpdateThread("Status update")
        statusUpdateThread.start()
        print("Server:-- Connected to " + msg)
    elif state == "MESSAGE":
        print("Server:-- Manual command received: ", msg)
        if msg == FOLLOW or msg == STOP or msg == STOP_FOLLOW:
            clearCommPool()
        commPool.put(msg)

def onStateChangedEV3(state, msg):
    global ev3Status = dict()
    if state == "LISTENING":
        print("Server:-- Listening...")
    elif state == "CONNECTED":
        print("Server:-- Connected to " + msg)
    elif state == "MESSAGE":
        print("Server:-- Sensor data received: ", msg)
        tokens = msg.split(':')
        ev3Status[tokens[0]] = float(tokens[1])
        
def clearCommPool():
    with commPool.mutex:
        commPool.queue.clear()

def main():
    global appServer, ev3Server, commPool, piMove
    piMove = OurPiMove()
    commPool = Queue.Queue()
    appServer = TCPServer(PORT, stateChanged=onStateChangedApp, endOfBlock=b'\n', isVerbose=True)
    ev3Server = TCPServer(EV3_PORT, stateChanged=onStateChangedEV3, endOfBlock=b'\n', isVerbose=True)
    ev3Server.join()
    appServer.join()



if __name__ == '__main__':
    main()
