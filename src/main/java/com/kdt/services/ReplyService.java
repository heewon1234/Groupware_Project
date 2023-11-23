package com.kdt.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.ReplyDAO;
import com.kdt.dto.ReplyDTO;

@Service
public class ReplyService {
	
	@Autowired
	ReplyDAO rdao;
	
	public int insertReply(ReplyDTO dto) {
		return rdao.insertReply(dto);
	}
	
	public List<ReplyDTO> replyList(String board_title, String parent_seq){
		return rdao.replyList(new ReplyDTO(0,null,null,board_title,Integer.parseInt(parent_seq),null));
	}
	
	public List<ReplyDTO> replySelectBy(String board_title, String parent_seq, String start, String end){
		Map<String,String> map = new HashMap<>();
		map.put("board_title",board_title);
		map.put("parent_seq", parent_seq);
		map.put("start", start);
		map.put("end", end);
		
		return rdao.replySelectBy(map);
		
	}
	public int replyTotalCount(String board_title, String parent_seq) {
		Map<String,String> map = new HashMap<>();
		map.put("board_title",board_title);
		map.put("parent_seq", parent_seq);

		return rdao.replyTotalCount(map);
	}
	
	public int delReply(ReplyDTO dto) {
		return rdao.delReply(dto);
	}
	
	public int updateReply(ReplyDTO dto) {
		return rdao.updateReply(dto);
	}
	
}
