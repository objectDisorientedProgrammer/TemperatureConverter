# Temperature Converter
This desktop application converts between Kelvin, Celcius, Rankine, and Fahrenheit temperature scales.

Author Douglas Chidester

## Dependencies
* Java 11
* gradle 4.4.1+

## Building
This project uses Gradle as a build system.

To build:

    gradle build

To clean:

    gradle clean
    
Default jar output path is: `build/libs/`

## Running the program
You can use `gradle run` from a command line interface or any of the following options when executing the jar file directly.

#### Linux
Double click the .jar file to run the program.

If this does not work, you may need to make the file executable:

    chmod +x TemperatureConverter.jar

You can also run through the command line:
    
    java -jar TemperatureConverter.jar

#### Mac
See instructions for Linux.

#### Windows
Double click the .jar file to run the program.
