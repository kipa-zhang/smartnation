package org.st.smartnation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="parking")
@XmlRootElement(name = "Parking")  
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlType(propOrder = { "id", "lon", "lat", "name", "carportNum", "remanentCarportNum" }) 
public class Parking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "lon", nullable = false)
	private String lon;
	
	@Column(name = "lat", nullable = false)
	private String lat;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "carportNum", nullable = false)
	private int carportNum;
	
	@Column(name = "remanentCarportNum", nullable = false)
	private int remanentCarportNum;
	
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCarportNum() {
		return carportNum;
	}
	public void setCarportNum(int carportNum) {
		this.carportNum = carportNum;
	}
	public int getRemanentCarportNum() {
		return remanentCarportNum;
	}
	public void setRemanentCarportNum(int remanentCarportNum) {
		this.remanentCarportNum = remanentCarportNum;
	}
	
}
