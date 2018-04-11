package com.wy.java;

import java.sql.Date;

public class qa_list {
	private int qa_ID;
	private String title_name;
	private String directer;
	private Date current_time;
	public int getQa_ID() {
		return qa_ID;
	}
	public void setQa_ID(int qa_ID) {
		this.qa_ID = qa_ID;
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
