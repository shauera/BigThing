# Next Big Thing

## General
This "library" is written in Java and requires JDK 1.8.

Use the hints in Build section to explore what is currently supported. Currently the unit tests will pass. However the library will not work since the function that fetches actual rates was not implemented (was not in the requirements).

Adding operations to the calculator like multiple of amounts by quantity will simply require an addition of one function in the Calculator.java file.

Adding "support" for other countries requires adding enum values to i18n/Countries enumeration. The added vaules must exist in he actual values fetched from a rates provider. About 30 countries can be potentially supported.

### Limitations
- Big numbers and overflows were completely ignored
- Rounding of currencies was completely ignored

### Options for further development
- Exposing the functionality using a REST framework such as Jersey
- Add JSON and YML support
- Implement actual rates fetching in model/RatesFetcher.java
- Deploy library in a docker image

## Build
Run the tests with:
```sh
./gradlew test
```
Compile the library:
```sh
./gradlew compile
```
Build a deploy-able jar file:
```sh
./gradlew jar
```