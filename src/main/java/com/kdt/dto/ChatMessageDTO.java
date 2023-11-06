package com.kdt.dto;

import java.sql.Timestamp;

public class ChatMessageDTO {
	private int MessageSeq;
	private int roomID; 
	private String userID;
    private String message;
    private MessageType type;
    private Timestamp writer_date;
    
    public ChatMessageDTO() {}
	public ChatMessageDTO(int messageSeq, int roomID, String userID, String message, MessageType type,
			Timestamp writer_date) {
		super();
		MessageSeq = messageSeq;
		this.roomID = roomID;
		this.userID = userID;
		this.message = message;
		this.type = type;
		this.writer_date = writer_date;
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



	public MessageType getType() {
		return type;
	}



	public void setType(MessageType type) {
		this.type = type;
	}



	public Timestamp getWriter_date() {
		return writer_date;
	}



	public void setWriter_date(Timestamp writer_date) {
		this.writer_date = writer_date;
	}



	public enum MessageType {
        CHAT, JOIN, LEAVE
    }
}
