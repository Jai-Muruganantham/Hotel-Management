# Hotel-Guest-Management

## Task Description

The hotel has 5 single rooms. The rooms are numbered from 1 to 5.
No more than one guest lives in one hotel room.

Guest details: 
- Name
- Surname

Possible actions:
1. Guest registration. The required information is the name and surname of the guest. The system itself selects the ship's room and informs the guest. If there are no rooms available at the moment, the guest will be replied.
2. Check-out of the guest.
3. Room occupancy review - occupied rooms and who lives in them are listed.
4. History and status of the room - show the names and surnames of all guests who have stayed in order. Showroom status - available or occupied.

## Tech Stack & Required Environment
**Client:** HTML, CSS, JavaScript

**Server:** Java 17, Maven, Spring boot 3.1.2, Junit, Mockito

**Database:** MySQL 8

## Run the Application

1.  Clone the repository https://github.com/Jai-Muruganantham/Hotel-Management.git
2. Set up your Mysql database
3. To establish a connection between your Spring Boot application and MySQL, you need to include the required configuration in the **application.properties** file.

You need to change 

  * **from** spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase10    **to**   spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME
  * **from** spring.datasource.username=root    **to** spring.datasource.username=YOUR_DATABASE_USER_NAME
  * **from** spring.datasource.password=Jai@1211    **to** spring.datasource.password=YOUR_DATABASE_PASSWORD
4. Run/Start the project
5. Check the Web Application at http://localhost:8080/hotel

