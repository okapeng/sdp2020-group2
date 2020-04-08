<!-- ---
layout: page
title: How does it work
permalink: /how-it-work/
--- -->

The NEAT robot is made of three main subsytems. These are the robot base, lift, and app. The main features of these will be discussed in a bit more detail in this section.

### Base
#### Base Design
The base final design was developed through an iterative development process in which 12 base iterations took place from graphical design to the final product you see today.

*Iteration 1: January 15th*

The first iteration of the robot was a simple lego base which had 4 wheels and a very fragile frame that spanned roughly 20cm by 20cm. It was created during the Robot Building Workshop.

*Iterations 2 and 3: January 16-20th*

The next iterations consisted of a simple sketch of the robot and a more extensive graphical design. This iteration in the design project gave us a projection of how we would like the final product to look and allowed us to explore a more realistic view of the final product.

![Ivy_sketch](/images/ivy_sketch.png) ![Barna Design](/images/barna_design.png)

*Iteration 4: January 21st*

The base by this point consisted of a small, flat lego platform with two wheels attached, controlled by 2 lego motors and the EV3.

*Iteration 5: January 27th*

This iteration was similar to the previous iteration with the exception of the arrival of the Mechanum wheels which saw the base vastly improve through providing a testing platform for the development of the EV3 movement code.

![First Lego Base](/images/lego_base1.png)

*Iteration 6: January 30th*

The base has increaase in size significantly with considerable structural improvement. This base helped develop the EV3 code to control directional movements as well as rotation on the spot. A single ultrasound sensor was added to aid development testing of the object detection and avoidance code.

![Second Lego Base](/images/lego_base2.png)

*Iteration 7: Febuary 1st*

Issues with the previous iteration of the robot base such as structural intregrity under weight resulted in this iteration being heavily reinforced with several layers of interleaving lego blocks. Furthermore, lego struts were placed along the inside to provide stability and support for the EV3 block to rest on during functionality. However, a major design flaw was discovered after completion, in that the wheels were not aligned and therefore, rotational and diagonal movement was subpar.

![Third Lego Base](/images/lego_base3.png)

*Iteration 8: Febuary 2nd*

The base was taken apart and the length was increased to align the wheels correctly and fix the issue of the of the previous iteration. Upon weight testing, the base still showed significant signs of weakness, especially the corner sections which were susceptible to breaking under reasonable weight.

![Fourth Lego Base](/images/lego_base4.png)

*Iteration 9: Febuary 7th*

The base was completely disassembled with a new design being implemented, mainly the addition of using L-shaped lego clips that would hold the corners and middle struts in place instead relying solely on interleaved lego blocks. This partially improved the weight issues from the previous iteration but the base was still bending under weight. Two more ultrasonic sensors were added (front left and front right) to increase the object avoidance capabilities.

*Iteration 10: Febuary 15th*

The most radical and ivotal design change took place here, resulting in the wheels which used to be situated on the side of the robot now being directly underneath the robot base. This meant that the weight of the robot was bearing directly on the wheels and no longer on the base resulting in drastic weight bearing capabilities and prevented the lego base from bending. A byproduct of the wheel placement change resulted in increasing the bases ground clearance to 9cm. Another change was the addition of an infrared sensor which was attached to the front of the robot and allowed for the testing of the "follow me" functionality.

![Fifth Lego Base](/images/lego_base5.jpg)

*Iteration 11: March 1st*

This iteration was less to do with design changes but rather implemented the Raspberry Pi onto the robot base after a testing comparison between the EV3 and Raspberry Pi showed that the Pi controlling the motors would not impact the robots performance drastically. The EV3 is still used for reading sensor data, but the Pi will be processing that data and controlling the robot as a whole.

![Sixth Lego Base](/images/lego_base6.jpg)

*Iteration 12: March 8th*

The final interation of the robot base entailed creating housing under the robot base to hold the Rasbperry Pi and the EV3 in order to make the base top as flat as possible for the arrival of the lift. The lift was intergrated into the robot base and secured, making this iteration the final product.

![Seventh Lego Base](/images/lego_base7.jpg)   ![Eighth Lego Base](/images/lego_base8.png)

#### Base Features
1. Provides a strong reinforced base that wholed the rest of final product.
2. Has the means for directional travel in up to 6 direction.
3. Securely houses the brains of the N.E.A.T. (EV3 and Raspberry Pi).
4. Contains on-board object detection and avoidance.
5. Provides power to the EV3, Raspberry Pi and Lift.
### Lift Feature Highlight
#### Lift Hardware Design
The lift’s design was developed using the SOLIDWORKS CAD modelling package. SOLIDWORKS includes several useful features which were used in the development of the system. Some of these features include motion studies to detect collision between components through the lifts range of motion, mass property evaluation to estimate the weight of the lift, and clearance verification to determine if there’s enough clearance between components.

Utilising these features allowed us to present a design which required minimal modification before being constructed in the mechanical workshop. The final design produced in the workshop was made from MDF and aluminium to ensure strength and stability.

Below is a video of the completed SOLIDWORKS design:

<video width="480" height="371" controls loop autoplay>
  <source src="/videos/Finished_Side_Profile.mp4" type="video/mp4">
</video>

And here you can see the completed product:

![Completed Lift 1](/images/lift_retracted.jpg)

![Completed Lift 2](/images/lift_extended.jpg)

##### Lift Drive System
The chosen drive system for the lift was a threaded rod design. Utilising a threaded rod design allowed us to provide a mechanism for moving the lift up and down without buckling under the weight of whatever was placed on the platform. It also means less effort is required too move the platform compared to a design that is moving directly against the forces exerted by the lift.

##### Lift Electronics
The lift uses a custom H-bridge circuit connected to the robot’s Raspberry Pi to control the movement of the motor. The circuit is made up of 6 transistors, 4 diodes, and 6 resistors. It was designed and simulated within the LTspice simulation program using transistor models imported for the specific components chosen for the project to ensure accurate simulation results.

![H-Bridge](/images/H-Bridge_Revised.PNG)

#### System Diagrams: 
  You should include images (where available) which illustrate the methods you used, how the system works etc. This can include system diagrams, images of your system in certain states etc.
  
  ![state-diagram](/images/state_diagram.png)
