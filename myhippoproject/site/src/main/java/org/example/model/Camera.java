package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

@Service("camera")
@Entity
@Table(name = "camera")
public class Camera {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id", unique = true, nullable = false)
	private int Id;
	
	@Column(name = "cameraID")
	private int cameraID;
	
	@Column(name = "lon")
	private String lon;
	
	@Column(name = "lat")
	private String lat;
	
	@Column(name = "picPath")
	private String picPath;
	
	@Column(name = "road")
	private String road;
	
	@Column(name = "dateTime")
	private String dateTime;
	
	public int getCameraID() {
		return cameraID;
	}
	public void setCameraID(int cameraID) {
		this.cameraID = cameraID;
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
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
}
