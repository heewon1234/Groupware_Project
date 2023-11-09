package com.kdt.dto;

public class Official_CalendarDTO {
	private int seq;
	private String organization;
	private String schedule;
	private String startday;
	private String endday;
	
	
	public Official_CalendarDTO() {
		super();
	}
	public Official_CalendarDTO(int seq, String organization, String schedule, String startday, String endday) {
		super();
		this.seq = seq;
		this.organization = organization;
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
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
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
