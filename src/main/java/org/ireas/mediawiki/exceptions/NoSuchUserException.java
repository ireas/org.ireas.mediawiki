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
 * Thrown if details for a missing user are queried.  The exception provides
 * the name of the user that caused it ({@code getUserName}).
 *
 * @author ireas
 */
public final class NoSuchUserException extends MediaWikiException {

    private static final long serialVersionUID = 1L;

    private final String userName;

    /**
     * Constructs a new exception caused by the specified user.
     *
     * @param userName the name of the missing user
     * @throws NullPointerException if the specified user name is null
     */
    public NoSuchUserException(final String userName) {
        super(String.format("No such user: %s", userName));

        Preconditions.checkNotNull(userName);
        this.userName = userName;
    }

    /**
     * Returns the name of the missing user that caused this exception
     * to be thrown.
     *
     * @return the name of the missing user
     */
    public String getUserName() {
        return userName;
    }

}
