package com.kdt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/commons/")
public class CommonsController {
	@RequestMapping("/topForm")
	public String topForm() {
		return "commons/topForm";
	}
	
}
