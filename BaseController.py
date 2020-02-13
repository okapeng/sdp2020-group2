import ev3dev.ev3 as ev3
#NEEDS TESTING WITH CURRENT BASE ROBOT

fr = ev3.LargeMotor('outA');#Front Right wheel to port A
fl = ev3.LargeMotor('outB');#Front Left wheel to port B
br = ev3.LargeMotor('outC');#Back Right wheel to port C
bl = ev3.LargeMotor('outD');#Back Left wheel to port D
fr.stop_action = 'coast' #'hold'
fl.stop_action = 'coast' #'hold'
br.stop_action = 'coast' #'hold'
bl.stop_action = 'coast' #'hold'

def back(speed, time):
    #Move robot forward at speed(tacho counts per second) for time(milliseconds)
    fr.run_timed(speed_sp = speed, time_sp = time);
    fl.run_timed(speed_sp = speed, time_sp = time);
    br.run_timed(speed_sp = -speed, time_sp = time);
    bl.run_timed(speed_sp = -speed, time_sp = time);
    return

def forward(speed, time):
    #Move robot back at speed(tacho counts per second) for time(milliseconds)
    back(-speed,time)
    return

def left(speed, time):
    #Move robot right at speed(tacho counts per second) for time(milliseconds)
    fr.run_timed(speed_sp = -speed, time_sp = time);
    fl.run_timed(speed_sp = speed, time_sp = time);
    br.run_timed(speed_sp = -speed, time_sp = time);
    bl.run_timed(speed_sp = speed, time_sp = time);
    return


def right(speed, time):
    #Move robot left at speed(tacho counts per second) for time(milliseconds)
    left(-speed,time)
    return

def rotl(speed,time):
    #Rotate robot clockwise(right) at speed(tacho counts per second) for time(milliseconds)
    fr.run_timed(speed_sp = -speed, time_sp = time);
    fl.run_timed(speed_sp = speed, time_sp = time);
    br.run_timed(speed_sp = speed, time_sp = time);
    bl.run_timed(speed_sp = -speed, time_sp = time);
    return

def rotr(speed,time):
    #Rotate robot anticlockwise(left) at speed(tacho counts per second) for time(milliseconds)
    rotl(-speed,time)
    return

def bleft(speed,time):
    #Move diagonally front right
    fr.run_timed(speed_sp = 0, time_sp = time);
    fl.run_timed(speed_sp = speed, time_sp = time);
    br.run_timed(speed_sp = -speed, time_sp = time);
    bl.run_timed(speed_sp = 0, time_sp = time);
    return


def fright(speed,time):
    #Move diagonally back left
    bleft(-speed,time)
    return

def bright(speed,time):
    #Move diagonally front left
    fr.run_timed(speed_sp = speed, time_sp = time);
    fl.run_timed(speed_sp = 0, time_sp = time);
    br.run_timed(speed_sp = 0, time_sp = time);
    bl.run_timed(speed_sp = -speed, time_sp = time);
    return

def fleft(speed,time):
    bright(-speed,time)
    return

def square(speed,time):
    forward(speed,time)
    if(fr.wait_until('holding')):
        left(speed,time)
        if(fr.wait_until('holding')):
            back(speed,time)
            if(fr.wait_until('holding')):
                right(speed,time)


def stop ():
    fr.stop()
    fl.stop()
    br.stop()
    bl.stop()

