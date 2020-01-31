import ev3dev.ev3 as ev3


m1 = ev3.LargeMotor('outB')#Motor 1 on port B
m2 = ev3.LargeMotor('outC')#Motor 2 on port C

def up(speed, time):
    m1.run_timed(speed_sp = speed, time_sp = time)
    m2.run_timed(speed_sp = speed, time_sp = time) 
    return

def down(speed,time):
    up(speed,time)
    return
