package com.baseproject.ui;

import android.os.Bundle;
import com.baseproject.ui.common.ImagePickerActivity;

/**
 * Created by Nishant Shah on 20-Sep-16.
 */
public class ProfileActivity extends ImagePickerActivity {

	@Override public void getPickedImage() {
	}

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pickImage();
	}
}
