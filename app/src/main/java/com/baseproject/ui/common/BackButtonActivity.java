package com.baseproject.ui.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import com.baseproject.R;

/**
 * This abstract activity adds back button support into toolbar. Main content view is set with
 * <code>setContentView(id)</code>,
 * and drawer view is set by <code>setDrawerView(id)</code>.
 * <p/>
 * Created by Nishant Shah on 5.1.2016.
 */
public abstract class BackButtonActivity extends BaseActivity {
	private Toolbar toolbar;
	private FrameLayout baseLayout;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_back_button);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				onBackPressed();
			}
		});

		baseLayout = (FrameLayout) findViewById(R.id.baseLayout);
	}

	@Override public void setContentView(int id) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(id, this.baseLayout);
	}

	@Override public void onBackPressed() {
		super.onBackPressed();
		supportFinishAfterTransition();
	}

	@Override public boolean onOptionsItemSelected(MenuItem item) {
		onBackPressed();
		supportFinishAfterTransition();
		return super.onOptionsItemSelected(item);
	}

	// getters for layout components
	protected Toolbar getToolbar() {
		return toolbar;
	}
}
