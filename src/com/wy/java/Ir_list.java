package com.wy.java;

import java.sql.Date;

public class Ir_list {
	private int Ir_ID;
	private String title_name;
	private String directer;
	private Date current_time;
	public int getIr_ID() {
		return Ir_ID;
	}
	public void setIr_ID(int ir_ID) {
		Ir_ID = ir_ID;
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
