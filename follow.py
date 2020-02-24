

from BaseController import *

usth = 300 #US threshold
ir = ev3.InfraredSensor('in4') #IR port 4
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
            print('BEACON NOT FOUND')
        elif(bs.distance == 100):
            stop()
            print('OUT OF RANGE')
        elif(usm.value()<usth): # or usr.value()<usth or usl.value()<usth):
            print('OBJECT DETECTED')
    #    object()
            stop()
        elif(bs.distance>30 and abs(bs.heading)<4):
            forward(750,100)
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


#def object():
#    if(usm.value()<usth and usr.value()<usth and usl.value()<usth):
#        rotr(500,100)
#        print('all three')
#        return
#    elif(usm.value()<usth and usr.value()<usth):
#        left(500,100)
#        print('middle, right')
#        return
#    elif(usm.value()<usth and usl.value()<usth):
#        right(500,100)
#        print('middle, left')
#        return
#    elif(usr.value()<usth and usl.value()<usth):
#        rotr(500,100)
#        print('right, left')
#        return
#    elif(usm.value()<usth):
#        rotl(500,100)
#        print('middle')
#        return
#    elif(usr.value()<usth):
#        left(500,100)
#        print('right')
#        return
#    elif(usl.value()<usth):
#        right(500,100)
#        print('left')
#        return

