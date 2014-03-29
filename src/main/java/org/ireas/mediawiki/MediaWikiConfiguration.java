package org.ireas.mediawiki;

/**
 * Configuration for a {@code MediaWiki} instance.  This class specifies
 * how a {@code MediaWiki} instance behaves when it does API queries.
 *
 * @author ireas
 */
public interface MediaWikiConfiguration {

    /**
     * Returns the user agent to be used when connecting to the MediaWiki API.
     *
     * @return the user agent to use in requests
     */
    String getUserAgent();

}
