package com.kdt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/approval/document")
@Controller
public class ApprovalController {

	@RequestMapping("/write")
	public String write(Model model) throws Exception {
		return "/approval/document/write";
	}
	
	@RequestMapping("/lists/all")
	public String listsAll(Model model) throws Exception {
		return "/approval/document/lists/all";
	}
	
	@RequestMapping(value="/left_item")
	public String toLeft_item(String selectItem, Model model) {
		model.addAttribute("selectItem", selectItem);
		
		return "/approval/document/left_item";
	}
}
