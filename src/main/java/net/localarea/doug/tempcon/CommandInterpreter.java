/**
 * @author Doug
 * @file CommandInterpreter.java
 */

package net.localarea.doug.tempcon;

import java.util.Arrays;

/**
 * A simple command line argument parser.
 */
public class CommandInterpreter
{
    private String[] copyrightFlags = {"-copyright", "--copyright"};
    private String[] helpFlags = {"-h", "-help", "--help"};
    // TODO Issue #18 private String[] licenseFlags = {"-license", "--license"};
    private String[] versionFlags = {"-v", "-version", "--version"};
    
    public CommandInterpreter(String[] commands)
    {
        if (commands.length == 1)
        {
            String arg = commands[0].toLowerCase();
            if (Arrays.asList(copyrightFlags).contains(arg))
            {
                System.out.println("Work in progress...");
            }
            else if (Arrays.asList(helpFlags).contains(arg))
            {
                printHelpText();
            }
            else if (Arrays.asList(versionFlags).contains(arg))
            {
                System.out.println("Version: v"+ConfigData.getInstance().getVersion());
            }
            else
            {
                System.err.println("Invalid option: " + arg);
            }
        }
        else
        {
            System.err.println("Error: too many arguments.");
        }
    }

    private void printHelpText()
    {
        String indent = "    ";
        System.out.println("Command line interface options:\n");
        System.out.println(createTextEntry(indent, copyrightFlags, "Show software license information"));
        System.out.println(createTextEntry(indent+indent, helpFlags, "Show this help message"));
        System.out.println(createTextEntry(indent, versionFlags, "Display application version number"));
    }
    
    private String createTextEntry(String indent, String[] options, String description)
    {
        StringBuilder sb = new StringBuilder();

        sb.append(indent);
        // Concatenate all valid flags
        for(String s : options)
            sb.append(s + " ");
        // visually separate the description from the flags
        sb.append(indent + description);

        return sb.toString();
    }
}
