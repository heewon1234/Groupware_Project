package com.kdt.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class WorkTimesDTO {
	private int seq;
	private String id;
	private String work_type;
	private Timestamp work_time;

	public WorkTimesDTO(int seq, String id, String work_type, Timestamp work_time) {
		super();
		this.seq = seq;
		this.id = id;
		this.work_type = work_type;
		this.work_time = work_time;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
		
	public WorkTimesDTO() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWork_type() {
		return work_type;
	}
	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}
	public Timestamp getWork_time() {
		return work_time;
	}
	public void setWork_time(Timestamp work_time) {
		this.work_time = work_time;
	}
		public String getTime() {
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(getWork_time());
	}
	
	
	
}
