#!/usr/bin/env bash
ssh robot@192.168.105.101
python3

#!/usr/bin/python
import ev3dev.ev3 as ev3
from move import *
from lift import *

forward(500,3000)
left(500,3000)
back(500,3000)
right(500,3000)

