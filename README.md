# My Microservices project

**This project contains following microservices:**

* Customer service, rest api which deal with customer entity, make all manipulation with model, such as creating, updating, getting, deleting and store data in postgres database which runs in docker.
* Account service, rest api which deal with account entity, make all manipulation with model, such as creating, updating, getting, deleting and store data in postgres database which runs in docker.
* Service Registry, eureka server 
* API Gateway, service for request routing and management, cloud gateway
* Config Server, service which get configs from GitHub repo and set this configs to other services
* Login service using in api gateway for security
* Email sender service for notification customer after transaction

**Also in docker compose run 4 containers**

* PostgreSQL server
* PGAdmin
* Redis stack server
* Zipkin server for logging
