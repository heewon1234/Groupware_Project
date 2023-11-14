package com.kdt.dto;

public class SurveyUserDTO {
	
	private int seq;
	private String id;
	private int parent_seq;
	private String board_title;
	public SurveyUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SurveyUserDTO(int seq, String id, int parent_seq, String board_title) {
		super();
		this.seq = seq;
		this.id = id;
		this.parent_seq = parent_seq;
		this.board_title = board_title;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getParent_seq() {
		return parent_seq;
	}
	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	
}
