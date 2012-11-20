package com.jamdeo.sqlite.pojo;

import java.lang.reflect.Field;

public class Program {

	private long programid;
	private long channelid;
	private String programname;
	private String description;
	private String thubmnail;
	private String poster;
	private int starttime;
	private int endtime;
	private int favoriteflag = 0; // 0 for no ,1for yes
	private int rate;
	private int programtype;
	private int createdtime;
	private int updatedtime;

	public long getChannelid() {
		return channelid;
	}

	public void setChannelid(long channelid) {
		this.channelid = channelid;
	}

	public int getStarttime() {
		return starttime;
	}

	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}

	public int getEndtime() {
		return endtime;
	}

	public int getFavoriteflag() {
		return favoriteflag;
	}

	public void setFavoriteflag(int favoriteflag) {
		this.favoriteflag = favoriteflag;
	}

	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getProgramtype() {
		return programtype;
	}

	public void setProgramtype(int programtype) {
		this.programtype = programtype;
	}

	public int getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(int createdtime) {
		this.createdtime = createdtime;
	}

	public int getUpdatedtime() {
		return updatedtime;
	}

	public void setUpdatedtime(int updatedtime) {
		this.updatedtime = updatedtime;
	}

	public Program() {

	}

	// should be an mandatory fields constructor
	// public Program(long programid, String name, String desc, String
	// thubmnail,
	// String poster) {
	// this.programid = programid;
	// this.programname = name;
	// this.description = desc;
	// this.thubmnail = thubmnail;
	// this.poster = poster;
	// }

	public Program(long programid, long channelid, String name, String desc,
			int starttime, int endtime, int favoriteflag, int programtype,
			int rate, String poster, String thubmnail, int createdtime,
			int lastupdatedtime) {
		this.programid = programid;
		this.programname = name;
		this.description = desc;
		this.thubmnail = thubmnail;
		this.poster = poster;
		this.channelid = channelid;
		this.favoriteflag = favoriteflag;
		this.starttime = starttime;
		this.endtime = endtime;
		this.rate = rate;
		this.programtype = programtype;
		this.createdtime = createdtime;
		this.updatedtime = lastupdatedtime;
	}

	public long getProgramid() {
		return programid;
	}

	public void setProgramid(long programid) {
		this.programid = programid;
	}

	public String getProgramname() {
		return programname;
	}

	public void setProgramname(String programname) {
		this.programname = programname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThubmnail() {
		return thubmnail;
	}

	public void setThubmnail(String thubmnail) {
		this.thubmnail = thubmnail;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	@Override
	public String toString() {
		Field[] fields = Program.class.getFields();
		StringBuilder sb = new StringBuilder();
		for (Field field : fields) {
			try {
				sb.append("<" + field.getName() + ">" + field.get(this));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || !(o instanceof Program)) {
			return false;
		}
		Program compared = (Program) o;
		if (this.programid == compared.getProgramid()
				&& this.channelid == compared.getChannelid()
				&& this.updatedtime == compared.getUpdatedtime()) {
			return true;
		}
		return false;
	}
}
