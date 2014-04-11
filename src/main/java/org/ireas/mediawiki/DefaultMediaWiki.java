package org.ireas.mediawiki;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.ireas.mediawiki.data.DefaultMediaWikiData;
import org.ireas.mediawiki.data.DefaultUserData;
import org.ireas.mediawiki.data.MediaWikiData;
import org.ireas.mediawiki.data.UserData;
import org.ireas.mediawiki.exceptions.HttpMediaWikiException;
import org.ireas.mediawiki.exceptions.MediaWikiException;
import org.ireas.mediawiki.exceptions.NoSuchUserException;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * Default implementation of the {@code MediaWiki} interfaces.  This class
 * uses the Apache HTTPComponents library to access the MediaWiki API.  It
 * is recommended to use the {@link MediaWikiFactory} to create new {@code
 * MediaWiki} instances instead of accessing this class directly.
 *
 * @author ireas
 */
public final class DefaultMediaWiki implements MediaWiki {

    private static final String HEADER_USER_AGENT = "User-Agent";

    private final DefaultMediaWikiData mediaWikiData;

    private final MediaWikiConfiguration configuration;

    private final CloseableHttpClient httpClient;

    /**
     * Constructs a new MediaWiki instance for the MediaWiki installation
     * representend by the specified MediaWiki data and using the specified
     * configuration.  It is recommended to use the {@link MediaWikiFactory}
     * instead of this constructor.
     *
     * @param mediaWikiData the data of the MediaWiki installation to access
     * @param configuration the configuration for the API request
     * @throws NullPointerException if the specified URI or configuration is
     *         null
     */
    public DefaultMediaWiki(final DefaultMediaWikiData mediaWikiData,
            final MediaWikiConfiguration configuration) {
        Preconditions.checkNotNull(mediaWikiData);
        Preconditions.checkNotNull(configuration);

        this.mediaWikiData = mediaWikiData;
        this.configuration = configuration;

        httpClient = HttpClients.createDefault();
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }

    private int getContribCount(final Map<String, String> arguments)
            throws MediaWikiException {
        JSONObject result =
                performJsonRequest(ApiConstants.ACTION_QUERY, arguments);
        MediaWikiUtils.requireJsonFields(result,
                ApiConstants.RESULT_USERCONTRIBS);
        JSONArray contributions =
                result.getJSONArray(ApiConstants.RESULT_USERCONTRIBS);
        return contributions.length();
    }

    @Override
    public int getContribCount(final String user, final int limit)
            throws MediaWikiException {
        Preconditions.checkNotNull(user);
        Preconditions.checkArgument(limit > 0);

        Set<Namespace> namespaces = Collections.emptySet();
        return getContribCount(user, limit, namespaces);
    }

    @Override
    public int getContribCount(final String user, final int limit,
            final Set<Namespace> namespaces) throws MediaWikiException {
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(namespaces);
        Preconditions.checkArgument(limit > 0);

        Map<String, String> arguments =
                getContribCountArguments(user, limit, namespaces);
        return getContribCount(arguments);
    }

    @Override
    public int getContribCount(final String user, final int limit,
            final Set<Namespace> namespaces, final DateTime endDate)
            throws MediaWikiException {
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(namespaces);
        Preconditions.checkNotNull(endDate);
        Preconditions.checkArgument(limit > 0);

        Map<String, String> arguments =
                getContribCountArguments(user, limit, namespaces, endDate);
        return getContribCount(arguments);
    }

    @Override
    public int getContribCount(final String user, final int limit,
            final Set<Namespace> namespaces, final DateTime endDate,
            final Period period) throws MediaWikiException {
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(namespaces);
        Preconditions.checkNotNull(endDate);
        Preconditions.checkNotNull(period);
        Preconditions.checkArgument(limit > 0);

        Map<String, String> arguments =
                getContribCountArguments(user, limit, namespaces, endDate,
                        period);
        return getContribCount(arguments);
    }

    private Map<String, String> getContribCountArguments(final String user,
            final int limit, final Set<Namespace> namespaces) {
        Map<String, String> arguments = new HashMap<>();
        arguments.put(ApiConstants.LIST, ApiConstants.LIST_USERCONTRIBS);
        arguments.put(ApiConstants.UC_LIMIT, Integer.toString(limit));
        arguments.put(ApiConstants.UC_USER, user);
        arguments.put(ApiConstants.UC_PROP, "");
        arguments.put(ApiConstants.UC_DIR, ApiConstants.UC_DIR_NEWER);

        if (!namespaces.isEmpty()) {
            String namespacesString =
                    StringUtils.join(namespaces, ApiConstants.SEPARATOR);
            arguments.put(ApiConstants.UC_NAMESPACE, namespacesString);
        }

        return arguments;
    }

