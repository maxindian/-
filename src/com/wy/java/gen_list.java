package com.wy.java;

import java.sql.Date;

public class gen_list {
	private int gen_ID;
	private String title_name;
	private String directer;
	private Date current_time;
	public int getGen_ID() {
		return gen_ID;
	}
	public void setGen_ID(int gen_ID) {
		this.gen_ID = gen_ID;
	}
	public String getTitle_name() {
		return title_name;
	}
	public void setTitle_name(String title_name) {
		this.title_name = title_name;
	}
	public String getDirecter() {
		return directer;
	}
	public void setDirecter(String directer) {
		this.directer = directer;
	}
	public Date getCurrent_time() {
		return current_time;
	}
	public void setCurrent_time(Date current_time) {
		this.current_time = current_time;
	}
	
}
