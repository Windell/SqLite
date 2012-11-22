package com.jamdeo.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jamdeo.sqlite.pojo.Category;
import com.jamdeo.sqlite.pojo.CategoryDetail;
import com.jamdeo.sqlite.pojo.Channel;
import com.jamdeo.sqlite.pojo.Program;

/**
 * Class using rawQuery and exeSQL()for DML database operations, rely on
 * {@link #MySQLiteHelper}
 * */
public class DataBaseManager {
	SQLiteOpenHelper sqliteOpenHelper;
	static SQLiteDatabase db = null;

	public DataBaseManager(Context context) {
		sqliteOpenHelper = new SimpleSQLiteHelper(context, 1);
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
					"update program set programname=?, description=?,poster=?,thumbnail=?,starttime=?"
							+ ", endtime=?,favoriteflag=?, programtype=?, rate=?, lastedUpdatedtime=? where programid=?",
					new Object[] { ((Program) object).getProgramname(),
							((Program) object).getDescription(),
							((Program) object).getPoster(),
							((Program) object).getThubmnail(),
							((Program) object).getStarttime(),
							((Program) object).getEndtime(),
							((Program) object).getFavoriteflag(),
							((Program) object).getProgramtype(),
							((Program) object).getRate(),
							(int) (System.currentTimeMillis() / 1000),
							((Program) object).getProgramid() });
		} else if (object instanceof Channel) {
			Channel channel = (Channel) object;
			db.execSQL(
					"update channel set channelname=?, channelnumber=?, favoriteflag=?, logo=? where channelid=?",
					new Object[] { channel.getChannelname(),
							channel.getChannelnumber(),
							channel.getFavoriteflag(), channel.getLogo(),
							channel.getChannelid() });
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
		} else if (object instanceof Channel) {
			finded = findById(((Channel) object).getChannelid(), Channel.class);
		}
		if (finded == null) {
			if (object instanceof Category) {
				db.execSQL(
						"insert into Category(categoryid,name,description) values(?,?,?)",
						new Object[] { ((Category) object).getCategoryid(),
								((Category) object).getCategoryName(),
								((Category) object).getCategoryDesc() });
			} else if (object instanceof Program) {
				Program program = (Program) object;
				db.execSQL(
						"insert into program(programid,channelid, programname, description, starttime, endtime,"
								+ "favoriteflag, programtype, rate, poster, thumbnail,createdtime,lastedUpdatedtime) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
						new Object[] { program.getProgramid(),
								program.getChannelid(),
								program.getProgramname(),
								program.getDescription(),
								program.getStarttime(), program.getEndtime(),
								program.getFavoriteflag(),
								program.getProgramtype(), program.getRate(),
								program.getPoster(), program.getThubmnail(),
								(int) (System.currentTimeMillis() / 1000),
								(int) (System.currentTimeMillis() / 1000) });
			} else if (object instanceof Channel) {
				Channel channel = (Channel) object;
				db.execSQL(
						"insert into channel(channelid, channelname, channelnumber, favoriteflag, logo)"
								+ "values(?,?,?,?,?)",
						new Object[] { channel.getChannelid(),
								channel.getChannelname(),
								channel.getChannelnumber(),
								channel.getFavoriteflag(), channel.getLogo() });
			} else if (object instanceof CategoryDetail) {
				db.execSQL(
						"insert into categorydetail(programid,categoryid,displayorder)values(?,?,?)",
						new Object[] {
								((CategoryDetail) object).getProgramid(),
								((CategoryDetail) object).getCategoryid(),
								((CategoryDetail) object).getDisplayOrder() });
			}

		} else if (!finded.equals(object)) {
			update(object);
		}
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	public <T> Object findById(Long id, Class<T> klass) {
		Cursor find = null;
		String querySQL = null;
		if (klass == Category.class) {
			querySQL = "select  categoryid, name, description from category where categoryid=?";
		} else if (klass == Program.class) {
			querySQL = "select programid,channelid, programname, description, starttime, endtime,"
					+ "favoriteflag, programtype, rate, poster, thumbnail,createdtime,lastedUpdatedtime from program where programid=?";
		} else if (klass == Channel.class) {
			querySQL = "select channelid, channelname, channelnumber, favoriteflag, logo from channel where channelid=?";
		}
		if (querySQL != null)
			find = db.rawQuery(querySQL, new String[] { id.toString() });
		Object result = null;
		if (find != null && find.moveToNext()) {
			if (klass == Category.class) {
				result = new Category(find.getLong(0), find.getString(1),
						find.getString(2));
			} else if (klass == Program.class) {
				result = new Program(find.getLong(0), find.getLong(1),
						find.getString(2), find.getString(3), find.getInt(4),
						find.getInt(5), find.getInt(6), find.getInt(7),
						find.getInt(8), find.getString(9), find.getString(10),
						find.getInt(11), find.getInt(12));
			} else if (klass == Channel.class) {
				result = new Channel(find.getLong(0), find.getString(1),
						find.getInt(2), find.getInt(3), find.getString(4));
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
		} else if (klass == Channel.class) {
			deleteSQL = "delete from channel where channelid=?";
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
