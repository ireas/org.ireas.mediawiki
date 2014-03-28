package org.ireas.mediawiki.data;

import org.joda.time.DateTime;

import com.google.common.base.Preconditions;

public final class UserData {

    private final String userName;

    private final int userId;

    private final DateTime registrationDate;

    public UserData(final String userName, final int userId,
            final DateTime registrationDate) {
        Preconditions.checkNotNull(userName);
        Preconditions.checkNotNull(registrationDate);

        this.userName = userName;
        this.userId = userId;
        this.registrationDate = registrationDate;
    }

    public DateTime getRegistrationDate() {
        return registrationDate;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return String.format("User[name='%s',id=%d,registration=%s]", userName,
                userId, registrationDate.toString());
    }

}
