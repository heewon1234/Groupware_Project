package com.kdt.dto;

public class OneToOneChatDTO {
	private int oneSeq;
	private String type;
	private String loggedInUserID;
	private String otherUserID;
	
	public OneToOneChatDTO() {}
	public OneToOneChatDTO(int oneSeq, String type, String loggedInUserID, String otherUserID) {
		super();
		this.oneSeq = oneSeq;
		this.type = type;
		this.loggedInUserID = loggedInUserID;
		this.otherUserID = otherUserID;
	}
	public int getOneSeq() {
		return oneSeq;
	}
	public void setOneSeq(int oneSeq) {
		this.oneSeq = oneSeq;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLoggedInUserID() {
		return loggedInUserID;
	}
	public void setLoggedInUserID(String loggedInUserID) {
		this.loggedInUserID = loggedInUserID;
	}
	public String getOtherUserID() {
		return otherUserID;
	}
	public void setOtherUserID(String otherUserID) {
		this.otherUserID = otherUserID;
	}
	
	
	
}
