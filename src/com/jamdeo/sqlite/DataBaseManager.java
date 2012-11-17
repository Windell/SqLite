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
		sqliteOpenHelper = new MySQLiteHelper(context, 1);
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

	public void update(Category category) {
		SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();
		db.beginTransaction();
		db.execSQL(
				"update category set name=?,description=? where categoryid=? ",
				new Object[] { category.getCategoryName(),
						category.getCategoryDesc(), category.getCategoryid() });
		db.setTransactionSuccessful();
		db.endTransaction();
		db.close();

	}

	public void insert(Category category) {
		SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();
		db.beginTransaction();
		db.execSQL(
				"insert into category(categoryid,name,description) values(?,?,?)",
				new Object[] { category.getCategoryid(),
						category.getCategoryName(), category.getCategoryDesc() });
		db.setTransactionSuccessful();
		db.endTransaction();
		db.close();
	}

	public Category findById(Long id) {
		SQLiteDatabase db = sqliteOpenHelper.getReadableDatabase();
		Cursor find = db.rawQuery("select * from category where categoryid=?",
				new String[] { id.toString() });
		Category category = null;
		while (find.moveToNext()) {
			category = new Category(find.getLong(1), find.getString(2),
					find.getString(3));
		}
		find.close();
		db.close();
		return category;
	}
	public void delete(Long id){
		SQLiteDatabase db=sqliteOpenHelper.getWritableDatabase();
		db.beginTransaction();
		db.execSQL("delete from category where categoryid=?", new Object[]{id});
		db.setTransactionSuccessful();
		db.endTransaction();
		db.close();
	}
}
