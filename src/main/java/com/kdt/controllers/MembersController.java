package com.kdt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdt.dto.MembersDTO1;
import com.kdt.services.MembersService;
import com.kdt.services.MembersService1;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/members/")
public class MembersController {
	@Autowired
	private MembersService mservice;
	
	@Autowired
	private MembersService1 mservice1;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("login")
	public String login(String id, String pw) throws Exception{
		//String shapw = EncryptionUtils.getSHA512(pw);
		boolean result = this.mservice.isMember(id,pw);
		if(result) {
			session.setAttribute("loginId",id);
			MembersDTO1 userDTO = mservice1.loginUser(id);
		    System.out.println(userDTO);
		    if (userDTO != null) {
		        // DTO에서 이름과 부서 가져오기
		        String name = userDTO.getName();
		        String position = userDTO.getPosition();

		        // 세션에 이름과 부서 정보 저장
		        session.setAttribute("name", name);
		        session.setAttribute("position", position);

		    }
			return "home";
		}
		
		
		return "redirect:/";
	}
	
	@RequestMapping("logout")
	public String logout() throws Exception{
		session.invalidate();
		return "redirect:/";
	}
	
	//
	//
	//
	@RequestMapping(value = "/updateWorkForm")
	public String updateWorkForm(String idList, String workForm) throws Exception {
		mservice.updateWorkForm(idList, workForm);

		return "redirect:/insa/manage/members";
	}
	
	@RequestMapping(value = "/updatePosition")
	public String updatePosition(String idList, String position) throws Exception {
		mservice.updatePosition(idList, position);

		return "redirect:/insa/manage/members";
	}
	
	@RequestMapping(value = "/updateOrg")
	public String updateOrg(String idList, String org) throws Exception {
		mservice.updateOrg(idList, org);

		return "redirect:/insa/manage/members";
	}
	
	@RequestMapping(value = "/deleteMember")
	public String deleteMember(String idList) throws Exception {
		mservice.deleteMember(idList);

		return "redirect:/insa/manage/members";
	}
}
