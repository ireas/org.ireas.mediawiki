package org.ireas.mediawiki;

import com.google.common.base.Preconditions;

public final class BasicMediaWikiConfiguration implements
		MediaWikiConfiguration {

	private static final String DEFAULT_USER_AGENT = "A tool using org.ireas.mediawiki";
	
	private final String userAgent;
	
	public BasicMediaWikiConfiguration() {
		this(DEFAULT_USER_AGENT);
	}
	
	public BasicMediaWikiConfiguration(String userAgent) {
		Preconditions.checkNotNull(userAgent);
		Preconditions.checkArgument(!userAgent.isEmpty(), "User agent may not be empty");
		
		this.userAgent = userAgent;
	}
	
	@Override
	public String getUserAgent() {
		return userAgent;
	}

}
