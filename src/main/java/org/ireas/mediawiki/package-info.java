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

/**
 * Provides a simple interface to the MediaWiki API.
 * <p>
 * The main interface providing the access methods is {@code MediaWiki}.  To
 * create a new {@code MediaWiki} instance, use the {@code MediaWikiFactory}.
 * It is recommended to create a custom {@code MediaWikiConfiguration}
 * before creating a {@code MediaWiki}.
 * <pre>
 * // Step 1: configuration (set the user agent)
 * MediaWikiFactory.setConfiguration(new BasicMediaWikiConfiguration(
 *         "My user agent"));
 * // Step 2: creating a MediaWiki instance
 * MediaWiki mediaWiki = MediaWikiFactory.newWikipediaInstance("de");
 * // Step 3: accessing the API
 * mediaWiki.getFirstEdit("Ireas");
 * </pre>
 */
@ParametersAreNonnullByDefault
package org.ireas.mediawiki;

import javax.annotation.ParametersAreNonnullByDefault;

