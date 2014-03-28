package org.ireas.mediawiki;

import com.google.common.base.Preconditions;

public enum Namespace {

    ARTICLE(0),

    ARTICLE_TALK(1),

    USER(2),

    USER_TALK(3),

    HELP(12),

    HELP_TALK(13);

    private final int namespace;

    private Namespace(final int namespace) {
        Preconditions.checkArgument(namespace >= 0);

        this.namespace = namespace;
    }

    public int getNamespace() {
        return namespace;
    }

    @Override
    public String toString() {
        return Integer.toString(namespace);
    }

}
