package org.example.beans;

import java.sql.Time;

public class BusSchedule {

	private int id;
	//线路编号
	private String code;
	//时分秒  小时*60+分钟*60+秒
	private int startTime;
	private int endTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

}
