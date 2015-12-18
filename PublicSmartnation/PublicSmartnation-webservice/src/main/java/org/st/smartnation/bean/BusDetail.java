package org.st.smartnation.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "BusDetail")  
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlType(propOrder = { "code", "startTime", "endTime", "sortNum", "stationName", "lon", "lat" }) 
public class BusDetail {

	public BusDetail(){}
	
	public BusDetail(String code,int startTime,int endTime,int sortNum,String stationName,String lon,String lat){
		this.code = code;
		this.startTime = startTime;
		this.endTime = endTime;
		this.sortNum = sortNum;
		this.stationName = stationName;
		this.lon = lon;
		this.lat = lat;
	}
	
	private String code;
	private int startTime;
	private int endTime;
	private int sortNum;
	private String stationName;
	private String lon;
	private String lat;
	
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
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
}
