# TMS // JOS / KATAS / Spring Boot Testing / Session 3

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

