package org.st.smartnation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.st.smartnation.model.SNUser;
import org.st.smartnation.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="hello")
	@ResponseBody
	public String sayHelloWorld(){
		return "Hello World!";
	}
	
	@RequestMapping(value="db")
	@ResponseBody
	public SNUser getUser(){
		return userService.getUserInfo(1);
	}
}
