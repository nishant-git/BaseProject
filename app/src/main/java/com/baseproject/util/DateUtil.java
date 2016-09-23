package com.baseproject.util;

import android.text.format.DateUtils;
import com.baseproject.Constants;
import com.baseproject.GlobalVars;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Utility class for date/time manipulation.
 * <p/>
 * Created by Nishant on 17.11.15..
 */
public final class DateUtil
{

	public static final String TIMESTAMP_OUTPUT_FORMAT  = "yyyy-MM-dd'T'HH:mm:ss.SSS'+00:00'";
	public static final String TIMESTAMP_MESSAGE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'+00:00'";//02/05/2015 9:46 AM

	private DateUtil()
	{
	}

	public static DateFormat getFormatter()
	{
		SimpleDateFormat formatter = new SimpleDateFormat( TIMESTAMP_OUTPUT_FORMAT, Locale.US );
		formatter.setTimeZone( Constants.TZ_UTC );
		return formatter;
	}

	public static Date toDate( String s )
	{
		if ( s == null )
		{
			return null;
		}

		DateTimeFormatter parser = ISODateTimeFormat.dateTimeParser().withZoneUTC();
		return parser.parseDateTime( s ).toDate();
	}

	public static String fromDate( Date date )
	{
		if ( date == null )
		{
			return null;
		}

		DateFormat formatter = getFormatter();

		return formatter.format( date );
	}

	public static String getDisplayDate( Date rawDate )
	{
		if ( rawDate == null )
		{
			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat( GlobalVars.currentDateOutputFormat, Locale.US );

		return dateFormat.format( rawDate );
	}


	public static String getDisplayDate( Calendar calendar )
	{
		return getDisplayDate( calendar.getTime() );
	}

	public static String getDisplayDateTime( Date rawDate )
	{
		if ( rawDate == null )
		{
			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat( GlobalVars.currentDateTimeOutputFormat, Locale.US );

		return dateFormat.format( rawDate );
	}

	public static String getDayName( Date date )
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat( GlobalVars.currentDayOutputFormat, Locale.US );
		return dateFormat.format( date );
	}

	public static String getDisplayDateTime( Calendar calendar )
	{
		return getDisplayDateTime( calendar.getTime() );
	}

	public static String getDisplayTime( Date rawDate )
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat( GlobalVars.currentTimeOutputFormat, Locale.US );

		return dateFormat.format( rawDate );
	}

	public static String getDisplayDateMonthName( Date rawDate )
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat( GlobalVars.currentDateMonthNameOutputFormat, Locale.US );

		return dateFormat.format( rawDate );
	}

	public static String calculateTimeAgo( Date date, Date serverTime )
	{
		return DateUtils.getRelativeTimeSpanString(
				date.getTime(),
				serverTime.getTime(),
				DateUtils.SECOND_IN_MILLIS ).toString();
	}

	public static boolean isSameDay( Date date1, Date date2 )
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime( date1 );
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime( date2 );
		return isSameDay( cal1, cal2 );
	}


	public static boolean isSameDay( Calendar cal1, Calendar cal2 )
	{
		return ( cal1.get( Calendar.ERA ) == cal2.get( Calendar.ERA ) &&
				cal1.get( Calendar.YEAR ) == cal2.get( Calendar.YEAR ) &&
				cal1.get( Calendar.DAY_OF_YEAR ) == cal2.get( Calendar.DAY_OF_YEAR ) );
	}

	public static boolean isPastDue( Date dueDate )
	{
		Date currentDate = new Date();
		//if > 0 that means current date is greater than due date.
		return currentDate.compareTo( dueDate ) > 0;
	}
}
