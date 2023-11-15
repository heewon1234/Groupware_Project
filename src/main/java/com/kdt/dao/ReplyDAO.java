package com.kdt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.ReplyDTO;

@Repository
public class ReplyDAO {
	
	@Autowired
	private SqlSession db;
	
	public int insertReply(ReplyDTO dto) {
		System.out.println("멀쩡");
		return db.insert("Reply.insertReply",dto);
	}
	
	public List<ReplyDTO> replyList(ReplyDTO dto){
		return db.selectList("Reply.replyList",dto);
	}
	public List<ReplyDTO> replySelectBy(Map<String,String> map){
		return db.selectList("Reply.replySelectBy",map);
	}
	public int replyTotalCount(Map<String,String> map) {
		return db.selectOne("Reply.replyTotalCount",map);
	}
	
	public int delReply(ReplyDTO dto) {
		return db.delete("Reply.delReply",dto);
	}
	
	public int delReplyByParentSeq(ReplyDTO dto) {
		return db.delete("Reply.delReplyByParentSeq",dto);
	}
	
	public int updateReply(ReplyDTO dto) {
		return db.update("Reply.updateReply",dto);
	}
	
	
}
