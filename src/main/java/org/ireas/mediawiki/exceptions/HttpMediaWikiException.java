package org.ireas.mediawiki.exceptions;

import org.apache.http.StatusLine;

public final class HttpMediaWikiException extends MediaWikiException {

    private static final long serialVersionUID = 1L;

    public static HttpMediaWikiException
            newInstance(final StatusLine statusLine) {
        return new HttpMediaWikiException(statusLine.getStatusCode(),
                statusLine.getReasonPhrase());
    }

    private final int statusCode;

    public HttpMediaWikiException(final int statusCode, final String reason) {
        super(reason);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
