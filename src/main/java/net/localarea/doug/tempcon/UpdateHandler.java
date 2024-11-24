/**
 * @author Doug
 * @file UpdateHandler.java
 */
package net.localarea.doug.tempcon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Handles checking for updates.
 */
public class UpdateHandler
{
	private static UpdateHandler instance;
	private ArrayList<String> versionList;
	private String errorVersion = "0.0.0";
	
	private UpdateHandler()
	{
		versionList = new ArrayList<String>();
	}
	
	public static UpdateHandler getInstance()
	{
		if (instance == null)
			instance = new UpdateHandler();
		return instance;
	}
	
	public boolean checkForUpdate(String uri)
	{
		ExecutorService exec = Executors.newFixedThreadPool(2);
		
		CompletableFuture<String> fetchVersions = CompletableFuture.supplyAsync(()-> {
			try
			{
				URI tags = new URI(uri);
				HttpURLConnection conn = (HttpURLConnection) tags.toURL().openConnection();
		        conn.setRequestMethod("GET");
		        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK)
		        {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            StringBuffer response = new StringBuffer();
		            String line = null;
		            while((line = in.readLine()) != null)
		            {
		                response.append(line);
		            }
		            in.close();
		            return response.toString();
		        }
		        else
		        {
		            System.err.println("ERROR got response: " + conn.getResponseCode() + "\n\t"+conn.getResponseMessage());
		        }
		        return errorVersion;
			}
			catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return errorVersion;
		}, exec);

		CompletableFuture<String> processJson = fetchVersions.thenApply(json -> {
			if (json != null && !json.equals(errorVersion))
			{
				collectVersions(json);
				return "DONE";
			}
			else
				System.out.println("failed retrieving JSON");
			return "FAIL";
		});

		try {
			if (!processJson.get().equals("DONE"))
			{
			    exec.shutdown();
			    versionList.add(errorVersion);
			}
			else
			{
			    exec.shutdown();
			    // sort descending (highest version first)
		        versionList.sort((v1, v2) -> v2.compareTo(v1));
			    return true;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Compare a version number with the most recent release.
	 * @param currentVersion - X.Y.Z style version number
	 * @return true if currentVersion is the newest; Otherwise false.
	 */
	public boolean isLatestVersion(String currentVersion)
	{
	    if (currentVersion != null)
	        return currentVersion.compareTo(versionList.getFirst()) < 0 ? false : true;
	    return false;
	}
	
	public String getLatestVersionNumber()
	{
		return versionList.getFirst();
	}
	
	public String getUriVersionTag()
	{
		return "v" + versionList.getFirst();
	}
	
	public String getDownloadUri(String jarfileName)
	{
		return null;
	}
	
	/**
	 * Parse through a JSON blob, retrieve version numbers, and normalize them.
	 * @note Version number formats may vary (v2.15, v2.16.1, 2.17.2); this method
	 * 		normalizes them.
	 * @param json - JSON blob to parse
	 */
	private void collectVersions(String json)
	{
		for (int ver = json.indexOf("name"); ver != -1; ver = json.indexOf("name"))
        {
			// parse version number
			json = json.substring(ver);
			json = json.substring(json.indexOf('"')+1);
	        json = json.substring(json.indexOf('"')+1);
	        // sanitize version numbers to the same format
	        int offset = Character.isLetter(json.charAt(0)) ? 1 : 0; // TODO need to retain this info for when creating the download link
	        String v = json.substring(offset, json.indexOf('"')).trim();
	        // make sure version number is X.Y.Z format and add it to the list
	        versionList.add(enforceVersionFormat(v));
        }
	}
	
	/**
	 * Create a version number of the form: X.Y.Z
	 * @note Version string will be arbitrarily shortened or lengthened using.
	 * 		parts from the existing string.
	 * @param version - existing version string
	 * @return version number conforming to the X.Y.Z standard
	 */
	private String enforceVersionFormat(String version)
	{
		String[] parts = version.split("\\.");
		if (parts.length == 3)
			return version;
		else if (parts.length > 3)
		{
			version = parts[0] + parts[1] + parts[2];
		}
		else // < 3
		{
			version += ".0";
			return enforceVersionFormat(version);
		}
		return version;
	}
	
	/* TODO
	protected class SemanticVersion
	{
		public int major = 0;
		public int minor = 0;
		public int patch = 0;
		public String version = null;
		private String originalVersion = null;
		
		public SemanticVersion()
		{
			super();
			version = major + "." + minor + "." + patch;
		}
		public SemanticVersion(int x, int y, int z)
		{
			super();
			major = x;
			minor = y;
			patch = z;
		}
	}
	*/
}
