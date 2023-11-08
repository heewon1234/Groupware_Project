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
	
//	public void createOneChatRoom(String loggedInUserID, String otherUserID) {
//	    Map<String, String> params = new HashMap<>();
//	    params.put("loggedInUserID", loggedInUserID);
//	    params.put("otherUserID", otherUserID);
//	    db.insert("oneChat.createOneChatRoom", params);
//	}
	public void createOneChatRoom(String loggedInUserID, String otherUserID,int oneSeq) {
		System.out.println(oneSeq);
	    Map<String, Object> params1 = new HashMap<>();
	    params1.put("oneSeq", oneSeq);
	    params1.put("loggedInUserID", loggedInUserID);
	    params1.put("otherUserID", otherUserID);
	    db.insert("oneChat.createOneChatRoom", params1);

	    Map<String, Object> params2 = new HashMap<>();
	    params2.put("oneSeq", oneSeq);
	    params2.put("loggedInUserID", otherUserID);
	    params2.put("otherUserID", loggedInUserID);
	    db.insert("oneChat.createOneChatRoom", params2);
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
	
	public int oneCountAll(String  id) {
	    return db.selectOne("oneChat.oneCountAll",id);
	}
	public List<OneToOneChatDTO> selectAll() {
		System.out.println(db.selectList("oneChat.selectAll"));
		return db.selectList("oneChat.selectAll");
	}



}
