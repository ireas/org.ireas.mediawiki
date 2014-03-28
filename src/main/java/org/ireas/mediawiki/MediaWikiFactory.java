package org.ireas.mediawiki;

import org.ireas.mediawiki.exceptions.MediaWikiException;

import com.google.common.base.Preconditions;

public final class MediaWikiFactory {

    private static final String HTTPS_SCHEME = "https";

    private static final int HTTPS_PORT = 443;

    private static final String WIKIMEDIA_API_PATH = "/w/api.php";

    private static final String WIKIPEDIA_HOST = "%s.wikipedia.org";

    private static MediaWikiConfiguration CONFIGURATION =
            new BasicMediaWikiConfiguration();

    public static final MediaWikiConfiguration getConfiguration() {
        return CONFIGURATION;
    }

    public static final MediaWiki newInstance(final String scheme,
            final String host, final int port, final String apiPath)
            throws MediaWikiException {
        return new DefaultMediaWiki(MediaWikiUtils.buildUri(scheme, host, port,
                apiPath), CONFIGURATION);
    }

    public static final MediaWiki newWikimediaInstance(final String host)
            throws MediaWikiException {
        return newInstance(HTTPS_SCHEME, host, HTTPS_PORT, WIKIMEDIA_API_PATH);
    }

    public static final MediaWiki newWikipediaInstance(final String language)
            throws MediaWikiException {
        return newWikimediaInstance(String.format(WIKIPEDIA_HOST, language));
    }

    public static final void setConfiguration(
            final MediaWikiConfiguration configuration) {
        Preconditions.checkNotNull(configuration);

        CONFIGURATION = configuration;
    }

    private MediaWikiFactory() {
        throw new UnsupportedOperationException();
    }

}
