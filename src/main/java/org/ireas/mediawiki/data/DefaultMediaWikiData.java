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
