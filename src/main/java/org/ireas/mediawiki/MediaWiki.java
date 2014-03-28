package org.ireas.mediawiki;

import java.io.Closeable;
import java.net.URI;
import java.util.Map;
import java.util.Set;

import org.ireas.mediawiki.data.UserData;
import org.ireas.mediawiki.exceptions.MediaWikiException;
import org.ireas.mediawiki.exceptions.NoSuchUserException;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.JSONObject;

import com.google.common.base.Optional;

public interface MediaWiki extends Closeable {

    URI getApiUri();

    int getContribCount(final String user, final int limit)
            throws MediaWikiException;

    int getContribCount(final String user, final int limit,
            final Set<Namespace> namespaces) throws MediaWikiException;

    int getContribCount(final String user, final int limit,
            final Set<Namespace> namespaces, final DateTime endDate)
            throws MediaWikiException;

    int getContribCount(final String user, final int limit,
            final Set<Namespace> namespaces, final DateTime endDate,
            final Period period) throws MediaWikiException;

    Optional<DateTime> getFirstEdit(String user) throws MediaWikiException;

    UserData getUserData(String user) throws NoSuchUserException,
            MediaWikiException;

    JSONObject performJsonRequest(String action, Map<String, String> arguments)
            throws MediaWikiException;

    String performRequest(Map<String, String> arguments)
            throws MediaWikiException;

}
