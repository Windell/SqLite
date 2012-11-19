package com.jamdeo.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jamdeo.sqlite.pojo.Category;
import com.jamdeo.sqlite.pojo.Program;

/**
 * Class using rawQuery and exeSQL()for DML database operations, rely on
 * {@link #MySQLiteHelper}
 * */
public class DataBaseManager {
	SQLiteOpenHelper sqliteOpenHelper;
	static SQLiteDatabase db = null;

	public DataBaseManager(Context context) {
		sqliteOpenHelper = new MySQLiteHelper(context, 1);
		if (db == null)
			db = sqliteOpenHelper.getReadableDatabase();
	}

	public Cursor query(String sql) {
		return db.rawQuery(sql, null);
	}

	public <T> void update(T object) {
		db.beginTransaction();
		if (object instanceof Category) {
			db.execSQL(
					"update category set name=?,description=? where categoryid=? ",
					new Object[] { ((Category) object).getCategoryName(),
							((Category) object).getCategoryDesc(),
							((Category) object).getCategoryid() });
		} else if (object instanceof Program) {
			db.execSQL(
					"update program set programname=?, description=?,poster=?,thumbnail=? where programid=?",
					new Object[] { ((Program) object).getProgramname(),
							((Program) object).getDescription(),
							((Program) object).getPoster(),
							((Program) object).getThubmnail(),
							((Program) object).getProgramid() });
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
		} else if (object instanceof Program) {
			finded = findById(((Program) object).getProgramid(), Program.class);
		}
		if (finded == null) {
			if (object instanceof Category) {
				db.execSQL(
						"insert into Category(categoryid,name,description) values(?,?,?)",
						new Object[] { ((Category) object).getCategoryid(),
								((Category) object).getCategoryName(),
								((Category) object).getCategoryDesc() });
			} else if (object instanceof Program) {
				db.execSQL(
						"insert into program(programid,programname,description,thumbnail,poster) values(?,?,?,?,?)",
						new Object[] { ((Program) object).getProgramid(),
								((Program) object).getProgramname(),
								((Program) object).getDescription(),
								((Program) object).getThubmnail(),
								((Program) object).getPoster() });
			}

		} else {
			update(object);
		}
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	public <T> Object findById(Long id, Class<T> klass) {
		Cursor find = null;
		String querySQL = null;
		if (klass == Category.class) {
			querySQL = "select * from category where categoryid=?";
		} else if (klass == Program.class) {
			querySQL = "select * from program where programid=?";
		}
		if (querySQL != null)
			find = db.rawQuery(querySQL, new String[] { id.toString() });
		Object result = null;
		while (find != null && find.moveToNext()) {
			if (klass == Category.class) {
				result = new Category(find.getLong(1), find.getString(2),
						find.getString(3));
			} else if (klass == Program.class) {
				result = new Program(find.getLong(0), find.getString(1),
						find.getString(2), find.getString(3), find.getString(4));
			}
		}
		find.close();
		return result;
	}

	public <T> void delete(Long id, Class<T> klass) {
		db.beginTransaction();
		String deleteSQL = null;
		if (klass == Category.class) {
			deleteSQL = "delete from category where categoryid=?";
		} else if (klass == Program.class) {
			deleteSQL = "delete from program where programid=?";
		}
		if (deleteSQL != null)
			db.execSQL(deleteSQL, new Object[] { id });
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	public void closeDB() {
		if (db != null && !db.isOpen()) {
			db.close();
		}
	}
}
