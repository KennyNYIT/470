# Trip Calculator
This program is for calculating the distance and cost between two locations.

To improve the performance, this program used a HashMap to cache all calculated result.

# dependency
    jackson to deserialize JSON to java object.
    String utils from apache commons-lang3.
    Junit 5.9.1 and mockito 5.1 to complete unit test.
    Springboot 3.0.3 
    Spring mvc 3.0.3
# How to Run:
    1. after checked out the repository
    2. import maven project to your IDE
    3. execute the TripCalculatorApplication.main class to run this program.
    4. use browser or postman to call the services:
        - http://localhost/location/{start location's name}/{end location's name}
        - http://localhost/location/id/{start location's id}/{end location's id}
    
