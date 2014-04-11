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

/**
 * Thrown if the data returned by the MediaWiki API does not match the expected
 * format.  If this exception is thrown, this library is either outdated (the
 * API return format has changed since the last update), or there is a bug in
 * the library code.  Since the problem can only be fixed by a library update,
 * this is an unchecked exception.
 *
 * @author ireas
 */
public final class InvalidResponseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception without detail information.
     */
    public InvalidResponseException() {
        super();
    }

    /**
     * Constructs a new exception using the specified detail message.
     *
     * @param message a detail message explaining this exception
     */
    public InvalidResponseException(final String message) {
        super(message);
    }

}
