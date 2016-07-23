/* KelvinConverter.java
 * 
 * Author: Douglas Chidester
 * created February 14, 2013
 * 
 * Converts from a temperature scale to the Kelvin scale.
 */

package net.localarea.doug.tempcon;

public class KelvinConverter implements TemperatureConverter
{
    public KelvinConverter()
    {
        super();
    }

    public double convertFromFahrenheit(double fahrenheitTemp)
    {
        return (fahrenheitTemp + 459.67) * (5.0 / 9.0);
    }

    public double convertFromCelsius(double celsiusTemp)
    {
        return celsiusTemp + 273.15;
    }

    public double convertFromKelvin(double kelvinTemp)
    {
        return kelvinTemp;
    }
}