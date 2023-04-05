## **Windsurfing Wind Finder**

"Windsurfing Wind Finder" is an application that shows todays
the most windy places. By default, there are three places
 saved in database, and the user can add his own places just
by entering the
city name. As a result, application returns a list of places with the daily 
wind and temperature forecast, sorted by wind strength.

The application uses an external API to operate. One for the 
location city geocode and the other for the weather forecast.

Application is using Java 14, Spring boot, Maven, H2, Lombok, JPA

## Installation
In the beggining of instalation you need to install java 14 and maven

[Java 11](https://adoptopenjdk.net/)
[Maven](https://maven.apache.org/download.cgi)

## Web
Application is available on below localhost:
http://localhost:8080/

## Endpoints
GET /printList - get list of all places

POST /addPoint - save place in database

DELETE /delete/{city} - delete place from database

