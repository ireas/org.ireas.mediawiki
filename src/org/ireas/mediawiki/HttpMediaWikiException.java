package org.ireas.mediawiki;

import org.apache.http.StatusLine;

public final class HttpMediaWikiException extends MediaWikiException {
	
	private static final long serialVersionUID = 1L;
	
	private final int statusCode;
	
	public HttpMediaWikiException(int statusCode, String reason) {
		super(reason);
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public static HttpMediaWikiException newInstance(StatusLine statusLine) {
		return new HttpMediaWikiException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
	}

}
