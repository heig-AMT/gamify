# Gamify

![Build and publish Docker image](https://github.com/heig-AMT/gamify/workflows/Build%20and%20publish%20Docker%20image/badge.svg?branch=dev)
[![Heroku App Status](http://heroku-shields.herokuapp.com/heig-amt-gamify)](https://heig-amt-gamify.herokuapp.com)
![Run tests](https://github.com/heig-AMT/gamify/workflows/Run%20tests/badge.svg?branch=dev)

This repository contains our version of the second project of the AMT class of HEIG-VD.

**:seedling: Our Cucumber reports for the `dev` branch are on [GitHub Pages](https://heig-amt.github.io/gamify/) :seedling:**

**:coffee: The generated `spec` API is on the [repository Wiki](https://github.com/heig-AMT/gamify/wiki/) :coffee:**

## Structure

+ The `docker` folder contains deployment information for our images and environments.
+ The `full-project` folder is useful to open both the implementation and specs simulateneously in IntelliJ IDEA.
+ The `gamify-impl` contains a Spring Boot project that will gamify your next app. That's probably what you came here for :v:
+ The `gamify-specs` contains our code-based specification. It is used to validate the behavior of the API.

## Team

| Name                                   |                                  |
|----------------------------------------|----------------------------------|
| Matthieu Burguburu 					 | matthieu.burguburu@heig-vd.ch    |
| David Dupraz                           | david.dupraz@heig-vd.ch          |
| Alexandre Piveteau 				     | alexandre.piveteau@heig-vd.ch    |
| Guy-Laurent Subri                      | guy-laurent.subri@heig-vd.ch     |

## Running the service (locally)

Assuming you have Docker installed locally, you can run the following scripts to get the app running on your `8080` port :

```bash
sh ./build-image.sh
sh ./run-compose.sh
```

The Swagger docs will then be available on the [root endpoint](http://localhost:8080/).

## Verifying the service with Cucumber

You can run the Cucumber validation via Maven. You need to make sure the app is running on your `8080` port :

```
cd gamify-specs/
mvn clean test
```

The reports are also generated and published to [GitHub Pages](https://heig-amt.github.io/gamify/).

Here are some of the behaviors verified in our `63` test scenarios :

- [x] Applications [can't register twice](https://heig-amt.github.io/gamify/#i-can-not-register-twice), and [can access the API endpoints only if they provide their API token](https://heig-amt.github.io/gamify/#i-can-read-an-authentication-token). Password-based authentication [works properly for `/accounts` management](https://heig-amt.github.io/gamify/#i-can-login-with-a-registered-application);
- [x] Multiple applications can create [categories](https://heig-amt.github.io/gamify/#two-users-may-create-a-specific-category-with-the-same-name) with identical names;
- [x] [Aggregates work properly](https://heig-amt.github.io/gamify/#i-can-get-a-category-aggregate-with-three-users-and-page-size-of-1-on-the-first-page). We also check that the amount of points generated by the rules is correct; and
- [x] Rules [can only be created for existing categories](https://heig-amt.github.io/gamify/#can-t-create-a-new-rule-when-the-category-is-unknown).

### The case of JMeter (what doesn't work)

As we were [load testing our system](load-testing/Add%20events.jmx), we realized that the `/events` endpoint sometimes skips point additions. We could not reproduce the issue with Cucumber tests, so we're glad to have found this issue with JMeter.

## Production Deployments

A live version of our API is available on [Heroku](https://heig-amt-gamify.herokuapp.com). We're using a free plan, so it may need a few seconds to start up if the instance was previously paused :rocket:
