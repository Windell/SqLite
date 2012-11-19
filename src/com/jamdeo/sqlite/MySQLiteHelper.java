package com.jamdeo.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This is a basic subclass of SQLiteOpenHelper,contains some DDL operation
 * override and new added from its parent class.<br>
 * {@link #DataBaseManager}is relied on this and mostly using rawQuery, exeSQL
 * methods to operate database.
 * <p>
 * */
public class MySQLiteHelper extends SQLiteOpenHelper {
	// When i was designing this program ,i am thinking about the CMS(Content
	// Management System), so there is category, program and so on..
	public static final String CREATE_TABLE_CATEGORY = "CREATE TABLE if not exists category"
			+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, categoryid INTEGER, name VARCHAR, description TEXT);";
	public static final String CREATE_TABLE_PROGRAM = "CREATE TABLE if not exists program"
			+ "(programid INTEGER, programname VARCHAR,description TEXT,poster VARCHAR,thumbnail VARCHAR);";
	public static final String CREATE_TABLE_CATEGORYDETAIL = "CREATE table if not exists categorydetail"
			+ "(_id integer primary key autoincrement, prorgamid integer,categoryid integer,displayorder integer)";
	public static final String DATABASE_NAME = "liveTV.db";

	public MySQLiteHelper(Context context, int version) {
		super(context, DATABASE_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO View, Foreign key, and Index to be added.
		db.execSQL(CREATE_TABLE_CATEGORY);
		db.execSQL(CREATE_TABLE_PROGRAM);
		db.execSQL(CREATE_TABLE_CATEGORYDETAIL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion != 1)
			try {
				throw new Exception("Upgrade? I don't know!");
			} catch (Exception e) {
				e.printStackTrace();
			}

	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		super.onDowngrade(db, oldVersion, newVersion);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		Log.i("OnOpen", "Now is only a stub on onOpen()");
	}

	public Cursor doQuery(String table, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = this.getReadableDatabase();
		return db.query(table, projection, selection, selectionArgs, null,
				null, sortOrder);
	}

	public Cursor doQuery(String table, String idField, long id,
			String[] projection) {
		return doQuery(table, projection, " " + idField + "=?",
				new String[] { Long.toString(id) }, null);
	}
}
