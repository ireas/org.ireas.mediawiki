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

import org.ireas.common.HashCodeGenerator;

import com.google.common.base.Preconditions;

/**
 * Default implementation of {@code PageData} that stores the metadata for a
 * page.  This is the namespace, the page ID and the page title.  Instances of
 * this class are ordered by their page ID.  Two instances of this class are
 * equal if the page ID, the page title and the namespace are equal.
 *
 * @author ireas
 */
public final class DefaultPageData implements PageData {

    private final Namespace namespace;

    private final int pageId;

    private final String pageTitle;

    /**
     * Constructs the page data for the page with the specified title, ID and
     * namespace.
     *
     * @param namespace the namespace of this page
     * @param pageId the ID of this page
     * @param pageTitle the title of this page, including the namespace prefix
     * @throws NullPointerException if the specified namespace or title is null
     */
    public DefaultPageData(final Namespace namespace, final int pageId,
            final String pageTitle) {
        Preconditions.checkNotNull(namespace);
        Preconditions.checkNotNull(pageTitle);

        this.namespace = namespace;
        this.pageId = pageId;
        this.pageTitle = pageTitle;
    }

    @Override
    public int compareTo(final PageData pageData) {
        Preconditions.checkNotNull(pageData);
        return Integer.compare(getPageId(), pageData.getPageId());
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof DefaultPageData)) {
            return false;
        }
        DefaultPageData pageData = (DefaultPageData) object;
        return getPageId() == pageData.getPageId()
                && getNamespace() == pageData.getNamespace()
                && getPageTitle().equals(pageData.getPageTitle());
    }

    @Override
    public Namespace getNamespace() {
        return namespace;
    }

    @Override
    public int getPageId() {
        return pageId;
    }

    @Override
    public String getPageTitle() {
        return pageTitle;
    }

    @Override
    public int hashCode() {
        HashCodeGenerator generator = new HashCodeGenerator();
        generator.add(getPageId());
        generator.add(getNamespace());
        generator.add(getPageTitle());
        return generator.getHashCode();
    }

    /**
     * Returns a string representation of this object.  The string
     * representation of the page data contains all information about the
     * page in a human-readable format.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return String.format("Page[title='%s',id=%d,namespace=%d]", pageTitle,
                pageId, namespace.getNamespace());
    }

}
