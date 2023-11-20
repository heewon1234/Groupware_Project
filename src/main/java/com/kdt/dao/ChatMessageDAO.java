package com.kdt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.ChatMessageDTO;

@Repository
public class ChatMessageDAO {
	@Autowired
	private SqlSession db;
	
	public int insert(ChatMessageDTO dto){ 
		return db.insert("ChatMessage.insert",dto); 
	}
	public List<ChatMessageDTO> selectByType(Map<String, Object> parameterMap){ 
		return db.selectList("ChatMessage.selectByType", parameterMap); 
	}
	public List<ChatMessageDTO> getPreviousMessages(String oneSeq){ 
		return db.selectList("ChatMessage.getPreviousMessages", oneSeq); 
	}
	public List<ChatMessageDTO> getPreviousGroupMessages(String groupSeq){ 
		List<ChatMessageDTO> list= db.selectList("ChatMessage.getPreviousGroupMessages", groupSeq); 
		return list;
	}
}
