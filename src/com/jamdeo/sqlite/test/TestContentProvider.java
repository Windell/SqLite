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
				new String[] { "_id", "categoryid", "name", "description" },
				null, null, null);
		while (cursor.moveToNext()) {
			Log.i("Category",
					new Category(cursor.getLong(1), cursor.getString(2), cursor
							.getString(3)).toString());
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
}
