package com.baseproject.ui.common;

import android.content.Intent;
import android.util.Log;
import com.baseproject.util.ImageUtil;

/**
 * Created by Nishant Shah on 20-Sep-16.
 */
public abstract class ImagePickerActivity extends BackButtonActivity {

	public abstract void getPickedImage();

	protected void pickImage(){
		ImageUtil.dispatchPickImageIntent(this, 0);
	}
	@Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == ImageUtil.REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
			Log.d("CameraDemo", "Pic saved");
		}
	}
}
