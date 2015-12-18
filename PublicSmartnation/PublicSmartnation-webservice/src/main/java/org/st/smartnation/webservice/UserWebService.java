package org.st.smartnation.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface UserWebService {

	public String getUser(@WebParam(name = "userId")int userId); 
}
