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

/**
 * Interface for use as an abstract base class.
 * @author Doug
 *
 */
public interface TemperatureConverter
{
    /**
     * @param fahrenheitTemp - temperature in units of degrees Fahrenheit.
     */
    public double convertFromFahrenheit(double fahrenheitTemp);

    /**
     * @param celsiusTemp - temperature in units of degrees Celsius.
     */
    public double convertFromCelsius(double celsiusTemp);

    /**
     * @param kelvinTemp - temperature in units of Kelvin.
     */
    public double convertFromKelvin(double kelvinTemp);
}
