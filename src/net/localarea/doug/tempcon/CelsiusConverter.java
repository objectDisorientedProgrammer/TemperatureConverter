/* CelsiusConverter.java
 * 
 * Author: Douglas Chidester
 * created February 14, 2013
 * 
 * Converts from a temperature scale to the Celsius scale.
 * 
 * MIT License
 * 
 * Copyright (c) 2013 Douglas Chidester
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package net.localarea.doug.tempcon;

public class CelsiusConverter implements TemperatureConverter
{
	/**
	 * CelsiusConverter provides methods used to convert a temperature to
	 * celsius.
	 * 
	 * @see TemperatureConverter
	 */
    public CelsiusConverter()
    {
        super();
    }

    /**
     * @see TemperatureConverter#convertFromFahrenheit(double)
     */
    public double convertFromFahrenheit(double fahrenheitTemp)
    {
        return (fahrenheitTemp - 32.0) * (5.0 / 9.0);
    }

    /**
     * @see TemperatureConverter#convertFromCelsius(double)
     */
    public double convertFromCelsius(double celsiusTemp)
    {
        return celsiusTemp;
    }

    /**
     * @see TemperatureConverter#convertFromKelvin(double)
     */
    public double convertFromKelvin(double kelvinTemp)
    {
        return kelvinTemp - 273.15;
    }
}