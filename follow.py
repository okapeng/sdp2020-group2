from BaseController import *

usth = 300 #US threshold
ir = ev3.InfraredSensor('in4') #IR port 2
bs = ev3.BeaconSeeker(sensor = ir,channel = 1)
usm = ev3.UltrasonicSensor('in3') #Middle US port 3
usm.mode = 'US-DIST-CM'
#usr = ev3.UltrasonicSensor('in1') #Right US port 1
#usr.mode = 'US-DIST-CM'
#usl = ev3.UltrasonicSensor('in4') #Left US port 4
#usl.mode = 'US-DIST-CM'

def follow(flag):
    #print('beacon distance='+str(bs.distance)+',bearing= '+str(bs.heading) +', us = '+str(usm.value()), end = ' ')
    if(flag):
        if(bs.distance<0):
            stop()
            #print('BEACON NOT FOUND')
        elif(bs.distance == 100):
            stop()
            print('OUT OF RANGE')
        elif(usm.value()<usth): # or usr.value()<usth or usl.value()<usth):
            print('OBJECT DETECTED')
    #    object()
            stop()
        elif(bs.distance>30 and abs(bs.heading)<4):
            forward(500,100)
            #print('FOLLOWING')
        elif( bs.heading>2):
            rotl(500,100)
            #print('TURNING')
        elif(bs.heading<-2):
            rotr(500,100)
            #print('TURNING')
        else:
            print()
    else:
        stop()
        return

