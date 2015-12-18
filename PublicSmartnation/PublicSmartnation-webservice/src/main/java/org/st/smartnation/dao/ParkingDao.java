package org.st.smartnation.dao;

import java.util.List;

import org.st.smartnation.model.Parking;

public interface ParkingDao {

	public List<Parking> getParkingInfo();
	public Parking getParkingInfo(String name);
	public boolean insertParkingInfo(List<Parking> parkingInfo);
	public boolean insertParkingInfo(Parking parkingInfo);
	public boolean updateParkingInfo(List<Parking> parkingInfo);
	public boolean updateParkingInfo(Parking parkingInfo);
}
