package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.ReplyDTO;

@Repository
public class ReplyDAO {
	
	@Autowired
	private SqlSession db;
	
	public int insertReply(ReplyDTO dto) {
		return db.insert("Reply.insertReply",dto);
	}
	
	public List<ReplyDTO> replyList(ReplyDTO dto){
		return db.selectList("Reply.replyList",dto);
	}
	
	public int delReply(int seq) {
		return db.delete("Reply.delReply",seq);
	}
	
	
}
