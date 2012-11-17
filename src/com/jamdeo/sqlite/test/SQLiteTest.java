package com.jamdeo.sqlite.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.jamdeo.sqlite.Category;
import com.jamdeo.sqlite.DataBaseManager;

public class SQLiteTest extends AndroidTestCase {
	private static final String TAG = "TestCategory";

	public void testSave() {
		DataBaseManager dbm = new DataBaseManager(this.getContext());
		dbm.insert(new Category(1L, "Movies", "This is the first test case"));
		dbm.insert(new Category(2L, "Series", "Series like ege of ice and fire"));
		Log.i(TAG, "Fisrt Category Added");
	}

	public void testQuery() {
		DataBaseManager dbm = new DataBaseManager(getContext());
		List<Category> categorys = dbm.query("select * from category");
		for (Category category : categorys) {
			Log.i("TestQuery", category.toString());
		}
	}

	public void testFind() {
		DataBaseManager dbm = new DataBaseManager(getContext());
		Category category = dbm.findById(2L);
		Log.i("TestFind", category == null ? null : category.toString());
	}

	public void testUpdate() {
		DataBaseManager dbm = new DataBaseManager(getContext());
		dbm.update(new Category(2L, "Sitcom",
				"Song of ice and fire, clash of the kings"));
		testFind();
	}

	public void testDelete() {
		DataBaseManager dbm = new DataBaseManager(getContext());
		dbm.delete(1L);
		Log.i("TestDelete", "Delete finished.....");
		testQuery();
	}
}
