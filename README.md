DELIVERY COMPANY WEB PAGE
=======================================


This is web project realized on Spring Boot using MySQL database. There are 3 types of user:
-Client - after loggin, can just manage his deliveries (create, edit or confirm delivery)
-Deliverer - can see all the deliveries. 
-Admin - can see also list of users and edit all the data.

It uses four microservices:
- API Gateway - single entry point for all users.
- Data collection service - responsible for collection and connection data from different sources
(like client-data-service or loan-data-service).
- Loan data service - responsible for accessing data from non-relational database (MongoDB).
- Client data service - responsible for accessing data from relational databse (MySQL).

Technologies
------------

- Eureka for Lookup
- 




