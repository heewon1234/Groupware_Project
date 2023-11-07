package com.kdt.dto;

public class GroupMembersDTO {
	private int groupMemberSeq;
	private int groupSeq;
	private String userID ;
	
	public GroupMembersDTO() {}
	public GroupMembersDTO(int groupMemberSeq, int groupSeq, String userID) {
		super();
		this.groupMemberSeq = groupMemberSeq;
		this.groupSeq = groupSeq;
		this.userID = userID;
	}
	public int getGroupMemberSeq() {
		return groupMemberSeq;
	}
	public void setGroupMemberSeq(int groupMemberSeq) {
		this.groupMemberSeq = groupMemberSeq;
	}
	public int getGroupSeq() {
		return groupSeq;
	}
	public void setGroupSeq(int groupSeq) {
		this.groupSeq = groupSeq;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
