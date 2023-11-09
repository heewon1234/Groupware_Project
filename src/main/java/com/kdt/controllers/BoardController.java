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

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	BoardService bservice;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("sideBar")
	public String sideBar(Model model) {
		List<Mk_BoardDTO> group_list = bservice.select_board_type_group();
		List<Mk_BoardDTO> all_list = bservice.select_board_type_all();
		model.addAttribute("group_list",group_list);
		model.addAttribute("all_list",all_list);
		return "boards/sideBar";
	}
	
	@RequestMapping("toFavoriteBoard")
	public String toFavoriteBoard() {
		return "boards/favorite_board";
	}
	
	// 게시판 생성 관련
	@RequestMapping("toMk_board")
	public String toMk_board(Model model){
		List<String> organizationList = bservice.selectAllOrganization(); // member service로 바꿀 예정		
		model.addAttribute("organizationList",organizationList);
		return "boards/mk_board";
	}
	
	@RequestMapping("Mk_boardInsert")
	public String Mk_boardInsert(Mk_BoardDTO dto, String headerList, String authorityList) {
		bservice.Mk_boardInsert(dto, headerList, authorityList);
		return "redirect:/board/toFavoriteBoard";
	}
	//
	
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
	
	// 게시글 등록 관련
	@RequestMapping("toWriteContentsBoard")
	public String toWriteContentsBoard(Model model) {
		//String id = (String)session.getAttribute("loginID");
		String id = "test1";
		List<String> boardList = bservice.selectAuthBoard(id);
		model.addAttribute("boardList",boardList);
		System.out.println(boardList.size());
		return "boards/write_contents_board";
	}
	//
	
	@RequestMapping("toEditBoard")
	public String toEditBoard() {
		return "boards/edit_board";
	}
	
	@RequestMapping("toContentsBoard")
	public String toContentsBoard() {
		return "boards/contents_board";
	}
	
}
