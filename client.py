# -*- coding:utf-8 -*-
import socket

ip_port = ('', 4445)

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)   

s.connect(ip_port)     

while True:  
    inp = input("Enter commands: ").strip()
    if not inp:  
        continue
    s.sendall(inp.encode())

    if inp == "disconnect":  
        print("terminate communication")
        break

    server_reply = s.recv(1024).decode()
    print(server_reply)

s.close()  
