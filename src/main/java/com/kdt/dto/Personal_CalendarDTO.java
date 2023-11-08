package com.kdt.dto;

public class Personal_CalendarDTO {
	private int seq;
	private String id;
	private String schedule;
	private String startday;
	private String endday;
	
	
	public Personal_CalendarDTO() {
		super();
	}

	
	public Personal_CalendarDTO(int seq, String id, String schedule, String startday, String endday) {
		super();
		this.seq = seq;
		this.id = id;
		this.schedule = schedule;
		this.startday = startday;
		this.endday = endday;
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


	public String getSchedule() {
		return schedule;
	}


	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}


	public String getStartday() {
		return startday;
	}


	public void setStartday(String startday) {
		this.startday = startday;
	}


	public String getEndday() {
		return endday;
	}


	public void setEndday(String endday) {
		this.endday = endday;
	}
}
