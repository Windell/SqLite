package com.jamdeo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	public static final String TABLE_PROGRAM = "";
	public static final String CREATE_TABLE_CATEGORY = "CREATE TABLE if not exists category(_id INTEGER PRIMARY KEY AUTOINCREMENT, categoryid INTEGER, name VARCHAR, description TEXT);";
	public static final String DATABASE_NAME = "liveTV.db";

	public MySQLiteHelper(Context context, int version) {
		super(context, DATABASE_NAME, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_CATEGORY);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
