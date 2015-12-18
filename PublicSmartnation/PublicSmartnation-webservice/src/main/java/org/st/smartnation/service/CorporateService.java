package org.st.smartnation.service;

import java.util.List;

import org.st.smartnation.model.CorporateDate;

public interface CorporateService {

	public List<CorporateDate> getCorporateDates();
	public int insertCorporateDate(CorporateDate corporateDate);
	public CorporateDate getCorporateDate(int id);
	
}
