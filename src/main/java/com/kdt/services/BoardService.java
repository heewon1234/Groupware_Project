package com.kdt.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.kdt.dao.BoardDAO;
import com.kdt.dao.FavoriteBoardDAO;
import com.kdt.dao.FileDAO;
import com.kdt.dao.Mk_BoardDAO;
import com.kdt.dao.ReplyDAO;
import com.kdt.dao.SurveyDAO;
import com.kdt.dto.BoardDTO;
import com.kdt.dto.FavoriteBoardDTO;
import com.kdt.dto.FileDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.ReplyDTO;
import com.kdt.dto.SurveyDTO;
import com.kdt.dto.SurveyUserDTO;

@Service
public class BoardService {

	@Autowired
	BoardDAO bdao;

	@Autowired
	Mk_BoardDAO mdao;

	@Autowired
	FavoriteBoardDAO fdao;

	@Autowired
	SurveyDAO sdao;

	@Autowired
	FileDAO filedao;

	@Autowired
	ReplyDAO rdao;

	@Autowired
	private Gson gson;

	// MemberService로 가시오
	
	//

	// 게시글 등록 관련
	@Transactional
	public void insertBoardContents(BoardDTO dto,String[] items, MultipartFile[] fileList) throws Exception{

		// board 테이블 insert
		int boardSeq = mdao.selectBoardSeq(dto.getBoard_title()); 
		String board_title = dto.getBoard_title();
		dto.setBoard_title("Board_"+boardSeq);
		int parent_seq = bdao.insertBoardContents(dto);

		// Survey 테이블 insert
		if(!dto.getSurvey_question().isEmpty()) {
			for(String item:items) {
				if(!item.isEmpty()) {
					sdao.insertSurveyItem(new SurveyDTO(0,board_title,parent_seq,item,0,0));
				}
			}

		}

		// File 테이블 insert
		// input type = file
		String realPath = "C:/uploads";
		File uploadPath = new File(realPath); 
		if(!uploadPath.exists()) { 
			uploadPath.mkdir();
		}

		if(fileList != null) { 
			for(MultipartFile file : fileList) {
				if(!file.isEmpty()) {
					String ori_name = file.getOriginalFilename();
					String sys_name = UUID.randomUUID() + "_" + ori_name;
					file.transferTo(new File(uploadPath+"/"+sys_name));
					filedao.insertFile(new FileDTO(0,sys_name,ori_name,parent_seq,board_title,"input"));
				}	
			}
		}
		// img tag
		String srcinput = dto.getContents();
		List<String> imgSrcList = extractImageSrc(srcinput);
		List<String> imgOriName = oriNameRegex(srcinput);
		for (int i=0;i<imgOriName.size();i++) {
			String sys_name = imgSrcList.get(i);
			String ori_name = imgOriName.get(i);
			filedao.insertFile(new FileDTO(0,sys_name,ori_name,parent_seq,board_title,"tag"));
		}
	}

	// 게시글 불러오기 관련
	// 조회수
	public int viewCountUpdate(String board_title, String seq) {
		Map<String,String> map = new HashMap<>();
		int boardSeq = mdao.selectBoardSeq(board_title);
		map.put("board_title", "Board_"+boardSeq);
		map.put("seq", seq);
		return bdao.viewCountUpdate(map);
	}

	// 게시글 리스트 ( 개수 구할 때 사용 )
	public int boardContentsList(String board_title){ 
		int boardSeq = mdao.selectBoardSeq(board_title);
		return bdao.boardContentsList("Board_"+boardSeq);
	}
	// 게시글 리스트 10개씩 가져오기
	public List<BoardDTO> BoardContentsListBy(String board_title, String id, String start, String end){ 
		Map<String,String> map = new HashMap<>();
		int boardSeq = mdao.selectBoardSeq(board_title);
		map.put("oriBoardTitle", board_title);
		map.put("board_title", "Board_"+boardSeq);
		map.put("id", id);
		map.put("start", start);
		map.put("end", end);
		return bdao.BoardContentsListBy(map);
	}
	// 즐겨찾기 테이블 모든 리스트 ( 개수 구할 때 사용 )
	public int FavoriteAllContentsList(String id){ 
		return bdao.FavoriteAllContentsList(id);
	}
	// 즐겨찾기 리스트 10개씩 가져오기
	public List<Map<String,String>> FavoriteListBy(String board_title,String id, String start){ 
		
		String fromBoard = boardSeqList();
		if(fromBoard.isEmpty()) {
			List<Map<String,String>> list = new ArrayList<>();
			return list;
		}
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		map.put("fromBoard", fromBoard);
		map.put("start", start);

		return bdao.FavoriteListBy(map);
	}
	public List<BoardDTO> homeFavList(String id){
		
		String fromBoard = boardSeqList();
		if(fromBoard.isEmpty()) {
			List<BoardDTO> list = new ArrayList<>();
			return list;
		}
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		map.put("fromBoard", fromBoard);
		return bdao.homeFavList(map);
	}

