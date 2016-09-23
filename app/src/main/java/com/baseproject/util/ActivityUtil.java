package com.baseproject.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.baseproject.ui.main.MainActivity;
import java.util.Map;

/**
 * Utility class for Activities.
 * <p>
 * Created by Nishant on 16.11.15..
 */
public final class ActivityUtil
{

	private static final String TAG = ActivityUtil.class.getSimpleName();

	private ActivityUtil()
	{
	}

	public static void logout( Activity activity )
	{
		ActivityUtil.redirect( activity, MainActivity.class );
	}

	public static void replace( Activity activity, Class< ? extends Activity > activityClass, Map< String, Object > params )
	{
		activity.finish();

		Intent intent = new Intent( activity, activityClass );

		if ( params != null )
		{
			for ( Map.Entry< String, Object > entry : params.entrySet() )
			{
				intent.putExtra( entry.getKey(), entry.getValue().toString() );
			}
		}

		// remove activity from hierarchy, so it won't be invoked with Back button
		intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK );
		intent.addFlags( Intent.FLAG_ACTIVITY_NO_ANIMATION );

		Log.d( TAG, "Replacing " + activity.getClass().getSimpleName() + " with " + activityClass.getSimpleName() );

		activity.startActivity( intent );
		activity.overridePendingTransition( 0, 0 );
	}

	public static void replace( Activity activity, Class< ? extends Activity > activityClass )
	{
		replace( activity, activityClass, null );
	}

	public static void redirect( Activity activity, Class< ? extends Activity > activityClass, Map< String, Object > params )
	{
		activity.finish();

		Intent intent = new Intent( activity, activityClass );

		if ( params != null )
		{
			for ( Map.Entry< String, Object > entry : params.entrySet() )
			{
				intent.putExtra( entry.getKey(), entry.getValue().toString() );
			}
		}

		// remove activity from hierarchy, so it won't be invoked with Back button
		intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK );

		Log.d( TAG, "Redirection from " + activity.getClass().getSimpleName() + " to " + activityClass.getSimpleName() );

		activity.startActivity( intent );
	}

	public static void redirect( Activity activity, Class< ? extends Activity > activityClass )
	{
		redirect( activity, activityClass, null );
	}

	public static boolean sdkBeforeM()
	{
		return Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
	}

	@SuppressLint( "NewApi" )
	@SuppressWarnings( "deprecation" )
	public static int getResColor( Context context, int resId )
	{
		return sdkBeforeM() ? context.getResources().getColor( resId ) : context.getColor( resId );
	}


	@SuppressLint( "NewApi" )
	@SuppressWarnings( "deprecation" )
	public static int[] getResColors( Context context, int... resIds )
	{
		int[] colors = new int[resIds.length];

		for ( int i = 0; i < resIds.length; i++ )
		{
			colors[i] = sdkBeforeM() ? context.getResources().getColor( resIds[i] ) : context.getColor( resIds[i] );
		}

		return colors;
	}

	public static String getDeviceName()
	{
		String manufacturer = Build.MANUFACTURER;
		String model        = Build.MODEL;
		if ( model.startsWith( manufacturer ) )
		{
			return StringUtil.capitalize( model );
		}
		else
		{
			return StringUtil.capitalize( manufacturer ) + " " + model;
		}
	}
}
