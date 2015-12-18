package org.st.smartnation.dao;

import java.util.List;

import org.st.smartnation.model.CorporateDate;

public interface CorporateDao {

	public List<CorporateDate> getCorporateDates();
	public CorporateDate getCorporateDate(int id);
	public int insertCorporateDate(CorporateDate corporateDate);
	public boolean deleteCorporateDate(int id);
	public boolean updateCorporateDate(CorporateDate corporateDate); 
}
