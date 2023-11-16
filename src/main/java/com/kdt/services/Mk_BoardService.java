package com.kdt.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kdt.dao.AuthorityDAO;
import com.kdt.dao.FileDAO;
import com.kdt.dao.HeaderDAO;
import com.kdt.dao.Mk_BoardDAO;
import com.kdt.dto.AuthorityDTO;
import com.kdt.dto.FileDTO;
import com.kdt.dto.HeaderDTO;
import com.kdt.dto.Mk_BoardDTO;

@Service
public class Mk_BoardService {

	@Autowired
	Mk_BoardDAO mdao;

	@Autowired
	HeaderDAO hdao;

	@Autowired
	AuthorityDAO adao;

	@Autowired
	FileDAO fdao;

	@Autowired
	private Gson gson;

	// sideBar 관련 
	public List<Mk_BoardDTO> select_board_type_group(String id){
		return mdao.select_board_type_group(id);
	}

	public List<Mk_BoardDTO> select_board_type_all(String id){
		return mdao.select_board_type_all(id);
	}
	//
	//게시판 이름 중복 체크
	public boolean isExistName(String board_title) {
		return mdao.isExistName(board_title);
	}
	// 게시판 생성
	@Transactional
	public void Mk_boardInsert(Mk_BoardDTO dto, String headerList, String authorityList) {
		mdao.Mk_boardInsert(dto);
		String sys_board_title = "Board_"+dto.getSeq();

		String sql = "create table "+sys_board_title+"(\r\n"
				+ "	seq int auto_increment primary key,\r\n"
				+ "	title varchar(300) not null,\r\n"
				+ "	writer varchar(30) not null,\r\n"
				+ "	write_date timestamp not null default now(),\r\n"
				+ "	header varchar(150), \r\n"
				+ "	notice boolean not null,\r\n"
				+ "	contents varchar(6000) not null,\r\n"
				+ "	survey_question varchar(300),\r\n"
				+ "	view_count int not null default 0\r\n"
				+ ");";
		mdao.createTable(sql);

		List<AuthorityDTO> authorityMember = gson.fromJson(authorityList, new TypeToken<List<AuthorityDTO>>() {}.getType());
		for(AuthorityDTO auth : authorityMember) {
			adao.authorityInsert(new AuthorityDTO(0,auth.getId(),dto.getBoard_title(),sys_board_title,auth.getAuthority()));
		}

		String[] headers = gson.fromJson(headerList, String[].class);
		for(String header:headers) {
			hdao.headerInsert(new HeaderDTO(0,dto.getBoard_title(),header));
		}
	}
	//

	// 게시판 불러오기
	public List<Mk_BoardDTO> selectAllBoard(){
		return mdao.selectAllBoard();
	}
	public Mk_BoardDTO boardDetail(String board_title) {
		return mdao.boardDetail(board_title);
	}
	public String selectNameType(String board_title) {
		return mdao.selectNameType(board_title);
	}
	//

	// 게시판 삭제
	@Transactional
	public void delBoard(String board_title) throws Exception{
		int boardSeq = mdao.selectBoardSeq(board_title);

		// drop table
		String sql = "drop table Board_"+boardSeq;
		mdao.delBoard(sql);

		// 서버 파일 날리기
		List<String> fileList = fdao.selectFileByBoardTitle(board_title);
		String realPath = "E:/uploads";
		File uploadPath = new File(realPath);
		for(String file:fileList) {
			Path path = Paths.get(uploadPath + "/" + file);
			Files.deleteIfExists(path);
		}
		// 게시판 관련 내용 삭제
		Map<String,String> map = new HashMap<>();
		map.put("board_title", board_title);

		String[] boardTitleTable = {"Mk_Board", "File", "Header", "Reply", "Survey", "Survey_User"};
		String[] oriBoardTitleTable = {"Authority","Favorite_Board"};

		for(String table : boardTitleTable) {
			map.put("table", table);
			mdao.deleteByBoardTitle(map);
		}

		for(String table : oriBoardTitleTable) {
			map.put("table", table);
			mdao.deleteByOriBoardTitle(map);
		}

	}
	//

	// 게시판 수정
	@Transactional
	public void editBoardDetail(Mk_BoardDTO dto, String headerList, String authorityList, String prevBoardTitle) {
		System.out.println(dto.getName_type());
		// mk_board 테이블 업데이트
		Map<String,String> map = new HashMap<>();
		map.put("board_title", dto.getBoard_title());
		map.put("board_type", dto.getBoard_type());
		map.put("name_type", dto.getName_type());
		map.put("use_header", String.valueOf(dto.isUse_header()));
		map.put("prevBoardTitle", prevBoardTitle);

		mdao.editBoardDetail(map); 

		// 관련 테이블 이름 변경
		String[] boardTitleTable = {"Mk_Board", "File", "Reply", "Survey", "Survey_User"};
		String[] oriBoardTitleTable = {"Favorite_Board"};

		for(String table : boardTitleTable) {
			map.put("table", table);
			mdao.editBoardByTitle(map);
		}

		for(String table : oriBoardTitleTable) {
			map.put("table", table);
			mdao.editBoardByOriBoardTitle(map);
		}

		// header 테이블, authority 테이블 데이터 삭제
		hdao.deleteHeader(prevBoardTitle);
		adao.deleteAuthority(prevBoardTitle);

		// header 테이블, authority 테이블에 새로 insert
		int boardSeq = mdao.selectBoardSeq(dto.getBoard_title());
		System.out.println("authorityList"+authorityList);
		List<AuthorityDTO> authorityMember = gson.fromJson(authorityList, new TypeToken<List<AuthorityDTO>>() {}.getType());
		System.out.println("authorityMember"+authorityMember);
		for(AuthorityDTO auth : authorityMember) {
			adao.authorityInsert(new AuthorityDTO(0,auth.getId(),dto.getBoard_title(),"Board_"+boardSeq,auth.getAuthority()));
		}

		if(dto.isUse_header()) {
			String[] headers = gson.fromJson(headerList, String[].class);
			for(String header:headers) {
				hdao.headerInsert(new HeaderDTO(0,dto.getBoard_title(),header));
			}
		}	
	}
	//
}
