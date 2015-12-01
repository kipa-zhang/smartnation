package org.example.spring.controller;

import org.example.service.CarparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/park")
public class CarparkController {
	
	@Autowired
	private CarparkService carparkService;
	
	@RequestMapping("/latest")
	@ResponseBody
	public String getLatestCarparkInfo()
	{
		return carparkService.getCarparkInfo();
	}
}
