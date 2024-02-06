# Windsurfing Wind Finder

"Windsurfing Wind Finder" is an application that shows todays
the most windy places. By default, there are few places
saved in database, and the user can add his own places just
by entering the
city name. As a result, application returns a list of places with the
wind and temperature daily forecast, sorted by wind strength.

The application uses an external API serwices to operate. One to 
download geocode form entered city name, the other to download the weather 
forecast based on that geocode.

Application is using Java 17, Spring boot, Maven, H2 database, Lombok, JPA

## Endpoints

### 1. Fetching Data

#### 1.1 `GET /index`

Endpoint for retrieving and displaying a list of points in the application.

**Parameters:**
- None

**Returned Data:**
- HTML view "index.html" with a list of points sorted in descending order by wind speed.

#### 1.2 `POST /saveNewPoint`

Endpoint for adding a new point based on the city name.

**Parameters:**
- `cityName` (String) - the name of the city for which a point will be added.

**Returned Data:**
- Redirect to the `GET /index` HTML view after adding the point.

#### 1.3 `POST /deletePoint`

Endpoint for deleting a point based on the city name.

**Parameters:**
- `cityToDelete` (String) - the name of the city for which the point will be deleted.

**Returned Data:**
- Redirect to the `GET /index` HTML view after deleting the point.

## Configuration

In the beggining of instalation you need to install java 14 and maven

[Java](https://jdk.java.net/21/)

[Maven](https://maven.apache.org/download.cgi)
## Running

When application is running is available on localhost:
http://localhost:8080/index