    private Map<String, String> getContribCountArguments(final String user,
            final int limit, final Set<Namespace> namespaces,
            final DateTime endDate) {
        Map<String, String> arguments =
                getContribCountArguments(user, limit, namespaces);
        arguments.put(ApiConstants.UC_END, MediaWikiUtils
                .formatApiDate(endDate));
        return arguments;
    }

    private Map<String, String> getContribCountArguments(final String user,
            final int limit, final Set<Namespace> namespaces,
            final DateTime endDate, final Period period) {
        Map<String, String> arguments =
                getContribCountArguments(user, limit, namespaces, endDate);
        arguments.put(ApiConstants.UC_START, MediaWikiUtils
                .formatApiDate(endDate.minus(period)));
        return arguments;
    }

    @Override
    public Optional<DateTime> getFirstEdit(final String user)
            throws MediaWikiException {
        Preconditions.checkNotNull(user);

        Map<String, String> arguments = new HashMap<>();
        arguments.put(ApiConstants.LIST, ApiConstants.LIST_USERCONTRIBS);
        arguments.put(ApiConstants.UC_DIR, ApiConstants.UC_DIR_NEWER);
        arguments.put(ApiConstants.UC_LIMIT, Integer.toString(1));
        arguments.put(ApiConstants.UC_USER, user);

        JSONObject result =
                performJsonRequest(ApiConstants.ACTION_QUERY, arguments);
        MediaWikiUtils.requireJsonFields(result,
                ApiConstants.RESULT_USERCONTRIBS);
        JSONArray contributions =
                result.getJSONArray(ApiConstants.RESULT_USERCONTRIBS);
        Optional<DateTime> firstEdit = Optional.absent();
        if (contributions.length() > 0) {
            JSONObject contribution = contributions.getJSONObject(0);
            MediaWikiUtils.requireJsonFields(contribution,
                    ApiConstants.RESULT_UC_TIMESTAMP);
            String firstEditTimestamp =
                    contribution.getString(ApiConstants.RESULT_UC_TIMESTAMP);
            DateTime firstEditDate =
                    MediaWikiUtils.parseApiTimestamp(firstEditTimestamp);
            firstEdit = Optional.of(firstEditDate);

        }
        return firstEdit;
    }

    @Override
    public MediaWikiData getMediaWikiData() {
        return mediaWikiData;
    }

    @Override
    public UserData getUserData(final String user) throws MediaWikiException {
        Preconditions.checkNotNull(user);

        Map<String, String> arguments = new HashMap<>();
        arguments.put(ApiConstants.LIST, ApiConstants.LIST_USERS);
        arguments.put(ApiConstants.US_PROP, ApiConstants.US_PROP_REGISTRATION);
        arguments.put(ApiConstants.US_USERS, user);

        JSONObject result =
                performJsonRequest(ApiConstants.ACTION_QUERY, arguments);
        MediaWikiUtils.requireJsonFields(result, ApiConstants.RESULT_USERS);
        JSONArray users = result.getJSONArray(ApiConstants.RESULT_USERS);
        MediaWikiUtils.requireJsonLength(users, 1);
        JSONObject userObject = users.getJSONObject(0);
        if (userObject.has(ApiConstants.RESULT_US_MISSING)) {
            throw new NoSuchUserException(user);
        }

        MediaWikiUtils.requireJsonFields(userObject,
                ApiConstants.RESULT_US_NAME, ApiConstants.RESULT_US_ID,
                ApiConstants.RESULT_US_REGISTRATION);

        String userName = userObject.getString(ApiConstants.RESULT_US_NAME);
        int userId = userObject.getInt(ApiConstants.RESULT_US_ID);
        String registrationString =
                userObject.getString(ApiConstants.RESULT_US_REGISTRATION);
        DateTime registrationDate =
                MediaWikiUtils.parseApiTimestamp(registrationString);

        return new DefaultUserData(userName, userId, registrationDate);
    }

    @Override
    public JSONObject performJsonRequest(final String action,
            final Map<String, String> arguments) throws MediaWikiException {
        Preconditions.checkNotNull(action);
        Preconditions.checkNotNull(arguments);
        Preconditions.checkArgument(!action.isEmpty(),
                "Action may not be empty");

        String modifiedAction = action.toLowerCase();
        Map<String, String> modifiedArguments =
                new HashMap<String, String>(arguments);
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
    public String performRequest(final Map<String, String> arguments)
            throws MediaWikiException {
        Preconditions.checkNotNull(arguments);

        HttpPost httpPost = new HttpPost(mediaWikiData.getApiUri());
        List<NameValuePair> nameValuePairs =
                MediaWikiUtils.mapToNameValuePairs(arguments);
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
            throw new MediaWikiException(
                    "An error occured during the API query.", exception);
        } finally {
            MediaWikiUtils.close(response);
        }

        return result;
    }

}
