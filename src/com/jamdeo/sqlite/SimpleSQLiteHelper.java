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
public class SimpleSQLiteHelper extends SQLiteOpenHelper {
	// When i was designing this program ,i am thinking about the CMS(Content
	// Management System), so there is category, program and so on..
	// TODO creation SQL should be read from some file
	private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE if not exists category"
			+ "( categoryid INTEGER primary key, name VARCHAR, description TEXT);";
	private static final String CREATE_TABLE_PROGRAM = "CREATE TABLE if not exists program "
			+ " (programid LONG primary key, channelid LONG, programname VARCHAR, description TEXT, starttime INTEGER, endtime INTEGER,"
			+ "favoriteflag INTEGER, programtype INTEGER, rate INTEGER, poster VARCHAR, thumbnail VARCHAR,createdtime INTEGER"
			+ ",lastedUpdatedtime INTEGER);";
	private static final String CREATE_TABLE_CATEGORYDETAIL = "CREATE table if not exists categorydetail"
			+ "(_id integer primary key autoincrement, programid integer,categoryid integer,displayorder integer,"
			+ "foreign key (programid) references program(programid) on delete cascade);";
	private static final String CREATE_TABLE_CHANNEL = "CREATE TABLE if not exists channel"
			+ "(channelid LONG primary key, channelname VARCHAR, channelnumber INTEGER, favoriteflag NUMERIC, logo VARCHAR);";

	private static final String CREATE_VIEW_CATEGORYPROGRAM = "CREATE VIEW CategoryProgram AS select  c.categoryid as categoryid,c.name as categoryname,"
			+ " p.programid as programid, p.programname as name,p.description as programdesc,p.starttime as starttime, p.endtime as endtime,"
			+ " p.programtype as programtype,p.favoriteflag as favoriteflag,p.rate as rate,p.poster as poster, p.thumbnail as thumbnail,"
			+ "d.displayorder as displayorder from category as c, program as p ,categorydetail as d "
			+ "where c.categoryid=d.categoryid and p.programid=d.programid;";
	private static final String CREATE_VIEW_CHANNELPROGRAM = "CREATE VIEW channelprogram AS select c.channelid as channelid, channelname, channelnumber,"
			+ "c.favoriteflag as channelfavorite,c.logo as channellogo,p.programid as programid,p.programname as programname,description,starttime,"
			+ "endtime,p.favoriteflag as programfavorite,programtype,rate,poster,thumbnail from channel  as c, program as p where c.channelid=p.channelid";

	private static final String DATABASE_NAME = "liveTV.db";

	public SimpleSQLiteHelper(Context context, int version) {
		super(context, DATABASE_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO View, Foreign key, and Index to be added.
		db.execSQL("PRAGMA foreign_keys=ON");
		db.execSQL(CREATE_TABLE_CATEGORY);
		db.execSQL(CREATE_TABLE_CHANNEL);
		db.execSQL(CREATE_TABLE_PROGRAM);
		db.execSQL(CREATE_TABLE_CATEGORYDETAIL);
		db.execSQL(CREATE_VIEW_CATEGORYPROGRAM);
		db.execSQL(CREATE_VIEW_CHANNELPROGRAM);
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
