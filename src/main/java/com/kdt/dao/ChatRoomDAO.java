package com.kdt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.OneToOneChatDTO;

@Repository
public class ChatRoomDAO {
	@Autowired
	private SqlSession db;
	
	public void createOneChatRoom(String loggedInUserID, String otherUserID) {
	    Map<String, String> params = new HashMap<>();
	    params.put("loggedInUserID", loggedInUserID);
	    params.put("otherUserID", otherUserID);
	    db.insert("oneChat.createOneChatRoom", params);
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
		return db.selectOne("oneChat.oneroomExists", params);
	}
	
	public int oneCountAll() {
	    return db.selectOne("oneChat.oneCountAll");
	}
	public List<OneToOneChatDTO> selectAll() {
		System.out.println(db.selectList("oneChat.selectAll"));
		return db.selectList("oneChat.selectAll");
	}



}
