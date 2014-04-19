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

package org.ireas.mediawiki.exceptions;

import com.google.common.base.Preconditions;

/**
 * Thrown if details for a missing page are queried.  The exception provides
 * the title of the missing page ({@code getTitle}).
 *
 * @author ireas
 */
public final class NoSuchPageException extends MediaWikiException {

    private static final long serialVersionUID = 1L;

    private final String title;

    /**
     * Constructs a new exception for the page with the specified title.
     *
     * @param title the title of the page that is missing
     * @throws NullPointerException if the specified page title is null
     */
    public NoSuchPageException(final String title) {
        super(String.format("No such page: %s", title));

        Preconditions.checkNotNull(title);
        this.title = title;
    }

    /**
     * Returns the title of the missing page that caused this exception.
     *
     * @return the title of the missing page
     */
    public String getTitle() {
        return title;
    }

}
