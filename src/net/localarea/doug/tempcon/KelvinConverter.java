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
	@SuppressWarnings("unused")
	private double kelvin;
	
	public KelvinConverter()
	{
		super();
		kelvin = 0;
	}

	public double convertFromFahrenheit(double fromTemp)
	{
		return kelvin = (fromTemp + 459.67) * (5.0/9.0);
	}

	public double convertFromCelsius(double fromTemp)
	{
		return kelvin = fromTemp + 273.15;
	}

	public double convertFromKelvin(double fromTemp)
	{
		return kelvin = fromTemp;
	}
}