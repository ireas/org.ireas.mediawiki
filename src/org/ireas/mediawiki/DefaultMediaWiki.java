package org.ireas.mediawiki;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.ireas.mediawiki.data.UserData;
import org.json.JSONObject;

import com.google.common.base.Preconditions;

public final class DefaultMediaWiki implements MediaWiki {
	
	private static final String HEADER_USER_AGENT = "User-Agent";
	
	private final URI apiUri;
	
	private final MediaWikiConfiguration configuration;
	
	private final CloseableHttpClient httpClient;
	
	public DefaultMediaWiki(URI apiUri, MediaWikiConfiguration configuration) {
		Preconditions.checkNotNull(apiUri);
		Preconditions.checkNotNull(configuration);
		
		this.apiUri = apiUri;
		this.configuration = configuration;
		
		httpClient = HttpClients.createDefault();
	}
	
	@Override
	public UserData getUserData(String user) throws MediaWikiException {
		Preconditions.checkNotNull(user);
		
		Map<String, String> arguments = new HashMap<>();
		arguments.put(ApiConstants.LIST, ApiConstants.LIST_USERS);
		arguments.put(ApiConstants.US_PROP, ApiConstants.US_PROP_REGISTRATION);
		arguments.put(ApiConstants.US_USERS, user);
		
		JSONObject result = performJsonRequest(ApiConstants.ACTION_QUERY, arguments);
		JSONObject userObject = result.getJSONArray(ApiConstants.RESULT_USERS).getJSONObject(0);
		String userName = userObject.getString(ApiConstants.RESULT_USER_NAME);
		int userId = userObject.getInt(ApiConstants.RESULT_USER_ID);
		String registrationString = userObject.getString(ApiConstants.RESULT_USER_REGISTRATION);
		Date registrationDate = MediaWikiUtils.parseApiTimestamp(registrationString);
		return new UserData(userName, userId, registrationDate);
	}

	@Override
	public String performRequest(Map<String, String> arguments) throws MediaWikiException {
		Preconditions.checkNotNull(arguments);
		
		HttpPost httpPost = new HttpPost(apiUri);
		List<NameValuePair> nameValuePairs = MediaWikiUtils.mapToNameValuePairs(arguments);
		UrlEncodedFormEntity argumentsEntity;
		try {
			argumentsEntity = new UrlEncodedFormEntity(nameValuePairs);
		} catch (UnsupportedEncodingException exception) {
			throw new MediaWikiException(exception);
		}
		httpPost.setEntity(argumentsEntity);
		httpPost.setHeader(HEADER_USER_AGENT, configuration.getUserAgent());
		
		CloseableHttpResponse response = null;
		String result;
		try {
			response = httpClient.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			 if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
				 throw HttpMediaWikiException.newInstance(statusLine);
			 }
			result = EntityUtils.toString(response.getEntity());
		} catch (IOException exception) {
			throw new MediaWikiException("An error occured during the API query.", exception);
		} finally {
			MediaWikiUtils.close(response);
		}
		
		return result;
	}

	@Override
	public JSONObject performJsonRequest(String action,
			Map<String, String> arguments) throws MediaWikiException {
		Preconditions.checkNotNull(action);
		Preconditions.checkNotNull(arguments);
		Preconditions.checkArgument(!action.isEmpty(), "Action may not be empty");
		
		String modifiedAction = action.toLowerCase();
		Map<String, String> modifiedArguments = new HashMap<String, String>(arguments);
		modifiedArguments.put(ApiConstants.FORMAT, ApiConstants.FORMAT_JSON);
		modifiedArguments.put(ApiConstants.ACTION, modifiedAction);
		
		String result = performRequest(modifiedArguments);
		JSONObject root = new JSONObject(result);
		
		if (root.has(ApiConstants.RESULT_ERROR)) {
			JSONObject error = root.getJSONObject(ApiConstants.RESULT_ERROR);
			String errorCode = error.getString(ApiConstants.RESULT_ERROR_CODE);
			throw new MediaWikiException(errorCode);
		}
		
		JSONObject returnValue = new JSONObject();
		if (root.has(modifiedAction)) {
			returnValue = root.getJSONObject(modifiedAction);
		}
		return returnValue;
	}

	@Override
	public URI getApiUri() {
		return apiUri;
	}
	
	@Override
	public void close() throws IOException {
		httpClient.close();
	}

}
