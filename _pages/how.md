---
layout: page
title: How does it work
permalink: /how-it-work/
---

The NEAT robot is made of three main subsytems. These are the robot base, lift, and app. The main features of these will be discussed in a bit more detail in this section.

#### Lift Feature Highlight
##### Lift Hardware Design
The lift’s design was developed using the SOLIDWORKS CAD modelling package. SOLIDWORKS includes several useful features which were used in the development of the system. Some of these features include motion studies to detect collision between components through the lifts range of motion, mass property evaluation to estimate the weight of the lift, and clearance verification to determine if there’s enough clearance between components.

Utilising these features allowed us to present a design which required minimal modification before being constructed in the mechanical workshop. The final design produced in the workshop was made from MDF and aluminium to ensure strength and stability.

Below is a video of the completed SOLIDWORKS design:

<video width="480" height="371" controls>
  <source src="/videos/Finsihed_Side_Profile.mp4" type="video/mp4">
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