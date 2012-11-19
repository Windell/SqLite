package com.jamdeo.sqlite.test;

import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import com.jamdeo.sqlite.MyContentProvider;
import com.jamdeo.sqlite.pojo.Category;

public class TestContentProvider extends AndroidTestCase {
	public void testCategoryQuery() {
		Cursor cursor=getContext().getContentResolver().query(
				Uri.parse("content://" + MyContentProvider.AUTHRORITY + "/"
						+ MyContentProvider.TABLE_CATEGORY),
				new String[] { "_id", "categoryid", "name", "description" },
				null, null, null);
		while(cursor.moveToNext()){
			new Category(cursor.getLong(1),cursor.getString(2),cursor.getString(3)).toString();
		}
	}
}
