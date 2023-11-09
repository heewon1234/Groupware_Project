package com.kdt.dto;

public class WorksDTO {
	private String id;
	private String name;
	private String organization;
	private int leave_use;
	private int leave_remainder;
	private int work_late;
	private int work_early;
	private int work_notcheck;
	private int work_truancy;
	private int all_workday;
	private int all_worktime;
	
	
	public WorksDTO() {
		super();
	}
	public WorksDTO(String id, String name, String organization, int leave_use, int leave_remainder, int work_late,
			int work_early, int work_notcheck, int work_truancy, int all_workday, int all_worktime) {
		super();
		this.id = id;
		this.name = name;
		this.organization = organization;
		this.leave_use = leave_use;
		this.leave_remainder = leave_remainder;
		this.work_late = work_late;
		this.work_early = work_early;
		this.work_notcheck = work_notcheck;
		this.work_truancy = work_truancy;
		this.all_workday = all_workday;
		this.all_worktime = all_worktime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public int getLeave_use() {
		return leave_use;
	}
	public void setLeave_use(int leave_use) {
		this.leave_use = leave_use;
	}
	public int getLeave_remainder() {
		return leave_remainder;
	}
	public void setLeave_remainder(int leave_remainder) {
		this.leave_remainder = leave_remainder;
	}
	public int getWork_late() {
		return work_late;
	}
	public void setWork_late(int work_late) {
		this.work_late = work_late;
	}
	public int getWork_early() {
		return work_early;
	}
	public void setWork_early(int work_early) {
		this.work_early = work_early;
	}
	public int getWork_notcheck() {
		return work_notcheck;
	}
	public void setWork_notcheck(int work_notcheck) {
		this.work_notcheck = work_notcheck;
	}
	public int getWork_truancy() {
		return work_truancy;
	}
	public void setWork_truancy(int work_truancy) {
		this.work_truancy = work_truancy;
	}
	public int getAll_workday() {
		return all_workday;
	}
	public void setAll_workday(int all_workday) {
		this.all_workday = all_workday;
	}
	public int getAll_worktime() {
		return all_worktime;
	}
	public void setAll_worktime(int all_worktime) {
		this.all_worktime = all_worktime;
	}
	
	
}
