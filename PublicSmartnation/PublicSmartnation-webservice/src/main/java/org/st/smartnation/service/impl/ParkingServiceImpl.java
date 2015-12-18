package org.st.smartnation.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.st.smartnation.dao.ParkingDao;
import org.st.smartnation.model.Parking;
import org.st.smartnation.service.ParkingService;

@Service
public class ParkingServiceImpl implements ParkingService {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private ParkingDao parkingDao; 
	
	@Override
	@Transactional
	public List<Parking> getParkingInfo() {
		return parkingDao.getParkingInfo();
	}

	@Override
	@Transactional
	public Parking getParkingInfo(String name) {
		if(name != null && name != ""){
			return parkingDao.getParkingInfo(name);
		}
		return null;
	}

	@Override
	@Transactional
	public void insertParkingInfo(List<Parking> parkingInfo) {
    	if(parkingInfo != null && !parkingInfo.isEmpty()){
    		parkingDao.insertParkingInfo(parkingInfo);
    	}
	}

	@Override
	@Transactional
	public void updateParkingInfo(List<Parking> parkings) {
		if(parkings != null && !parkings.isEmpty()){
			parkingDao.updateParkingInfo(parkings);
		}
	}

	@Override
	@Transactional
	public void insertParkingInfo(Parking parkingInfo) {
		if(parkingInfo != null){
			parkingDao.insertParkingInfo(parkingInfo);
		}
	}

	@Override
	@Transactional
	public void updateParkingInfo(Parking parking) {
		if(parking != null){
			parkingDao.updateParkingInfo(parking);
		}
	}

}
