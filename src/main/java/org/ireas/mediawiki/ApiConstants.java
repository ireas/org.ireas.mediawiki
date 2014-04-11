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

/**
 * Provides constants used by the MediaWiki API, e. g. the names of the
 * parameters, keywords and the name of result fields.  For a detailed
 * explanation of the values, consult the MediaWiki API documentation.
 *
 * @author ireas
 */
public final class ApiConstants {

    /**
     * Name of the parameter {@code action}.
     */
    public static final String ACTION = "action";

    /**
     * Name of the action {@code query}.
     */
    public static final String ACTION_QUERY = "query";

    /**
     * Name of the parameter {@code format}.
     */
    public static final String FORMAT = "format";

    /**
     * Name of the format {@code json}.
     */
    public static final String FORMAT_JSON = "json";

    /**
     * Name of the parameter {@code list}.
     */
    public static final String LIST = "list";

    /**
     * Name of the list {@code usercontribs}.
     */
    public static final String LIST_USERCONTRIBS = "usercontribs";

    /**
     * Name of the list {@code users}.
     */
    public static final String LIST_USERS = "users";

    /**
     * Key of the result field {@code error}.
     */
    public static final String RESULT_ERROR = "error";

    /**
     * Key of the result field {@code error.code}.
     */
    public static final String RESULT_ERROR_CODE = "code";

    /**
     * Key of the result field {@code usercontribs}.
     */
    public static final String RESULT_USERCONTRIBS = "usercontribs";

    /**
     * Key of the result field {@code usercontribs.timestamp}.
     */
    public static final String RESULT_UC_TIMESTAMP = "timestamp";

    /**
     * Key of the result field {@code users}.
     */
    public static final String RESULT_USERS = "users";

    /**
     * Key of the result field {@code users.userid}.
     */
    public static final String RESULT_US_ID = "userid";

    /**
     * Key of the result field {@code users.missing}.
     */
    public static final String RESULT_US_MISSING = "missing";

    /**
     * Key of the result field {@code users.name}.
     */
    public static final String RESULT_US_NAME = "name";

    /**
     * Key of the result field {@code users.registration}.
     */
    public static final String RESULT_US_REGISTRATION = "registration";

    /**
     * Separator symbol for parameter values.
     */
    public static final String SEPARATOR = "|";

    /**
     * Name of the argument {@code ucdir}.
     */
    public static final String UC_DIR = "ucdir";

    /**
     * Name of the user contributions direction {@code newer}.
     */
    public static final String UC_DIR_NEWER = "newer";

    /**
     * Name of the argument {@code ucend}.
     */
    public static final String UC_END = "ucend";

    /**
     * Name of the argument {@code uclimit}.
     */
    public static final String UC_LIMIT = "uclimit";

    /**
     * Name of the argument {@code ucnamespaces}.
     */
    public static final String UC_NAMESPACE = "ucnamespace";

    /**
     * Name of the argument {@code ucpro}.
     */
    public static final String UC_PROP = "ucprop";

    /**
     * Name of the argument {@code ucstart}.
     */
    public static final String UC_START = "ucstart";

    /**
     * Name of the argument {@code ucuser}.
     */
    public static final String UC_USER = "ucuser";

    /**
     * Name of the argument {@code usprop}.
     */
    public static final String US_PROP = "usprop";

    /**
     * Name of the user property {@code registration}.
     */
    public static final String US_PROP_REGISTRATION = "registration";

    /**
     * Name of the argument {@code ususers}.
     */
    public static final String US_USERS = "ususers";

    private ApiConstants() {
        throw new UnsupportedOperationException();
    }

}
