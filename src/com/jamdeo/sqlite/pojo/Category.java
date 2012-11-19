package com.jamdeo.sqlite.pojo;

public class Category {
	private long categoryid;
	private String categoryName;

	public Category() {

	}

	public Category(long id, String name, String desc) {
		this.categoryid = id;
		this.categoryDesc = desc;
		this.categoryName = name;
	}

	public long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	private String categoryDesc;

	@Override
	public String toString() {
		return "<ID>" + this.categoryid + "<Name>" + categoryName
				+ "<Description>" + categoryDesc;
	}

}