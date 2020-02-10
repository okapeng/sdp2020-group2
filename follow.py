from BaseController import *

usth = 200 #US threshold
ir = ev3.InfraredSensor('in2')
bs = ev3.BeaconSeeker(sensor = ir,channel = 1)
usm = ev3.UltrasonicSensor('in3') #Middle US 
usm.mode = 'US-DIST-CM'
usr = ev3.UltrasonicSensor('in1') #Right US
usr.mode = 'US-DIST-CM'
usl = ev3.UltrasonicSensor('in4') #Left US
usl.mode = 'US-DIST-CM'
#rs = ev3.RemoteControl(sensor = ir, channel = 1)

def follow():
    while(True):
        print('beacon distance='+str(bs.distance)+',bearing= '+str(bs.heading)+', us = '+str(usm.value()), end = ' ')
        if(bs.distance<0):
            stop()
            print('BEACON NOT FOUND')
        elif(bs.distance == 100):
            stop()
            print('OUT OF RANGE')
        elif(usm.value()<usth or usr.value()<usth or usl.value()<usth):
            print('OBJECT DETECTED')
            object()
        elif(bs.distance>30 and abs(bs.heading)<4):
            forward(500,100)
            print('FOLLOWING')
        elif( bs.heading>4):
            rotl(500,100)
            print('TURNING')
        elif(bs.heading<-4):
            rotr(500,100)
            print('TURNING')
        else:
            continue

def object():
    if(usm.value()<usth and usr.value()<usth and usl.value()<usth):
        rotr(500,100)
    elif(usm.value()<usth and usr.value()<usth):
        left(500,100)
    elif(usm.value()<usth and usl.value()<usth):
        right(500,100)
    elif(usr.value()<usth and usl.value()<usth):
        rotr(500,100)
    elif(usm.value()<usth):
        rotl(500,100)
    elif(usr.value()<usth):
        left(500,100)
    elif(usl.value()<usth):
        right(500,100)
    else:
        return
