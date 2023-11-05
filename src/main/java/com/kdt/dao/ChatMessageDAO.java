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
		return db.insert("ChatMessage.insert"); 
	}
	public List<ChatMessageDTO> selectByType(Map<String, Object> parameterMap){ 
		return db.selectList("ChatMessage.selectByType", parameterMap); 
	}
}
