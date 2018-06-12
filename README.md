# EveSkillsTracker

Copyright (c) Victor Ochia This work is available under the "MIT License" Please see the file LICENSE for license terms. 
For any inquires, please Email : vicochia@pdx.edu.


# What is EveSkillsTracker

EveSkillsTracker is a skill viewing tool for Eve Online where users may view all of their current skills, view their queued 
training skills, and calculate how many skillpoints they will need in order to advance to a certain skill level from their 
current level with a simple login and click of a button. 

# In Depth

EveSkillsTracker uses the ESI (Eve Swagger Interface) API to pull user information from Eve Online, along with 
the helper tool Retrofit to gather the information into class objects. Once logged in,you will be able to view your attributes 
and ISK. You are then greeted with three buttons, "view current skills", which gives and overview descrpription, skill rank, and skill points of each skill, "view queued skills", which does the same with the start and end time of training displayed in order of the queued position, and "skills projection calculator", which calculates the skillpoints you will need to advance to a level 1-5 from your current skill level. The skill rank multiplier is considered during the calculation.

# How is EveSkillTracker currently

EveSkillsTracker is working as it should, but reinforcements remain in order to possibly make initial loading time a bit
quicker during the large amount of API calls that are made. This project could possibly be extended in the future to be much
more than just a skill tracker, but a interface with all different kinds of helpful user tools.

# Build Instructions

Go to www.androidstudio.com and download the Android Studio IDE. You must have JDK installed as well. After opening a new 
project, clone this repository. Run the application using an emulator (Preferably Nexus 6P API 24 for best results). .APK file 
included can be used to attempt to run app on your own Android device.

If any inquires or questions arise, to not hesitate to contact me by email: vicochia@pdx.edu
