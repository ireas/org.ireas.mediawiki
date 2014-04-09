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
