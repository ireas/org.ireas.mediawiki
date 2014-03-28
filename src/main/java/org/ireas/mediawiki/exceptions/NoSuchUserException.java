package org.ireas.mediawiki.exceptions;

public final class NoSuchUserException extends MediaWikiException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final String userName;

    public NoSuchUserException(final String userName) {
        super(String.format("No such user: %s", userName));

        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}
