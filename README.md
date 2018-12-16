Summary
-------
Implements simple student grade finder REST web service in Java


# Configuration

## Base url
Web service is running on port 8080 by default (http://localhost:8080). This can be changed with java property context_url, e.g.: -Dcontext_url=http://localhost:8090

## Datasource
Data file student_classes.json is binded in the application binary
Application can be pointed to external json data file with java property data, 
e.g.  -Ddata=~/some-other-file.json 

# Build
Implemented with gradle

## To build
Posix:		./gradlew clean build
Windows: 	gradlew clean build

## To run
Posix:							./gradlew clean run
Windows: 						gradlew clean run
Alternatively (after build):		java -jar ./build/libs/student-grades.jar.


## To test
Posix:		./gradlew clean test
Windows: 	gradlew clean test

# Documentation

## Swagger documentation
Accessible on http://localhost:8080/swagger.json
or http://localhost:8080/swagger.yml after application startup

## Usage examples
curl http://localhost:8080/students/ping		
curl http://localhost:8080/students/3002
curl http://localhost:8080/students/?firstName=Tyler&lastName=Cortez&countMax=100

## Distribution
@See ./build/libs and ./	build/distributions	(after build)


# Shortcomings

The application is of the demo and not industrial grade. This is mostly due to the time limitations.

-Minimal service input validation, error handling, and integration tests
-"Naked" domain model. The same objects are used for persistence and representation.
-Absent json configuration, property ordering, etc.
-Rudimentary configuration, and logging 
-Absent built in instrumentation, security, actuator endpoints, load balancing, 
rate limiting, etc.

