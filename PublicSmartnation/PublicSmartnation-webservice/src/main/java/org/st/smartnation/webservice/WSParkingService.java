package org.st.smartnation.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.st.smartnation.model.Parking;

@WebService
public interface WSParkingService {

	public List<Parking> getAllParkingInfo();
	public Parking getParkingInfo(@WebParam(name="parkingName")String parkingName);
	
}
