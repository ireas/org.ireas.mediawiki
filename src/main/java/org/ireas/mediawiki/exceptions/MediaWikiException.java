package org.ireas.mediawiki.exceptions;

public class MediaWikiException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MediaWikiException() {
        super();
    }

    public MediaWikiException(final String message) {
        super(message);
    }

    public MediaWikiException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MediaWikiException(final Throwable cause) {
        super(cause);
    }

}
