package com.jamdeo.sqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * <p>
 * Content Provider for {@link #Category} {@link #CategoryDetail}
 * {@link #Program}.will mostly using override methods
 * <p>
 * 
 * */
public class MyContentProvider extends ContentProvider {

	private MySQLiteHelper sqlite = null;
	private static final int DATABASE_VERSION = 1;
	private static final UriMatcher matcher = new UriMatcher(
			UriMatcher.NO_MATCH);

	// temporary the following constants will be here
	public static final String AUTHRORITY = "com.jamdeo.livetv.provider";
	public static final String TABLE_CATEGORY = "category";
	public static final String TABLE_PROGRAM = "program";
	public static final String TABLE_CATEGORYDETAIL = "categorydetail";

	private static final int LIVETV_CATEGORYS = 1;
	private static final int LIVETV_CATEGORY = 2;
	private static final int LIVETV_PROGRAMS = 3;
	private static final int LIVETV_PROGRAM = 4;
	private static final int LIVETV_CATEGORYDETAILS = 5;
	private static final int LIVETV_CATEGORYDETAIL = 6;
	static {
		matcher.addURI(AUTHRORITY, TABLE_CATEGORY, LIVETV_CATEGORYS);
		matcher.addURI(AUTHRORITY, TABLE_CATEGORY + "/#", LIVETV_CATEGORY);
		matcher.addURI(AUTHRORITY, TABLE_PROGRAM, LIVETV_PROGRAMS);
		matcher.addURI(AUTHRORITY, TABLE_PROGRAM + "/#", LIVETV_PROGRAM);
		matcher.addURI(AUTHRORITY, TABLE_CATEGORYDETAIL, LIVETV_CATEGORYDETAILS);
		matcher.addURI(AUTHRORITY, TABLE_CATEGORYDETAIL + "/#",
				LIVETV_CATEGORYDETAIL);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		sqlite = new MySQLiteHelper(this.getContext(), DATABASE_VERSION);
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		switch (matcher.match(uri)) {
		case LIVETV_CATEGORYS:
			return sqlite.doQuery(TABLE_CATEGORY, projection, selection,
					selectionArgs, sortOrder);
		case LIVETV_CATEGORY:
			return sqlite.doQuery(TABLE_CATEGORY, "categoryid",
					Long.parseLong(uri.getLastPathSegment()), projection);
		}
		return null;
	}

	@Override
	public String getType(Uri uri) {
		switch (matcher.match(uri)) {
		case LIVETV_CATEGORYS:
		case LIVETV_PROGRAMS:
		case LIVETV_CATEGORYDETAILS:
			return "vnd.android.cursor.dir/media";
		case LIVETV_CATEGORY:
		case LIVETV_PROGRAM:
		case LIVETV_CATEGORYDETAIL:
			return "vnd.android.cursor.item/media";
		default:
			throw new IllegalArgumentException("Unsupported URI " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
