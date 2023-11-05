package com.kdt.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomDAO {
	@Autowired
	private SqlSession db;
	
	public int createOneChatRoom(String loggedInUserID, String otherUserID) {
	    Map<String, String> params = new HashMap<>();
	    params.put("loggedInUserID", loggedInUserID);
	    params.put("otherUserID", otherUserID);
	    return db.insert("oneChat.createOneChatRoom", params);
	}
	
	public int createGroupChat(String groupName) {
        return db.insert("groupChat.createGroupChat", groupName);
    }

	public boolean addUserToGroupChat(int groupSeq, String userID) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("groupSeq", groupSeq);
	    params.put("userID", userID);
	    return db.insert("groupChat.addUserToGroupChat", params) > 0;
	}
	public boolean oneroomExists(String loggedInUserID, String otherUserID) {
		Map<String, Object> params = new HashMap<>();
		params.put("loggedInUserID", loggedInUserID);
		params.put("otherUserID", otherUserID);
		return db.insert("oneChat.oneroomExists", params) < 0;
	}
	
	public int oneCountAll() {
	    return db.selectOne("oneChat.oneCountAll");
	}



}
