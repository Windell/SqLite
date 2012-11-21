package com.jamdeo.sqlite.pojo;

public class CategoryDetail {
	private long _id;
	private long programid;
	private long categoryid;
	private int displayOrder;

	public CategoryDetail() {

	}

	public CategoryDetail(long _id, long programid, long categoryid,
			int displayOrder) {
		this._id = _id;
		this.programid = programid;
		this.categoryid = categoryid;
		this.displayOrder = displayOrder;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public long getProgramid() {
		return programid;
	}

	public void setProgramid(long programid) {
		this.programid = programid;
	}

	public long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
}
