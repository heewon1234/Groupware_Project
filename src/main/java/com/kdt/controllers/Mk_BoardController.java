package com.kdt.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.dto.AuthorityDTO;
import com.kdt.dto.HeaderDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.Mk_BoardDTO;
import com.kdt.services.AuthorityService;
import com.kdt.services.BoardService;
import com.kdt.services.HeaderService;
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
	HttpSession session;
	
	// 게시판 목록 불러오기
	@RequestMapping("sideBar")
	public String sideBar(Model model) {
		String id = (String)session.getAttribute("loginId");
		List<Mk_BoardDTO> group_list = mservice.select_board_type_group(id);
		List<Mk_BoardDTO> all_list = mservice.select_board_type_all(id);
		boolean isBoardAdmin = mservice.isBoardAdmin(id);
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
		List<String> organizationList = bservice.selectAllOrganization(); // member service로 바꿀 예정		
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
	public List<String> selectByJobName(String job_name, String organization){
		MembersDTO dto = new MembersDTO();
		dto.setOrganization(organization);
		dto.setJob_name(job_name);
		return bservice.selectByJobName(dto);
		
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
	
	
	// 게시판 관리창으로 이동
	@RequestMapping("toEditBoard")
	public String toEditBoard(Model model) {
		String id = (String)session.getAttribute("loginId");
		List<Mk_BoardDTO> boardList = mservice.selectBoardById(id);
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
		List<String> organizationList = bservice.selectAllOrganization(); // member service로 바꿀 예정		
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
	public String editBoardDetail(Mk_BoardDTO dto, String headerList, String authorityList, String prevBoardTitle) {
		mservice.editBoardDetail(dto, headerList, authorityList, prevBoardTitle);
		return "redirect:/mk_board/toEditBoard";
	}
}
