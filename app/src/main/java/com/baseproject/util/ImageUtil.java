package com.baseproject.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Nishant Shah on 20-Sep-16.
 */
public class ImageUtil{

	public static final int REQUEST_TAKE_PHOTO = 1;

	public static File createImageFile(Activity activity) throws IOException {
	//	requestPermissionForStorage(activity);
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
		String imageFileName = timeStamp + "_";
		File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName,  /* prefix */
				".jpg",         /* suffix */
				storageDir      /* directory */);
		return image;
	}

	public static void dispatchPickImageIntent(Activity activity, int requestCode) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestCode);
	}

	public static void dispatchTakePictureIntent(Activity activity) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try {
				photoFile = createImageFile(activity);
			} catch (IOException ex) {
				// Error occurred while creating the File
				ex.printStackTrace();
			}
			// Continue only if the File was successfully created
			if (photoFile != null) {
				Uri photoURI = Uri.fromFile(photoFile);
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
				activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
			}
		}
	}
}
