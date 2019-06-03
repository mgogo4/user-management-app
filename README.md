# user-management-app

Application user-management-app is a solution of HSBC user management task.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### Prerequisites

Maven is required to build application.
Java Runtime Environment (JRE) 8 is required to run application. 
The application has been tested for compatibility with Maven version 3.6.1 and Java 8 Update 201.

### Installing

To build an application, execute the Maven command:

```
mvn package spring-boot:repackage
```

To skip compiling and running tests use command:

```
mvn package spring-boot:repackage -Dmaven.test.skip=true
```

The application will be packed into user-management-app-1.0.0.jar and placed in target directory. To run application use command:

```
java -jar target/user-management-app-1.0.0.jar
```

By default, the application starts at:

```
http://localhost:8080
```

## Built With

* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) Version: 2.1.5-RELEASE
* [Maven](https://maven.apache.org/) - Dependency Management
* [RSQL Parser](https://github.com/jirutka/rsql-parser) - RSQL parser used for search requests. Version: 2.0.0
* [Mapstruct](http://mapstruct.org/) - Generating mappers. Version: 1.3.0

## Tools

* [Swagger](https://www.eclemma.org/jacoco/) - API documentation. Version: 2.9.2

Generates API documentation and provides tools for sending requests. Swagger UI is available at:
```
http://localhost:8080/swagger-ui.html
```

* [H2 Database](https://www.eclemma.org/jacoco/) - In-memory project database. Version: 2.9.2

Database storing user entities. Database console available at:

```
http://localhost:8080/h2-console
```

Default database properties:

```
URL: jdbc:h2:mem:usermanagementDB
User Name: user
Password:
```

## Usage

Application provides REST endpoints for managing users data.
Application supports Polish and English language versions. English is the default language. The language can be set in the header:

```
Accept-Language: pl-PL
```

### Create user

Create new user.

**URL** : `/users/`

**Method** : `POST`

#### Request
All fields must be filled. Request can not be empty.

* name - varchar [1 to 64 chars]
* surname - varchar [1 to 64 chars]
* grade - integer [not null]
* surname - integer [not null]

```json
{
  "user": {
    "grade": 0,
    "name": "string",
    "salary": 0,
    "surname": "string"
  }
}
```

#### Success Response
```json
{
  "user": {
    "id": 13,
    "name": "string",
    "surname": "string",
    "grade": 0,
    "salary": 0
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/users/13"
    },
    "delete": {
      "href": "http://localhost:8080/users/13"
    },
    "update": {
      "href": "http://localhost:8080/users/13"
    },
    "users": {
      "href": "http://localhost:8080/users"
    }
  }
}
```

#### Error Response
```json
{
  "status": 400,
  "error": "Bad Request",
  "timestamp": "00:00:02:250 01/06/2019",
  "messages": [
    "Request validation error occurred while processing request.",
    "Null User grade. User has to get specified not null grade."
  ]
}
```

### Get user by ID

Retrieves user by ID from database.

**URL** : `/users/{ID}`

**Method** : `GET`

#### Success Response
```json
{
  "user": {
    "id": 13,
    "name": "string",
    "surname": "string",
    "grade": 0,
    "salary": 0
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/users/13"
    },
    "delete": {
      "href": "http://localhost:8080/users/13"
    },
    "update": {
      "href": "http://localhost:8080/users/13"
    },
    "users": {
      "href": "http://localhost:8080/users"
    }
  }
}
```

#### Error Response
```json
{
  "status": 404,
  "error": "Not Found",
  "timestamp": "00:00:02:250 01/06/2019",
  "messages": [
    "User with id=34 does not exist."
  ]
}
```

### Update user by ID

Updates specified fields of the user of given ID in database.

**URL** : `/users/{ID}`

**Method** : `PUT`

#### Request
Only specified user fields will be updated. Any number of fields can be specified for update.

* name - varchar [0 to 64 chars]
* surname - varchar [0 to 64 chars]
* grade - integer
* surname - integer

```json
{
  "user": {
    "grade": 0,
    "name": "string",
    "salary": 0,
    "surname": "string"
  }
}
```

#### Success Response
```json
{
  "user": {
    "id": 13,
    "name": "string",
    "surname": "string",
    "grade": 0,
    "salary": 0
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/users/13"
    },
    "delete": {
      "href": "http://localhost:8080/users/13"
    },
    "update": {
      "href": "http://localhost:8080/users/13"
    },
    "users": {
      "href": "http://localhost:8080/users"
    }
  }
}
```

#### Error Response
```json
{
  "status": 404,
  "error": "Not Found",
  "timestamp": "00:00:02:250 01/06/2019",
  "messages": [
    "User with id=34 does not exist."
  ]
}
```

### Delete user by ID

Deletes user with given ID from database.

**URL** : `/users/{ID}`

**Method** : `DELETE`

#### Success Response
```json

```

#### Error Response
```json
{
  "status": 404,
  "error": "Not Found",
  "timestamp": "00:00:02:250 01/06/2019",
  "messages": [
    "User with id=34 does not exist."
  ]
}
```
### Search users

Retrieves pages of users matching given criteria from database.

**URL** : `/users/{?search}{?page,size,sort}`

**Method** : `GET`

#### Request

Searching criteria are specified with a standard of RSQL: https://github.com/jirutka/rsql-parser

```
http://localhost:8080/api/users?search=name==jo*,salary=gt=25&page=1&size=2&sort=name,asc&sort=surname,desc
```

#### Success Response
```json
{
   "_embedded":{
      "userResourceList":[
         {
            "user":{
               "id":11,
               "name":"Donald",
               "surname":"Foreman",
               "grade":2,
               "salary":12000
            },
            "_links":{
               "self":{
                  "href":"http://localhost:8080/users/11"
               },
               "delete":{
                  "href":"http://localhost:8080/users/11"
               },
               "update":{
                  "href":"http://localhost:8080/users/11"
               },
               "users":{
                  "href":"http://localhost:8080/users"
               }
            }
         },

        ...

   "_links":{
      "first":{
         "href":"http://localhost:8080/users?search=name==jo*,salary=gt=25&page=0&size=2&sort=name,asc&sort=surname,desc"
      },
      "prev":{
         "href":"http://localhost:8080/users?search=name==jo*,salary=gt=25&page=0&size=2&sort=name,asc&sort=surname,desc"
      },
      "self":{
         "href":"http://localhost:8080/users?search=name==jo*,salary=gt=25&page=1&size=2&sort=name,asc&sort=surname,desc"
      },
      "next":{
         "href":"http://localhost:8080/users?search=name==jo*,salary=gt=25&page=2&size=2&sort=name,asc&sort=surname,desc"
      },
      "last":{
         "href":"http://localhost:8080/users?search=name==jo*,salary=gt=25&page=5&size=2&sort=name,asc&sort=surname,desc"
      },
      "users":{
         "href":"http://localhost:8080/users"
      }
   },
   "page":{
      "size":2,
      "totalElements":12,
      "totalPages":6,
      "number":1
   }
}
```

#### Error Response
```json
{
  "status": 500,
  "error": "Internal Server Error",
  "timestamp": "12:11:24:676 01/06/2019",
  "messages": [
    "An unexpected internal server error occurred while processing request."
  ]
}
```

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Michał Gogolewski** 
