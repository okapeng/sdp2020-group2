#!/usr/bin/env python3

'''
Example usage of the TCPClient class from the TCPCOM library
'''

from tcpcom import TCPClient
from time import sleep
import threading
from ev3dev2.sensor.lego import UltrasonicSensor, InfraredSensor
from time import sleep

server_ip = "192.168.0.2"
server_port = 4444


def onStateChanged(state, msg):
    global isConnected

    if state == "LISTENING":
        print("DEBUG: Client:-- Listening...")

    elif state == "CONNECTED":
        isConnected = True
        print("DEBUG: Client:-- Connected to ", msg)

    elif state == "DISCONNECTED":
        isConnected = False
        print("DEBUG: Client:-- Connection lost.")
        main()

    elif state == "MESSAGE":
        print("DEBUG: Client:-- Message received: ", msg)


def main():
    global client, ir, us
    client = TCPClient(server_ip, server_port, stateChanged=onStateChanged, isVerbose=True)
    print("Client starting")
    us_left = UltrasonicSensor('in1')
    us_mid = UltrasonicSensor('in2')
    us_right = UltrasonicSensor('in3')
    ir = ev3.InfraredSensor('in4')
    bs = ev3.BeaconSeeker(sensor = ir,channel = 1)
    us_left.mode = 'US-DIST-CM'
    us_mid.mode = 'US-DIST-CM'
    us_right.mode = 'US-DIST-CM'
    try:
        while True:
            rc = client.connect()
            sleep(0.01)
            if rc:
                while True:
                    client.sendMessage('left:{}'.format(us_left.distance_centimeters_ping))
                    client.sendMessage('middle:{}'.format(us_mid.distance_centimeters_ping))
                    client.sendMessage('right:{}'.format(us_right.distance_centimeters_ping))
                    client.sendMessage('bc_dist:{}'.format(bs.distance))
                    client.sendMessage('bc_heading:{}'.format(bs.heading))
                    sleep(0.05)
            else:
                print("Client:-- Connection failed")
                sleep(0.1)
    except KeyboardInterrupt:
        pass

    # missin done; close connection
    client.disconnect()
    threading.cleanup_stop_thread()  # needed if we want to restart the client


if __name__ == '__main__':
    main()
