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
	@SuppressWarnings("unused")
	private double degreesF;

	public FahrenheitConverter()
	{
		super();
		degreesF = 0;
	}

	public double convertFromFahrenheit(double fromTemp)
	{
		return degreesF = fromTemp;
	}

	public double convertFromCelsius(double fromTemp)
	{
		return degreesF = (fromTemp * (9.0/5.0)) + 32.0;
	}

	public double convertFromKelvin(double fromTemp)
	{
		return degreesF = (fromTemp * (9.0/5.0)) - 459.67;
	}
}