from motors import Motors
from time import time, sleep

#forward function
#duration in seconds, 100 is reasonable start speed
#fr = 3
#fl = 2
#br = 5
#bl = 4
class OurPiMove:
    def __init__(self):
        #setup
        self.mc = Motors()
        #start value to differenciate between continuous movement (not -1) and
        # starting form a stationary position (-1)
        self.progress = -1
        #motor ids eg.: fr = front-right
        self.fr = 3
        self.fl = 2
        self.br = 5
        self.bl = 4
        #encoder sampling rate
        self.sample_rate = 0.2 # Use a sample_rate of at least 0.1, toavoid errors

        # info on how to set up the movement of motors, (a multiplier on speed)
        # direction : pos/neg speed, fr, fl, br, bl
        self.directions = {
        "forward" : [ 1, 1, 1,-1,-1],
        "back"    : [-1,-1,-1, 1, 1],
        "rotr"    : [ 1,-1, 1, 1,-1],
        "rotl"    : [-1, 1,-1,-1, 1],
        "right"   : [ 1,-1, 1,-1, 1],
        "left"    : [-1, 1,-1, 1,-1]
        #"fright"  : [ 1, 0, 1,-1, 0],
        #"bleft"   : [-1, 0,-1, 1, 0],
        #"fleft"   : [ 1, 1, 0, 0,-1],
        #"bright"  : [ 1,-1, 0, 0, 1]
        }

    def move(self, direction, speed, distance):
        '''
        Move robot in the given DIRECTION for a set DISTANCE at a given SPEED (usually 100%)
        '''
        self.mySetMotors(direction, speed)

        #normal operation
        try:
            self.chooseStartKind()

            #main run funtion
            self.runForGivenDistance(distance)

        #catches an IO error from the encoder board and tries to finish the program
        except Exception as er:
            print er
            print "Error, retry 1"
            try:
                self.mySetMotors(direction, speed)

                #main run funtion
                self.runForGivenDistance(distance)
            #we dont expect 2 IO errors in one run so we exit here
            except Exception as er:
                print er
                print "IO error 2, something is probably wrong"



    def stop(self):
        #stops all motors
        self.mc.stop_motors()
        sleep(0.75)
        self.progress = -1

    def mySetMotors(self,direction,speed):
        '''
        function to set motors in one line, saves space and repetition
        '''
        # the encoder will start from 0 (1) or 255 (-1)
        self.encoder_dir, front_right, front_left, back_right, back_left = self.directions[direction]

        self.mc.move_motor(self.fr,front_right * speed)
        self.mc.move_motor(self.fl,front_left * speed)
        self.mc.move_motor(self.br,back_right * speed)
        self.mc.move_motor(self.bl,back_left * speed)

    def chooseStartKind(self):
        if self.progress != -1:
            sleep(0.45)
        print "accumulated encoder data:"
        self.mc.print_encoder_data()
        print "starting actual count:"
        self.progress = 0

    def runForGivenDistance(self, distance):
        '''
        timer funtion that prints the encoder data and manages how long the
        motors should run
        '''
        extra = 0
        if self.encoder_dir < 0:
            extra = 255
        #sets extra to 0 or 255 depending on which way the encoder will count
        while self.progress < distance:
            sleep(self.sample_rate)
            tick = abs(self.mc.read_encoder(self.fr)-extra)
            self.progress = self.progress + tick
            print  self.progress
            if self.close_enough(tick, distance):
                break

    def close_enough(self, last_tick, goal):
        '''
        False: dont need to stop motors yet (not close enough to goal distance)
        True: stop motors to get as close to goal distance as possible
        (delays return True by a percentage of the sample rate)
        '''
        diff = goal-self.progress
        if  diff < last_tick:
            waittime = self.sample_rate * (diff/last_tick)
            sleep(waittime)
            return True
        else:
            return False






mypi = OurPiMove()


mypi.move('forward', 100, 400)
mypi.stop()
mypi.move('back', 100, 400)
mypi.stop()
mypi.move('rotr', 100, 550)
mypi.stop()
mypi.move('rotl', 100, 550)
mypi.stop()
mypi.move('left', 100, 400)
mypi.stop()
mypi.move('right', 100, 400)
mypi.stop()
#mypi.move('fright', 100, 400)
#mypi.stop()
#mypi.move('bleft', 100, 400)
#mypi.stop()
#mypi.move('fleft', 100, 400)
#mypi.stop()
#mypi.move('bright', 100, 400)
#mypi.stop()
