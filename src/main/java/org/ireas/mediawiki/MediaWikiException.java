package org.ireas.mediawiki;

public class MediaWikiException extends Exception {

	private static final long serialVersionUID = 1L;

	public MediaWikiException() {
		super();
	}
	
	public MediaWikiException(String message) {
		super(message);
	}
	
	public MediaWikiException(Throwable cause) {
		super(cause);
	}
	
	public MediaWikiException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
