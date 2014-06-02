/* Window.java
 * 
 * Author: Douglas Chidester
 * created February 14, 2013
 * 
 * A JFrame with UI to convert between temperature scales. This window is where the logic is used.
 */

package net.localarea.doug.tempcon;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Window extends JFrame
{
	// variables
	private static final String applicationName = "Temperature Converter";
	private static final String version = "2.16.1";
	private final static String author = "Douglas Chidester";
	private static int frameWidth = 345;
	private static int frameHeight = 160;
	private JPanel mainPanel;
	
	private TemperatureConverter tempCon;
	
	private DecimalFormat formatter;
	private String precision = "#.#####";	// number of decimal places
	private double temperature = 0.0;
	
	private String choices[] = {"Farenheit", "Celcius", "Kelvin"};
	private String gettingStartedMsg = "Enter a temperature to convert, then press " +
			"the enter\nkey or select a different temperature scale.";
	
	// GUI components
	private JTextField temperatureInputTF;
	private JTextField temperatureResultTF;
	private JLabel fromLbl;
	private JLabel toLbl;
	private JComboBox<String> fromTemperature;
	private JComboBox<String> toTemperature;
	private JMenuItem menuItemAbout;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemGettingStarted;
	
	// Images
	String imagePath = "/images/";	// path in jar file
	
	public Window()
	{
		super(applicationName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(frameWidth, frameHeight);
		setLocationRelativeTo(null); 		// set frame location to center of screen
		
		formatter = new DecimalFormat(precision);
		
		// add GUI components
		createAndShowGUI();
		createMenubar();
        //pack();
        setVisible(true);	// display
	}

	/**
	 * Create all GUI elements and add them to mainPanel.
	 */
	private void createAndShowGUI()
	{
		int rows = 3;
		int columns = 2;
		int vSpacing = 5;
		int hSpacing = 5;
		mainPanel = new JPanel(new GridLayout(rows, columns, vSpacing, hSpacing));
		
		TempChangeListener tcl = new TempChangeListener();
		
		// label to go with temperatureInputTF
		fromLbl = new JLabel("From", null, JLabel.CENTER); 
		mainPanel.add(fromLbl);
		
		// label to go with temperatureResultTF
		toLbl = new JLabel("To", null, JLabel.CENTER); 
		mainPanel.add(toLbl);
		
		// temperatureInputTF
		temperatureInputTF = new JTextField(8);
		temperatureInputTF.setText("" + 0.0f);
		temperatureInputTF.setHorizontalAlignment(JTextField.CENTER);
		temperatureInputTF.addActionListener(tcl);
		mainPanel.add(temperatureInputTF);
		
		// temperatureResultTF
		temperatureResultTF = new JTextField(10);
		temperatureResultTF.setText("-17.77778");
		temperatureResultTF.setEditable(false);
		temperatureResultTF.setHorizontalAlignment(JTextField.CENTER);
		mainPanel.add(temperatureResultTF);
		
		// add comboboxes
		fromTemperature = new JComboBox<String>(choices);
		fromTemperature.setEditable(false);
        fromTemperature.setSelectedItem(choices[0]);
        fromTemperature.setMaximumRowCount(3);
        fromTemperature.addActionListener(tcl);
        mainPanel.add(fromTemperature);
        
        toTemperature = new JComboBox<String>(choices);
        toTemperature.setEditable(false);
        toTemperature.setSelectedItem(choices[1]);
        toTemperature.setMaximumRowCount(3);
        toTemperature.addActionListener(tcl);
        mainPanel.add(toTemperature);
        
        this.add(mainPanel);
	}
	
	/**
	 * Class to handle changing temperature scales.
	 * @author doug
	 *
	 */
	private class TempChangeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			temperature = Float.parseFloat(temperatureInputTF.getText().toString());
			updateResultTF();
		}
	}
	
	/**
	 * Create and add a menu bar to the frame.
	 */
	private void createMenubar()
	{
		JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);
        
        menuItemExit = new JMenuItem("Exit", new ImageIcon(this.getClass().getResource(imagePath+"exit.png")));
        menuItemExit.setMnemonic(KeyEvent.VK_E);
        menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // close program if user clicks: File -> Exit
			}
		});
        fileMenu.add(menuItemExit);
        
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(helpMenu);
        
        menuItemGettingStarted = new JMenuItem("Getting Started", new ImageIcon(this.getClass().getResource(imagePath+"help.png")));
        menuItemGettingStarted.setMnemonic(KeyEvent.VK_G);
        menuItemGettingStarted.setToolTipText("Basic useage instructions");
        menuItemGettingStarted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// show basic use instructions if user clicks: Help -> Getting Started
				JOptionPane.showMessageDialog(null, gettingStartedMsg, "Getting Started",
						JOptionPane.PLAIN_MESSAGE, new ImageIcon(this.getClass().getResource(imagePath+"help64.png")));
			}
		});
        helpMenu.add(menuItemGettingStarted);
        
        menuItemAbout = new JMenuItem("About", new ImageIcon(this.getClass().getResource(imagePath+"about.png")));
        menuItemAbout.setMnemonic(KeyEvent.VK_A);
        menuItemAbout.setToolTipText("About this program");
        menuItemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// show credits & version if user clicks: Help -> About
				JOptionPane.showMessageDialog(null, "Created by " + author + "\nVersion " + version, "About",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(this.getClass().getResource(imagePath+"person.png")));
			}
		});
        helpMenu.add(menuItemAbout);
	}
	
	/**
	 * Calculate the appropriate temperature and display it in the result textfield.
	 */
	private void updateResultTF()
	{
		// To farenheit cases
		tempCon = new FahrenheitConverter();
		// case of						farenheit				to				farenheit
		if(fromTemperature.getSelectedItem().equals(choices[0]) && toTemperature.getSelectedItem().equals(choices[0]) )
			temperatureResultTF.setText(formatter.format(tempCon.convertFromFahrenheit(temperature)));
		// case of						celcius					to				farenheit
		if(fromTemperature.getSelectedItem().equals(choices[1]) && toTemperature.getSelectedItem().equals(choices[0]) )
			temperatureResultTF.setText(formatter.format(tempCon.convertFromCelsius(temperature)));
		// case of						Kelvin					to				farenheit
		if(fromTemperature.getSelectedItem().equals(choices[2]) && toTemperature.getSelectedItem().equals(choices[0]) )
			temperatureResultTF.setText(formatter.format(tempCon.convertFromKelvin(temperature)));

		// To celcius cases
		tempCon = new CelsiusConverter();
		// case of						farenheit				to				celcius
		if(fromTemperature.getSelectedItem().equals(choices[0]) && toTemperature.getSelectedItem().equals(choices[1]) )
			temperatureResultTF.setText(formatter.format(tempCon.convertFromFahrenheit(temperature)));
		// case of						celcius					to				celcius
		if(fromTemperature.getSelectedItem().equals(choices[1]) && toTemperature.getSelectedItem().equals(choices[1]) )
			temperatureResultTF.setText(formatter.format(tempCon.convertFromCelsius(temperature)));
		// case of						Kelvin					to				celcius
		if(fromTemperature.getSelectedItem().equals(choices[2]) && toTemperature.getSelectedItem().equals(choices[1]) )
			temperatureResultTF.setText(formatter.format(tempCon.convertFromKelvin(temperature)));

		// To Kelvin cases
		tempCon = new KelvinConverter();
		// case of						farenheit				to				Kelvin
		if(fromTemperature.getSelectedItem().equals(choices[0]) && toTemperature.getSelectedItem().equals(choices[2]) )
			temperatureResultTF.setText(formatter.format(tempCon.convertFromFahrenheit(temperature)));
		// case of						celcius					to				Kelvin
		if(fromTemperature.getSelectedItem().equals(choices[1]) && toTemperature.getSelectedItem().equals(choices[2]) )
			temperatureResultTF.setText(formatter.format(tempCon.convertFromCelsius(temperature)));
		// case of						Kelvin					to				Kelvin
		if(fromTemperature.getSelectedItem().equals(choices[2]) && toTemperature.getSelectedItem().equals(choices[2]) )
			temperatureResultTF.setText(formatter.format(tempCon.convertFromKelvin(temperature)));
	}
}
