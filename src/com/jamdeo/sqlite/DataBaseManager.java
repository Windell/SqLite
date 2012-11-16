package com.jamdeo.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseManager {
	SQLiteOpenHelper sqliteOpenHelper;

	public DataBaseManager(Context context) {
		sqliteOpenHelper = new MySQLiteHelper(context, 0);
	}

	public List<Category> query(String sql) {
		SQLiteDatabase db = sqliteOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		List<Category> categorys = new ArrayList<Category>();
		while (cursor.moveToNext()) {
			Category category = new Category();
			category.setCategoryid(cursor.getLong(1));
			category.setCategoryName(cursor.getString(2));
			category.setCategoryDesc(cursor.getString(3));
			categorys.add(category);
		}
		cursor.close();
		db.close();
		return categorys;
	}
}
