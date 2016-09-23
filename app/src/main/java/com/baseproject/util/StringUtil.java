package com.baseproject.util;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class for string manipulation.
 * <p/>
 * Created by Nishant Shah on 18.11.15..
 */
public final class StringUtil
{

	private StringUtil()
	{
	}

	public static boolean isEmpty( String s )
	{
		return s == null || s.length() == 0;
	}

	public static String underscoreToCamel( String input )
	{
		boolean       wordStart = false;
		StringBuilder sb        = new StringBuilder();

		for ( int index = 0; index < input.length(); index++ )
		{
			char ch = input.charAt( index );
			if ( ch == '_' )
			{
				wordStart = true;
				continue;
			}

			if ( wordStart )
			{
				wordStart = false;
				ch = Character.toUpperCase( ch );
			}

			sb.append( ch );
		}

		return sb.toString();
	}

	public static String capitalize( String s )
	{
		if ( s == null || s.length() == 0 )
		{
			return "";
		}
		char first = s.charAt( 0 );
		if ( Character.isUpperCase( first ) )
		{
			return s;
		}
		else
		{
			return Character.toUpperCase( first ) + s.substring( 1 );
		}
	}

	public static List< String > commaSeparatedToList( String value )
	{
		return Arrays.asList( value.split( "\\s*,\\s*" ) );
	}
}
