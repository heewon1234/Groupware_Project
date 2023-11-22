package com.kdt.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.Mk_BoardDTO;
import com.kdt.services.AuthorityService;
import com.kdt.services.BoardService;
import com.kdt.services.HeaderService;
import com.kdt.services.MembersService;
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
	AuthorityService aservice;
	
	@Autowired
	HeaderService hservice;
	
	@Autowired
	MembersService memberservice;
	
	@Autowired
	HttpSession session;
	
	// 게시판 목록 불러오기
	@RequestMapping("sideBar")
	public String sideBar(Model model) {
		String id = (String)session.getAttribute("loginId");
		String jobName = memberservice.getJobName(id);
		List<Mk_BoardDTO> group_list = new ArrayList<>();
		List<Mk_BoardDTO> all_list = new ArrayList<>();
		
		if(jobName.equals("관리")) {
			group_list = mservice.selectAllboard_type_group();
			all_list = mservice.selectAllboard_type_all();
		} else {
			group_list = mservice.select_board_type_group(id);
			all_list = mservice.select_board_type_all(id);
		}
		// 게시판 관리 보이게 하는 권한
		boolean isBoardAdmin = mservice.isBoardAdmin(id);
		if(jobName.equals("관리")) {
			isBoardAdmin = true;
		}
		model.addAttribute("isBoardAdmin",isBoardAdmin);
		model.addAttribute("group_list",group_list);
		model.addAttribute("all_list",all_list);
		return "boards/sideBar";
	}
	
	// 게시판 이름 중복 체크
	@ResponseBody
	@RequestMapping("isExistName")
	public boolean isExistName(String board_title) {
		boolean result = mservice.isExistName(board_title);
		return result;
	}
	
	// 게시판 목록 불러오기
	@RequestMapping("toMk_board")
	public String toMk_board(Model model){
		model.addAttribute("Mk_Board","게시판 만들기");
		List<String> organizationList = memberservice.selectAllOrganization(); // member service로 바꿀 예정		
		model.addAttribute("organizationList",organizationList);
		return "boards/mk_board";
	}
	

	@RequestMapping("Mk_boardInsert")
	public String Mk_boardInsert(Mk_BoardDTO dto, String headerList, String authorityList) {
		dto.setId((String)session.getAttribute("loginId"));
		mservice.Mk_boardInsert(dto, headerList, authorityList);
		return "redirect:/board/toBoard";
	}
	
	// MemberController로 옮겨라

	////////////////
	
	
	// 게시판 관리창으로 이동
	@RequestMapping("toEditBoard")
	public String toEditBoard(Model model) {
		String id = (String)session.getAttribute("loginId");
		List<Mk_BoardDTO> boardList = new ArrayList<>();
		if(memberservice.getJobName(id).equals("관리")) {
			boardList = mservice.selectAllBoard();
		} else {
			boardList = mservice.selectBoardById(id);
		}
		model.addAttribute("boardList",boardList);
		return "boards/edit_board";
	}
	
	// 게시판 삭제
	@RequestMapping("toDelBoard")
	public String toDelBoard() {
		return "boards/delBoard";
	}
	
	@RequestMapping("delBoard")
	public String delBoard(String board_title) throws Exception{
		mservice.delBoard(board_title);
		return "redirect:/mk_board/toEditBoard";
	}
	
	// 게시판 수정
	@RequestMapping("toEditBoardDetail")
	public String toEditBoardDetail(String board_title, Model model) {
		List<String> organizationList = memberservice.selectAllOrganization(); // member service로 바꿀 예정		
		model.addAttribute("organizationList",organizationList);
		
		Mk_BoardDTO boardDetail = mservice.boardDetail(board_title);
		List<Map<String,String>> authMember = aservice.selectAuthMember(board_title);
		
		if(boardDetail.isUse_header()) {
			List<String> headerList = hservice.selectHeader(board_title);
			model.addAttribute("headerList",headerList);
		}
		
		model.addAttribute("boardDetail",boardDetail);
		model.addAttribute("authMember",authMember);
		model.addAttribute("prevBoardTitle",board_title);
		return "boards/edit_board_detail";
	}
	
	@RequestMapping("editBoardDetail")
	public String editBoardDetail(Mk_BoardDTO dto, String headerList, String authorityList, String prevBoardTitle, String changeHeader) {
		System.out.println(dto.isUse_header());
		mservice.editBoardDetail(dto, headerList, authorityList, prevBoardTitle, changeHeader);
		return "redirect:/mk_board/toEditBoard";
	}
}
