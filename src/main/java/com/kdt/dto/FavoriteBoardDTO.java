package com.kdt.dto;

public class FavoriteBoardDTO {
	private int seq;
	private String board_title;
	private String ori_board_title;
	private String id;
	private int parent_seq;
	public FavoriteBoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FavoriteBoardDTO(int seq, String board_title, String ori_board_title, String id, int parent_seq) {
		super();
		this.seq = seq;
		this.board_title = board_title;
		this.ori_board_title = ori_board_title;
		this.id = id;
		this.parent_seq = parent_seq;
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
	public String getOri_board_title() {
		return ori_board_title;
	}
	public void setOri_board_title(String ori_board_title) {
		this.ori_board_title = ori_board_title;
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
	
	
}
