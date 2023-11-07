package com.kdt.dto;

public class GroupChatDTO {
	private int groupSeq;
	private String type;
	private String groupName ;
	private String userID;
	
	public GroupChatDTO() {}
	public GroupChatDTO(int groupSeq, String type, String groupName, String userID) {
		super();
		this.groupSeq = groupSeq;
		this.type = type;
		this.groupName = groupName;
		this.userID = userID;
	}
	public int getGroupSeq() {
		return groupSeq;
	}
	public void setGroupSeq(int groupSeq) {
		this.groupSeq = groupSeq;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
}
