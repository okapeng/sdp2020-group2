#/usr/bin/python3
from multiprocessing import Process
import socket
import threading
import move 
import queue

DEFAULT_HOST = ""
DEFAULT_RECV_PORT = 4445  
DEFAULT_SPEED = 700
DEFAULT_TIME = 100
DISCONNECTED = "disconnected"

class TcpRecv(threading.Thread):
    def __init__(self, t_name, socket, commands):
        threading.Thread.__init__(self,name=t_name)
        self.socket = socket
        self.commands = commands
        # self.disconnected = False
        print("init TcpRecieve")

    def run(self):        
        self.socket.listen(10)  
        # while not self.disconnected:
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
                print(command)
                
                
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
                move.forward(DEFAULT_SPEED, DEFAULT_TIME)
            if command == "back":
                move.back(DEFAULT_SPEED, DEFAULT_TIME)
            if command == "left":
                move.left(DEFAULT_SPEED, DEFAULT_TIME)
            if command == "right":
                move.right(DEFAULT_SPEED, DEFAULT_TIME)
            if command == DISCONNECTED:
                break
        print("stop base control")

# class BaseController:
#     fr = ev3.LargeMotor('outA');#Front Right wheel to port A
#     fl = ev3.LargeMotor('outB');#Front Left wheel to port B
#     br = ev3.LargeMotor('outC');#Back Right wheel to port C
#     bl = ev3.LargeMotor('outD');#Back Left wheel to port D
#     fr.stop_action = 'hold'
#     fl.stop_action = 'hold'
#     br.stop_action = 'hold'
#     bl.stop_action = 'hold'

#     def forward(speed, time):
#         #Move robot forward at speed(tacho counts per second) for time(milliseconds)
#         fr.run_timed(speed_sp = speed, time_sp = time);
#         fl.run_timed(speed_sp = speed, time_sp = time);
#         br.run_timed(speed_sp = -speed, time_sp = time);
#         bl.run_timed(speed_sp = -speed, time_sp = time);
#         return

#     def back(speed, time):
#         #Move robot back at speed(tacho counts per second) for time(milliseconds)
#         forward(-speed,time)
#         return

#     def right(speed, time):
#         #Move robot right at speed(tacho counts per second) for time(milliseconds)
#         fr.run_timed(speed_sp = -speed, time_sp = time);
#         fl.run_timed(speed_sp = speed, time_sp = time);
#         br.run_timed(speed_sp = -speed, time_sp = time);
#         bl.run_timed(speed_sp = speed, time_sp = time);
#         return

#     def left(speed, time):
#         #Move robot left at speed(tacho counts per second) for time(milliseconds)
#         right(-speed,time)
#         return

#     def rotr(speed,time):
#         #Rotate robot clockwise(right) at speed(tacho counts per second) for time(milliseconds)
#         fr.run_timed(speed_sp = -speed, time_sp = time);
#         fl.run_timed(speed_sp = speed, time_sp = time);
#         br.run_timed(speed_sp = speed, time_sp = time);
#         bl.run_timed(speed_sp = -speed, time_sp = time);
#         return

#     def rotl(speed,time):
#         #Rotate robot anticlockwise(left) at speed(tacho counts per second) for time(milliseconds)
#         rotr(-speed,time)
#         return

#     def fright(speed,time):
#         #Move diagonally front right
#         fr.run_timed(speed_sp = 0, time_sp = time);
#         fl.run_timed(speed_sp = speed, time_sp = time);
#         br.run_timed(speed_sp = -speed, time_sp = time);
#         bl.run_timed(speed_sp = 0, time_sp = time);
#         return

#     def bleft(speed,time):
#         #Move diagonally back left
#         fright(-speed,time)
#         return

#     def fleft(speed,time):
#         #Move diagonally front left
#         fr.run_timed(speed_sp = speed, time_sp = time);
#         fl.run_timed(speed_sp = 0, time_sp = time);
#         br.run_timed(speed_sp = 0, time_sp = time);
#         bl.run_timed(speed_sp = -speed, time_sp = time);
#         return

#     def bright(speed,time):
#         fleft(-speed,time)
#         return
                

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
