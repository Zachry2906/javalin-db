# Javalin Database

Javalin Database is a backend project that demonstrates the integration of Javalin with PostgreSQL database using SQL2o. It provides RESTful API endpoints for performing CRUD (Create, Read, Update, Delete) operations, searching data and pagination, with results returned in JSON format. [Learning Source](https://www.postgresqltutorial.com/postgresql-getting-started/postgresql-sample-database/) and [database for this repo](https://www.postgresqltutorial.com/wp-content/uploads/2019/05/dvdrental.zip)

## Technologies Used

- Java: Primary programming language
- Javalin: Web framework for creating REST APIs
- PostgreSQL: Database for storing and retrieving data
- SQL2o: SQL interface library for database operations
- GSON: Library for JSON serialization/deserialization

## Features

- RESTful API endpoints for CRUD operations
- Search and paginatioin functionality
- JSON responses using GSON
- Efficient database operations with SQL2o

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- PostgreSQL database
- Maven (for dependency management)

## Installation and Setup

1. Clone the repository:
``` bash
git clone https://github.com/Zachry2906/JavalinDB.git
```
2. Download and import database provided above
2. Navigate to the project directory
3. Make sure to refresh pom.xml to avoid unexpected error
3. Configure your database connection in `src/main/java/id.dojo/DBConnect`:
```bash
  static{sql2o = new Sql2o("jdbc:postgresql://localhost:5432/{your database name}", "{username}", "{password}");}
```
4. Build the project:
``` bash
mvn compile
mvn install
```
5. Run the application:
``` bash
java -jar target/javalin-database-1.0-SNAPSHOT.jar
```
or you can run it directly using your IDE

**Remember to always compile your java using *mvn compile* for every changes you do**

## API Endpoints

- `GET /actors`: Retrieve list of actors
- `GET /actor/<id>`: Retrieve a specific actor by ID
- `GET /film-actor`: Retrieve list of films joined with actors
- `PUT /actor/update`: Update an existing actor
- `POST /actor`: Create a new actor
- `POST /insertCity`: Insert a new city
- `GET /getList/<page>`: Retrieve a paginated list of cities
- `DELETE /actor/delete`: Delete an actor
- `GET /payment`: Retrieve payment information
- `GET /carinama/`: Search actors by name
- `GET /carinama/<page>`: Search actors by name with pagination
- `GET /film-categories`: Retrieve list of film categories
- `GET /getCityById/<id>`: Retrieve a specific city by ID
- `PUT /updateCity`: Update an existing city


## Project Structure
- src/main/java/id.dojo
- Main.java: Entry point of the application
-DBConnect: Database configuration 
- controllers/: API endpoint handlers and logic
- models/: Data models
- helper/: Utility classes


## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.
