# TMS // JOS / KATAS / Spring Boot Testing / Session 3

![Java CI with Maven](https://github.com/tblasCode/spring-boot-testing-v2/workflows/Java%20CI%20with%20Maven/badge.svg)

```gherkin
Feature: EUR-USD Conversor using the best Rate 
Background: The provider has an agreement with few REST API
Scenario: Consume the APIs in a Happy path scenario
    Given an amount of Euros as input
    When call endpoint GET /convert/eur/usd/{amount}
    Then ask rate for: https://api.frankfurter.app/latest 
    And  ask rate for: https://api.ratesapi.io/api/latest 
    And  ask rate for: https://api.exchangeratesapi.io/latest 
    And  use the best rate for USD  
    Then return {amount}*rate
```

### Requirements

For building and running the application you need:

- [Java 11.07](https://www.oracle.com/technetwork/java/javase/downloads/).

### How to run
If you use Maven, run the following command in a terminal window (in the complete directory):

```bash
mvn spring-boot:run
```

### How To launch Tests
If you use Maven, run the following command in a terminal window (in the complete directory):

```bash
mvn clean test
```

### How To launch Checkstyle
If you use Maven, run the following command in a terminal window (in the complete directory):

```bash
mvn checkstyle:checkstyle
```

### How To launch PMD
If you use Maven, run the following command in a terminal window (in the complete directory):

```bash
mvn pmd:check

mvn mvn pmd:pmd
```

### How To launch Spotbugs
If you use Maven, run the following command in a terminal window (in the complete directory):

```bash
mvn spotbugs:check

mvn spotbugs:spotbugs

mvn spotbugs:gui
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate :).


## References:

https://cucumber.io/docs/gherkin/reference/
https://spring.io/guides/gs/async-method/
https://www.baeldung.com/spring-async
https://dzone.com/articles/spring-boot-async-methods
https://www.baeldung.com/java-completablefuture
https://www.baeldung.com/java-9-completablefuture
https://grokonez.com/java/java-8/java-8-multiple-completablefutures
https://dzone.com/articles/20-examples-of-using-javas-completablefuture
https://stackoverflow.com/questions/47025206/java-collecting-results-of-completablefuture-from-multiple-calls
https://levelup.gitconnected.com/completablefuture-a-new-era-of-asynchronous-programming-86c2fe23e246
https://dzone.com/articles/using-optional-correctly-is-not-optional
https://www.baeldung.com/java-9-optional
