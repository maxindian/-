package com.wy.java;

import java.sql.Date;

public class qutum_list {
	private int qutum_ID;
	private String title_name;
	private String directer;
	private Date current_time;
	public int getQutum_ID() {
		return qutum_ID;
	}
	public void setQutum_ID(int qutum_ID) {
		this.qutum_ID = qutum_ID;
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
