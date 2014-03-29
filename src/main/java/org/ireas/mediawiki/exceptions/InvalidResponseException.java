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
