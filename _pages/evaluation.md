---
layout: page
title: Evaluation
permalink: /evaluation/
---
### Evaluation Tests: 
#### Base Testing:
  Throughout the development of the N.E.A.T. we carried out numerous base related tests that were designed to uncover the robots weight capacity, battery life, structural integrity and the efficiency comparison of different hardware controllers.
  
  **Lift with Weight Test:**
  Aim: To monitor how load weight affects lift performance in terms of completion time and the integrity of the lift in motion.
  Method: Lift was operated independently, with different masses placed onto the lift, ranging from 0kg to 2.2kg. The time it took the lift to reach 75cm was recorded as well as the time for it to return to base height (0 cm).
  ![Lift Weight Test](/images/lift_test.png)
  Evaluation: The results indicate that the varying weights from 0kg to 2.2kg had little to no effect on the raising and lowering time taken by the lift, which averages at approximately 1 min 55s. This implies that the load on the lift is not an issue but rather the  weight  that  the  wheels  can  handle  is  the  main (weight) constraint of this project. The substitution of bigger wheels would achieve a profound breakthrough in the load weight limit of the robot.  However, the average mass of a meal (1.25kg) can be easily accommodated by the current system and this test was carried out to understand the maximum weight load of the robot.
  
  **Battery Life Test:**
  Aim: To gain a realisitic estimation of the robot's battery life.
  Method: The fully built robot had 1.25kg (the average weight of a standard meal containing plate of food, water and cutlery) placed on top. The batteries of the robot were checked to be 100% charged. The robot carried out continous rotation movements on the spot for 5 minutes. The charge of the batteries were then extrapolated to get an overall battery life estimation.
  Evaluation: The reason for rotation movement was that was found to be more power consuming movement when the robot was bearing weight and is by far the most used movement during the robots general function. The battery estimate was 17 minutes and 21 seconds which is a sufficient battery life for the robot to carry out several short to medium length jobs without the performance depreciating significantly. The user of the robot must keep in mind that reusable battery charge hold depreciates over time as general use takes place and therefore, should take the battery life test results purely as an estimation.
  
  **Raspberry Pi vs EV3 Test:**
  Aim: The current controller of the N.E.A.T. was the standalone EV3 and this test was carried out to ensure that replacing the EV3 with the Raspberry Pi would not impact the robots peformance noticably. The reason for the change-over is due to the limited processing power of the EV3 and the shortage of ports in which motors and sensors can be attached.
  Method: The robot was tested using the EV3 and the Raspberry Pi by completing rotations on the spot under varying weights from 0kg to 2kg. The time for each base to rotate 360 degrees was recorded and repeated 3 times for each implementation and weight.
  ![RPI vs EV3 Test](/images/ev3_rpi_test.png)
  Evaluation: The results that the switch over from the EV3 to the Raspberry Pi did not impact the the robots performance significantly and so the the EV3 was replaced. The robot with the Raspberry Pi is now able to carry out general tasks like the EV3 could but hold heavily increased processing power which is required for the likes of running the object avoidance code.
  
  **Base Development Drop Tests:**
  Throughout the development of the N.E.A.T. base structure, several drop tests were conducted in a safe and controlled environment to prevent harm to those around the robot. These tests were carried out to identify structural weaknesses and ways to increase structural integrity of the robot. These tests were not carried out in an offical manner like the previous tests mentioned above, but were rather done incrementally as the base development iterations went on. The heights dropped from were no higher than 50cm and were always conducted on the lab floor, with advanced warnings given to surrounding groups to make them aware of what was taking place. 

### User Tests: 
  Present any user testing you have performed for your product. 
  The same advice about presenting data should be used as above

### Main Areas of Improvement: 
  The tests conducted on the robot base found several areas of improvement with the base drop tests uncovering structural problems that required redesigning and changing the base to answer these problems. The Rasbperry Pi vs EV3 test uncovered that switching from the EV3 to the Rasbperry Pi wouldnt comprimise the robots performance levels and gave the green light for implementing the Rasbperry Pi with its significantly increased processing capabilities for the likes of object avoidance implementation.
