package org.ireas.mediawiki;

import java.io.Closeable;
import java.util.Map;
import java.util.Set;

import org.ireas.mediawiki.data.MediaWikiData;
import org.ireas.mediawiki.data.UserData;
import org.ireas.mediawiki.exceptions.HttpMediaWikiException;
import org.ireas.mediawiki.exceptions.InvalidResponseException;
import org.ireas.mediawiki.exceptions.MediaWikiException;
import org.ireas.mediawiki.exceptions.NoSuchUserException;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.JSONObject;

import com.google.common.base.Optional;

/**
 * Interface to the API of a MediaWiki installation.  This class accesses
 * the {@code api.php} file of a MediaWiki installation and parses the
 * result in the JSON format.  Instead of directly performing requests using
 * the {@code performRequest} or {@code performJsonRequest} methods, use the
 * predefined methods.  To receive an instance of this class, use the
 * {@link MediaWikiFactory}.
 * <p>
 * The following requests are supported at the moment:
 * <table>
 *   <thead>
 *     <tr><th>API request</th><th>Method</th></tr>
 *   </thead>
 *   <tbody>
 *     <tr>
 *       <td>{@code action=query&list=usercontribs} (counts)</td>
 *       <td>{@code getContribCount}</td>
 *     </tr>
 *     <tr>
 *       <td>{@code action=query&list=usercontribs} (first edit)</td>
 *       <td>{@code getFirstEdit}</td>
 *     </tr>
 *     <tr>
 *       <td>{@code action=query&list=users} (single user)</td>
 *       <td>{@code getUserData}</td>
 *     </tr>
 *     <tr>
 *   </tbody>
 * </table>
 *
 * @author ireas
 * @see MediaWikiFactory
 */
public interface MediaWiki extends Closeable {

    /**
     * Returns the contribution count for the specified user using
     * the specified limit.  If the user does not exist, zero is returned.
     *
     * @param user the name of the user to get the contribution count
     * @param limit the maximum contribution count
     * @return the contribution count of the specified user, at maximum
     *         the specified limit
     * @throws InvalidResponseException if the API response cannot be parsed
     * @throws HttpMediaWikiException if an HTTP error occurs
     * @throws MediaWikiException if an error occurs during the request
     * @throws NullPointerException if the specified user is null
     * @throws IllegalArgumentException if the specified limit is less than one
     */
    int getContribCount(final String user, final int limit)
            throws MediaWikiException;

    /**
     * Returns the contribution count for the specified user in the specified
     * namespaces using the specified limit.  If the user does not exist, zero
     * is returned.  If the set of namespaces is empty, all namespaces are
     * used.
     *
     * @param user the name of the user to get the contribution count
     * @param limit the maximum contribution count
     * @param namespaces the namespaces to count
     * @return the contribution count of the specified user, at maximum
     *         the specified limit
     * @throws InvalidResponseException if the API response cannot be parsed
     * @throws HttpMediaWikiException if an HTTP error occurs
     * @throws MediaWikiException if an error occurs during the request
     * @throws NullPointerException if the specified user or namespaces are
     *         null
     * @throws IllegalArgumentException if the specified limit is less than one
     */
    int getContribCount(final String user, final int limit,
            final Set<Namespace> namespaces) throws MediaWikiException;

    /**
     * Returns the contribution count for the specified user in the specified
     * namespaces until the specified end date using the specified limit.  If
     * the user does not exist, zero is returned.  If the set of namespaces is
     * empty, all namespaces are used.
     *
     * @param user the name of the user to get the contribution count
     * @param limit the maximum contribution count
     * @param namespaces the namespaces to count
     * @param endDate the date until which contributions will be counted
     * @return the contribution count of the specified user, at maximum
     *         the specified limit
     * @throws InvalidResponseException if the API response cannot be parsed
     * @throws HttpMediaWikiException if an HTTP error occurs
     * @throws MediaWikiException if an error occurs during the request
     * @throws NullPointerException if the specified user, namespaces or end
     *         date are null
     * @throws IllegalArgumentException if the specified limit is less than one
     */
    int getContribCount(final String user, final int limit,
            final Set<Namespace> namespaces, final DateTime endDate)
            throws MediaWikiException;

