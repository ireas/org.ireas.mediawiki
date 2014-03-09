package org.ireas.mediawiki;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.google.common.base.Preconditions;

public final class MediaWikiUtils {
	
	private final static DateTimeFormatter API_TIMESTAMP_FORMAT = ISODateTimeFormat.dateTimeNoMillis();

	private MediaWikiUtils() {
		throw new UnsupportedOperationException();
	}
	
	public static void close(AutoCloseable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception exception) {
				// ignore exception
			}
		}
	}
	
	public static void close(Collection<? extends AutoCloseable> closeables) {
		for (AutoCloseable closeable : closeables) {
			close(closeable);
		}
	}
	
	public static void close(AutoCloseable... closeables) {
		for (AutoCloseable closeable : closeables) {
			close(closeable);
		}
	}
	
	public static List<NameValuePair> mapToNameValuePairs(Map<String, String> arguments) {
		Preconditions.checkNotNull(arguments);
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : arguments.keySet()) {
			String value = arguments.get(key);
			NameValuePair nameValuePair = new BasicNameValuePair(key, value);
			nameValuePairs.add(nameValuePair);
		}
		return nameValuePairs;
	}
	
	public static URI buildUri(String scheme, String host, int port, String apiPath) throws MediaWikiException {
		if (scheme == null || host == null || port < 0 || apiPath == null) {
			throw new IllegalArgumentException();
		}
		
		URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme(scheme);
		uriBuilder.setHost(host);
		uriBuilder.setPort(port);
		uriBuilder.setPath(apiPath);
		try {
			return uriBuilder.build();
		} catch (URISyntaxException exception) {
			throw new MediaWikiException(exception);
		}
	}
	
	public static Date parseApiTimestamp(String timeStamp) {
		if (timeStamp == null) {
			throw new IllegalArgumentException();
		}
		return API_TIMESTAMP_FORMAT.parseDateTime(timeStamp).toDate();
	}
	
}
