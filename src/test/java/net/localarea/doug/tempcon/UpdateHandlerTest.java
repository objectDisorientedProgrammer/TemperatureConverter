package net.localarea.doug.tempcon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class UpdateHandlerTest {
	UpdateHandler up;
	
	@BeforeEach
    void init() {
		up = new UpdateHandler("https://api.github.com/repos/objectDisorientedProgrammer/TemperatureConverter/tags");
    }

	@Test
	void testUpdateHandler() {
		assertNotNull(up);
	}

	@Test
	void testIsLatestVersion() {
		String current = up.getLatestVersionNumber();
		assertTrue(up.isLatestVersion(current));
		
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
		
		assertTrue(up.isLatestVersion(newer));
		assertFalse(up.isLatestVersion(older));
	}

	@Test
	void testGetLatestVersionNumber() {
		String version = up.getLatestVersionNumber();
		assertNotNull(version);
		assertEquals(3, version.split("\\.").length);
	}

	@Test
	void testGetUriVersionTag() {
		String tag = up.getUriVersionTag();
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
