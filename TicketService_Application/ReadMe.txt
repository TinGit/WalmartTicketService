**************************************
*           Tinbit Hirutu
*************************************

======================================
              	ReadME
======================================

Technologies uses are:
	Java, Maven, Spring Framework, MySQL database,Eclipse mars, JUnit


Instructions:
* The database script is under src/main/resources/databaseScript folder
   - Run the script and set the your MySQL database username=root and password=root
* Import the project in eclipse as maven project
* To start console application run WalmartMainApp located under com.ticketService.driverClass package
* Console application will walk you through finding seats, holding seats, reserving seats and releasing seats operations.
  - Type "FH" to find & hold seats, "R" to reserve seats and "RL" to release seats.
   

Structure
* Has separated layers for better maintenance and reusability: 
    - Domain, DAO, Service, Presentation Layers 


Features
* Finding & holding seats method holds seats that are next to one another rather than randomly picking seats
* Reserving seats
* Releasing seats


Assumptions
* During finding & holding seats, it looks for the availability of number of seats requested. If the requested number
  of seats are available, then return seatHold object otherwise it returns null and appropriate message will be displayed
  to the user.
* To walk through the console application, I have used only one customer email "tinbit.hirutu@gmail.com" to test the 
  features.
* Invoking release seats will release all seats hold 
