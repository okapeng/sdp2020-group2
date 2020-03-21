#!/usr/bin/python2
import serial
import time
ser = serial.Serial("/dev/ttyUSB",9600)
ser.open()
while True:
    # data = ser.read(1024)
    # print(data)
    time.sleep(2)
    ser.write("pc msg recv: "+data)
ser.close()