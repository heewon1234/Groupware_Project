package com.kdt.dto;

public class ApprovalResponsiblesDTO {
	private int id;
	private int doc_id;
	private String approver_id;
	private String approval_status;
	
	
	public ApprovalResponsiblesDTO() {
		super();
	}
	public ApprovalResponsiblesDTO(int id, int doc_id, String approver_id, String approval_status) {
		super();
		this.id = id;
		this.doc_id = doc_id;
		this.approver_id = approver_id;
		this.approval_status = approval_status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}
	public String getApprover_id() {
		return approver_id;
	}
	public void setApprover_id(String approver_id) {
		this.approver_id = approver_id;
	}
	public String getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}
}
