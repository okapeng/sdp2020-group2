from move import *
#Iteration 1 of follow me
ir = ev3.InfraredSensor('in3')#IR in port 3
bs = ev3.BeaconSeeker(sensor = ir,channel = 1)
us = ev3.UltrasonicSensor('in4')#US in port 4
us.mode = 'US-DIST-CM'
#rs = ev3.RemoteControl(sensor = ir, channel = 1)
while(True):
    print('ir = '+str(bs.distance)+', us = '+str(us.value()))
    if(us.value()<500):
        stop()
        ev3.Sound.beep(args = '-l 500')
    #if(rs.red_down or rs.red_up or rs.blue_up or rs.blue_down):
        #stop()
        #break
    if(bs.distance>30):
        forward(500,1000)
    else:
        continue



