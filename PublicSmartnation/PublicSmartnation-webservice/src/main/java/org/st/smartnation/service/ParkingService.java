package org.st.smartnation.service;

import java.util.List;

import org.st.smartnation.model.Parking;

public interface ParkingService {

	public List<Parking> getParkingInfo();
	public Parking getParkingInfo(String name);
	public void insertParkingInfo(List<Parking> parkingInfo);
	public void insertParkingInfo(Parking parkingInfo);
	public void updateParkingInfo(List<Parking> parkings);
	public void updateParkingInfo(Parking parking);
}
