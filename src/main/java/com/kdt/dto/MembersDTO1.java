package com.kdt.dto;

public class MembersDTO1 {
	private String id;
	private String pw;
	private String name;
	private String position;
	public MembersDTO1() {}
	public MembersDTO1(String id, String pw, String name, String position) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.position = position;
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
	
}
