package org.ireas.mediawiki.exceptions;

public final class InvalidResponseException extends MediaWikiException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InvalidResponseException() {
        super();
    }

    public InvalidResponseException(final String message) {
        super(message);
    }

    public InvalidResponseException(final Throwable cause) {
        super(cause);
    }

}
