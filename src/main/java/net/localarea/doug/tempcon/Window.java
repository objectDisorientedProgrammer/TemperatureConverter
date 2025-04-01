/* Window.java
 * 
 * Author: Douglas Chidester
 * created February 14, 2013
 * 
 * A JFrame with UI to convert between temperature scales. This window is where the logic is used.
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

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URI;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    private final static String applicationName = "Temperature Converter";
    private final String author = "Douglas Chidester";
    private final int frameWidth = 340;
    private final int frameHeight = 140;
    private JPanel comboboxPanel;
    private JPanel textFieldPanel;
    
    private DecimalFormat formatter;
    private String precision = "#.##";   // number of decimal places
    private double temperature = 11.0;
    
    private final String celsius = "Celsius";
    private final String fahrenheit = "Fahrenheit";
    private final String kelvin = "Kelvin";
    private final String rankine = "Rankine";
    private String choices[] = { celsius, fahrenheit, kelvin, rankine };
    
    private String gettingStartedMsg = "Input a temperature then press " +
            "the enter key.\nYou can select different temperature scales from the drop down menu.";
    
    private String licenseString = "MIT License\n\nCopyright (c) 2013 " + author + "\n\n" +
            "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
            "of this software and associated documentation files (the \"Software\"), to deal\n" +
            "in the Software without restriction, including without limitation the rights\n" +
            "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
            "copies of the Software, and to permit persons to whom the Software is\n" +
            "furnished to do so, subject to the following conditions:\n\n" +
            "The above copyright notice and this permission notice shall be included in all\n" +
            "copies or substantial portions of the Software.\n\n" +
            "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
            "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
            "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
            "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
            "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
            "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
            "SOFTWARE.";
    
    TempChangeListener tcl;
    // GUI components
    private JTextField temperatureInputTF;
    private JTextField temperatureResultTF;
    private JComboBox<String> fromTemperature;
    private JComboBox<String> toTemperature;
    private JButton equalsBtn;
    private JButton swapConversions;
    private JMenuItem menuItemAbout;
    private JMenuItem menuItemExit;
    private JMenuItem menuItemGettingStarted;
    
    // Images
    String imagePath = "/images/";  // path in jar file
    
    public Window()
    {
        super(applicationName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null); // set frame location to center of screen
        setMinimumSize(new Dimension(frameWidth-40, frameHeight-20));
        
        formatter = new DecimalFormat(precision);
        
        // add GUI components
        createGUI();
        addComponentsToPanel();
        createMenubar();
        updateResultTF(); // calculate and update initial values
        pack();
        setVisible(true); // display
    }
    
    public JFrame getMainWindow()
    {
        return this;
    }

    /**
     * Add all GUI elements to the panel and the panel to the frame.
     */
    private void addComponentsToPanel()
    {
        int vSpacing = 5;
        int hSpacing = 5;

        // add padding around each component in each "row"
        Dimension componentPadding = new Dimension(hSpacing, 0);
        comboboxPanel.add(Box.createRigidArea(componentPadding));
        comboboxPanel.add(fromTemperature);
        comboboxPanel.add(Box.createRigidArea(componentPadding));
        comboboxPanel.add(swapConversions);
        comboboxPanel.add(Box.createRigidArea(componentPadding));
        comboboxPanel.add(toTemperature);
        comboboxPanel.add(Box.createRigidArea(componentPadding));
        
        textFieldPanel.add(Box.createRigidArea(componentPadding));
        textFieldPanel.add(temperatureInputTF);
        textFieldPanel.add(Box.createRigidArea(componentPadding));
        textFieldPanel.add(equalsBtn);
        textFieldPanel.add(Box.createRigidArea(componentPadding));
        textFieldPanel.add(temperatureResultTF);
        textFieldPanel.add(Box.createRigidArea(componentPadding));

        // add padding around each "column"
        // This creates the desired spacing but only resizes components in the
        // textFieldPanel when the JFrame is resized...
        Dimension panelPadding = new Dimension(hSpacing, vSpacing);
        this.getContentPane().add(Box.createRigidArea(panelPadding));
        this.getContentPane().add(textFieldPanel);
        this.getContentPane().add(Box.createRigidArea(panelPadding));
        this.getContentPane().add(comboboxPanel);
        this.getContentPane().add(Box.createRigidArea(panelPadding));
    }

    /**
     * Create all GUI elements.
     */
    private void createGUI()
    {
        // make the JFrame's layout define "rows"
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // make "columns" for additional components
        comboboxPanel = new JPanel(); // holds combobox components
        comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.X_AXIS));

        textFieldPanel = new JPanel(); // holds textfield components
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.X_AXIS));
        
        tcl = new TempChangeListener();
        
        Font uiFont = new Font(Font.DIALOG, Font.BOLD, 14);
        
        // temperatureInputTF
        temperatureInputTF = new JTextField(10);
        temperatureInputTF.setText("" + temperature);
        temperatureInputTF.setHorizontalAlignment(JTextField.CENTER);
        temperatureInputTF.addActionListener(tcl);
        temperatureInputTF.setFont(uiFont);
        
        // temperatureResultTF
        temperatureResultTF = new JTextField(10);
        temperatureResultTF.setEditable(false);
        temperatureResultTF.setHorizontalAlignment(JTextField.CENTER);
        temperatureResultTF.setFont(uiFont);
        
        // add comboboxes
        fromTemperature = new JComboBox<String>(choices);
        fromTemperature.setEditable(false);
        fromTemperature.setSelectedItem(choices[0]);
        fromTemperature.setMaximumRowCount(choices.length); // display all options
        fromTemperature.addActionListener(tcl);
        // center text
        ((JLabel)fromTemperature.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        fromTemperature.setFont(uiFont);
        
        toTemperature = new JComboBox<String>(choices);
        toTemperature.setEditable(false);
        toTemperature.setSelectedItem(choices[1]);
        toTemperature.setMaximumRowCount(choices.length); // display all options
        toTemperature.addActionListener(tcl);
        // center text
        ((JLabel)toTemperature.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        toTemperature.setFont(uiFont);

        // equals button
        equalsBtn = new JButton(new ImageIcon(this.getClass().getResource(imagePath+"equals.png")));
        equalsBtn.addActionListener(tcl);

        // swap conversions button
        swapConversions = new JButton(new ImageIcon(this.getClass().getResource(imagePath+"swap.png")));
        swapConversions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int tmp = fromTemperature.getSelectedIndex();
                fromTemperature.setSelectedItem(choices[toTemperature.getSelectedIndex()]);
                toTemperature.setSelectedItem(choices[tmp]);
            }
        });
    }
    
    /**
     * Class to handle changing temperature scales.
     */
    private class TempChangeListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) {
            temperature = Float.parseFloat(temperatureInputTF.getText().toString());
            // sanatize input
            if (temperature == -0)
            {
                temperature = 0;
                temperatureInputTF.setText(temperature +"");
            }
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
        
        menuItemExit = new JMenuItem("Exit",
                new ImageIcon(this.getClass().getResource(imagePath+"exit.png")));
        menuItemExit.setMnemonic(KeyEvent.VK_E);
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // close program if user clicks: File -> Exit
            }
        });
        
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        JMenuItem menuItemUpdate = new JMenuItem("Check for updates",
                new ImageIcon(this.getClass().getResource(imagePath+"update.png")));
        menuItemUpdate.setMnemonic(KeyEvent.VK_C);
        menuItemUpdate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Set up a REST GET query to the github API
                String urlCommon = "objectDisorientedProgrammer/"+applicationName.replaceAll("\\s+","")+"/";
                String urlBase = "https://api.github.com/repos/" + urlCommon;
                boolean updateAvailable = UpdateHandler.getInstance().checkForUpdate(urlBase + "tags");
                String version = ConfigData.getInstance().getVersion();
                
                if (updateAvailable && !UpdateHandler.getInstance().isLatestVersion(version))
                {
                	// Create a fancy panel to show current and new versions along with a
                    // button to take the user to the download page
                    JPanel update = new JPanel();
                    update.setLayout(new BoxLayout(update, BoxLayout.Y_AXIS));
                    JLabel curver = new JLabel("Current version: " + version);
                    curver.setAlignmentX(Component.CENTER_ALIGNMENT);
                    update.add(curver);

                    JLabel newver = new JLabel("New version: " + UpdateHandler.getInstance().getLatestVersionNumber());
                    newver.setAlignmentX(Component.CENTER_ALIGNMENT);
                    update.add(newver);

                    update.add(new JLabel(" ")); // poor man's padding

                    // TODO replace image?
                    JButton download = new JButton(
                            new ImageIcon(this.getClass().getResource(imagePath+"update.png")));
                    download.setAlignmentX(Component.CENTER_ALIGNMENT);
                    download.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            try {
                                final String programName = applicationName.replaceAll("\\s+","");
                                final String dl = "https://www.github.com/" + urlCommon +
                                        "releases/download/" + UpdateHandler.getInstance().getUriVersionTag() + "/"
                                        + /*TemperatureConverter.*/programName + ".jar";
                                //TODO for testing System.out.println("[DOWNLOAD LINK] >" + dl + "<");
                                Desktop.getDesktop().browse(new URI(dl));
                            } catch (Exception e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage(), "URL ERROR",
                                        JOptionPane.ERROR_MESSAGE, null);
                            }
                        }
                    });
                    update.add(download);
                    update.add(new JLabel(" ")); // poor man's padding

                    // Display the update message window
                    Object[] options = { "Close" };
                    JOptionPane.showOptionDialog(null, update, "Update Available", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                }
                else
                {
                	// Program is up to date - inform the user
                    Object[] opt = { "Great" };
                    JOptionPane.showOptionDialog(null, "Version: "+ version, "Up to date",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, opt[0]);
                }
            }
        });
        
        menuItemGettingStarted = new JMenuItem("Getting Started",
                new ImageIcon(this.getClass().getResource(imagePath+"help.png")));
        menuItemGettingStarted.setMnemonic(KeyEvent.VK_G);
        menuItemGettingStarted.setToolTipText("Basic useage instructions");
        menuItemGettingStarted.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // show basic use instructions if user clicks: Help -> Getting Started
                JOptionPane.showMessageDialog(getMainWindow(), gettingStartedMsg, "Getting Started",
                        JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(this.getClass().getResource(imagePath+"help64.png")));
            }
        });
        
        JMenuItem menuItemLicense = new JMenuItem("License");
        menuItemLicense.setMnemonic(KeyEvent.VK_L);
        menuItemLicense.setToolTipText("Display software license");
        menuItemLicense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(getMainWindow(), licenseString, "License", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        menuItemAbout = new JMenuItem("About", new ImageIcon(this.getClass().getResource(imagePath+"about.png")));
        menuItemAbout.setMnemonic(KeyEvent.VK_A);
        menuItemAbout.setToolTipText("About this program");
        menuItemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // show credits & version if user clicks: Help -> About
                JOptionPane.showMessageDialog(getMainWindow(), "Created by " + author + "\nVersion " + ConfigData.getInstance().getVersion(), "About",
                        JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon(this.getClass().getResource(imagePath+"person.png")));
            }
        });
        
        // add file menu
        menuBar.add(fileMenu);
        fileMenu.add(menuItemExit);
        
        // add help menu
        menuBar.add(helpMenu);
        helpMenu.add(menuItemGettingStarted);
        helpMenu.addSeparator();
        helpMenu.add(menuItemUpdate);
        helpMenu.add(menuItemLicense);
        helpMenu.add(menuItemAbout);
    }
    
    /**
     * Calculate the appropriate temperature and display it in the result textfield.
     */
    private void updateResultTF()
    {
        TemperatureConverter tempCon = null;
        
        // determine which temperature to convert to
        switch(choices[toTemperature.getSelectedIndex()])
        {
            case fahrenheit:
                tempCon = new FahrenheitConverter();
                break;
            case celsius:
                tempCon = new CelsiusConverter();
                break;
            case kelvin:
                tempCon = new KelvinConverter();
                break;
            case rankine:
                tempCon = new RankineConverter();
                break;
            default:
                // this should never happen
                break;
        }
        
        if(tempCon != null)
        {
            // determine which temperature to convert from
            switch(choices[fromTemperature.getSelectedIndex()])
            {
            case fahrenheit:
                temperatureResultTF.setText(formatter.format(tempCon.convertFromFahrenheit(temperature)));
                break;
            case celsius:
                temperatureResultTF.setText(formatter.format(tempCon.convertFromCelsius(temperature)));
                break;
            case kelvin:
                temperatureResultTF.setText(formatter.format(tempCon.convertFromKelvin(temperature)));
                break;
            case rankine:
                temperatureResultTF.setText(formatter.format(tempCon.convertFromRankine(temperature)));
                break;
            default:
                // this should never happen
                break;
            }
        }
    }
}

