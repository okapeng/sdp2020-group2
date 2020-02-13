#!/usr/bin/env python3

from tcpcom import TCPServer
import queue
import threading
# from move import move

# connection configuration settings
tcp_ip = ""
tcp_port = 4445
tcp_reply = "Server message"
DISCONNECTED = "disconnect"


class BaseCtrl(threading.Thread):
    def __init__(self,t_name,commPool):
        threading.Thread.__init__(self,name=t_name)
        self.commPool = commPool
    
    def run(self):
        while True:
            command = self.commPool.get(1)
            print("execute command:", command)
            # if command == "forward":
            #     forward(DEFAULT_SPEED, DEFAULT_TIME)
            # if command == "back":
            #     back(DEFAULT_SPEED, DEFAULT_TIME)
            # if command == "left":
            #     left(DEFAULT_SPEED, DEFAULT_TIME)
            # if command == "right":
            #     right(DEFAULT_SPEED, DEFAULT_TIME)
            # if command == "rotr":
            #     rotr(DEFAULT_SPEED, DEFAULT_TIME)
            # if command == "rotl":
            #     rotl(DEFAULT_SPEED, DEFAULT_TIME)
            if command == DISCONNECTED:
                break
        print("Base control stop")


def onStateChanged(state, msg):
    global isConnected
    if state == "LISTENING":
        print("Server:-- Listening...")
    elif state == "CONNECTED":
        isConnected = True
        print("Server:-- Connected to " + msg)
    elif state == "MESSAGE":
        print("Server:-- Message received: ", msg)
    

def main():
    global server, commPool
    commPool = queue.Queue()
    baseCtrlThread = BaseCtrl("Base controller", commPool)
    baseCtrlThread.start()

    server = TCPServer(tcp_port, stateChanged=onStateChanged, commPool=commPool, isVerbose=True)
    baseCtrlThread.join()



if __name__ == '__main__':
    main()

# class server(threading.Thread):
#     def __init__(self, commPool):
#         threading.Thread.__init__(self, name="Server thread")
#         server = TCPServer(tcp_port, stateChanged=self.onStateChanged, commPool=commPool, isVerbose=True)

#     def onStateChanged(self, state, msg):
#         global isConnected
#         if state == "LISTENING":
#             print("Server:-- Listening...")
#         elif state == "CONNECTED":
#             isConnected = True
#             print("Server:-- Connected to " + msg)
#         elif state == "MESSAGE":
#             print("Server:-- Message received: ", msg)
        