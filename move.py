import ev3dev.ev3 as ev3
#NEEDS TESTING WITH CURRENT BASE ROBOT

fr = ev3.LargeMotor('outA');#Front Right wheel to port A
fl = ev3.LargeMotor('outB');#Front Left wheel to port B
br = ev3.LargeMotor('outC');#Back Right wheel to port C
bl = ev3.LargeMotor('outD');#Back Left wheel to port D

def forward(speed, time):
    #Move robot forward at speed(tacho counts per second) for time(milliseconds)
    fr.run_timed(speed_sp = speed, time_sp = time);
    fl.run_timed(speed_sp = speed, time_sp = time);
    br.run_timed(speed_sp = -speed, time_sp = time);    
    bl.run_timed(speed_sp = -speed, time_sp = time);
    return

def back(speed, time):
    #Move robot back at speed(tacho counts per second) for time(milliseconds)
    forward(-speed,time)
    return

def right(speed, time):
    #Move robot right at speed(tacho counts per second) for time(milliseconds)
    fr.run_timed(speed_sp = -speed, time_sp = time);
    fl.run_timed(speed_sp = speed, time_sp = time);
    br.run_timed(speed_sp = -speed, time_sp = time);
    bl.run_timed(speed_sp = speed, time_sp = time);
    return

def left(speed, time):
    #Move robot left at speed(tacho counts per second) for time(milliseconds)
    right(-speed,time)
    return

def rotr(speed,time):
    #Rotate robot clockwise(right) at speed(tacho counts per second) for time(milliseconds)
    fr.run_timed(speed_sp = -speed, time_sp = time);
    fl.run_timed(speed_sp = speed, time_sp = time);
    br.run_timed(speed_sp = speed, time_sp = time);
    bl.run_timed(speed_sp = -speed, time_sp = time);
    return

def rotl(speed,time):
    #Rotate robot anticlockwise(left) at speed(tacho counts per second) for time(milliseconds)
    rotr(-speed,time)
    return


