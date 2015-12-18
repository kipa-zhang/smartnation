package org.st.smartnation.webserviceimpl;

import java.io.IOException;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.st.smartnation.model.SNUser;
import org.st.smartnation.service.UserService;
import org.st.smartnation.webservice.UserWebService;

@WebService
@SOAPBinding(style = Style.RPC)
public class UserWebServiceImpl implements UserWebService {

	@Autowired
	private UserService userService;
	
	@Override
	public String getUser(@WebParam(name = "userId")int userId) {
		String result = "";
		SNUser user = userService.getUser(userId);
		try {
			result = new ObjectMapper().writeValueAsString(user);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
