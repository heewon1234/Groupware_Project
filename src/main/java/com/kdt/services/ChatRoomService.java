package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.ChatRoomDAO;
import com.kdt.dto.GroupChatDTO;
import com.kdt.dto.OneToOneChatDTO;

@Service
public class ChatRoomService {
	@Autowired//자동으로 연결됨
	private ChatRoomDAO dao;

	public void createOneChatRoom(String loggedInUserID, String otherUserID,int oneSeq) {
		dao.createOneChatRoom(loggedInUserID, otherUserID,oneSeq);
	}

	public void createGroupChat(GroupChatDTO group_dto) {
		dao.createGroupChat(group_dto);
	}

	// 방의 존재 여부 확인 메서드
	public boolean oneroomExists(String loggedInUserID, String otherUserID) {
		boolean roomExists = dao.oneroomExists(loggedInUserID, otherUserID);
        return roomExists;
    }
	//일대일 채팅 전체 수
	public int oneCountAll(String  id) {
	    return dao.oneCountAll(id);
	}
	//selectAll
	public List<OneToOneChatDTO> selectAll() throws Exception {
		return dao.selectAll();
	}
	//groupselectAll
	public List<GroupChatDTO> groupSelectAll() throws Exception {
		return dao.groupSelectAll();
	}

}
