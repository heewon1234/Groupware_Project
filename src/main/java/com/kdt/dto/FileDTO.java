package com.kdt.dto;

public class FileDTO {
	private int seq;
	private String sys_name;
	private String ori_name;
	private int parent_seq;
	private String board_title;
	private String type;
	public FileDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileDTO(int seq, String sys_name, String ori_name, int parent_seq, String board_title, String type) {
		super();
		this.seq = seq;
		this.sys_name = sys_name;
		this.ori_name = ori_name;
		this.parent_seq = parent_seq;
		this.board_title = board_title;
		this.type = type;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	public String getOri_name() {
		return ori_name;
	}
	public void setOri_name(String ori_name) {
		this.ori_name = ori_name;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
