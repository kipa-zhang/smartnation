package org.st.smartnation.webserviceimpl;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.st.smartnation.model.Parking;
import org.st.smartnation.service.ParkingService;
import org.st.smartnation.webservice.WSParkingService;

@WebService  
public class WSParkingServiceImpl implements WSParkingService {

	@Autowired
	ParkingService parkingService;

	@Override
	public List<Parking> getAllParkingInfo() {
		return parkingService.getParkingInfo();
	}

	@Override
	public Parking getParkingInfo(@WebParam(name="parkingName")String parkingName) {
		return parkingService.getParkingInfo(parkingName);
	}
	
	
}
