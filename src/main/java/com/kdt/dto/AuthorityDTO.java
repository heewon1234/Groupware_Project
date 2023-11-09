package com.kdt.dto;

public class AuthorityDTO {
	private int seq;
	private String id;
	private String ori_board_title;
	private String sys_board_title;
	private String authority;
	public AuthorityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthorityDTO(int seq, String id, String ori_board_title, String sys_board_title, String authority) {
		super();
		this.seq = seq;
		this.id = id;
		this.ori_board_title = ori_board_title;
		this.sys_board_title = sys_board_title;
		this.authority = authority;
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
	public String getOri_board_title() {
		return ori_board_title;
	}
	public void setOri_board_title(String ori_board_title) {
		this.ori_board_title = ori_board_title;
	}
	public String getSys_board_title() {
		return sys_board_title;
	}
	public void setSys_board_title(String sys_board_title) {
		this.sys_board_title = sys_board_title;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
}
