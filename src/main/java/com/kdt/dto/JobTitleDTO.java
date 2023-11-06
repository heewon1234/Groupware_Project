package com.kdt.dto;

public class JobTitleDTO {
	private String position;
	private int job_rank;
	
	public JobTitleDTO() {
		super();
	}
	public JobTitleDTO(String position, int job_rank) {
		super();
		this.position = position;
		this.job_rank = job_rank;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getJob_rank() {
		return job_rank;
	}

	public void setJob_rank(int job_rank) {
		this.job_rank = job_rank;
	}

	
		
	
}
