/*
 * Copyright (C) 2014 Robin Krahl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
