/* TemperatureConverter.java
 * 
 * Author: Douglas Chidester
 * created February 14, 2013
 * 
 * This is an interface to use as a base for the other temperature converter classes.
 * 
 * Last Update:
 * [6/12/13] - changed TemperatureConverter from an abstract class to an interface.
 */

package net.localarea.doug.tempcon;

public interface TemperatureConverter
{
	/**
	 * 
	 * @param fromTemp - number to convert
	 */
	public double convertFromFahrenheit(double fromTemp);

	/**
	 * 
	 * @param fromTemp - number to convert
	 */
	public double convertFromCelsius(double fromTemp);

	/**
	 * 
	 * @param fromTemp - number to convert
	 */
	public double convertFromKelvin(double fromTemp);
}