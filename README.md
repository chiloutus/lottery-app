# Lottery Application
A Spring Boot application written in Java 8 to provide the following functionality:

-Create lottery tickets with n lines

-Amend lottery tickets with the specified number of lines

-Check the results of the tickets that have been generated.

# Getting started guide

This application exposes three REST API's, all which can be found in the generated swagger
(the default address is http://localhost:9996/swagger-ui.html) 

For reference however:

Create (POST): http://localhost:9996/v1/tickets/create?numberOfLines=6

Update (PUT): http://localhost:9996/v1/tickets/update/ticket_1?numberOfLines=6

Check Status (PUT): http://localhost:9996/v1/tickets/check/ticket_1


The application can be ran using "mvn spring-boot:run"

# Contact

For more information, contact me @ lynamgaz@yahoo.ie