package org.example.beans;

public class Carpark {

private int Id;
		
	private int carparkID;
	
	private String area;
	
	private String development;
	
	private int lots;
	
	private String latitude;
	
	private String longitude;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getCarparkID() {
		return carparkID;
	}

	public void setCarparkID(int carparkID) {
		this.carparkID = carparkID;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDevelopment() {
		return development;
	}

	public void setDevelopment(String development) {
		this.development = development;
	}

	public int getLots() {
		return lots;
	}

	public void setLots(int lots) {
		this.lots = lots;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
}
