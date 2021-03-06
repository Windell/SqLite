package com.jamdeo.sqlite.test;

import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

import com.jamdeo.sqlite.ProgramContentProvider;
import com.jamdeo.sqlite.pojo.Category;

public class TestContentProvider extends AndroidTestCase {
	public void testCategoryQuery() {
		Cursor cursor = getContext().getContentResolver().query(
				Uri.parse("content://" + ProgramContentProvider.AUTHRORITY
						+ "/" + ProgramContentProvider.TABLE_CATEGORY),
				new String[] { "categoryid", "name", "description" },
				null, null, null);
		while (cursor.moveToNext()) {
			Log.i("Category",
					new Category(cursor.getLong(0), cursor.getString(1), cursor
							.getString(2)).toString());
		}
	}

	public void testSingleCategoryQuery() {
		Cursor cursor = getContext().getContentResolver()
				.query(Uri.parse("content://"
						+ ProgramContentProvider.AUTHRORITY + "/"
						+ ProgramContentProvider.TABLE_CATEGORY + "/" + 3),
						null, null, null, null);
		while (cursor.moveToNext()) {
			Log.i("SingleCategory",
					new Category(cursor.getLong(1), cursor.getString(2), cursor
							.getString(3)).toString());
		}
	}

	public void testCategoryProgramsQuery() {
		Cursor cursor = getContext().getContentResolver().query(
				Uri.parse("content://" + ProgramContentProvider.AUTHRORITY
						+ "/" + ProgramContentProvider.VIEW_TAGEGORYPROGRAM),
				new String[] { "categoryid", "categoryname", "programid",
						"name", "programdesc", "starttime", "endtime",
						"programtype", "favoriteflag", "rate", "poster",
						"thumbnail", "displayorder" }, null, null, null);
		while (cursor.moveToNext()) {
			Log.i("CategoryPrograms",
					getFormattedInt(cursor, 0) + getFormattedString(cursor, 1)
							+ getFormattedInt(cursor, 2)
							+ getFormattedString(cursor, 3)
							+ getFormattedString(cursor, 4)
							+ getFormattedInt(cursor, 5)
							+ getFormattedInt(cursor, 6)
							+ getFormattedString(cursor, 7)
							+ getFormattedInt(cursor, 8)
							+ getFormattedInt(cursor, 9)
							+ getFormattedString(cursor, 10)
							+ getFormattedString(cursor, 11)
							+ getFormattedInt(cursor, 12));
		}
	}

	private String getFormattedInt(Cursor cursor, int index) {
		return "<" + cursor.getColumnName(index) + ">" + cursor.getInt(index);
	}

	private String getFormattedString(Cursor cursor, int index) {
		return "<" + cursor.getColumnName(index) + ">"
				+ cursor.getString(index);
	}
}
