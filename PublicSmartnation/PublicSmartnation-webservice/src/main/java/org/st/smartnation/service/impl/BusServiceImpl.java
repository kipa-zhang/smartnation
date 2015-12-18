package org.st.smartnation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.st.smartnation.bean.BusDetail;
import org.st.smartnation.dao.BusDao;
import org.st.smartnation.model.BusInfo;
import org.st.smartnation.service.BusService;

@Service
public class BusServiceImpl implements BusService {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private BusDao busDao;
	
	@Override
	@Transactional
	public List<BusInfo> getBusInfo() {
		return busDao.getBusInfo();
	}

	@Override
	@Transactional
	public List<BusDetail> getBusDetail(String code) {
		if(code != null){
			return busDao.getBusDetail(code);
		}
		return null;
	}

	/**
	 * codes 组成由 ;隔离
	 */
	@Override
	@Transactional
	public List<BusDetail> getBusDetails(String codes) {
		List<BusDetail> list = new ArrayList<BusDetail>();
		if(codes != null){
			String[] busCodes = codes.split(";");
			if(busCodes.length > 0){
				for (String buscode : busCodes) {
					List<BusDetail> busDetailList = this.getBusDetail(buscode);
					if(busDetailList != null && !busDetailList.isEmpty()){
						list.addAll(busDetailList);
					}else{
						logger.warn("bus code :"+buscode+" does not exist! ");
					}
				}
				return list;
			}
		}
		return null;
	}

}
