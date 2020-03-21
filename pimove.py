from motors import Motors
from time import time, sleep

#forward function
#duration in seconds, 100 is reasonable start speed
fr = 3
fl = 2
br = 5
bl = 4
class OurPiMove:
    def __init__(self):
        self.mc = Motors()
        self.progress = -1
    def forward(self,speed,distance):
        #Move robot forward at speed
        self.mc.move_motor(fr,speed)
        self.mc.move_motor(fl,speed)
        self.mc.move_motor(br,-speed)
        self.mc.move_motor(bl,-speed)
        sample_rate = 0.2
        try:
            if self.progress != -1:
                sleep(0.45)
            print "accumulated encoder data:"
            self.mc.print_encoder_data()
            print "starting actual count:"
            self.progress = 0
            if speed > 0:
                while self.progress < distance:
                    sleep(sample_rate)
                    tick = self.mc.read_encoder(fr)
                    self.progress = self.progress + tick
                    print  self.progress
                    if self.close_enough(tick, distance, sample_rate):
                        break
                    # Use a sleep of at least 0.1, toavoid errors
            else:
                while self.progress < distance:
                    sleep(sample_rate)
                    tick = 255 - self.mc.read_encoder(fr)
                    self.progress = self.progress + tick
                    print  self.progress
                    if self.close_enough(tick, distance, sample_rate):
                        break
                    # Use a sleep of at least 0.1, toavoid errors
        except:
            print "IO Error"

    def back(self,speed,distance):
        #Move robot back at speed for distance(seconds)
        self.forward(-speed,distance)

    def right(self,speed,duration):
        #Move robot right at speed for duration(seconds)
        speed = speed*1.5
        self.mc.move_motor(fr,speed)
        self.mc.move_motor(fl,-speed)
        self.mc.move_motor(br,-speed)
        self.mc.move_motor(bl,-speed)
        start_time = time()
        try:
            while time() < start_time + duration:
                #self.mc.print_encoder_data()
                sleep(0.2)
                    # Use a sleep of at least 0.1, toavoid errors
        except:
            print "IO Error"

    def left(self,speed,duration):
        #Move robot left at speed for duration(seconds)
        self.right(-speed,duration)

    def rotr(self,speed,distance):
        #Rotate the robot right at speed for duration(seconds)
        self.mc.move_motor(fr,-speed)
        self.mc.move_motor(fl,speed)
        self.mc.move_motor(br,speed)
        self.mc.move_motor(bl,-speed)
        sample_rate = 0.2
        try:
            if self.progress != -1:
                sleep(0.45)
            print "accumulated encoder data:"
            self.mc.print_encoder_data()
            print "starting actual count:"
            self.progress = 0
            extra = 0
            if speed < 0:
                extra = 255
            while self.progress < distance:
                sleep(sample_rate)
                tick = abs(self.mc.read_encoder(fr)-extra)
                print tick
                self.progress = self.progress + tick
                print  self.progress
                if self.close_enough(tick, distance, sample_rate):
                    break
                # Use a sleep of at least 0.1, toavoid errors



                #while s < distance:
            #        sleep(sample_rate)
                #    tick = self.mc.read_encoder(fr)
                #    s = s + tick
                #    print  s
            #        if self.close_enough(s, tick, distance, sample_rate):
                        #break
                    # Use a sleep of at least 0.1, toavoid errors
            #else:
            #    while s < distance:
            #        sleep(sample_rate)
            #        tick = 255 - self.mc.read_encoder(fr)
            #        s = s + tick
            #        print  s
            #        if self.close_enough(s, tick, distance, sample_rate):
            #            break
                    # Use a sleep of at least 0.1, toavoid errors
        except:
            print "IO Error"

    def rotl(self, speed,distance):
        #Rotate the robot left at speed for duration(seconds)
        self.rotr(-speed,distance)

    def fright(self, speed,duration):
        #Move the robot diagonal forward-right at speed for duration(seconds)
        self.mc.move_motor(fr,0)
        self.mc.move_motor(fl,speed)
        self.mc.move_motor(br,-speed)
        self.mc.move_motor(bl,0)
        start_time = time()
        try:
            while time() < start_time + duration:
                self.mc.print_encoder_data()
                sleep(0.2)
                # Use a sleep of at least 0.1, toavoid errors
        except:
            print "IO Error"

    def bleft(self,speed,duration):
        #Move the robot diagonal backwards-left at speed for duration(seconds)
        fright(-speed,duration)


    def fleft(self,speed,duration):
        #Move the robot diagonal forward-left at speed for duration(seconds)
        self.mc.move_motor(fr,speed)
        self.mc.move_motor(fl,0)
        self.mc.move_motor(br,0)
        self.mc.move_motor(bl,-speed)
        start_time = time()
        try:
            while time() < start_time + duration:
                self.mc.print_encoder_data()
                sleep(0.2)
                # Use a sleep of at least 0.1, toavoid errors
        except:
            print "IO Error"

    def bright(self,speed,duration):
        #Move the robot diagonal backwards-right at speed for duration(seconds)
        fleft(-speed,duration)

    def stop(self):
        #stops all motors
        self.mc.stop_motors()
        self.progress = -1

    def square(self, speed,distance):
        rot_dur = 530
        self.forward(speed,distance)
        self.stop()
        sleep(1)
        self.rotl(speed,rot_dur)
        self.stop()
        sleep(1)
        self.forward(speed,distance)
        self.stop()
        sleep(1)
        self.rotl(speed,rot_dur)
        self.stop()
        sleep(1)
        self.forward(speed,distance)
        self.stop()
        sleep(1)
        self.rotl(speed,rot_dur)
        self.stop()
        sleep(1)
        self.forward(speed,distance)
        self.stop()
        sleep(1)
        self.rotl(speed,rot_dur)
        self.stop()

    def close_enough(self, last_tick, goal, interval):
        #checks if the last sum value is closer to the goal than with the next expected tick
        diff = goal-self.progress
        if  diff < last_tick:
            waittime = interval * (diff/last_tick)
            sleep(waittime)
            return True
        else:
            return False


#round 3
mypi = OurPiMove()
#mypi.rotl(100,2)
#mypi.forward(100,400)
#mypi.back(100,400)
#mypi.rotl(100,400)
#mypi.rotr(100,400)
mypi.square(100,400)
mypi.stop()
