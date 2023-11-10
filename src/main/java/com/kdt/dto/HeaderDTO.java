package com.kdt.dto;

public class HeaderDTO {
	private int seq;
	private String board_title;
	private String header;
	public HeaderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HeaderDTO(int seq, String board_title, String header) {
		super();
		this.seq = seq;
		this.board_title = board_title;
		this.header = header;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
	
}
