/* CelsiusConverter.java
 * 
 * Author: Douglas Chidester
 * created February 14, 2013
 * 
 * Converts from a temperature scale to the Celsius scale.
 */

package net.localarea.doug.tempcon;

public class CelsiusConverter implements TemperatureConverter
{
    public CelsiusConverter()
    {
        super();
    }

    public double convertFromFahrenheit(double fahrenheitTemp)
    {
        return (fahrenheitTemp - 32.0) * (5.0 / 9.0);
    }

    public double convertFromCelsius(double celsiusTemp)
    {
        return celsiusTemp;
    }

    public double convertFromKelvin(double kelvinTemp)
    {
        return kelvinTemp - 273.15;
    }
}