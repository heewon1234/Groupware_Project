package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.ChatRoomDAO;

@Service
public class ChatRoomService {
	@Autowired//자동으로 연결됨
	private ChatRoomDAO dao;

	public int createOneChatRoom(String loggedInUserID, String otherUserID) {
		// 새로운 1:1 채팅방을 생성하는 로직을 구현합니다.
		// 이 부분은 데이터베이스 테이블에 새로운 레코드를 삽입하고 방 ID를 반환하는 방식으로 구현합니다.

		// 예를 들어, chatRoomDAO를 사용하여 데이터베이스에 새로운 방을 삽입하고 방 ID를 가져오는 코드를 작성합니다.
		int chatRoomID = dao.createOneChatRoom(loggedInUserID, otherUserID);

		// 방 ID를 반환합니다.
		return chatRoomID;
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
        return dao.oneroomExists(loggedInUserID, otherUserID);
    }
	//일대일 채팅 전체 수
	public int oneCountAll() {
	    return dao.oneCountAll();
	}

}
