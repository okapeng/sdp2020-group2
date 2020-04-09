---
layout: page
title: Evaluation
permalink: /evaluation/
---
<style>.tab { text-indent: 40px; }</style>
<center><h2>Testing Summary</h2></center>
  Throughout the development process of N.E.A.T. we carried out numerous base related tests that were designed to uncover the robots weight capacity, battery life, structural integrity and the efficiency comparison of different hardware controllers.
  
> ##### 1. How load weight affects lift performance?

  : **Aim**: Since the lift is expected to carry out its function routinely, it is important to monitor how load weight affects lift performance in terms of completion time and the integrity of the lift in motion.
  
  **Method**: The lift was operated independently, with different masses placed onto the lift, ranging from 0kg to 2.2kg. The time it took the lift to reach 75cm was recorded as well as the time for it to return to base height (0 cm).
  ![Lift Weight Test](/images/lift_test.png)
  **Evaluation**: The results indicate that the varying weights from 0kg to 2.2kg had little to no effect on the raising and lowering time taken by the lift, which averages at approximately 1 min 55s. The average mass of a meal (1.25kg) can be easily accommodated by the current system.
  
> ##### 2. Battery Life Test:  Result: ~17,5 minutes
  
  **Aim**: To gain a realisitic estimation of the robot's battery life.
  
  **Method**: The fully built robot had a load of 1.25kg (to represent a standard meal containing plate of food, water and cutlery) placed on top. The batteries were fully charged before the test was carried out. The robot was then made to complete continuous cycles of a 2 metre square formation for 5 minutes. The charge left on the batteries were then extrapolated to get an estimate for the battery life.
  
  **Evaluation**: The rotation movement was chosen as it is the most power consuming movement when the robot is bearing weight and it is also by far the most used motion during the robot's general function. The battery life estimate was **17 minutes and 21 seconds** which is sufficient battery life for the robot to carry out several short to medium length jobs without the performance depreciating significantly. 
  However, the user of the robot must bear in mind that reusable battery charge hold depreciates with time as general use takes place. Therefore, they should take this battery life purely as an estimation and recharge the batteries long before the usage time approaches the battery life estimate. 
  
> ##### 3. Raspberry Pi vs EV3 Performance Test:
  
  **Aim**: The original controller of N.E.A.T. was the standalone EV3 and this test was carried out to ensure that replacing the EV3 with the Raspberry Pi would not impact the robot's peformance detrimentally. The reason for the change-over was due to the limited processing power of the EV3 and the shortage of ports in which motors and sensors could be attached.
  
  **Method**: The EV3 implementation of the robot was tested against the Raspberry Pi implementation. Each implementation of the robot was made to rotate on the spot under varying weights ranging from 0kg to 2kg. The time taken to rotate 360 degrees was recorded and this was repeated 3 times for each implementation and load weight pairing.
  ![RPI vs EV3 Test](/images/ev3_rpi_test.png)
  **Evaluation**: The results show that the switch over from the EV3 to the Raspberry Pi did not impact the robot's performance significantly and so the EV3 was replaced. The robot with the Raspberry Pi is still able to carry out general tasks like the EV3 implementation could but has a substantial advantage in processing power which is required for the likes of running the object avoidance code.
  
> ##### 4. Base Development Drop Tests:
  
  Throughout the development of the N.E.A.T. base structure, several drop tests were conducted to identify areas of structural weaknesses and aid design changes. The tests were carried out in a safe environment (lab floor) with advanced warnings given to surrounding groups to make them aware of what was taking place and prevent harm. The heights dropped from were no higher than 50 cm and were always conducted in a controller manner to avoid serious equipment damage. These tests were not carried out in an offical manner like the previous tests mentioned above, but were rather done incrementally throughtout the base development process.

<<<<<<< HEAD
<hr style="height:3px;border:none;color:#333;background-color:#333;">
<center><h2>User Tests</h2></center>
  No official user test has been conducted as we never got the ethics approval for it. Instead we tested the robot ourselves trying to give our best estimation of how a person with different disabilities or age would use the robot so we can adjust features and discover limitations. Factors we took into consideration were: Speed, duration, load weight, environment(obstacles).
=======
### User Tests: 
  No official user test has been conducted as we never got the ethics approval for it. Instead we tested the robot ourselves trying to give our best estimation of how a person with different disabilities or age would use the robot so we can adjust features and discover limitations. Factors we took into consideration were: Speed, duration, load weight, environment (obstacles).
>>>>>>> 83a62d61bf523856e7ca9bc333f3bc0654dee73d

<hr style="height:3px;border:none;color:#333;background-color:#333;">
<center><h2>Main Areas of Improvement</h2></center>

  * **More sensors**, 360 degree vision for improved object avoidance. General rule: Detect, Turn, Clear, Continue
  * Use **better** infrared **sensor-beacon combination** for longer range following possibly with wider (360 degree) range
  * **Better materials**, structural improvements would help improve the weight limit and make it usable for heavier objects
  * **Greater weight bearing** mecanum wheels (4kg is what our current wheels are meant to take at maximum)
  
