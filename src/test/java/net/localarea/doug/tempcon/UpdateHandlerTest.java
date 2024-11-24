package net.localarea.doug.tempcon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class UpdateHandlerTest {
    String githubApi = "https://api.github.com/repos/objectDisorientedProgrammer/TemperatureConverter/tags";
	
	@BeforeEach
    void init() {
    }

	@Test
	void testUpdateHandler() {
		assertNotNull(UpdateHandler.getInstance());
	}

	@Test
	void testIsLatestVersion() {
	    assertTrue(UpdateHandler.getInstance().checkForUpdate(githubApi));
		String current = UpdateHandler.getInstance().getLatestVersionNumber();
		assertTrue(UpdateHandler.getInstance().isLatestVersion(current));
		
		String[] semver = current.split("\\.");
		int major = Integer.parseInt(semver[0]);
		int minor = Integer.parseInt(semver[1]);
		int patch = Integer.parseInt(semver[2]);
		
		String newer, older = null;
		newer = semver[0] +"."+ semver[1] +"."+ (patch+1);
		if (patch > 0)
			older = semver[0] +"."+ semver[1] +"."+ (patch-1);
		else if (minor > 0)
			older = semver[0] +"."+ (minor-1) +"."+ semver[2];
		else if (major > 0)
			older = (major-1) +"."+ semver[1] +"."+ semver[2];
		
		assertTrue(UpdateHandler.getInstance().isLatestVersion(newer));
		assertFalse(UpdateHandler.getInstance().isLatestVersion(older));
	}

	@Test
	void testGetLatestVersionNumber() {
	    assertTrue(UpdateHandler.getInstance().checkForUpdate(githubApi));
		String version = UpdateHandler.getInstance().getLatestVersionNumber();
		assertNotNull(version);
		assertEquals(3, version.split("\\.").length);
	}

	@Test
	void testGetUriVersionTag() {
	    assertTrue(UpdateHandler.getInstance().checkForUpdate(githubApi));
		String tag = UpdateHandler.getInstance().getUriVersionTag();
		assertNotNull(tag);
		assertEquals('v', tag.charAt(0));
		assertEquals(3, tag.split("\\.").length);
	}

	@Disabled
	@Test
	void testGetDownloadUri() {
		fail("Not yet implemented");
	}
}
