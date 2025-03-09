# App Template

This repository is a sample application repository using Java Quarkus for creating new repositories that will be used to create applications.

## Included Services

This repository comes with a configured `docker-compose` to run the following services:
- **3 Kafka Brokers**: Servers that form a Kafka cluster for message management and data streaming.
- **Control Center**: Management and monitoring tool for Kafka clusters.
- **Zookeeper**: Coordination service to manage the state of the Kafka cluster.
- **Postgres Database**: Relational database used for persistent data storage.

### How to Build the Application with Unit Tests
To build the application with unit tests, use the command:
```sh
./gradlew build
```
### How to Run the Application Locally
To run the application locally, use the command:
```sh
./gradlew quarkusDev
```
### How to Prepare the Container to Run with Docker Compose
To build the application and its container for use with docker compose, use the command:
```sh
./publish.bat
```
### How to Start the Services
To start the services, just have Docker Compose installed on your computer and run the following command:
```sh
docker compose up -d
``` 