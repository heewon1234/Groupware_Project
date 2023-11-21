package com.kdt.dto;

import java.sql.Timestamp;

public class ChatMessageDTO {
	private int MessageSeq;
	private int roomID; 
	private String profile_image; 
	private String userID;
    private String message;
    private String messageType;
    private MessageType type;
    private Timestamp write_date;
    
    public ChatMessageDTO() {}
	public ChatMessageDTO(int messageSeq, int roomID, String profile_image, String userID, String message,
			String messageType, MessageType type, Timestamp write_date) {
		super();
		MessageSeq = messageSeq;
		this.roomID = roomID;
		this.profile_image = profile_image;
		this.userID = userID;
		this.message = message;
		this.messageType = messageType;
		this.type = type;
		this.write_date = write_date;
	}


	public int getMessageSeq() {
		return MessageSeq;
	}


	public void setMessageSeq(int messageSeq) {
		MessageSeq = messageSeq;
	}


	public int getRoomID() {
		return roomID;
	}


	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}


	public String getProfile_image() {
		return profile_image;
	}


	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getMessageType() {
		return messageType;
	}


	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}


	public MessageType getType() {
		return type;
	}


	public void setType(MessageType type) {
		this.type = type;
	}


	public Timestamp getWrite_date() {
		return write_date;
	}


	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}


	public enum MessageType {
        CHAT, JOIN, LEAVE
    }
}
