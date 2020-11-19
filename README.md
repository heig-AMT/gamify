# Gamify

This repository contains our version of the second project of the AMT class of HEIG-VD.

## Structure

+ The `docker` folder contains deployment information for our images and environments.
+ The `full-project` folder is useful to open both the implementation and specs simulateneously in IntelliJ IDEA.
+ The `gamify-impl` contains a Spring Boot project that will gamify your next app. That's probably what you came here for :smile:
+ The `gamify-specs` contains our code-based specification. It is used to validate the behavior of the API.

## Team

| Name                                   |                                  |
|----------------------------------------|----------------------------------|
| Matthieu Burguburu 					 | matthieu.burguburu@heig-vd.ch    |
| David Dupraz                           | david.dupraz@heig-vd.ch          |
| Alexandre Piveteau 				     | alexandre.piveteau@heig-vd.ch    |
| Guy-Laurent Subri                      | guy-laurent.subri@heig-vd.ch     |

## Running the service locally

Assuming you have Maven installed locally, you can run the following goal to get the app running on your `8080` port :

```
cd gamify-impl/
mvn spring-boot:run
```

The Swagger docs will then be available on the [root endpoint](http://localhost:8080/).

## Verifying the service with Cucumber

You can run the Cucumber validation via Maven. You need to make sure the app is running on your `8080` port :

```
cd cd fruits-specs/
mvn clean test
```

## Deployments

We're working on a live version of our app. Please come back soon !
