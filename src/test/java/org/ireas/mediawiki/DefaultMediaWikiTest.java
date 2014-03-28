package org.ireas.mediawiki;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import org.ireas.mediawiki.data.UserData;
import org.ireas.mediawiki.exceptions.MediaWikiException;
import org.ireas.mediawiki.exceptions.NoSuchUserException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

public class DefaultMediaWikiTest {

    private static final String USER_NAME = "Ireas";

    private static final String USER_NAME_MISSING = "Ireas-";

    private MediaWiki mediaWiki;

    @Before
    public void setUp() throws Exception {
        MediaWikiConfiguration configuration =
                new BasicMediaWikiConfiguration("org.ireas.mediawiki.test");
        MediaWikiFactory.setConfiguration(configuration);
        mediaWiki = MediaWikiFactory.newWikipediaInstance("de");
    }

    @After
    public void tearDown() throws Exception {
        MediaWikiUtils.close(mediaWiki);
    }

    @Test
    public void testGetApiUri() throws URISyntaxException {
        Assert.assertEquals(new URI("https://de.wikipedia.org:443/w/api.php"),
                mediaWiki.getApiUri());
    }

    @Test
    public void testGetContribCountHighNamespace() throws MediaWikiException {
        Set<Namespace> namespaces = EnumSet.of(Namespace.ARTICLE);
        int count = mediaWiki.getContribCount(USER_NAME, 500, namespaces);
        Assert.assertEquals(count, 500);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetContribCountInvalidLimit() throws MediaWikiException {
        mediaWiki.getContribCount(USER_NAME, 0);
    }

    @Test
    public void testGetContribCountLimit() throws MediaWikiException {
        int count = mediaWiki.getContribCount(USER_NAME, 1);
        Assert.assertEquals(count, 1);
    }

    @Test
    public void testGetContribCountLowNamespace() throws MediaWikiException {
        Set<Namespace> namespaces = EnumSet.of(Namespace.HELP_TALK);
        int count = mediaWiki.getContribCount(USER_NAME, 50, namespaces);
        Assert.assertTrue(count < 20);
    }

    @Test
    public void testGetContribCountNamespacePeriod() throws MediaWikiException {
        Set<Namespace> namespaces = EnumSet.of(Namespace.USER_TALK);
        DateTime dateEnd = new DateTime(2009, 4, 30, 0, 0, 0, DateTimeZone.UTC);
        Period period = Period.days(29);
        int count =
                mediaWiki.getContribCount(USER_NAME, 50, namespaces, dateEnd,
                        period);
        Assert.assertEquals(count, 2);
    }

    @Test
    public void testGetContribCountNoSuchUser() throws MediaWikiException {
        int count = mediaWiki.getContribCount(USER_NAME_MISSING, 50);
        Assert.assertEquals(count, 0);
    }

    @Test
    public void testGetContribCountZeroPeriod() throws MediaWikiException {
        Set<Namespace> namespaces = Collections.emptySet();
        DateTime endDate = new DateTime(2006, 1, 1, 0, 0, 0, DateTimeZone.UTC);
        Period period = Period.months(2);

        int count =
                mediaWiki.getContribCount(USER_NAME, 50, namespaces, endDate,
                        period);
        Assert.assertEquals(count, 0);
    }

    @Test
    public void testGetFirstEdit() throws MediaWikiException {
        Optional<DateTime> date = mediaWiki.getFirstEdit(USER_NAME);
        DateTime firstEdit =
                new DateTime(2007, 1, 15, 15, 44, 31, DateTimeZone.UTC);
        Assert.assertEquals(firstEdit.getMillis(), date.get().getMillis());
    }

    @Test
    public void testGetFirstEditNoSuchUser() throws MediaWikiException {
        Optional<DateTime> date = mediaWiki.getFirstEdit(USER_NAME_MISSING);
        Assert.assertTrue(!date.isPresent());
    }

    @Test
    public void testGetUserData() throws MediaWikiException {
        UserData userData = mediaWiki.getUserData(USER_NAME);
        Assert.assertEquals("Ireas", userData.getUserName());
        Assert.assertEquals(336793, userData.getUserId());
        DateTime registration =
                new DateTime(2007, 1, 15, 15, 7, 16, DateTimeZone.UTC);
        Assert.assertEquals(registration.getMillis(), userData
                .getRegistrationDate().getMillis());
    }

    @Test(expected = NoSuchUserException.class)
    public void testGetUserDataNoSuchUser() throws MediaWikiException {
        mediaWiki.getUserData(USER_NAME_MISSING);
    }

}
