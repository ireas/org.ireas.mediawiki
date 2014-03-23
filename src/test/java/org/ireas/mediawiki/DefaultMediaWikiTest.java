package org.ireas.mediawiki;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.TimeZone;

import org.ireas.mediawiki.data.UserData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultMediaWikiTest {

	private MediaWiki mediaWiki;
	
	@Before
	public void setUp() throws Exception {
		MediaWikiConfiguration configuration = new BasicMediaWikiConfiguration("org.ireas.mediawiki.test");
		MediaWikiFactory.setConfiguration(configuration);
		mediaWiki = MediaWikiFactory.newWikipediaInstance("de");
	}

	@After
	public void tearDown() throws Exception {
		MediaWikiUtils.close(mediaWiki);
	}

	@Test
	public void testGetUserData() throws MediaWikiException {
		UserData userData = mediaWiki.getUserData("Ireas");
		Assert.assertEquals("Ireas", userData.getUserName());
		Assert.assertEquals(336793, userData.getUserId());
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.set(Calendar.YEAR, 2007);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 15);
		calendar.set(Calendar.HOUR_OF_DAY, 15);
		calendar.set(Calendar.MINUTE, 7);
		calendar.set(Calendar.SECOND, 16);
		calendar.set(Calendar.MILLISECOND, 0);
		Assert.assertEquals(calendar.getTime(), userData.getRegistrationDate());
	}

	@Test
	public void testGetApiUri() throws URISyntaxException {
		Assert.assertEquals(new URI("https://de.wikipedia.org:443/w/api.php"), mediaWiki.getApiUri());
		
	}

}
