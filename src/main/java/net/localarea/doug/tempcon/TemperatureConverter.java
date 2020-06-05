/* TemperatureConverter.java
 * 
 * Author: Douglas Chidester
 * created February 14, 2013
 * 
 * This is an interface to use as a base for the other temperature converter classes.
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

/**
 * This interface provides methods to convert from one temperature base to the
 * desired temperature base.
 * 
 * @author Douglas Chidester
 *
 */
public interface TemperatureConverter
{
    /**
     * Convert from Fahrenheit to the desired unit.
     * @param fahrenheitTemp - temperature in units of degrees Fahrenheit.
     * @return the converted value
     */
    public double convertFromFahrenheit(double fahrenheitTemp);

    /**
     * Convert from Celsius to the desired unit.
     * @param celsiusTemp - temperature in units of degrees Celsius.
     * @return the converted value
     */
    public double convertFromCelsius(double celsiusTemp);

    /**
     * Convert from Kelvin to the desired unit.
     * @param kelvinTemp - temperature in units of Kelvin.
     * @return the converted value
     */
    public double convertFromKelvin(double kelvinTemp);
    
    /**
     * Convert from Rankine to the desired unit.
     * @param rankineTemp temperature in units of degrees Rankine.
     * @return the converted value
     */
    public double convertFromRankine(double rankineTemp);
}
