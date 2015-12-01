package org.example.beans;

public class Parking {
	private int id;
	private String lon;
	private String lat;
	private String name;
	private int positions;
	private int lotsPositions;
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
	public int getPositions() {
		return positions;
	}
	public void setPositions(int positions) {
		this.positions = positions;
	}
	public int getLotsPositions() {
		return lotsPositions;
	}
	public void setLotsPositions(int lotsPositions) {
		this.lotsPositions = lotsPositions;
	}
	
	
}
