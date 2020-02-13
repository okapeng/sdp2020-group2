#/usr/bin/python3
from multiprocessing import Process
import socket
import threading
from follow import *
import queue

DEFAULT_HOST = ""
DEFAULT_RECV_PORT = 1811
DEFAULT_SPEED = 500
DEFAULT_TIME = 200
DISCONNECTED = "disconnected"

class TcpRecv(threading.Thread):
    def __init__(self, t_name, socket, commands):
        threading.Thread.__init__(self,name=t_name)
        self.socket = socket
        self.commands = commands
        print("init TcpRecieve")

    def run(self):
        self.socket.listen(10)
        while True:
            conn, addr = self.socket.accept()
            with conn:
                print('Connected by', addr)
                command = ""
                while True:
                    data = conn.recv(1024)
                    if not data:
                        break 
                    command = str(data.decode()).strip()
                    conn.sendall(('Command recv... '+command).encode())
                    self.commands.put(command)
                    if (command == DISCONNECTED):
                        break

class BaseCtrl(threading.Thread):
    def __init__(self,t_name,queue):
        threading.Thread.__init__(self,name=t_name)
        self.commands = queue
        print("init base controller")

    def run(self):
        while True:
            command = self.commands.get(1)
            print("execute command:", command)
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
            if command == "follow":
                follow(1)
            if command == "stop following":
                follow(0)
            if command == DISCONNECTED:
                stop()
                commands = ""
                break
        print("Base control stop")

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

