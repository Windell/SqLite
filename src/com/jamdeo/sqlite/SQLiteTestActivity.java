package com.jamdeo.sqlite;

import android.app.Activity;
import android.os.Bundle;

public class SQLiteTestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		MySQLiteHelper sqlite = new MySQLiteHelper(this, 0);
	}
}