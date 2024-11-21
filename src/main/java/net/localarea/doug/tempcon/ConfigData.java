/**
 * @file ConfigData.java
 * @author Doug
 */
package net.localarea.doug.tempcon;

import java.io.InputStream;
import java.util.jar.Manifest;

/**
 * Stores application data.
 */
public class ConfigData
{
	private static ConfigData instance;
	private String version;

	private ConfigData()
	{
		// initialize data
		version = readVersionFromManifest();
	}

	public static ConfigData getInstance()
	{
		if (instance == null)
			instance = new ConfigData();
		return instance;
	}

	/**
	 * Get the application version number.
	 * @return semver string (X.Y.Z) or null
	 */
	public String getVersion()
	{
		return version;
	}

	private String readVersionFromManifest()
	{
		try {
            // Get the path to the JAR file or the current project JAR
            InputStream manifestStream = Main.class.getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");

            if (manifestStream == null) {
                System.err.println("Manifest file not found.");
                return null;
            }

            // Load the manifest
            Manifest manifest = new Manifest(manifestStream);

            // Read the version from the manifest
            String version = manifest.getMainAttributes().getValue("Implementation-Version");

            if (version == null)
                System.err.println("`Implementation-Version` not specified in MANIFEST.MF");

            return version;

        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
}
