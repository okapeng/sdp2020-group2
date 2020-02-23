#!/usr/bin/env python3

from tcpcom import TCPServer
import queue
import threading

# connection configuration settings
tcp_ip = ""
tcp_port = 4445
tcp_reply = "Server message"

# base controller configuration settings
EV3_CONNECTED = False
DEFAULT_SPEED = 500
DEFAULT_TIME = 100
DISCONNECTED = "disconnect"
STOP = "stop"
FOLLOW = "follow"

class BaseCtrl(threading.Thread):
    def __init__(self,t_name,commPool):
        threading.Thread.__init__(self,name=t_name)
        self.commPool = commPool
        self.followMode = FollowMode() if EV3_CONNECTED else None
    
    def run(self):
        while True:
            command = self.commPool.get(1)
            print("execute command:", command)
            if command == DISCONNECTED:
                break
            if command == STOP:
                print("command pool is empty: " + str(commPool.empty()))
            if not EV3_CONNECTED:
                continue
            if command == STOP:
                self.followMode.stop()
                stop()
            if command == FOLLOW:
                self.followMode.start()
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
        print("Base control stop")


def onStateChanged(state, msg):
    global isConnected
    if state == "LISTENING":
        print("Server:-- Listening...")
    elif state == "CONNECTED":
        isConnected = True
        print("Server:-- Connected to " + msg)
    elif state == "MESSAGE":
        print("Server:-- Manual command received: ", msg)
        if msg == "follow" or msg == "come" or msg == "stop":
            clear_pool()
        commPool.put(msg)
        
    
def clear_pool():
    with commPool.mutex:
        commPool.queue.clear()

def main():
    global server, commPool, baseCtrlThread
    commPool = queue.Queue()
    baseCtrlThread = BaseCtrl("Base controller", commPool)
    baseCtrlThread.start()

    server = TCPServer(tcp_port, stateChanged=onStateChanged, endOfBlock=b'\n', isVerbose=False)
    baseCtrlThread.join()



if __name__ == '__main__':
    if EV3_CONNECTED:
        from BaseController import *
        from FollowMode import *
    main()
