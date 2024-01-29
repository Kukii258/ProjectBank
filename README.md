# Bank Project

## Overview

The Bank Project is a simple banking application implemented in Java using the Spring Boot framework. It allows users to perform various banking operations such as transferring money, viewing balance, adding contacts, and more. The application is backed by a MariaDB database named "bank" for storing user information, transactions, and contacts.

## Usage
  
-Register a new user or log in with existing credentials.
-Add contacts by providing their email addresses in the contact section.
-Make money transactions by specifying the recipient's email and amount in the transaction section.
-View user information and balance graph to track transaction history.


## Technologies Used

- Java
- Spring Boot
- Thymeleaf (for frontend)
- MariaDB (as the relational database)

## Features

1. **User Registration and Login:** 
  -Users can register with their name, email, age, address, and phone number. 
  -Existing users can log in with their credentials

2. **Contact Management:** 
  -Users can add contacts by providing their email addresses. 
  -The application ensures that the added email is valid and not a duplicate.
  - Contacts are stored in the database.

3. **Money Transactions:** 
  -Users can transfer money to their contacts by providing the recipient's email and the amount to be transferred. 
  -The application checks for valid emails, sufficient balance, and handles the transaction process.
  -Transaction history is recorded and can be viewed.

4. **User Information and Balance Graph:** 
  -Users can view their information and a graph representing their balance over time based on transactions.


## Setup

1. **Database Configuration:**
   - Create a MariaDB database named "bank".
   - Update the `application.properties` file with your database connection details.

2. **Build and Run:**
   - Build and run the application using your preferred IDE or by running `mvn spring-boot:run` in the project directory.

3. **Access the Application:**
   - Open a web browser and go to [http://localhost:8080/login](http://localhost:8080/login) to access the login page.

## Project Structure

1. **Controllers:**
  -Contains the main controller classes for handling HTTP requests and managing the flow of the application.

2. **Model:** 
  -Includes the entity classes representing User, Contact, and Transaction.

3. **Repositories:** 
  -Data access layer containing JPA repositories for User, Contact, and Transaction entities.

4. **Services:** 
  -Business logic and service classes for user management, contact operations, and transaction handling.

5. **Views:** 
  -Thymeleaf templates for rendering HTML pages.

## Contributing

Feel free to contribute to the project by opening issues or submitting pull requests.
