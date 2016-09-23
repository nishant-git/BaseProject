package com.baseproject.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;

/**
 * Utility class for file and file system.
 * <p/>
 * Created by Nishant on 24.11.15..
 */
public final class FileUtil
{
	private static final String TAG = FileUtil.class.getSimpleName();

	private FileUtil()
	{

	}

	public static boolean isExternalStorageRemovable()
	{
		return Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD || Environment.isExternalStorageRemovable();
	}

	public static File getExternalCacheDir( Context context )
	{
		if ( hasExternalCacheDir() )
		{
			return context.getExternalCacheDir();
		}

		// Before Froyo we need to construct the external cache dir ourselves
		final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
		return new File( Environment.getExternalStorageDirectory().getPath() + cacheDir );
	}

	public static boolean hasExternalCacheDir()
	{
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	}

	public static File getDiskCacheDir( Context context, String uniqueName )
	{

		// Check if media is mounted or storage is built-in,
		// if so, try and use external cache dir
		// otherwise use internal cache dir
		String cachePath = context.getCacheDir().getPath();

		try
		{
			if ( Environment.MEDIA_MOUNTED.equals( Environment.getExternalStorageState() ) && !isExternalStorageRemovable() )
			{
				cachePath = getExternalCacheDir( context ).getPath();
			}
		}
		catch ( Exception e )
		{
			Log.e( TAG, "Unable to find external cache path", e );
		}

		Log.i( TAG, String.format( "Cache Path: %1$s", cachePath ) );

		return new File( cachePath + File.separator + uniqueName );
	}

	// url = file path or whatever suitable URL you want.
	public static String getMimeType(String url) {
		String type = null;
		String extension = MimeTypeMap.getFileExtensionFromUrl( url);
		if (extension != null) {
			type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
		}
		return type;
	}
}
