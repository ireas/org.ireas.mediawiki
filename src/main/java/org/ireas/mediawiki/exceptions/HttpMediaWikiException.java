package org.ireas.mediawiki.exceptions;

import org.apache.http.StatusLine;

import com.google.common.base.Preconditions;

/**
 * Thrown if an HTTP error occurs during an API request.  The exception
 * provides the status code of the HTTP error ({@code getStatusCode}).
 *
 * @author ireas
 */
public final class HttpMediaWikiException extends MediaWikiException {

    private static final int MINIMUM_STATUS_CODE = 100;

    private static final int MAXIMUM_STATUS_CODE = 999;

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception from the specified status line.
     *
     * @param statusLine the status line with information about the error
     *        that caused this exception to be thrown
     * @return a new exception for the error represented by the specified
     *         status line
     * @throws NullPointerException if the specified status line or its
     *         reason phrase is null
     * @throws IllegalArgumentException if the status code of the specified
     *         status line is less than 100 or higher than 999
     */
    public static HttpMediaWikiException
            newInstance(final StatusLine statusLine) {
        Preconditions.checkNotNull(statusLine);

        String reasonPhrase = statusLine.getReasonPhrase();
        Preconditions.checkNotNull(reasonPhrase);

        int statusCode = statusLine.getStatusCode();
        Preconditions.checkArgument(statusCode >= MINIMUM_STATUS_CODE);
        Preconditions.checkArgument(statusCode <= MAXIMUM_STATUS_CODE);

        return new HttpMediaWikiException(statusCode, reasonPhrase);
    }

    private final int statusCode;

    /**
     * Constructs a new exception caused by an HTTP error with the
     * specified status code and the specified reason.
     *
     * @param statusCode the status code of the HTTP error that caused this
     *        exception to be thrown
     * @param reason the reason phrase of the HTTP error that caused this
     *        exception to be thrown
     * @throws NullPointerException if the specified reason phrase is null
     * @throws IllegalArgumentException if the specified status code is less
     *         than 100 or higher than 999
     */
    public HttpMediaWikiException(final int statusCode, final String reason) {
        super(reason);

        Preconditions.checkNotNull(reason);
        Preconditions.checkArgument(statusCode >= MINIMUM_STATUS_CODE);
        Preconditions.checkArgument(statusCode <= MAXIMUM_STATUS_CODE);
        this.statusCode = statusCode;
    }

    /**
     * Returns the status code of the HTTP error that caused this exception
     * to be thrown.
     *
     * @return the error code for this exception
     */
    public int getStatusCode() {
        return statusCode;
    }

}
