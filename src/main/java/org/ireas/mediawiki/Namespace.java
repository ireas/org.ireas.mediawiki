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
