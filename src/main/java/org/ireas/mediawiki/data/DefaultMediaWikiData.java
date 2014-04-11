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

package org.ireas.mediawiki.data;

import java.net.URI;

import com.google.common.base.Preconditions;

/**
 * Default implementation of {@code MediaWIkiData} that stores connection
 * details for a MediaWiki installation.
 *
 * @author ireas
 */
public final class DefaultMediaWikiData implements MediaWikiData {

    private final URI apiUri;

    /**
     * Constructs a new MediaWiki data object for the MediaWiki installation
     * with the {@code api.php} file at the specified URI.
     *
     * @param apiUri the URI of the MediaWiki installation to connect to
     */
    public DefaultMediaWikiData(final URI apiUri) {
        Preconditions.checkNotNull(apiUri);

        this.apiUri = apiUri;
    }

    @Override
    public int compareTo(final MediaWikiData mediaWikiData) {
        Preconditions.checkNotNull(mediaWikiData);
        return apiUri.compareTo(mediaWikiData.getApiUri());
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof DefaultMediaWikiData)) {
            return false;
        }
        DefaultMediaWikiData mediaWikiData = (DefaultMediaWikiData) object;
        return getApiUri().equals(mediaWikiData.getApiUri());
    }

    @Override
    public URI getApiUri() {
        return apiUri;
    }

    @Override
    public int hashCode() {
        return apiUri.hashCode();
    }

    /**
     * Returns a string representation of this object, which is the URI to the
     * {@code api.php} file of the MediaWiki installation to connect to.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return apiUri.toString();
    }

}
