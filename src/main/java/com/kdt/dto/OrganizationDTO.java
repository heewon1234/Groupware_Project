package com.kdt.dto;

public class OrganizationDTO {
	private String organization;
	private int level;
	private String manager;
	private int count;
	
	public OrganizationDTO() {}
	public OrganizationDTO(String organization, int level, String manager, int count) {
		super();
		this.organization = organization;
		this.level = level;
		this.manager = manager;
		this.count = count;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
