package com.kdt.dto;

public class ApprovalDTO {
	private int seq;
	private String id;
	private String title;
	private String contents;
	private boolean confirm;
	
	
	public ApprovalDTO() {
		super();
	}
	public ApprovalDTO(int seq, String id, String title, String contents, boolean confirm) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.confirm = confirm;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public boolean isConfirm() {
		return confirm;
	}
	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	
	
}
