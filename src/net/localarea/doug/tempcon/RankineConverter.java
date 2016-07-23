/* RankineConverter.java
 * 
 * Author: Douglas Chidester
 * created July 23, 2016
 * 
 * Converts from a temperature scale to the Rankine scale.
 * 
 * MIT License
 * 
 * Copyright (c) 2016 Douglas Chidester
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
 * @author Douglas Chidester
 */
public class RankineConverter implements TemperatureConverter {

	/* (non-Javadoc)
	 * @see net.localarea.doug.tempcon.TemperatureConverter#convertFromFahrenheit(double)
	 */
	@Override
	public double convertFromFahrenheit(double fahrenheitTemp) {
		return fahrenheitTemp + 459.67;
	}

	/* (non-Javadoc)
	 * @see net.localarea.doug.tempcon.TemperatureConverter#convertFromCelsius(double)
	 */
	@Override
	public double convertFromCelsius(double celsiusTemp) {
		return (celsiusTemp + 273.15) * (9.0 / 5.0);
	}

	/* (non-Javadoc)
	 * @see net.localarea.doug.tempcon.TemperatureConverter#convertFromKelvin(double)
	 */
	@Override
	public double convertFromKelvin(double kelvinTemp) {
		return kelvinTemp * ( 9.0 / 5.0);
	}

	/* (non-Javadoc)
	 * @see net.localarea.doug.tempcon.TemperatureConverter#convertFromRankine(double)
	 */
	@Override
	public double convertFromRankine(double rankineTemp) {
		return rankineTemp;
	}

}
