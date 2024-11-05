
Project Overview

The API will generate unique tracking numbers based on the provided parameters like origin_country_id, destination_country_id, weight, created_at, customer_id, customer_name, and customer_slug. The tracking numbers must adhere to the pattern ^[A-Z0-9]{1,16}$ and should be unique and generated efficiently.

Step 1: Set up the Spring Boot Project

Create the project using Spring Boot.

Project: Maven Project
Language: Java
Spring Boot Version: Latest
Packaging: Jar
Java Version: 17 or later

Dependencies:
Spring Web
Spring Boot DevTools
Spring Data JPA

Step 2: Design the API Endpoint

TrackingNumber Controller: The main endpoint /next-tracking-number will receive the query parameters and return the generated tracking number.

TrackingNumberResponse (for API response format):

Step 3: Implement Tracking Number Generation Logic

TrackingNumberService:

This approach generates a unique tracking number by hashing the combined input string. The first 16 characters of the hash are used to form the tracking number, ensuring it matches the regex pattern.

Step 4: Handling Concurrency and Uniqueness

To ensure uniqueness, we can leverage a distributed unique ID generation system like UUID or an external system like Redis. In this case, using Redis is a good approach for horizontal scalability.

Using Redis for Distributed Locking (if scalability is required):

Redis can be used to store the generated tracking numbers and ensure that no duplicate values are created.

Redis Service (for ensuring uniqueness):

Step 5: Testing and Deployment

Unit Tests: Can use JUnit and Mockito to write unit tests for the TrackingNumberService and TrackingNumberController.

Integration Testing: Can test the API endpoint with different combinations of input parameters to ensure correct behavior.

Step 6: Final Notes

Scalability Considerations: For handling high concurrency, using Redis or a similar distributed cache/store for unique IDs is key to scaling horizontally.

Load Balancing: If deploying across multiple instances, systems should be stateless (no dependent on local server state).

Testing: Can perform load testing using tools like JMeter to verify that the system can handle high loads.

GET http://localhost:8080/api/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=2018-11-20T19:29:32 08:00&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox Logistics&customer_slug=redbox-logistics

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 05 Nov 2024 13:32:03 GMT

{
  "trackingNumber": "VImJP4GKtlqK9NVZ",
  "createdAt": "2024-11-05T13:32:03.074078100Z"
}

Response code: 200; Time: 8ms (8 ms); Content length: 82 bytes (82 B)



