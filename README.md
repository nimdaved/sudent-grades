Summary
-------
Implements simple student grade finder REST web service in Java


#Configuration

##Base url
Web service is running on port 8080 by default (http://localhost/8080). This can be changed with java property context_url, e.g.: -Dcontext_url=http://localhost/8090

##Datasource
Provided data files student_classes.json is binded in the application binary
Application can be pointed to external json data file with java property data, 
e.g.  -Ddata=~/some-other-file.json 

#Build
Implemented with gradle

## To build
Posix:		./gradlew clean build
Windows: 	gradlew clean build

## To run
Posix:			./gradlew clean run
Windows: 		gradlew clean run
Alternatively:	java -jar ./build/libs/student-grades.jar.


## To test
Posix:		./gradlew clean test
Windows: 	gradlew clean test

# Documentation

##Swagger documentation
It is accessible on http://localhost/swagger.json
or http://localhost/swagger.yml

##Usage examples
curl http://localhost/students/ping		
curl http://localhost/students/3002
curl http://firstName=Tyler&lastName=Cortez&countMax=100

##Distribution
@See ./build/libs and ./	build/distributions	


# Shortcomings

The application is of the demo and not industrial grade. This is mostly due to the time limitations.

-Minimal service input validation, and error messages
-Naked domain model. The same objects are used for persistence and representation.
-Absent json configuration, property ordering, etc.
-Rudimentary configuration, and logging 
-Absent built in instrumentation, security, actuator endpoints, load balancing, 
rate limiting, etc.
