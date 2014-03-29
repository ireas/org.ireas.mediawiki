package org.ireas.mediawiki.exceptions;

import javax.annotation.Nullable;

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
    public NoSuchUserException(@Nullable final String userName) {
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
