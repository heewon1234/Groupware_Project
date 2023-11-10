package com.kdt.dto;

public class SurveyDTO {
	private int seq;
	private String board_title;
	private int parent_seq;
	private String items;
	public SurveyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SurveyDTO(int seq, String board_title, int parent_seq, String items) {
		super();
		this.seq = seq;
		this.board_title = board_title;
		this.parent_seq = parent_seq;
		this.items = items;
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
	public int getParent_seq() {
		return parent_seq;
	}
	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	
	
}
