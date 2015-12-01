package org.example.beans;

public class HRMessage {
	
	private int id;
	private String name;
	private long time;
	private String lon;
	private String lat;
	private String content;
	private String title;
	private String icon;
	private String imgs;
	private String fromperson;
	private String toperson;
	
	
	public String getFromperson() {
		return fromperson;
	}
	public void setFromperson(String fromperson) {
		this.fromperson = fromperson;
	}
	public String getToperson() {
		return toperson;
	}
	public void setToperson(String toperson) {
		this.toperson = toperson;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
	
}
