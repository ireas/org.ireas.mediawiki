package org.ireas.mediawiki;

import com.google.common.base.Preconditions;

/**
 * Defines the default MediaWiki namespaces and the namespaces used in
 * WMF wikis.
 *
 * @author ireas
 */
public enum Namespace {

    /**
     * Article namespace.
     */
    ARTICLE(0),

    /**
     * Article talk namespace.
     */
    ARTICLE_TALK(1),

    /**
     * User namespace.
     */
    USER(2),

    /**
     * User talk namespace.
     */
    USER_TALK(3),

    /**
     * Help namespace.
     */
    HELP(12),

    /**
     * Help talk namespace.
     */
    HELP_TALK(13);

    private final int namespace;

    private Namespace(final int namespace) {
        Preconditions.checkArgument(namespace >= 0);

        this.namespace = namespace;
    }

    /**
     * Returns the ID of this namespace.
     *
     * @return the ID of this namespace
     */
    public int getNamespace() {
        return namespace;
    }

    /**
     * Returns a string representation of this object.  The string
     * representation of a namespace is the MediaWiki namespace ID.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return Integer.toString(namespace);
    }

}