	// 검색 기능
	// 즐겨찾기 게시판
	public List<Map<String,String>> SearchFavoriteListBy(String id, String start,String searchText){
		String fromBoard = boardSeqList();
		searchText = "%"+searchText+"%";
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		map.put("fromBoard", fromBoard);
		map.put("start", start);
		map.put("searchText", searchText);
		
		return bdao.SearchFavoriteListBy(map);
	}
	
	public int countSearchList(String id, String start,String searchText) {
		searchText = "%"+searchText+"%";
		String fromBoard = boardSeqList();
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		map.put("fromBoard", fromBoard);
		map.put("searchText", searchText);
		
		return bdao.countSearchList(map);
	}
	// 일반게시판
	public List<BoardDTO> searchContentsListBy(String board_title, String id, String start, String searchText){
		searchText = "%"+searchText+"%";
		Map<String,String> map = new HashMap<>();
		int boardSeq = mdao.selectBoardSeq(board_title);
		map.put("oriBoardTitle", board_title);
		map.put("board_title", "Board_"+boardSeq);
		map.put("id", id);
		map.put("start", start);
		map.put("searchText", searchText);
		return bdao.searchContentsListBy(map);
	}
	public int searchCountContentsListBy(String board_title,String searchText) {
		searchText = "%"+searchText+"%";
		Map<String,String> map = new HashMap<>();
		int boardSeq = mdao.selectBoardSeq(board_title);
		map.put("board_title", "Board_"+boardSeq);
		map.put("searchText", searchText);
		return bdao.searchCountContentsListBy(map);
	}
	private String boardSeqList() {
		List<Integer> seqList = mdao.allBoardSeq();
		String fromBoard = "";
		for(int i=0;i<seqList.size();i++) {
			if(i==seqList.size()-1) {
				fromBoard+="select *, 'Board_"+seqList.get(i)+"' as board_title from Board_"+seqList.get(i);	
			} else {			
				fromBoard+="select *, 'Board_"+seqList.get(i)+"' as board_title from Board_"+seqList.get(i) + " union all ";
			}
		}
		return fromBoard;
	}
	// 일반 게시판
	// 공지 최근 5개만 가져오기
	public List<BoardDTO> Notice(String board_title){ 
		Map<String,String> map = new HashMap<>();
		int seq = mdao.selectBoardSeq(board_title);
		map.put("board_title", "Board_"+seq);
		map.put("ori_board_title", board_title);	
		return bdao.Notice(map);
	}

	// 게시글 내용 불러오기
	public BoardDTO boardContents(String board_title, String seq) {		
		int boardSeq = mdao.selectBoardSeq(board_title);
		String sysBoardTitle = "Board_"+boardSeq;
		BoardDTO dto = new BoardDTO();
		dto.setBoard_title(sysBoardTitle);
		dto.setSeq(Integer.parseInt(seq));
		return bdao.boardContents(dto);
	}

	//

	// 게시글 삭제 관련
	@Transactional
	public void delContents(String seq, String board_title,String id) throws Exception{
		int boardSeq = mdao.selectBoardSeq(board_title);

		Map<String,String> map = new HashMap<>();
		map.put("board_title", "Board_"+boardSeq);
		map.put("seq", seq);

		int parent_seq = Integer.parseInt(seq);

		// 서버 파일 삭제
		List<FileDTO> fileList = filedao.selectFileList(new FileDTO(0,null,null,parent_seq,board_title,null));
		String realPath = "C:/uploads";
		File uploadPath = new File(realPath);
		for(FileDTO file : fileList) {
			Path path = Paths.get(uploadPath + "/" + file.getSys_name());
			Files.deleteIfExists(path);
		}


		// 테이블에서 데이터 삭제
		bdao.delContents(map); // board 테이블 데이터 삭제
		fdao.delFavContents(new FavoriteBoardDTO(0,"Board_"+boardSeq,board_title,id,parent_seq)); // 즐겨찾기 테이블 데이터 삭제
		rdao.delReplyByParentSeq(new ReplyDTO(0,null,null,board_title,parent_seq,null)); // 댓글 테이블 데이터 삭제
		filedao.delFileByParensSeq(new FileDTO(0,null,null,parent_seq,board_title,null)); // 파일 테이블 데이터 삭제
		sdao.delSurveyByParentSeq(new SurveyDTO(0,board_title,parent_seq,null,0,0)); // Survey 테이블 데이터 삭제
		sdao.delSurveyUserByParentSeq(new SurveyUserDTO(0,null,parent_seq,board_title)); // SurveyUser 테이블 데이터 삭제
	}
	//

