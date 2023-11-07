package com.kdt.dto;

public class MembersDTO1 {
	private String id;
	private String pw;
	private String name;
	private String position;
	private String organization;
	public MembersDTO1() {}
	
	public MembersDTO1(String id, String pw, String name, String position, String organization) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.position = position;
		this.organization = organization;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	
}
