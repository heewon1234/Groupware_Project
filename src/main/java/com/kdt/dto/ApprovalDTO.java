package com.kdt.dto;

import java.sql.Timestamp;

public class ApprovalDTO {
	private int seq;
	private String id;
	private String title;
	private String contents;
	private Timestamp draft_date;
	private String approval_status;
	private String doc_type;
	
	
	public ApprovalDTO() {
		super();
	}
	public ApprovalDTO(int seq, String id, String title, String contents, Timestamp draft_date, String approval_status,
			String doc_type) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.draft_date = draft_date;
		this.approval_status = approval_status;
		this.doc_type = doc_type;
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
	public String getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}
	public String getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	
}
