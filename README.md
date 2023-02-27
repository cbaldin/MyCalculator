# MyCalculator

## Overview
"My Calculator" is a back-end-project whose objective is to carry out operations with a cost control of each one of the operations.

* The available operations are and the respective cost of them:
  * /sum - 1.0
  * /sub - 1.50 
  * /mult - 3.00
  * /div - 2.00
  * /square-root - 5.00
  * /random-string - 10.00
  * /login
  * /login/verify
  * /records - get, delete (soft delete)

For testing purposes, the standard user has 50 credits.
Default user:
* test@test.com
  
Every operation has an authentication/authorization, for this reason, to make requests it is first necessary to obtain a token in the /login service and later make a request in the desired service

## Tech Stack
Project uses Java 19, SpringBoot, Rest APIs, Junit, Mockito, PostgreSQL, JPA and Maven .

## cURL
Simple cURL for login.

curl --location 'https://ec2-44-212-33-47.compute-1.amazonaws.com:4443/api/v1/login' \
--header 'Content-Type: application/json' \
--data-raw '{
"username": "test@test.com",
"password": ""
}'

Simple cURL for verify the Bearer token.

curl --location 'https://ec2-44-212-33-47.compute-1.amazonaws.com:4443/api/v1/login/verify' \
--header 'Authorization: Bearer eb7YczBrHyCBXasYa-U7bVAx-0kEd3LS'

Ps: The aws address can be different because it is not a fixed IP.