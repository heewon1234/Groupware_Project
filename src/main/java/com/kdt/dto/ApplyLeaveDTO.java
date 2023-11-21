package com.kdt.dto;

public class ApplyLeaveDTO {
	private int seq; 
	private String id; 
	private String user_names; 
	private String work_types; 
	private String work_days; 
	private int doc_id; 
	private String approval_status;
	public ApplyLeaveDTO() {
		super();
	}
	public ApplyLeaveDTO(int seq, String id, String user_names, String work_types, String work_days, int doc_id,
			String approval_status) {
		super();
		this.seq = seq;
		this.id = id;
		this.user_names = user_names;
		this.work_types = work_types;
		this.work_days = work_days;
		this.doc_id = doc_id;
		this.approval_status = approval_status;
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
	public String getUser_names() {
		return user_names;
	}
	public void setUser_names(String user_names) {
		this.user_names = user_names;
	}
	public String getWork_types() {
		return work_types;
	}
	public void setWork_types(String work_types) {
		this.work_types = work_types;
	}
	public String getWork_days() {
		return work_days;
	}
	public void setWork_days(String work_days) {
		this.work_days = work_days;
	}
	public int getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}
	public String getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}
	
	
	
}
