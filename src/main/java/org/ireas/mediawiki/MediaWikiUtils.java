package org.ireas.mediawiki;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.ireas.mediawiki.exceptions.InvalidResponseException;
import org.ireas.mediawiki.exceptions.MediaWikiException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.base.Preconditions;

/**
 * Utility class for {@code org.ireas.mediawiki}. This class contains helper
 * methods for common operations as closing objects or parsing the API output.
 *
 * @author ireas
 */
public final class MediaWikiUtils {

    private static final DateTimeFormatter API_TIMESTAMP_FORMAT =
            ISODateTimeFormat.dateTimeNoMillis();

    /**
     * Constructs an {@code URI} object from {@code scheme}, {@code host},
     * {@code port} and {@code apiPath}. The constructed URI will have the
     * structure {@code "<scheme>://<host>:<port>/<apiPath>"}.
     *
     * @param scheme
     *            the scheme of the URI, e. g. {@code "https"}
     * @param host
     *            the host of the URI, e. g. {@code "example.org"}
     * @param port
     *            the port of the URI, e. g. {@code 443}. The port must be
     *            non-negative.
     * @param path
     *            the path of the URI, e. g. {@code "/index.html"}
     * @return an {@code URI} constructed from the given parts
     * @throws MediaWikiException
     *             if the given parts do not form a valid URI
     * @throws NullPointerException
     *             if {@code scheme}, {@code host} or {@code path} is
     *             {@code null}
     * @throws IllegalArgumentException
     *             if {@code port} is negative
     */
    public static URI buildUri(final String scheme, final String host,
            final int port, final String path) throws MediaWikiException {
        Preconditions.checkNotNull(scheme);
        Preconditions.checkNotNull(host);
        Preconditions.checkNotNull(path);
        Preconditions.checkArgument(port >= 0);

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme(scheme);
        uriBuilder.setHost(host);
        uriBuilder.setPort(port);
        uriBuilder.setPath(path);
        try {
            return uriBuilder.build();
        } catch (URISyntaxException exception) {
            throw new MediaWikiException(exception);
        }
    }

    /**
     * Closes {@code closeable} (if not {@code null}) and ignores any exception
     * thrown closing the object.
     *
     * @param closeable
     *            the object to close
     */
    public static void close(@Nullable final AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception exception) {
                ignoreException(exception);
            }
        }
    }

    /**
     * Closes all objects in {@code closeables} and ignores any exceptions
     * thrown closing the objects. All objects will be closed, regardless
     * whether an exception occurs. The given objects may be {@code null}.
     *
     * @param closeables
     *            a list of objects to close. Elements of the list may be
     *            {@code null}
     */
    public static void close(final AutoCloseable... closeables) {
        for (@Nullable
        AutoCloseable closeable : closeables) {
            close(closeable);
        }
    }

    /**
     * Closes all objects in {@code closeables} and ignores any exceptions
     * thrown closing the objects. All objects will be closed, regardless
     * whether an exception occurs. The elements in the collection may be
     * {@code null}.
     *
     * @param closeables
     *            a collection of objects to close. Elements of the collection
     *            may be {@code null}.
     */
    public static void close(
            final Collection<? extends AutoCloseable> closeables) {
        for (@Nullable
        AutoCloseable closeable : closeables) {
            close(closeable);
        }
    }

    public static String formatApiDate(final DateTime date) {
        Preconditions.checkNotNull(date);

        return API_TIMESTAMP_FORMAT.print(date.toDateTime(DateTimeZone.UTC));
    }

    /**
     * Does nothing to the given exception.  May be useful to avoid warnings
     * about empty blocks in {@code catch} clauses where an exception is
     * ignored on purpose.  Use with care.
     *
     * @param exception
     *            the exception to be ignored
     */
    public static void ignoreException(@Nullable final Exception exception) {

    }

    /**
     * Constructs a list of {@code NameValuePair} objects from {@code arguments}.
     *
     *
     * @param arguments
     *            the map to be converted to a list of {@code NameValuePair}
     *            objects
     * @return a list of {@code NameValuePair} objects constructed from
     *         {@code arguments}
     * @throws NullPointerException
     *             if {@code arguments}, a key of {@code arguments} or a value
     *             of {@code arguments} is {@code null}
     */
    public static List<NameValuePair> mapToNameValuePairs(
            final Map<String, String> arguments) {
        Preconditions.checkNotNull(arguments);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String key : arguments.keySet()) {
            Preconditions.checkNotNull(key);
            String value = arguments.get(key);
            Preconditions.checkNotNull(value);

            NameValuePair nameValuePair = new BasicNameValuePair(key, value);
            nameValuePairs.add(nameValuePair);
        }
        return nameValuePairs;
    }

    /**
     * Parses a {@code timeStamp} as returned by the MediaWiki API and returns a
     * {@code Date} for that timestamp. MediaWiki API timestamps are in the
     * format {@code "yyyy-MM-dd'T'HH:mm:ssZZ"}.
     *
     * @param timeStamp
     *            the API timestamp to parse
     * @return a {@code Date} representing the MediaWiki API {@code timeStamp}
     * @throws NullPointerException
     *             if {@code timeStamp} is {@code null}
     */
    public static DateTime parseApiTimestamp(final String timeStamp) {
        Preconditions.checkNotNull(timeStamp);

        return API_TIMESTAMP_FORMAT.parseDateTime(timeStamp);
    }

    public static void requireJsonFields(final JSONObject object,
            final String... fields) throws InvalidResponseException {
        for (String field : fields) {
            if (!object.has(field)) {
                throw new InvalidResponseException(String.format(
                        "Field missing: %s", field));
            }
        }
    }

    public static void requireJsonLength(final JSONArray array,
            final int requiredLength) throws InvalidResponseException {
        if (array.length() < requiredLength) {
            throw new InvalidResponseException();
        }
    }

    private MediaWikiUtils() {
        throw new UnsupportedOperationException();
    }

}
