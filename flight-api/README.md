# Read Me First
Project Description-

**FlightSearch Api**-
Use case: 
- User can be able to find list of flights from Origin - Destination
- User can supply extra parameters in the request to sort results based on price, duration...

**Step 1: Set Up Your Development Environment**

Before you start coding, make sure you have the necessary tools and environment set up:

- Install Java Development Kit (JDK).
- Install an Integrated Development Environment (IDE) like Eclipse or IntelliJ IDEA.
- Install Spring Boot if not already installed.
- Set up a version control system (e.g., Git).

**Step 2: Create a New Spring Boot Project**

Use your IDE or the Spring Initializer (https://start.spring.io/) to create a new Spring Boot project. Select the necessary dependencies, including "Spring Web" for building RESTful APIs.

**Step 3: Define the Flight Entity**

Create a Flight entity class to represent flight information. This class will be used to model flight data in your application.


**Step 4: Create a Flight Repository**

Create a repository interface that extends `JpaRepository` to interact with the Flight entity in the database.


**Step 5: Implement Flight Service**

Create a FlightService class to implement business logic for flight-related operations such as searching for flights, booking flights, and retrieving flight details.

**Step 6: Implement Flight Controller**

Create a FlightController class to define RESTful API endpoints for flight operations. Use annotations like `@RestController`, `@GetMapping`, `@PostMapping`, etc., to map URLs to methods.

**Step 7: Implement DTOs (Data Transfer Objects)**

Define DTO classes to transfer data between your API and the client. This helps in controlling the data that is sent and received.

**Step 8: Set Up Database Configuration**

Configure your database connection in the `application.properties` or `application.yml` file. You can use an in-memory database like H2 for testing or set up a production database like H2 or MySQL.

**Step 9: Implement Exception Handling**

Implement custom exception handling to handle errors gracefully and provide meaningful error responses.

**Step 10: Test Your API**

Write unit and integration tests to ensure the functionality of your API. Use testing frameworks like JUnit and tools like Postman or Swagger for API testing.

**Step 11: Document Your API**

Create API documentation using tools like Swagger  to help other developers understand how to use your API.

**Step 12: Run and Deploy Your Application**

Run your Spring Boot application locally for testing. When you're ready, deploy it to a server.

**Api Endpoint**-

1. **Get All Flights (Sorted by Duration or Price)**

   - **URL:** `http://localhost:8080/api/flights/sorted`
   - **Method:** GET
   - **Description:** Retrieve a list of all available flights. You can optionally sort the results by duration or price.
   - **Parameters:**
     - `sortBy` (string, optional): Sorting criteria ("duration" or "price").
   - **Response:** A JSON array containing flight information.

2. **Create a New Flight**

   - **URL:** `http://localhost:8080/api/add-flight`
   - **Method:** POST
   - **Description:** Create a new flight record.
   - **Request Body:** Flight information in JSON format.
   - **Response:** The newly created flight's ID and confirmation.

3. **Update Flight Information**

   - **URL:** `http://localhost:8080/api/{Id}`
   - **Method:** PUT
   - **Description:** Update the details of a specific flight by its ID.
   - **Request Body:** Updated flight information in JSON format.
   - **Response:** Confirmation of the update.

4. **Delete a Flight**

   - **URL:** `/api/{Id}`
   - **Method:** DELETE
   - **Description:** Delete a specific flight by its ID.
   - **Response:** Confirmation of the deletion.

5. **Get Flight by ID**

   - **URL:** `http://localhost:8080/api/flights/{Id}`
   - **Method:** GET
   - **Description:** Retrieve detailed information about a specific flight by its ID.
   - **Response:** JSON containing flight details.

6. **Get Flight By Origin and Destination**

   - **URL:** `http://localhost:8080/api/find-list?origin=NWEF &destination=FGHJ`
   - **Method:** GET
   - **Description:** Retrieve detailed information about a specific origin and destination.
   - **Response:** A JSON array containing flight information.







