package com.kdt.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class LeavesDTO {
	private String id;
	private String name;
	private String organization;
	private String joinday;
	private int leave_regular;
	private int leave_bonus;
	private int leave_other_bonus;
	private int year_leave;
	private int bonus_leave;
	private int month_leave;
	private int family_event_leave;
	
	public LeavesDTO() {
		super();
	}
	public LeavesDTO(String id, String name, String organization, String joinday, int leave_regular, int leave_bonus,
			int leave_other_bonus, int year_leave, int bonus_leave, int month_leave, int family_event_leave) {
		super();
		this.id = id;
		this.name = name;
		this.organization = organization;
		this.joinday = joinday;
		this.leave_regular = leave_regular;
		this.leave_bonus = leave_bonus;
		this.leave_other_bonus = leave_other_bonus;
		this.year_leave = year_leave;
		this.bonus_leave = bonus_leave;
		this.month_leave = month_leave;
		this.family_event_leave = family_event_leave;
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
	public String getJoinday() {
		return joinday;
	}
	public void setJoinday(String joinday) {
		this.joinday = joinday;
	}
	public int getLeave_regular() {
		return leave_regular;
	}
	public void setLeave_regular(int leave_regular) {
		this.leave_regular = leave_regular;
	}
	public int getLeave_bonus() {
		return leave_bonus;
	}
	public void setLeave_bonus(int leave_bonus) {
		this.leave_bonus = leave_bonus;
	}
	public int getLeave_other_bonus() {
		return leave_other_bonus;
	}
	public void setLeave_other_bonus(int leave_other_bonus) {
		this.leave_other_bonus = leave_other_bonus;
	}
	public int getYear_leave() {
		return year_leave;
	}
	public void setYear_leave(int year_leave) {
		this.year_leave = year_leave;
	}
	public int getBonus_leave() {
		return bonus_leave;
	}
	public void setBonus_leave(int bonus_leave) {
		this.bonus_leave = bonus_leave;
	}
	public int getMonth_leave() {
		return month_leave;
	}
	public void setMonth_leave(int month_leave) {
		this.month_leave = month_leave;
	}
	public int getFamily_event_leave() {
		return family_event_leave;
	}
	public void setFamily_event_leave(int family_event_leave) {
		this.family_event_leave = family_event_leave;
	}
	public int getall_leave() {
		return (this.getLeave_regular()+this.getLeave_bonus()+this.getLeave_other_bonus());
	}
	public int getremainder_leave() {
		return this.getall_leave()-(this.getYear_leave()+this.getBonus_leave()+this.getMonth_leave()+this.getFamily_event_leave());
	}
	
}
