package org.st.smartnation.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.st.smartnation.model.CorporateDate;

@WebService
public interface WSCorporateDateService {

	public List<CorporateDate> getAllCorporatedateInfo();
	public int saveCorporatedateInfo(@WebParam(name="corporateDate")CorporateDate corporateDate);
	public CorporateDate getCorporateDateInfo(@WebParam(name="id")int id);
}
