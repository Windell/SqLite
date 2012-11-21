package com.jamdeo.sqlite.pojo;

public class Channel {
	private long channelid;
	private String channelname;
	private int channelnumber;
	private int favoriteflag;
	private String logo;

	public Channel() {

	}

	public Channel(long channelid, String name, int number, int favoriteflag,
			String logo) {
		this.channelid = channelid;
		this.channelname = name;
		this.channelnumber = number;
		this.favoriteflag = favoriteflag;
		this.logo = logo;
	}

	public long getChannelid() {
		return channelid;
	}

	public void setChannelid(long channelid) {
		this.channelid = channelid;
	}

	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}

	public int getChannelnumber() {
		return channelnumber;
	}

	public void setChannelnumber(int channelnumber) {
		this.channelnumber = channelnumber;
	}

	public int getFavoriteflag() {
		return favoriteflag;
	}

	public void setFavoriteflag(int favoriteflag) {
		this.favoriteflag = favoriteflag;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
