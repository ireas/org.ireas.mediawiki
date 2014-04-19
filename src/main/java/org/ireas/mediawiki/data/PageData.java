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

/**
 * Stores the metadata for one page.  The metadata consists of the namespace,
 * the page ID and the page title (including the namespace prefix).
 * Implementations of this interface must order by the page ID and must be
 * equal if the page ID, the page title and the namespace are equal.
 *
 * @author ireas
 */
public interface PageData extends Data<PageData> {

    /**
     * Returns the namespace of this page.
     *
     * @return the namespace of this page
     */
    Namespace getNamespace();

    /**
     * Returns the ID of this page.
     *
     * @return the ID of this page
     */
    int getPageId();

    /**
     * Returns the title of this page, including the namespace prefix.
     *
     * @return the title of this page
     */
    String getPageTitle();

}
