package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.MembersDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/commons/")
public class CommonsController {
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/topForm")
	public String topForm(Model model) {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		model.addAttribute("userDTO", userDTO);
		return "commons/topForm";
	}
	
}
