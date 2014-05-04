/* Main.java
 * 
 * Author: Douglas Chidester
 * created February 14, 2013
 * finished February 14, 2013
 * 
 * A new attempt at a better temperature converter using a more object-oriented approach.
 * 
 * See Window.java for more documentation.
 */

package net.localarea.doug.tempcon;

import javax.swing.SwingUtilities;

public class Main
{
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable()
		{
            @Override
            public void run()
            {
            	new Window();
            }
        });
	}
}