package com.kdt.dto;

public class GroupChatDTO {
	private int groupSeq;
	private String messageType;
	private String groupName ;
	private String memberName;
	
	public GroupChatDTO() {}

	public GroupChatDTO(int groupSeq, String messageType, String groupName, String memberName) {
		super();
		this.groupSeq = groupSeq;
		this.messageType = messageType;
		this.groupName = groupName;
		this.memberName = memberName;
	}

	public int getGroupSeq() {
		return groupSeq;
	}

	public void setGroupSeq(int groupSeq) {
		this.groupSeq = groupSeq;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
}
