package org.ireas.mediawiki.data;

import java.util.Date;

import com.google.common.base.Preconditions;

public final class UserData {

	private final String userName;
	
	private final int userId;
	
	private final Date registrationDate;
	
	public UserData(String userName, int userId, Date registrationDate) {
		Preconditions.checkNotNull(userName);
		Preconditions.checkNotNull(registrationDate);
		
		this.userName = userName;
		this.userId = userId;
		this.registrationDate = registrationDate;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public Date getRegistrationDate() {
		return registrationDate;
	}
	
	@Override
	public String toString() {
		return String.format("User[name='%s',id=%d,registration=%s]", userName, userId, registrationDate.toString());
	}

}
