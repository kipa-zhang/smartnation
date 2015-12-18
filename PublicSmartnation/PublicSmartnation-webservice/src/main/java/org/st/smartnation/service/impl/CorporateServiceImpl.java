package org.st.smartnation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.st.smartnation.dao.CorporateDao;
import org.st.smartnation.model.CorporateDate;
import org.st.smartnation.service.CorporateService;

@Service
public class CorporateServiceImpl implements CorporateService {

	@Autowired
	private CorporateDao corporateDao;
	
	@Override
	@Transactional
	public List<CorporateDate> getCorporateDates() {
		
		return corporateDao.getCorporateDates();
	}

	@Override
	@Transactional
	public int insertCorporateDate(CorporateDate corporateDate) {
		if(corporateDate != null){
			return corporateDao.insertCorporateDate(corporateDate);
		}
		return 0;
	}

	@Override
	@Transactional
	public CorporateDate getCorporateDate(int id) {
		if(id != 0){
			return corporateDao.getCorporateDate(id);
		}
		return null;
	}

}
