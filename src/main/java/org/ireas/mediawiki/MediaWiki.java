package org.ireas.mediawiki;

import java.io.Closeable;
import java.net.URI;
import java.util.Map;

import org.ireas.mediawiki.data.UserData;
import org.json.JSONObject;

public interface MediaWiki extends Closeable {
	
	String performRequest(Map<String, String> arguments) throws MediaWikiException;
	
	JSONObject performJsonRequest(String action, Map<String, String> arguments) throws MediaWikiException;
	
	URI getApiUri();
	
	UserData getUserData(String user) throws MediaWikiException;

}
