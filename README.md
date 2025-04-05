# Temperature Converter
This desktop application converts between Celcius, Fahrenheit, Kelvin, and Rankine temperature scales. <!-- It may be run as a command line interface (CLI) or graphical user interface (GUI). -->

## Requirements
* Java 21+
* gradle 8.11

## Building
This project uses Gradle as a build system.

To build:

    ./gradlew build

To clean:

    ./gradlew clean
    
Default jar output path is: `build/libs/*.jar`

**Note:** if builds begin failing because unit tests get a 403 error, use `./gradlew -x test build`

## Running the program
Use gradle to run the program:

    ./gradlew run

#### Linux
Double click the .jar file to run the program. If this does not work, you may need to make the file executable:

    chmod +x TemperatureConverter.jar

You can also run through the command line:

    java -jar TemperatureConverter.jar

#### Mac
Should be similar to the instructions for Linux.

#### Windows
Double click the .jar file to run the program.


Created by objectDisorientedProgrammer
