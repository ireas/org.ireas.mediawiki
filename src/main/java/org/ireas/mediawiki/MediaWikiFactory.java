package org.ireas.mediawiki;

import org.ireas.mediawiki.exceptions.MediaWikiException;

import com.google.common.base.Preconditions;

/**
 * Factory for {@code MediaWiki} instances.  The factory provides easy access
 * to wikis installed according to the WMF installation scheme ({@code
 * newWikimediaInstance}) and to Wikipedia language versions ({@code
 * newWikipediaInstance}).  Per default, HTTPS is used.  {@code newInstance}
 * connects to a custom MediaWiki installation.
 *
 * @author ireas
 */
public final class MediaWikiFactory {

    private static final String HTTPS_SCHEME = "https";

    private static final int HTTPS_PORT = 443;

    private static final String WIKIMEDIA_API_PATH = "/w/api.php";

    private static final String WIKIPEDIA_HOST = "%s.wikipedia.org";

    private static MediaWikiConfiguration configuration =
            new BasicMediaWikiConfiguration();

    /**
     * Returns the current configuration for the created {@code MediaWiki}
     * instances.
     *
     * @return the current MediaWiki configuration
     */
    public static MediaWikiConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Constructs a new {@code MediaWiki} instance using the specified
     * connection credentials.  The specified credentials are the parts of
     * the URI pointing to the MediaWiki {@code api.php} file to access.
     *
     * @param scheme the scheme of the URI, e. g. {@code "https"}
     * @param host the host of the URI, e. g. {@code "example.org"}
     * @param port the port of the URI, e. g. {@code 443}. The port must be
     *            non-negative.
     * @param apiPath the path of the URI, e. g. {@code "/index.html"}
     * @return a new {@code MediaWiki} instance pointing to the MediaWiki
     *         installation located at the specified URI
     * @throws MediaWikiException if the specified URI parts are invalid
     * @throws NullPointerException of the specified scheme, host or api
     *         path is null
     * @throws IllegalArgumentException if the specified port is negative
     */
    public static MediaWiki newInstance(final String scheme, final String host,
            final int port, final String apiPath) throws MediaWikiException {
        Preconditions.checkNotNull(scheme);
        Preconditions.checkNotNull(host);
        Preconditions.checkNotNull(apiPath);
        Preconditions.checkArgument(port >= 0);

        return new DefaultMediaWiki(MediaWikiUtils.buildUri(scheme, host, port,
                apiPath), configuration);
    }

    /**
     * Constructs a new {@code MediaWiki} instance using the {@code api.php}
     * located on the specified host according to the WMF scheme.  That
     * means that the {@code api.php} file must be located at {@code
     * "/w/api.php"}.  The constructed {@code MediaWiki} uses HTTPS on port
     * 443.
     *
     * @param host the host of the MediaWiki installation to connect to
     * @return a new {@code MediaWiki} instance pointing to the MediaWiki
     *         installation on the specified host
     * @throws MediaWikiException if the specified host is invalid
     * @throws NullPointerException if the specified host is null
     */
    public static MediaWiki newWikimediaInstance(final String host)
            throws MediaWikiException {
        Preconditions.checkNotNull(host);
        return newInstance(HTTPS_SCHEME, host, HTTPS_PORT, WIKIMEDIA_API_PATH);
    }

    /**
     * Constructs a new {@code MediaWiki} instance for the specified
     * Wikipedia language version.  The specified language must be the
     * WMF language code (the subdomain, e. g. {@code "de"} for German).
     * The constructed {@code MediaWiki} instance will use HTTPS.
     *
     * @param language the language code of the Wikipedia version to connect to
     * @return a new {@code MediaWiki} instance for the specified Wikipedia
     *         language version
     * @throws MediaWikiException if the specified language is invalid
     * @throws NullPointerException if the specified language is null
     */
    public static MediaWiki newWikipediaInstance(final String language)
            throws MediaWikiException {
        Preconditions.checkNotNull(language);
        return newWikimediaInstance(String.format(WIKIPEDIA_HOST, language));
    }

    /**
     * Sets the configuration used for new {@code MediaWiki} instances
     * created using this factory class.  A change does not affect
     * previously created instances.
     *
     * @param configuration the configuration for new {@code MediaWiki}
     *        instances
     * @throws NullPointerException if the specified configuration is null
     */
    public static void setConfiguration(
            final MediaWikiConfiguration configuration) {
        Preconditions.checkNotNull(configuration);
        MediaWikiFactory.configuration = configuration;
    }

    private MediaWikiFactory() {
        throw new UnsupportedOperationException();
    }

}
