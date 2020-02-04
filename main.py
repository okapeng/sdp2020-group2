#!/usr/bin/python3
from multiprocessing import Process
import socket
import threading
# import move 
import queue

DEFAULT_HOST = socket.gethostname() 
DEFAULT_RECV_PORT = 4445  
DEFAULT_SPEED = 700
DEFAULT_TIME = 100
DISCONNECTED = "disconnected"

class TcpRecv(threading.Thread):
    def __init__(self, t_name, socket, commands):
        threading.Thread.__init__(self,name=t_name)
        self.socket = socket
        self.commands = commands
        self.disconnected = False
        print("init Tcp recieve")

    def run(self):
        print(socket.gethostname())
        
        self.socket.listen(2)  
        while not self.disconnected:
            conn, addr = self.socket.accept()
            with conn:
                print('Connected by', addr)
                datas = []
                while True:
                    data = conn.recv(1)
                    if not data:
                        break 
                    datas.append(str(data.decode()))
                command = ''.join(datas).strip()
                self.disconnected = (command == DISCONNECTED)
                self.commands.put(command)
        
    

class BaseCtrl(threading.Thread):
    def __init__(self,t_name,queue):
        threading.Thread.__init__(self,name=t_name)
        self.commands = queue
        print("init base controller")

    def run(self):
        while True:
            command = self.commands.get(1)
            print("execute command: ", command)
            # if command == "forward":
            #     move.forward(DEFAULT_SPEED, DEFAULT_TIME)
            # if command == "back":
            #     move.back(DEFAULT_SPEED, DEFAULT_TIME)
            # if command == "left":
            #     move.left(DEFAULT_SPEED, DEFAULT_TIME)
            # if command == "right":
            #     move.right(DEFAULT_SPEED, DEFAULT_TIME)
            if command == DISCONNECTED:
                break
        print("stop base control")
            

if __name__ == '__main__':
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((DEFAULT_HOST, DEFAULT_RECV_PORT)) 
        commands = queue.Queue()
        baseCtrl = BaseCtrl('BaseController.', commands)
        baseCtrl.start()

        tcp = TcpRecv('TcpConnection', s, commands)
        tcp.start()
        tcp.join()
        baseCtrl.join()
        print('All terminate!')
