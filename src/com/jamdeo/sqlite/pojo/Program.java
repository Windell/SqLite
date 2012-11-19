package com.jamdeo.sqlite.pojo;

public class Program {
	private long programid;
	private String programname;
	private String description;
	private String thubmnail;
	private String poster;

	public Program() {

	}

	public Program(long programid, String name, String desc, String thubmnail,
			String poster) {
		this.programid = programid;
		this.programname = name;
		this.description = desc;
		this.thubmnail = thubmnail;
		this.poster = poster;
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
		return "Program:<ID>" + programid + "<Name>" + programname + "<Desc>"
				+ description + "<Poster>" + poster + "<Thumbnail>" + thubmnail;
	}

}