	// 게시글 수정
	@Transactional
	public void updateContentsBoard(BoardDTO dto,String prevContents, String delInputFileList, String useSurvey, MultipartFile[] fileList,String[] items) throws Exception{
		// board 테이블 업데이트
		String board_title = dto.getBoard_title();
		int seq = mdao.selectBoardSeq(board_title);
		dto.setBoard_title("Board_"+seq);
		bdao.updateContentsBoard(dto);

		if(useSurvey.equals("false")) {
			dto.setSurvey_question(null);
			bdao.updateSurveyQuestion(dto);
		}

		if(dto.getSurvey_question()!=null) {
			bdao.updateSurveyQuestion(dto);
		}

		// 파일 테이블 업데이트
		String realPath = "C:/uploads";
		File uploadPath = new File(realPath);

		// input type=file
		String[] delFileList = gson.fromJson(delInputFileList,String[].class);
		for(String delFile:delFileList) {
			filedao.delFileBySysName(delFile);
			// 서버 파일 삭제
			Path path = Paths.get(uploadPath + "/" + delFile);
			Files.deleteIfExists(path);
		}

		if(fileList != null) { 
			for(MultipartFile file : fileList) {
				String ori_name = file.getOriginalFilename();
				String sys_name = UUID.randomUUID() + "_" + ori_name;
				file.transferTo(new File(uploadPath+"/"+sys_name));
				filedao.insertFile(new FileDTO(0,sys_name,ori_name,dto.getSeq(),board_title,"input"));
			}
		}

		// img tag
		// 태그 데이터 삭제
		FileDTO fdto = new FileDTO();
		fdto.setParent_seq(dto.getSeq());
		fdto.setBoard_title(board_title);
		filedao.delFileTag(fdto);

		// 파일 테이블에 태그 데이터 추가
		List<String> sysNameList = extractImageSrc(dto.getContents());
		List<String> oriNameList = oriNameRegex(dto.getContents());
		for(int i=0;i<sysNameList.size();i++) {
			filedao.insertFile(new FileDTO(0,sysNameList.get(i),oriNameList.get(i),dto.getSeq(),board_title,"tag"));
		}

		// 서버 파일 날리기
		List<String> prevFile = extractImageSrc(prevContents);
		List<String> notExistFile = notExistFile(prevFile,sysNameList);

		for(String file : notExistFile) {
			Path path = Paths.get(uploadPath + "/" + file);
			Files.deleteIfExists(path);
		}


		// 설문조사
		if(useSurvey.equals("false")) {
			sdao.delSurveyByParentSeq(new SurveyDTO(0,board_title,dto.getSeq(),null,0,0));
			sdao.delSurveyUserByParentSeq(new SurveyUserDTO(0,null,dto.getSeq(),board_title));
		}

		if(items!=null && useSurvey.equals("true")) { // 설문조사 항목 수정
			sdao.delSurveyByParentSeq(new SurveyDTO(0,board_title,dto.getSeq(),null,0,0));

			for(String item:items) {
				if(!item.isEmpty()) {
					sdao.insertSurveyItem(new SurveyDTO(0,board_title,dto.getSeq(),item,0,0));
				}
			}


		}

	}
	//

	// 사라진 태그 검색
	public List<String> notExistFile(List<String> prevList, List<String> updateList) { 
		boolean exist = false;
		List<String> existFile = new ArrayList<>();
		for(String prev : prevList) {
			for(String update : updateList) {
				if(prev.equals(update)) {
					exist = true;
					break;
				}
			}
			if(exist) {exist = false;}
			else {existFile.add(prev);}

		}

		return existFile;
	}

	public List<String> extractImageSrc(String input) { // sys_name 추출
		List<String> imgSrcList = new ArrayList<>();
		String regex = "<img\\s+type=\"file\"\\s+src\\s*=\\s*\"/uploads/([^\"]+)\"";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			String src = matcher.group(1);
			imgSrcList.add(src);
		}

		return imgSrcList;
	}

	public List<String> oriNameRegex(String input) { // ori_name 추출
		List<String> imgSrcList = new ArrayList<>();
		String regex =  "<img[^>]*type=\"file\"[^>]*src\\s*=\\s*\"/uploads/[^_]+_([^\"\\>]+)\"";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			String src = matcher.group(1); // 두 번째 그룹을 가져와서 저장 (뒤의 글자들)
			imgSrcList.add(src);
		}

		return imgSrcList;
	}
}
