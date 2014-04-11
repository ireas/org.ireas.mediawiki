package org.ireas.mediawiki.data;

import java.net.URI;

/**
 * Stores the connection details for a MediaWiki installation.
 *
 * @author ireas
 */
public interface MediaWikiData extends Data<MediaWikiData> {

    /**
     * Returns the URI of the {@code api.php} file of the MediaWiki instance
     * to connect to.
     *
     * @return the URI to the MediaWiki {@code api.php}
     */
    URI getApiUri();

}
