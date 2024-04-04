# bookstore-ims

Bookstore Inventory Management System - a SpringBoot application to manage a bookstore's inventory. This backend system support operations to add, update, delete, and retrieve books. The focus is on using gRPC with Protobuf for communication with Project Reactor for reactive programming.

## Technologies
* Java 17
* Spring Boot 3.2.4, Spring Data R2DBC
* Protobuf, gRPC, Reactor
* Docker
* PostgreSQL


## Installation and Launch
1. Ensure you have Docker installed on your system.
2. Fork this repository.
3. Clone your forked repository.
4. Configure your database settings in the .env file.
5. Build from the root project folder with Maven.
6. Build then run it with Docker Compose: 
```
docker-compose up --build 
``` 
7. Use Postman for sending requests.

## Usage Example

Create request:  
![create method](https://github.com/kateryna-mykh/bookstore-ims/blob/main/app/src/main/resources/img/postman_test-create-book.PNG)  
Update request:  
![update method](https://github.com/kateryna-mykh/bookstore-ims/blob/main/app/src/main/resources/img/postman_test-is-updated.PNG)  
Get all request:  
![get all method](https://github.com/kateryna-mykh/bookstore-ims/blob/main/app/src/main/resources/img/postman_test-get-all.PNG)  
