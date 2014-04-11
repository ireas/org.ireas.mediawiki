package org.ireas.mediawiki.data;

import org.ireas.common.HashCodeGenerator;
import org.joda.time.DateTime;

import com.google.common.base.Preconditions;

/**
 * Default implementation of {@code UserData} that stores the metadata for an
 * user.  This includes the user’s name, ID and registration date.
 *
 * @author ireas
 */
public final class DefaultUserData implements UserData {

    private final String userName;

    private final int userId;

    private final DateTime registrationDate;

    /**
     * Constructs the user data for the specified user with the specified
     * id and the specified registration date.
     *
     * @param userName the name of the user
     * @param userId the ID of the user
     * @param registrationDate the user’s registration date
     * @throws NullPointerException if the specified name or registration
     *         date is null
     * @throws IllegalArgumentException if the specified ID is negative
     */
    public DefaultUserData(final String userName, final int userId,
            final DateTime registrationDate) {
        Preconditions.checkNotNull(userName);
        Preconditions.checkNotNull(registrationDate);
        Preconditions.checkArgument(userId >= 0);

        this.userName = userName;
        this.userId = userId;
        this.registrationDate = registrationDate;
    }

    @Override
    public int compareTo(final UserData userData) {
        Preconditions.checkNotNull(userData);
        return Integer.compare(getUserId(), userData.getUserId());
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof DefaultUserData)) {
            return false;
        }
        DefaultUserData userData = (DefaultUserData) object;
        return getUserId() == userData.getUserId()
                && getUserName().equals(getUserName());
    }

    @Override
    public DateTime getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public int hashCode() {
        HashCodeGenerator generator = new HashCodeGenerator();
        generator.add(getUserId());
        generator.add(getUserName());
        return generator.getHashCode();
    }

    /**
     * Returns a string representation of this object.  The string
     * representation of the user data contains all information about the
     * user in a human-readable format.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return String.format("User[name='%s',id=%d,registration=%s]", userName,
                userId, registrationDate.toString());
    }

}
