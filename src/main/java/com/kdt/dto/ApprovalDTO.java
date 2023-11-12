package com.kdt.dto;

import java.sql.Timestamp;

public class ApprovalDTO {
	private int seq;
	private String id;
	private String title;
	private String contents;
	private Timestamp draft_date;
	private String status;
	
	
	public ApprovalDTO() {
		super();
	}
	public ApprovalDTO(int seq, String id, String title, String contents, Timestamp draft_date, String status) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.draft_date = draft_date;
		this.status = status;
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
	public Timestamp getDraft_date() {
		return draft_date;
	}
	public void setDraft_date(Timestamp draft_date) {
		this.draft_date = draft_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
