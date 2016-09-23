package com.baseproject;

import java.nio.charset.Charset;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * System wide constants.
 * <p/>
 * Created by Nishant on 2015-11-15
 */
public class Constants
{
	// Application Configuration
	public static final String SERVER_URL_BASE = "https://"; // test server

	// Request HTTP Headers
	public static final String HTTP_HEADER_ACCEPT = "Accept";

	// Charset
	public static final String  UTF8         = "UTF-8";
	public static final Charset CHARSET_UTF8 = Charset.forName( UTF8 );

	// JSON Content type
	public static final String JSON_CONTENT_TYPE      = "application/json";
	public static final String JSON_CONTENT_TYPE_UTF8 = JSON_CONTENT_TYPE + "; charset=" + UTF8;

	// Time Zone
	public static final String   UTC    = "UTC";
	public static final TimeZone TZ_UTC = TimeZone.getTimeZone( UTC );

	// Network Timeout in ms
	public static final int NETWORK_REQUEST_TIMEOUT = 7000;


	public static final int MIN_NUMBER_OF_CHARACTERS_FOR_PASSWORD = 4;
	public static final int MAX_EMAILS                            = 3;

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile( "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE );
}
