package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.MembersDTO;
import com.kdt.services.MembersService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/commons/")
public class CommonsController {
	@Autowired
	private HttpSession session;
	@Autowired
	private MembersService mService;
	
	@RequestMapping("/topForm")
	public String topForm(Model model) {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		userDTO = mService.loginUser(userDTO.getId());
		model.addAttribute("userDTO", userDTO);
		return "commons/topForm";
	}
	
}
