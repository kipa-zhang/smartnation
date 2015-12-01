package org.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

@Service("busstop")
@Entity
@Table(name = "busstop")
public class BusStop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "busStopId", unique = true, nullable = false)
	private int busStopId;
	
	@Column(name = "busStopCode")
	private String busStopCode;
	
	@Column(name = "lon")
	private String lon;
	
	@Column(name = "lat")
	private String lat;
	
	@Column(name = "road")
	private String road;
	
	@Column(name = "description")
	private String description;

	public int getBusStopId() {
		return busStopId;
	}

	public void setBusStopId(int busStopId) {
		this.busStopId = busStopId;
	}

	public String getBusStopCode() {
		return busStopCode;
	}

	public void setBusStopCode(String busStopCode) {
		this.busStopCode = busStopCode;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}
	
	
}
