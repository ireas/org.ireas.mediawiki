package org.ireas.mediawiki;

import com.google.common.base.Preconditions;

/**
 * Configuration object for {@code MediaWiki} instances.  The values of the
 * configuration must be set in the constructor.  The default user agent is
 * {@code "A tool using org.ireas.mediawiki"}.  It is highly recommended to
 * set the user agent to a string identifying the application using this
 * library.
 *
 * @author ireas
 */
public final class BasicMediaWikiConfiguration implements
        MediaWikiConfiguration {

    private static final String DEFAULT_USER_AGENT =
            "A tool using org.ireas.mediawiki";

    private final String userAgent;

    /**
     * Constructs a new configuration using the default values.
     */
    public BasicMediaWikiConfiguration() {
        this(DEFAULT_USER_AGENT);
    }

    /**
     * Constructs a new configuration using the specified user agent.
     *
     * @param userAgent the user agent for API connections
     * @throws NullPointerException if the specified user agent is null
     * @throws IllegalArgumentException if the specified user agent is empty
     */
    public BasicMediaWikiConfiguration(final String userAgent) {
        Preconditions.checkNotNull(userAgent);
        Preconditions.checkArgument(!userAgent.isEmpty(),
                "User agent may not be empty");

        this.userAgent = userAgent;
    }

    @Override
    public String getUserAgent() {
        return userAgent;
    }

}
