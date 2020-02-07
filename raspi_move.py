from motors import Motors
from time import time, sleep

#forward function
#duration in seconds, 100 is reasonable start speed
fr = 0
fl = 1
br = 2
bl = 3
mc = Motors()

def forward(speed,duration):
	#Move robot forward at speed
        mc.move_motor(fr,speed)
        mc.move_motor(fl,speed)
        mc.move_motor(br,speed)
        mc.move_motor(bl,speed)
        start_time = time()
        try:
                while time() < start_time + duration:
                        mc.print_encoder_data()
                        sleep(0.2)
                        # Use a sleep of at least 0.1, toavoid errors
        except:
                print "IO Error"
        mc.stop_motors()

def back(speed,duration):
        #Move robot back at speed for duration(seconds)
	forward(-speed,duration)

def right(speed,duration):\
	#Move robot right at speed for duration(seconds)
        mc.move_motor(fr,-speed)
        mc.move_motor(fl,speed)
        mc.move_motor(br,-speed)
        mc.move_motor(bl,speed)
        start_time = time()
        try:
                while time() < start_time + duration:
                        mc.print_encoder_data()
                        sleep(0.2)
                        # Use a sleep of at least 0.1, toavoid errors
        except:
                print "IO Error"
        mc.stop_motors()

def left(speed,duration):
	#Move robot left at speed for duration(seconds)
        right(-speed,duration)

def rotr(speed,duration):
	#Rotate the robot right at speed for duration(seconds)
        mc.move_motor(fr,-speed)
        mc.move_motor(fl,speed)
        mc.move_motor(br,speed)
        mc.move_motor(bl,-speed)
        start_time = time()
        try:
                while time() < start_time + duration:
                        mc.print_encoder_data()
                        sleep(0.2)
                        # Use a sleep of at least 0.1, toavoid errors
        except:
                print "IO Error"
        mc.stop_motors()

def rotl(speed,duration):
	#Rotate the robot left at speed for duration(seconds)
	rotr(-speed,duration)

def fright(speed,duration):
	#Move the robot diagonal forward-right at speed for duration(seconds)
        mc.move_motor(fr,0)
        mc.move_motor(fl,speed)
        mc.move_motor(br,-speed)
        mc.move_motor(bl,0)
        start_time = time()
        try:
                while time() < start_time + duration:
                        mc.print_encoder_data()
                        sleep(0.2)
                        # Use a sleep of at least 0.1, toavoid errors
        except:
                print "IO Error"
        mc.stop_motors()

def bleft(speed,duration):
	#Move the robot diagonal backwards-left at speed for duration(seconds)
        fright(-speed,duration)


def fleft(speed,duration):
	#Move the robot diagonal forward-left at speed for duration(seconds)
        mc.move_motor(fr,speed)
        mc.move_motor(fl,0)
        mc.move_motor(br,0)
        mc.move_motor(bl,-speed)
        start_time = time()
        try:
                while time() < start_time + duration:
                        mc.print_encoder_data()
                        sleep(0.2)
                        # Use a sleep of at least 0.1, toavoid errors
        except:
                print "IO Error"
        mc.stop_motors()

def bright(speed,duration):
	#Move the robot diagonal backwards-right at speed for duration(seconds)
        fleft(-speed,duration)

def stop():
	#stops all motors
	mc.stop_motors()

def square(speed,duration):
	forward(100,4)
	left(100,4)
	back(100,4)
	right(100,4)

#round 3
sleep(5)
mc = Motors()
forward(100,3)
sleep(5)
mc.stop_motors()
