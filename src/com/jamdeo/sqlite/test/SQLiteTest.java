package com.jamdeo.sqlite.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.jamdeo.sqlite.DataBaseManager;
import com.jamdeo.sqlite.pojo.Category;

public class SQLiteTest extends AndroidTestCase {
	private static final String TAG = "TestCategory";

	public void testSaveCategory() {
		DataBaseManager dbm = new DataBaseManager(this.getContext());
		dbm.insert(new Category(1L, "Movies", "This is the first test case"));
		dbm.insert(new Category(2L, "Series", "Series like ege of ice and fire"));
		Log.i(TAG, "Fisrt Category Added");
	}

	public void testQueryCategory() {
		DataBaseManager dbm = new DataBaseManager(getContext());
		List<Category> categorys = dbm.query("select * from category");
		for (Category category : categorys) {
			Log.i("TestQuery", category.toString());
		}
	}

	public void testFindCategory() {
		DataBaseManager dbm = new DataBaseManager(getContext());
		Category category = (Category) dbm.findById(2L,Category.class);
		Log.i("TestFind", category == null ? "" : category.toString());
	}

	public void testUpdateCategory() {
		DataBaseManager dbm = new DataBaseManager(getContext());
		dbm.update(new Category(2L, "Sitcom",
				"Song of ice and fire, clash of the kings"));
		testFindCategory();
	}

	public void testDeleteCategory() {
		DataBaseManager dbm = new DataBaseManager(getContext());
		dbm.delete(1L,Category.class);
		Log.i("TestDelete", "Delete finished.....");
		testQueryCategory();
	}
}
