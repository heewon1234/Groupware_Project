package com.kdt.dto;

public class MembersDTO {
	private String id;
	private String pw;
	private String name;
	private String work_form;
	private String organization;
	private String job_name;
	private String position;
	private String phone;
	private String email;
	private String zipcode;
	private String address1;
	private String address2;
	private String profile_image;
	
	public MembersDTO() {
		super();
	}
	public MembersDTO(String id, String pw, String name, String work_form, String organization, String job_name,
			String position, String phone, String email, String zipcode, String address1, String address2,
			String profile_image) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.work_form = work_form;
		this.organization = organization;
		this.job_name = job_name;
		this.position = position;
		this.phone = phone;
		this.email = email;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
		this.profile_image = profile_image;
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
	public String getWork_form() {
		return work_form;
	}
	public void setWork_form(String work_form) {
		this.work_form = work_form;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	
	
}
