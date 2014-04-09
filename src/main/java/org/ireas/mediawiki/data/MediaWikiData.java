package org.ireas.mediawiki.data;

import java.net.URI;

import com.google.common.base.Preconditions;

/**
 * Connection details for a MediaWiki installation.
 *
 * @author ireas
 */
public final class MediaWikiData implements Comparable<MediaWikiData> {

    private final URI apiUri;

    /**
     * Constructs a new MediaWiki data object for the MediaWiki installation
     * with the {@code api.php} file at the specified URI.
     *
     * @param apiUri the URI of the MediaWiki installation to connect to
     */
    public MediaWikiData(final URI apiUri) {
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
        if (!(object instanceof MediaWikiData)) {
            return false;
        }
        MediaWikiData mediaWikiData = (MediaWikiData) object;
        return getApiUri().equals(mediaWikiData.getApiUri());
    }

    /**
     * Returns the URI of the {@code api.php} file of the MediaWiki instance
     * to connect to.
     *
     * @return the URI to the MediaWiki {@code api.php}
     */
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
