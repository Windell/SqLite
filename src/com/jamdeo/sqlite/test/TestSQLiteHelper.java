package com.jamdeo.sqlite.test;

import android.database.Cursor;
import android.test.AndroidTestCase;
import android.util.Log;

import com.jamdeo.sqlite.DataBaseManager;
import com.jamdeo.sqlite.pojo.Category;
import com.jamdeo.sqlite.pojo.Program;

public class TestSQLiteHelper extends AndroidTestCase {
	// TODO How can I control part of these methods executed
	private static final String TAG = "TestCategory";

	public void testCategory() {
		DataBaseManager dbm = new DataBaseManager(getContext());
		dbm.insert(new Category(1L, "Movies", "This is the first test case"));
		dbm.insert(new Category(2L, "Series", "Series like ege of ice and fire"));
		dbm.insert(new Category(3L, "To Be Deleted", "No description"));
		assertNotNull(dbm.findById(3L, Category.class));
		dbm.delete(3L, Category.class);
		assertNull(dbm.findById(3L, Category.class));

		dbm.update(new Category(2L, "Sitcom", "No desc"));
		Category category = (Category) dbm.findById(2L, Category.class);
		assertEquals(category.getCategoryName(), "Sitcom");
		assertEquals(category.getCategoryDesc(), "No desc");

		dbm.insert(new Category(1L, "Movies", "Updated"));
		category = (Category) dbm.findById(1L, Category.class);
		assertEquals("Updated", category.getCategoryDesc());
		dbm.closeDB();

	}

	// A ha,this method more like a test case method .
	public void testProgram() {
		DataBaseManager dbm = new DataBaseManager(getContext());

		dbm.insert(new Program(1L, "Total Socer", "19:30-21:00", null, null));
		Program program = (Program) dbm.findById(1L, Program.class);
		assertNotNull(program);
		dbm.insert(new Program(2L, "News Broadcasting", "19:00-19:30", null,
				null));
		dbm.insert(new Program(3L, "Sports News", "12:00-12:30", null, null));
		Cursor cursor = dbm.query("select count(*) from program");
		assertNotNull(cursor);
		assertTrue(cursor.moveToNext());
		assertEquals(cursor.getInt(0), 3);

		dbm.delete(3L, Program.class);
		assertNull(dbm.findById(3L, Program.class));
		dbm.update(new Program(1L, "Total Soccer", "19:30-21:00", null, null));
		Program finded = (Program) dbm.findById(1L, Program.class);
		assertNotNull(finded);
		assertEquals(finded.getProgramname(), "Total Soccer");
		assertEquals(finded.getDescription(), "19:30-21:00");

		dbm.closeDB();
	}
}
