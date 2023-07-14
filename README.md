## User Location Problem

    
This is a Spring Boot project built using Gradle  which provides REST API endpoints for managing user locations. The project has the following requirements:


1. There are two types of users: ADMIN and READER.
2. ADMIN users can perform CRUD operations (POST, DELETE, PATCH, etc.).
3. READER users can only perform GET operations.
4. There is a REST API called create_data that creates a table in the database named 'user_location'.
5. The 'user_location' table has three fields: NAME, Latitude, and Longitude.
6. Users can update the 'user_location' table using the REST API called update_data.
7. READER users can call the get_users/N API, which returns the nearest N users from the location (0,0)



### Technology Stack
        
* Java 17
* Spring Boot 3.0.8
* Gradle 7.2
* HSQLDB 2.5.2

#### How to run the project

1. Clone the project from the repository.
2. Open the project in IntelliJ IDEA.
3. Run the project using the following command:
    ```
    gradle bootRun
    ```
4. The project will run on port 8080.  Access the API endpoints using a REST client or browser.



#### API End Points

     
 * '/api/signup' - POST "Valid User Registration"  Admin and Reader both can access this API
     
 * '/api/login' - POST "Valid User Login"  Admin and Reader both can access this API

* '/api/create_data' - POST "Create user_location table"  Only Admin can access this API

* '/api/update_data/{id}' - POST "Update user_location table"  Only Admin can access this API

* '/api/get_users/{n}' - GET "Get nearest n users from location (0,0)"  Both can access this API

* '/api/get_users' - GET "Get all users"  Both can access this API


#### Database

The project uses HSQl in-memory database.

#### Security

 Spring Security is used for authentication and authorization. The project uses JWT for authentication. The project uses the following roles:
    
    1. ADMIN
    2. READER



#### Thank you for your time.
    




