package com.kdt.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.ChatMessageDAO;
import com.kdt.dto.ChatMessageDTO;

@Service
public class ChatMessageService {
	@Autowired//자동으로 연결됨
	private ChatMessageDAO dao;
	
	public int insert(ChatMessageDTO dto) throws Exception {
		return dao.insert(dto);
	}
	
	public List<ChatMessageDTO> selectByType(Map<String, Object> parameterMap) {
        return dao.selectByType(parameterMap);
    }
}
