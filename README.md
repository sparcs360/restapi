# restapi
![](https://img.shields.io/badge/release-0.0.1--SNAPSHOT-green.svg)

A simple REST API built with Java 8 on top of the [Spring Framework](https://spring.io/).

The exposed API is almost identical to the one at [**http://skybettechtestapi.herokuapp.com/**](http://skybettechtestapi.herokuapp.com/) except that odds are in decimal format rather than fractional.

- - -
# Build with Maven

I'm using [v3.3.9](https://maven.apache.org/download.cgi).

## Module(s)

| Name       | Description | Dependencies                |
|:---------- |:------------|:----------------------------|
| **betapi** | Betting API | Spring Boot (1.4.1.RELEASE) |

## Build Instructions

Make the ```betapi``` directory the current directory and then **compile**, **test** and **package** using the ubiquitous:

```bash
$ mvn clean install
```

- - -
# Starting the API

From the ```betapi``` directory, execute the following command to start an instance on port 10000:

```bash
$ java -jar target/betapi-0.0.1-RELEASE.jar
```

# Consuming the API

Use your favourite HTTP client...

| Verb | URI        | Description                       | Example body |
|:-----|:-----------|:----------------------------------|:----------------------------------------------|
| GET  | /available | Return the list of available bets | n/a                                           |
| POST | /bets      | Place a bet!                      | `{ "bet_id": 1, "odds": 11.0, "stake": 100 }` |
