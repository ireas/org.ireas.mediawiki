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

package org.ireas.mediawiki.data;

import org.ireas.mediawiki.ApiConstants;

import com.google.common.base.Preconditions;

/**
 * Defines the token types that can be retrieved from the MediaWiki API.
 *
 * @author ireas
 */
public enum TokenType {

    /**
     * Block token type.
     */
    BLOCK(ApiConstants.TOKEN_BLOCK),

    /**
     * CentralAuth token type.
     */
    CENTRAL_AUTH(ApiConstants.TOKEN_CENTRAL_AUTH),

    /**
     * Delete token type.
     */
    DELETE(ApiConstants.TOKEN_DELETE),

    /**
     * Delete global account token type.
     */
    DELETE_GLOBAL_ACCOUNT(ApiConstants.TOKEN_DELETE_GLOBAL_ACCOUNT),

    /**
     * Edit token type.
     */
    EDIT(ApiConstants.TOKEN_EDIT),

    /**
     * Email token type.
     */
    EMAIL(ApiConstants.TOKEN_EMAIL),

    /**
     * Import token type.
     */
    IMPORT(ApiConstants.TOKEN_IMPORT),

    /**
     * Move token type.
     */
    MOVE(ApiConstants.TOKEN_MOVE),

    /**
     * Options token type.
     */
    OPTIONS(ApiConstants.TOKEN_OPTIONS),

    /**
     * Patrol token type.
     */
    PATROL(ApiConstants.TOKEN_PATROL),

    /**
     * Protect token type.
     */
    PROTECT(ApiConstants.TOKEN_PROTECT),

    /**
     * Set global account status token type.
     */
    SET_GLOBAL_ACCOUNT_STATUS(ApiConstants.TOKEN_SET_GLOBAL_ACCOUNT_STATUS),

    /**
     * Unblock token type.
     */
    UNBLOCK(ApiConstants.TOKEN_UNBLOCK),

    /**
     * Watch token type.
     */
    WATCH(ApiConstants.TOKEN_WATCH);

    private final String value;

    private TokenType(final String value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(!value.isEmpty());

        this.value = value;
    }

    /**
     * Returns the value of the token type, i. e. the name used in the API.
     *
     * @return the value of this token type
     */
    public String getValue() {
        return value;
    }

}
