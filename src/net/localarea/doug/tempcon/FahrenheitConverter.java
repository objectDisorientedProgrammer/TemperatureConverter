/* FahrenheitConverter.java
 * 
 * Author: Douglas Chidester
 * created February 14, 2013
 * 
 * Converts from a temperature scale to the Fahrenheit scale.
 */

package net.localarea.doug.tempcon;

public class FahrenheitConverter implements TemperatureConverter
{
    public FahrenheitConverter()
    {
        super();
    }

    public double convertFromFahrenheit(double fahrenheitTemp)
    {
        return fahrenheitTemp;
    }

    public double convertFromCelsius(double celsiusTemp)
    {
        return (celsiusTemp * (9.0 / 5.0)) + 32.0;
    }

    public double convertFromKelvin(double kelvinTemp)
    {
        return (kelvinTemp * (9.0 / 5.0)) - 459.67;
    }
}