package com.jamdeo.sqlite.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.database.Cursor;
import android.test.AndroidTestCase;

import com.jamdeo.sqlite.DataBaseManager;
import com.jamdeo.sqlite.pojo.Category;
import com.jamdeo.sqlite.pojo.Program;

public class TestSQLiteHelper extends AndroidTestCase {

	public void testCategory() {
		DataBaseManager dbm = new DataBaseManager(getContext());
		dbm.insert(new Category(1L, "Movies", "This is the first test case"));
		dbm.insert(new Category(2L, "Series", "Series like ege of ice and fire"));
		dbm.insert(new Category(3L, "To Be Deleted", "No description"));
		assertNotNull(dbm.findById(3L, Category.class));
		dbm.delete(3L, Category.class);
		assertNull(dbm.findById(3L, Category.class));

		Cursor cursor = dbm.query("select count(*) from category");
		assertTrue(cursor.moveToNext());
		assertEquals(cursor.getInt(0), 2);

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
		Program pro1 = new Program();
		pro1.setProgramid(1L);
		pro1.setChannelid(1L);
		pro1.setProgramname("天下足球");
		pro1.setDescription("五大联赛最新动态.....胡老师帽子戏法,曹总王者归来,张鑫自摆乌龙.....");
		pro1.setProgramtype(1);
		pro1.setStarttime(getSeconds("2012-11-21 19:30:00"));
		pro1.setEndtime(getSeconds("2012-11-21 21:30:00"));
		pro1.setPoster("12345678.png");
		pro1.setThubmnail("2345678.jpg");
		pro1.setRate(5);
		dbm.insert(pro1);
		Program program = (Program) dbm.findById(1L, Program.class);
		assertNotNull(program);
		Program pro2=new Program();
		pro2.setProgramid(2L);
		pro2.setChannelid(1L);
		pro2.setProgramname("德甲直播");
		pro2.setDescription("拜仁慕尼黑——多特蒙德");
		pro2.setProgramtype(1);
		pro2.setStarttime(getSeconds("2012-11-21 21:30:00"));
		pro2.setEndtime(getSeconds("2012-11-21 23:30:00"));
		pro2.setPoster("1234567890.png");
		pro2.setThubmnail("234567890.jpg");
		pro2.setRate(4);
		dbm.insert(pro2);
		Program pro3=new Program();
		pro3.setProgramid(2L);
		pro3.setChannelid(1L);
		pro3.setProgramname("西甲集锦");
		pro3.setDescription("梅西超越贝利.......");
		pro3.setProgramtype(1);
		pro3.setStarttime(getSeconds("2012-11-21 23:30:00"));
		pro3.setEndtime(getSeconds("2012-11-22 00:30:00"));
		pro3.setPoster("1234567890123.png");
		pro3.setThubmnail("234567890123.jpg");
		pro3.setRate(4);
		dbm.insert(pro3);
		Cursor cursor = dbm.query("select count(*) from program");
		assertNotNull(cursor);
		assertTrue(cursor.moveToNext());
		assertEquals(cursor.getInt(0), 3);

		dbm.delete(3L, Program.class);
		assertNull(dbm.findById(3L, Program.class));
		Program find1=(Program) dbm.findById(1L, Program.class);
		find1.setProgramname("Total Soccer");
		assertNotNull(find1);
		dbm.update(find1);
		Program finded = (Program) dbm.findById(1L, Program.class);
		assertNotNull(finded);
		assertEquals(finded.getProgramname(), "Total Soccer");

		dbm.closeDB();
	}

	private int getSeconds(String date){
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return (int) formatter.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
