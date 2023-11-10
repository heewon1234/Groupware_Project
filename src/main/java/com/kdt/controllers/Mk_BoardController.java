package com.kdt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.MembersDTO;
import com.kdt.dto.Mk_BoardDTO;
import com.kdt.services.BoardService;
import com.kdt.services.Mk_BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mk_board/")
public class Mk_BoardController {
	
	@Autowired
	BoardService bservice;
	
	@Autowired
	Mk_BoardService mservice;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("toMk_board")
	public String toMk_board(Model model){
		List<String> organizationList = bservice.selectAllOrganization(); // member service로 바꿀 예정		
		model.addAttribute("organizationList",organizationList);
		return "boards/mk_board";
	}
	

	@RequestMapping("Mk_boardInsert")
	public String Mk_boardInsert(Mk_BoardDTO dto, String headerList, String authorityList) {
		mservice.Mk_boardInsert(dto, headerList, authorityList);
		return "redirect:/board/toFavoriteBoard";
	}
	
	// MemberController로 옮겨라
	@ResponseBody
	@RequestMapping("selectAllMembers")
	public List<MembersDTO> selectAllMembers(){
		return bservice.selectAllMembers();
	}
	
	@ResponseBody
	@RequestMapping("selectByOrganization")
	public List<String> selectByOrganization(String organization){
		return bservice.selectByOrganization(organization);
	}
	
	@ResponseBody
	@RequestMapping("selectByJobName")
	public List<String> selectByJobName(String job_name){
		return bservice.selectByJobName(job_name);
		
	}
	
	@ResponseBody
	@RequestMapping("selectMemberByOrganization")
	public List<MembersDTO> selectMemberByOrganization(String organization){
		return bservice.selectMemberByOrganization(organization);
	}
	
	@ResponseBody
	@RequestMapping("selectMemberByOrganizationAndJobName")
	public List<MembersDTO> selectMemberByOrganizationAndJobName(String organization, String job_name){
		return bservice.selectMemberByOrganizationAndJobName(organization,job_name);
	}
	
	@ResponseBody
	@RequestMapping("selectMemberByName")
	public MembersDTO selectMemberByName(MembersDTO dto){
		return bservice.selectMemberByName(dto);
	}
	////////////////
}
