import ev3dev.ev3 as ev3
#NOTE: NEEDS SOME TESTING AND EDITTING
fr = ev3.LargeMotor('outA');
fl = ev3.LargeMotor('outB');
br = ev3.LargeMotor('outC');
bl = ev3.LargeMotor('outD');
def forward(speed, time):
    fr.run_timed(speed_sp = speed, time_sp = time);
    fl.run_timed(speed_sp = speed, time_sp = time);
    br.run_timed(speed_sp = -speed, time_sp = time);    
    bl.run_timed(speed_sp = -speed, time_sp = time);
    return
def back(speed, time):
    forward(-speed,time)
    return
def right(speed, time):
    fr.run_timed(speed_sp = -speed, time_sp = time);
    fl.run_timed(speed_sp = speed, time_sp = time);
    br.run_timed(speed_sp = speed, time_sp = time);
    bl.run_timed(speed_sp = -speed, time_sp = time);
    return
def left(speed, time):
    right(-speed,time)
    return
def fright(speed, time):
    fr.run_timed(speed_sp = 0, time_sp = time);
    fl.run_timed(speed_sp = speed, time_sp = time);
    br.run_timed(speed_sp = speed, time_sp = time);
    bl.run_timed(speed_sp = 0, time_sp = time);
    return
def fleft(speed, time):
    fr.run_timed(speed_sp = speed, time_sp = time);
    fl.run_timed(speed_sp = 0, time_sp = time);
    br.run_timed(speed_sp = 0, time_sp = time);
    bl.run_timed(speed_sp = speed, time_sp = time);
    return
def bright(speed, time):
    fleft(-speed,time)
    return
def bleft(speed, time):
    fright(-speed,time)
    return
def rotr(speed,time):
    fr.run_timed(speed_sp = -speed, time_sp = time);
    fl.run_timed(speed_sp = speed, time_sp = time);
    br.run_timed(speed_sp = -speed, time_sp = time);
    bl.run_timed(speed_sp = speed, time_sp = time);
    return
def rotl(speed,time):
    rotr(-speed,time)
    return

def main():
    forward(500,1000);
    right(500,1000);
    forward(-500,1000);
    right(-500,1000);

main()

