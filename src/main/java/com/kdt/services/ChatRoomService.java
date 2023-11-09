package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.ChatRoomDAO;
import com.kdt.dto.OneToOneChatDTO;

@Service
public class ChatRoomService {
	@Autowired//자동으로 연결됨
	private ChatRoomDAO dao;

	public void createOneChatRoom(String loggedInUserID, String otherUserID,int oneSeq) {
		dao.createOneChatRoom(loggedInUserID, otherUserID,oneSeq);
	}

	public int createGroupChat(String groupName, List<String> users) {
		// 그룹 채팅방 생성 로직
		int groupSeq = dao.createGroupChat(groupName);

		if (groupSeq > 0) {
			// 생성된 채팅방에 사용자 추가 로직 한사람씩 insert를 해줌
			for (String userID : users) {
				addUserToGroupChat(groupSeq, userID); // 서비스 내부에서 다른 서비스 메서드를 호출
			}
		}

		return groupSeq;
	}
	public boolean addUserToGroupChat(int groupSeq, String userID) {
		// 사용자를 그룹 채팅방에 추가하는 로직
		return dao.addUserToGroupChat(groupSeq, userID);
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

}
