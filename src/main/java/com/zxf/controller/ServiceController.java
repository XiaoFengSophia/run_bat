package com.zxf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
public class ServiceController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/service")
	public String service() {
		
		return "service success !";
	}

}
