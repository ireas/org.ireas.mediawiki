/*
 * Copyright (C) 2014 Robin Krahl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