    /**
     * Returns the contribution count for the specified user in the specified
     * namespaces until the specified end date within the specified period
     * using the specified limit.  If the user does not exist, zero is
     * returned.  If the set of namespaces is empty, all namespaces are used.
     * The specified period is subtracted from the specified end date to
     * calculate the start date.
     *
     * @param user the name of the user to get the contribution count
     * @param limit the maximum contribution count
     * @param namespaces the namespaces to count
     * @param endDate the date until which contributions will be counted
     * @param period the period before the end date in which the contributions
     *        will be counted
     * @return the contribution count of the specified user, at maximum
     *         the specified limit
     * @throws InvalidResponseException if the API response cannot be parsed
     * @throws HttpMediaWikiException if an HTTP error occurs
     * @throws MediaWikiException if an error occurs during the request
     * @throws NullPointerException if the specified user, namespaces, end
     *         date or period are null
     * @throws IllegalArgumentException if the specified limit is less than one
     */
    int getContribCount(final String user, final int limit,
            final Set<Namespace> namespaces, final DateTime endDate,
            final Period period) throws MediaWikiException;

    /**
     * Returns the date of the first edit for the specified user.  If the
     * user does not exist or has not edited yet, an absent object is
     * returned.
     *
     * @param user the name of the user to get the first edit for
     * @return the date of the user’s first edit
     * @throws InvalidResponseException if the API response cannot be parsed
     * @throws HttpMediaWikiException if an HTTP error occurs
     * @throws MediaWikiException if an error occurs during the request
     * @throws NullPointerException if the specified user is null
     */
    Optional<DateTime> getFirstEdit(String user) throws MediaWikiException;

    /**
     * Returns the data of the MediaWiki installation that is accessed by this
     * object.
     *
     * @return the data of the MediaWiki installation to access
     */
    MediaWikiData getMediaWikiData();

    /**
     * Returns the user date for the user with the given name.  The user data
     * consists of the name (as returned by the API), the MediaWiki ID and the
     * registration date.
     *
     * @param user the name of the user to get the date for
     * @return the detail data for the specified user
     * @throws NoSuchUserException if there is no user with the specified name
     * @throws InvalidResponseException if the API response cannot be parsed
     * @throws HttpMediaWikiException if an HTTP error occurs
     * @throws MediaWikiException if an error occurs during the request
     * @throws NullPointerException if the specified user is null
     */
    UserData getUserData(String user) throws MediaWikiException;

    /**
     * Performs an API request on the specified action using the specified
     * arguments and returns the action result.  This method uses the JSON
     * format.  (This format cannot be overwritten by the values in the
     * specified arguments.)  If the API returns an error, a {@code
     * MediaWikiException} is thrown.  Otherwise the JSON object with the
     * same name as the specified action is returned (if it exists).
     *
     * @param action the name of the action to request
     * @param arguments the arguments to pass to the API
     * @return the action result in the JSON format
     * @throws HttpMediaWikiException if an HTTP error occurs
     * @throws MediaWikiException if an error occurs during the request
     * @throws NullPointerException if the specified action or arguments map is
     *         null
     * @throws IllegalArgumentException if the specified action is empty
     */
    JSONObject performJsonRequest(String action, Map<String, String> arguments)
            throws MediaWikiException;

    /**
     * Performs an API request using the specified arguments and returns the
     * raw result.
     *
     * @param arguments the arguments to pass to the API
     * @return the raw API result
     * @throws HttpMediaWikiException if an HTTP error occurs
     * @throws MediaWikiException if an error occurs during the request
     * @throws NullPointerException if the specified arguments map is null
     */
    String performRequest(Map<String, String> arguments)
            throws MediaWikiException;

}
