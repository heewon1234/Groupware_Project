package com.kdt.controllers;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.constants.Constants;
import com.kdt.dto.BoardDTO;
import com.kdt.dto.FileDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.ReplyDTO;
import com.kdt.dto.SurveyDTO;
import com.kdt.dto.SurveyUserDTO;
import com.kdt.services.AuthorityService;
import com.kdt.services.BoardService;
import com.kdt.services.FileService;
import com.kdt.services.HeaderService;
import com.kdt.services.MembersService;
import com.kdt.services.Mk_BoardService;
import com.kdt.services.ReplyService;
import com.kdt.services.SurveyService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	HeaderService hservice;

	@Autowired
	BoardService bservice;

	@Autowired
	Mk_BoardService mservice;

	@Autowired
	AuthorityService aservice;

	@Autowired
	ReplyService rservice;

	@Autowired
	SurveyService sservice;

	@Autowired
	FileService fservice;

	@Autowired
	HttpSession session;

	@Autowired
	MembersService memberService;
	
	// R 관련 기능

	@RequestMapping("toBoard") // 게시글 리스트 보는 곳 이동
	public String toBoard(String board_title, Model model, String cPage, String authBoard) throws Exception{
		model.addAttribute("authBoard",authBoard);
		String id = (String)session.getAttribute("loginId");
		if(session.getAttribute("searchText") != null && cPage != null) {
			String searchText = (String)session.getAttribute("searchText");
			String encodedSearchText = URLEncoder.encode(searchText, "UTF-8");
			return "redirect:/board/search?searchText="+encodedSearchText+"&cPage="+cPage;
		}
		
		if(board_title==null) {
			board_title = session.getAttribute("board_title")==null?"중요게시물":(String)session.getAttribute("board_title");
		}
		if(!board_title.equals("중요게시물")) {
			boolean auth = aservice.isExistAuth(id, board_title);
			if(!auth && !memberService.getJobName(id).equals("관리")) {
				model.addAttribute("auth",auth);
				board_title="중요게시물";
			}
		}
		session.setAttribute("board_title", board_title);

		// 페이징 처리
		int currentPage=1;
		if(cPage!=null) {currentPage = Integer.parseInt(cPage);}
		session.setAttribute("currentPage", currentPage);		
		int start = currentPage*Constants.RECORD_COUNT_PER_PAGE-(Constants.RECORD_COUNT_PER_PAGE-1)-1;
		int end = currentPage*Constants.RECORD_COUNT_PER_PAGE;
		
		List<BoardDTO> noticeList;
		if(board_title.equals("중요게시물")) {
			// 레코드 개수 
			int totalBoardContents = bservice.FavoriteAllContentsList(id);
			model.addAttribute("recordTotalCount",totalBoardContents); 
			// 컨텐츠 리스트
			List<Map<String,String>> boardContentsList = bservice.FavoriteListBy(board_title, id, String.valueOf(start));
			model.addAttribute("boardContentsList", boardContentsList);
			model.addAttribute("name_type",null);
		} else {
			// 레코드 개수
			int totalBoardContents = bservice.boardContentsList(board_title);
			model.addAttribute("recordTotalCount",totalBoardContents);
			
			// 공지 최근 5개만
			noticeList = bservice.Notice(board_title);
			model.addAttribute("noticeList", noticeList);
			
			// 게시글 리스트 최신 10개
			List<BoardDTO> boardContentsList = bservice.BoardContentsListBy(board_title, id, String.valueOf(start), String.valueOf(end));
			model.addAttribute("boardContentsList", boardContentsList);
			
			String name_type = mservice.selectNameType(board_title);
			model.addAttribute("name_type",name_type);
		}
		
		model.addAttribute("recordCountPerPage",Constants.RECORD_COUNT_PER_PAGE);
		model.addAttribute("naviCountPerPage",Constants.NAVI_COUNT_PER_PAGE);

		return "boards/contentsList_board";
	}

	@RequestMapping("search")
	public String search(String searchText, String cPage, Model model) {
		
		if(searchText.isEmpty()) {
			session.removeAttribute("searchText");
			return "redirect:/board/toBoard";
		}
		
		session.setAttribute("searchText", searchText);
		
		int currentPage=1;
		if(cPage!=null) {currentPage = Integer.parseInt(cPage);}
		session.setAttribute("currentPage", currentPage);		
		int start = currentPage*Constants.RECORD_COUNT_PER_PAGE-(Constants.RECORD_COUNT_PER_PAGE-1)-1;
		int end = currentPage*Constants.RECORD_COUNT_PER_PAGE;
		
		String board_title = (String)session.getAttribute("board_title");
		String id = (String)session.getAttribute("loginId");
		
		if(board_title.equals("중요게시물")) {
			
			int totalBoardContents = bservice.countSearchList(id, String.valueOf(start), searchText);
			model.addAttribute("recordTotalCount",totalBoardContents); 
			
			List<Map<String,String>> boardContentsList = bservice.SearchFavoriteListBy(id, String.valueOf(start), searchText);
			model.addAttribute("boardContentsList",boardContentsList);
			model.addAttribute("name_type",null);
		} else {
			int totalBoardContents = bservice.searchCountContentsListBy(board_title, searchText);
			model.addAttribute("recordTotalCount",totalBoardContents); 
			List<BoardDTO> boardContentsList = bservice.searchContentsListBy(board_title,id,String.valueOf(start),searchText);
			model.addAttribute("boardContentsList", boardContentsList);
			String name_type = mservice.selectNameType(board_title);
			model.addAttribute("name_type",name_type);
		}
		
		model.addAttribute("recordCountPerPage",Constants.RECORD_COUNT_PER_PAGE);
		model.addAttribute("naviCountPerPage",Constants.NAVI_COUNT_PER_PAGE);
		return "boards/contentsList_board";
	}
	
	@RequestMapping("toContentsBoard") // 게시글 내용 보는 곳으로 이동
	public String toContentsBoard(String seq, String board_title, Model model, String rNum) {

		if(board_title==null && (String)session.getAttribute("board_title")!="중요게시물") {
			board_title= (String)session.getAttribute("board_title");
		}
		session.setAttribute("board_title", board_title);

		// 댓글 페이징 처리
		int currentReplyPage=1;
		if(rNum!=null) {currentReplyPage = Integer.parseInt(rNum);}
		session.setAttribute("currentReplyPage", currentReplyPage);		
		int start = currentReplyPage*Constants.RECORD_COUNT_PER_PAGE-(Constants.RECORD_COUNT_PER_PAGE-1)-1;
		int end = currentReplyPage*Constants.RECORD_COUNT_PER_PAGE;
		List<ReplyDTO> replyList = rservice.replySelectBy(board_title, seq, String.valueOf(start), String.valueOf(end)); // 댓글 리스트
		model.addAttribute("replyList",replyList);
		model.addAttribute("replyListSize",rservice.replyTotalCount(board_title, seq));
		model.addAttribute("recordCountPerPage",Constants.RECORD_COUNT_PER_PAGE);
		model.addAttribute("naviCountPerPage",Constants.NAVI_COUNT_PER_PAGE);
		//

		BoardDTO boardContents = bservice.boardContents(board_title, seq); // 게시글 내용
		

		model.addAttribute("surveyList", null); // 설문조사 관련
		model.addAttribute("isVote",false);

		String id = (String)session.getAttribute("loginId");
		
		boolean auth = memberService.getJobName(id).equals("관리");
		model.addAttribute("auth",auth);
		
		if(boardContents.getSurvey_question()!=null) {
			model.addAttribute("isVote",sservice.isVote(new SurveyUserDTO(0,id,Integer.parseInt(seq),board_title)));
			List<SurveyDTO> list = sservice.selectServeyItem(new SurveyDTO(0,board_title, Integer.parseInt(seq),null,0,0));
			model.addAttribute("surveyList", list);
		}

		// 파일 목록
		FileDTO fdto = new FileDTO();
		fdto.setBoard_title(board_title);
		fdto.setParent_seq(Integer.parseInt(seq));
		List<FileDTO> fileList = fservice.selectFileList(fdto);
		model.addAttribute("fileList",fileList);
		
		// 게시판 이름 타입
		String name_type = mservice.selectNameType(board_title);
		model.addAttribute("name_type",name_type);
		
		// 내 프로필 사진
		String profile = memberService.loginUser(id).getProfile_image();
		String myProfile = profile==null?null:profile;
		model.addAttribute("myProfile",myProfile);
		
		// 작성자 이름, 프로필사진
		MembersDTO writerDTO = memberService.loginUser(boardContents.getWriter());
		String writerProfile = writerDTO.getProfile_image()==null ? null : writerDTO.getProfile_image();
		String writerName = writerDTO.getName();
		model.addAttribute("writerProfile",writerProfile);
		model.addAttribute("writerName",writerName);
		
		bservice.viewCountUpdate(board_title, seq);
		boardContents.setView_count(boardContents.getView_count()+1);
		model.addAttribute("boardContents",boardContents);
		return "boards/contents_board";
	}

	// C 관련 기능
	@RequestMapping("toWriteContentsBoard") // 게시글 작성하는 곳 이동
	public String toWriteContentsBoard(Model model) {
		String id = (String)session.getAttribute("loginId");
		boolean authBoard = aservice.isWriteAuthExist(id);
		
		if(!authBoard) {
			return "redirect:/board/toBoard?authBoard=false";
		}
		
		List<String> boardList = aservice.selectAuthBoard(id);
		model.addAttribute("boardList",boardList);
		return "boards/write_contents_board";
	}

	@RequestMapping("insertBoardContents") // 게시글 등록
	public String insertBoardContents(BoardDTO dto,String[] items, MultipartFile[] fileList) throws Exception{
		String writer = (String)session.getAttribute("loginId");
		dto.setWriter(writer);
		dto.setWrite_date(new Timestamp(System.currentTimeMillis()));
		bservice.insertBoardContents(dto, items, fileList);

		String board_title=(String)session.getAttribute("board_title");
		return "redirect:/board/toBoard";	
	}

	// U 관련 기능
	@RequestMapping("toEditContentsBoard") // 게시판 수정하는 곳 이동
	public String toEditBoard(String seq, Model model) {
		String board_title = (String)session.getAttribute("board_title");
		String id = (String)session.getAttribute("loginId");

		BoardDTO boardContents = bservice.boardContents(board_title, seq);
		List<FileDTO> fileList = fservice.selectFileList(new FileDTO(0,null,null,Integer.parseInt(seq),board_title,null));
		List<String> headerList = hservice.selectHeader(board_title);
		List<String> boardList = aservice.selectAuthBoard(id);
		List<SurveyDTO> surveyItemList = sservice.selectServeyItem(new SurveyDTO(0,board_title,Integer.parseInt(seq),null,0,0));
		boolean isExistVote = sservice.isExistVote(new SurveyDTO(0,board_title,Integer.parseInt(seq),null,0,0));

		model.addAttribute("isExistVote",isExistVote);
		model.addAttribute("surveyItemList",surveyItemList);
		model.addAttribute("boardContents",boardContents);
		model.addAttribute("headerList",headerList);
		model.addAttribute("fileList",fileList);
		model.addAttribute("boardList",boardList);
		model.addAttribute("seq",seq);

		return "boards/edit_contents_board";
	}

	@RequestMapping("updateContentsBoard")
	public String updateContentsBoard(BoardDTO dto,String prevContents, String inputFileDelBtn, String useSurvey, String[] items, MultipartFile[] fileList) throws Exception{
		bservice.updateContentsBoard(dto, prevContents, inputFileDelBtn, useSurvey, fileList, items);
		return "redirect:/board/toContentsBoard?seq="+dto.getSeq();
	}

	// D 관련 기능
	@RequestMapping("toDelContents")
	public String toDelContents() {
		return "boards/delContents";
	}

	@RequestMapping("delContents")
	public String delContents(String seq) throws Exception{
		String board_title = (String)session.getAttribute("board_title");
		String id = (String)session.getAttribute("loginId");
		bservice.delContents(seq, board_title,id);
		return "redirect:/board/toBoard";
	}


}
