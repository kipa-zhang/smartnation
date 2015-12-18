package org.st.smartnation.webserviceimpl;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.st.smartnation.model.CorporateDate;
import org.st.smartnation.service.CorporateService;
import org.st.smartnation.webservice.WSCorporateDateService;

@WebService
public class WSCorporateDateServiceImpl implements WSCorporateDateService {

	@Autowired
	CorporateService corporateService;

	@Override
	public List<CorporateDate> getAllCorporatedateInfo() {
		return corporateService.getCorporateDates();
	}

	@Override
	public int saveCorporatedateInfo(@WebParam(name="corporateDate")CorporateDate corporateDate) {
		return corporateService.insertCorporateDate(corporateDate);
	}

	@Override
	public CorporateDate getCorporateDateInfo(@WebParam(name="id")int id) {
		return corporateService.getCorporateDate(id);
	}
	
}
