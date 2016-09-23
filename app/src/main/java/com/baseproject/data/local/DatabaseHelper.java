package com.baseproject.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nishant Shah on 20-Sep-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "app_data.db";
	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context) {
		/*
		 * The reason of passing null is you want the standard SQLiteCursor behaviour.
		 * If you want to implement a specialized Cursor you can get it by by extending
		 * the Cursor class( this is for doing additional operations on the query results).
		 * And in these cases, you can use the CursorFactory class to return an instance
		 * of your Cursor implementation
		 */
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override public void onCreate(SQLiteDatabase sqLiteDatabase) {

	}

	@Override public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

	}
}
