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

package org.ireas.mediawiki;

import java.io.Closeable;
import java.util.Map;
import java.util.Set;

import org.ireas.mediawiki.data.MediaWikiData;
import org.ireas.mediawiki.data.Namespace;
import org.ireas.mediawiki.data.TokenType;
import org.ireas.mediawiki.data.UserData;
import org.ireas.mediawiki.exceptions.HttpMediaWikiException;
import org.ireas.mediawiki.exceptions.InvalidResponseException;
import org.ireas.mediawiki.exceptions.MediaWikiException;
import org.ireas.mediawiki.exceptions.NoSuchUserException;
import org.ireas.mediawiki.exceptions.WrongPasswordException;
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
 *       <td>{@code action=login}</td>
 *       <td>{@code login}</td>
 *     </tr>
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
 *       <td>{@code action=tokens}</td>
 *       <td>{@code getToken}</td>
 *     </tr>
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
     * Returns a token of the specified type.
     *
     * @param type the type of the token to get
     * @return a token of the specified type
     * @throws InvalidResponseException if the API response cannot be parsed
     * @throws HttpMediaWikiException if an HTTP error occurs
     * @throws MediaWikiException if an error occurs during the request
     * @throws NullPointerException if the specified type is null
     */
    String getToken(final TokenType type) throws MediaWikiException;

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
     * Logs in as the specified user using the specified password.  It is
     * strongly recommended to use this method only on SSL connections.  If
     * required, this method gets a login token and then uses the {@link
     * #login(String, String, String) login} method to authenticate.
     *
     * @param user the name of the user to login
     * @param password the password of the user to login
     * @throws NoSuchUserException if there is no user with the specified name
     * @throws WrongPasswordException if the specified password is wrong
     * @throws InvalidResponseException if the API response cannot be parsed
     * @throws HttpMediaWikiException if an HTTP error occurs
     * @throws MediaWikiException if an error occurs during the request
     * @throws NullPointerException if the specified user or password is null
     * @throws IllegalArgumentException if the specified user or password is
     *         empty
     */
    void login(String user, String password) throws MediaWikiException;

    /**
     * Logs in as the specified user using the specified password and the
     * specified login token.  It is strongly recommended to use this method
     * only on SSL connections.  Usually this method is called by the {@link
     * #login(String, String) login} method if a token is required.
     *
     * @param user the name of the user to login
     * @param password the password of the user to login
     * @param token the login token for the user
     * @throws NoSuchUserException if there is no user with the specified name
     * @throws WrongPasswordException if the specified password is wrong
     * @throws InvalidResponseException if the API response cannot be parsed
     * @throws HttpMediaWikiException if an HTTP error occurs
     * @throws MediaWikiException if an error occurs during the request
     * @throws NullPointerException if the specified user or password is null
     * @throws IllegalArgumentException if the specified user or password is
     *         empty
     */
    void login(String user, String password, String token)
            throws MediaWikiException;

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
