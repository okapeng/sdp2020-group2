
#!/usr/bin/python3

import socket
 
s = socket.socket()         
host = socket.gethostname() 
port = 4445
s.bind((host, port))        
print(socket.gethostname())
print(port)
 
s.listen(2)
while True:    
    conn, addr = s.accept()
    print("connected with ", addr)          
    

    while True:
        data = conn.recv(1024)
        if data != "":
            print data 
        if not data:
            conn.close()
            break
s.close()
