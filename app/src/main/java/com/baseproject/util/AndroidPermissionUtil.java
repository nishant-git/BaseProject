package com.baseproject.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Nishant Shah on 20-Sep-16.
 */
public class AndroidPermissionUtil {
	private static final int AUDIO_PERMISSION_REQUEST_CODE = 101;
	private static final int PERMISSION_REQUEST_CODE = 102;
	private static final int CAMERA_PERMISSION_REQUEST_CODE = 103;

	private Activity activity;
	private String[] permissionsMethods = new String[] {
			"requestPermissionForCamera", "requestPermissionForStorage", "requestPermissionForRecordAudio"
	};
	private int methodCallCount = 0;

	public AndroidPermissionUtil() {
	}

	public AndroidPermissionUtil(Activity activity) {
		this.activity = activity;
		// askForNextPermission();
	}

	private static boolean isAndroid6() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M; // If we are on M or above
	}

	public static void requestPermissionForStorage(Activity activity) {
		if (isAndroid6() && !checkPermissionForSD(activity)) {
			ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, PERMISSION_REQUEST_CODE);
		}
	}

	public void requestPermissionForRecordAudio() {
		if (isAndroid6() && !checkPermissionForAudio()) {
			ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.RECORD_AUDIO },
					PERMISSION_REQUEST_CODE);
		}
	}

	public void requestPermissionForCamera() {
		if (isAndroid6() && !checkPermissionForCamera()) {
			ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.CAMERA },
					PERMISSION_REQUEST_CODE);
		}
	}

	public boolean checkPermissionForCamera() {
		int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
		if (result == PackageManager.PERMISSION_GRANTED) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkPermissionForSD(Activity activity) {
		int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		if (result == PackageManager.PERMISSION_GRANTED) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPermissionForAudio() {
		int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);
		if (result == PackageManager.PERMISSION_GRANTED) {
			return true;
		} else {
			return false;
		}
	}

	public void handlePermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		switch (requestCode) {
			case PERMISSION_REQUEST_CODE: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

					// permission was granted, yay! Do the
					// contacts-related task you need to do.
					methodCallCount++;
					askForNextPermission();
				} else {

					// permission denied, boo! Disable the
					// functionality that depends on this permission.
					activity.finish();
				}
				return;
			}

			// other 'case' lines to check for other
			// permissions this app might request
		}
	}

	public void askForNextPermission() {
		if (methodCallCount >= permissionsMethods.length) {
			return;
		}
		try {
			//no paramater
			Class noparams[] = {};
			Class<?> c = Class.forName("com.baseproject.util.AndroidPermissionUtil");
			Method method = c.getDeclaredMethod(permissionsMethods[methodCallCount], noparams);
			method.invoke(this, noparams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
