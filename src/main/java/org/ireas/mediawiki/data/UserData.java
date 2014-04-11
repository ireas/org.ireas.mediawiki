package org.ireas.mediawiki.data;

import org.joda.time.DateTime;

/**
 * Stores the metadata for on user.  This includes the user’s name, ID
 * and registration date.
 *
 * @author ireas
 */
public interface UserData extends Data<UserData> {

    /**
     * Returns the user’s registration date.
     *
     * @return the registration date
     */
    DateTime getRegistrationDate();

    /**
     * Returns the user’s ID.
     *
     * @return the user ID
     */
    int getUserId();

    /**
     * Returns the user’s name as returned by the API.
     *
     * @return the user name
     */
    String getUserName();

}
