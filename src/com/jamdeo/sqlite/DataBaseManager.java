package com.jamdeo.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseManager {
	SQLiteOpenHelper sqliteOpenHelper;
	static SQLiteDatabase db = null;

	public DataBaseManager(Context context) {
		sqliteOpenHelper = new MySQLiteHelper(context, 1);
		if(db==null)
		db = sqliteOpenHelper.getReadableDatabase();
	}

	public List<Category> query(String sql) {
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
		return categorys;
	}

	public <T> void update(T object) {
		db.beginTransaction();
		if (object instanceof Category) {
			db.execSQL(
					"update category set name=?,description=? where categoryid=? ",
					new Object[] { ((Category) object).getCategoryName(),
							((Category) object).getCategoryDesc(),
							((Category) object).getCategoryid() });
		}
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	public <T> void insert(T object) {
		db.beginTransaction();
		Object finded = null;
		if (object instanceof Category) {
			finded = findById(((Category) object).getCategoryid(),
					Category.class);
		}
		if (finded == null) {
			db.execSQL(
					"insert into Category(categoryid,name,description) values(?,?,?)",
					new Object[] { ((Category) object).getCategoryid(),
							((Category) object).getCategoryName(),
							((Category) object).getCategoryDesc() });
		} else {
			update(finded);
		}
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	public <T> Object findById(Long id, Class<T> klass) {
		Cursor find = null;
		if (klass == Category.class) {
			find = db.rawQuery("select * from category where categoryid=?",
					new String[] { id.toString() });
		}
		Object result = null;
		while (find.moveToNext()) {
			if (klass == Category.class) {
				result = new Category(find.getLong(1), find.getString(2),
						find.getString(3));
			}
		}
		find.close();
		return result;
	}

	public <T> void delete(Long id, Class<T> klass) {
		db.beginTransaction();
		if (klass == Category.class) {
			db.execSQL("delete from category where categoryid=?",
					new Object[] { id });
		}
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	public void closeDB() {
		if (db != null && !db.isOpen()) {
			db.close();
		}
	}
}
