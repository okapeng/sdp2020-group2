#! /usr/bin/env python3
import serial
import time

import EV3BT

EV3 = serial.Serial('/dev/rfcomm0')
print("Listening for EV3 Bluetooth messages, press CTRL C to quit.")
try:
 while 1:
 n = EV3.inWaiting()
 if n != 0:
  s = EV3.read(n)
  base, lift = EV3BT.decodeMessage(s, EV3BT.MessageType.Text)
  print(base, lift) 
 else:
  # No data is ready to be processed
  time.sleep(0.1)
except KeyboardInterrupt:
 pass
EV3.close()