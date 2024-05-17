# NationalWeatherServiceWrapper
A Wrapper api that reports weather from the National Weather Service 

## Requirments
JDK 17+, Gradle

## Quick Start
install gradle https://gradle.org/install/

check for gradle version
```gradle -v```

install JDK 17+ https://openjdk.java.net/install/

or

install JDK 17 with Homebrew package manager
```brew install openjdk@17```

check for jdk version
```java -version```

##### Install
```gradle install```

##### Build
```gradle build```

##### Run
```./gradlew bootRun```
## REST API

##### endpoints

GET - Get weather for given location by latitude and longitude

```localhost:8080/weather/highlow?{latitude}&{longitude}```

*Latitude and longitude are required parameters and must be numeric values.

