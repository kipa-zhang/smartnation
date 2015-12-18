package org.st.smartnation.service;

import java.util.List;

import org.st.smartnation.bean.BusDetail;
import org.st.smartnation.model.BusInfo;

public interface BusService {

	public List<BusInfo> getBusInfo();
	public List<BusDetail> getBusDetail(String code);
	public List<BusDetail> getBusDetails(String codes);
	
}
