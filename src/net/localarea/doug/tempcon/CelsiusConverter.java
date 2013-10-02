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

	public double convertFromFahrenheit(double fromTemp)
	{
		return (fromTemp - 32.0) * (5.0/9.0);
	}

	public double convertFromCelsius(double fromTemp)
	{
		return fromTemp;
	}

	public double convertFromKelvin(double fromTemp)
	{
		return fromTemp - 273.15;
	}
}