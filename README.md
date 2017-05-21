#Kalah Game

Kalah Gameboard is a software that implements an ancient board game called Kalah or Mancala.
It is a web application that runs the game of 6-stone Kalah. 
For the general rules of the game please refer to Wikipedia: 
https://en.wikipedia.org/wiki/Kalah. 
This is a Java based project using Maven for building and managing.

#Tech stack
1. Java 1.8 
2. Spring 4
3. Maven 
4. Angular js 
5. Bootstrap 3
6. Apache Tomcat 8

#Project's structure

- src/main/java - Server side java + spring based implementation
- src/test/java - Server side java + spring based Junit test case
- src/main/webapp/resources - Front End UI with Angular JS + Bootstrap
   css, js, partial, img
- src/WEB-INF - Deployment Descriptor
- index.html - Welcome file
 
 #How to run
 
 #1.Build :-
 - Start a terminal, then run:
 - git clone https://github.com/gopalvpatil/Kalah
 - cd kalah
 - mvn clean install 
 
 It will successfully build kalah.war file in target directory
 
 #2.Deployment :-
 - copy kalah.war from target directory to tomcat8 webapps folder
 - startup tomacat
 - This step will deploy kalah.war file.
 
 #3.Run :-
 - Open your favorite web browser and access
 -  http://localhost:8080/kalah
 
 
 
 
 
 

