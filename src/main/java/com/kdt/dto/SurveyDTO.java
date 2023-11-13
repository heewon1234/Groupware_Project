package com.kdt.dto;

public class SurveyDTO {
	private int seq;
	private String board_title;
	private int parent_seq;
	private String items;
	private int vote;
	private int totalVote;
	public SurveyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SurveyDTO(int seq, String board_title, int parent_seq, String items, int vote, int totalVote) {
		super();
		this.seq = seq;
		this.board_title = board_title;
		this.parent_seq = parent_seq;
		this.items = items;
		this.vote = vote;
		this.totalVote = totalVote;
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
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public int getTotalVote() {
		return totalVote;
	}
	public void setTotalVote(int totalVote) {
		this.totalVote = totalVote;
	}
	
	
	
}
