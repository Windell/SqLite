package com.jamdeo.sqlite.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.test.AndroidTestCase;

import com.jamdeo.sqlite.DataBaseManager;
import com.jamdeo.sqlite.pojo.Category;
import com.jamdeo.sqlite.pojo.CategoryDetail;
import com.jamdeo.sqlite.pojo.Channel;
import com.jamdeo.sqlite.pojo.Program;

public class TestDatabaseManager extends AndroidTestCase {
	private DataBaseManager dbm;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dbm = new DataBaseManager(getContext());
	}

	public void test_1_Category() {

		dbm.insert(new Category(1L, "体育", "足球,篮球,羽毛球"));
		dbm.insert(new Category(2L, "连续剧", "连续剧"));
		dbm.insert(new Category(3L, "To Be Deleted", "No description"));
		assertNotNull(dbm.findById(3L, Category.class));
		dbm.delete(3L, Category.class);
		assertNull(dbm.findById(3L, Category.class));

		Cursor cursor = dbm.query("select count(*) from category");
		assertTrue(cursor.moveToNext());
		assertEquals(cursor.getInt(0), 2);

		dbm.update(new Category(2L, "电视剧", "没描述"));
		Category category = (Category) dbm.findById(2L, Category.class);
		assertEquals(category.getCategoryName(), "电视剧");
		assertEquals(category.getCategoryDesc(), "没描述");

		dbm.insert(new Category(1L, "体育", "CCTV5"));
		category = (Category) dbm.findById(1L, Category.class);
		assertEquals("CCTV5", category.getCategoryDesc());
		dbm.closeDB();

	}

	// A ha,this method more like a test case method .
	public void test_3_Program() {
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
		Program pro2 = new Program();
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
		Program pro3 = new Program();
		pro3.setProgramid(3L);
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
		assertTrue(cursor.getInt(0)>0);

		dbm.delete(3L, Program.class);
		assertNull(dbm.findById(3L, Program.class));

		dbm.insert(pro3);
		// clone to two channels;
		pro1.setChannelid(2L);
		pro1.setProgramid(21L);
		pro2.setChannelid(2L);
		pro2.setProgramid(22L);
		pro3.setChannelid(2L);
		dbm.insert(pro1);
		dbm.insert(pro2);
		dbm.insert(pro3);

		Program find1 = (Program) dbm.findById(1L, Program.class);
		find1.setProgramname("Total Soccer");
		assertNotNull(find1);
		dbm.update(find1);
		Program finded = (Program) dbm.findById(1L, Program.class);
		assertNotNull(finded);
		assertEquals(finded.getProgramname(), "Total Soccer");

		dbm.closeDB();
	}

	public void test_2_Channel() {
		Channel channel1 = new Channel();
		channel1.setChannelid(1L);
		channel1.setChannelnumber(1);
		channel1.setChannelname("CCTV5");
		channel1.setLogo("1234567.png");

		Channel channel2 = new Channel();
		channel2.setChannelid(2L);
		channel2.setChannelnumber(2);
		channel2.setChannelname("CCTV6");
		channel2.setLogo("23456.png");

		dbm.insert(channel1);
		dbm.insert(channel2);
		dbm.closeDB();
	}

	public void test_4_CategoryDetail() {
		CategoryDetail detail1=new CategoryDetail();
		detail1.setCategoryid(1L);
		detail1.setProgramid(1L);
		detail1.setDisplayOrder(1);
		
		dbm.insert(detail1);
		detail1.setProgramid(2L);
		detail1.setDisplayOrder(2);
		dbm.insert(detail1);
		
		detail1.setProgramid(3L);
		detail1.setDisplayOrder(3);
		dbm.insert(detail1);
		
		CategoryDetail detail2=new CategoryDetail();
		detail2.setCategoryid(2L);
		detail2.setProgramid(1L);
		detail2.setDisplayOrder(1);
		dbm.insert(detail2);
		
		detail2.setProgramid(2L);
		detail2.setDisplayOrder(2);
		dbm.insert(detail2);
		
	}

	@SuppressLint("SimpleDateFormat")
	private int getSeconds(String date) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return (int) formatter.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
