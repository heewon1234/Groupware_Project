package com.kdt.dto;

public class ContactDTO {
	private int seq;
	private String name;
	private String email;
	private String phone;
	private String tag;
	private String company_name;
	private String company_rank;
	private String company_department;
	private String address;
	private String link;
	private String birthday;
	private String memo;
	private String edit;
	private String writer;
	
	public ContactDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContactDTO(int seq, String name, String email, String phone, String tag, String company_name,
			String company_rank, String company_department, String address, String link, String birthday, String memo,
			String edit, String writer) {
		super();
		this.seq = seq;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.tag = tag;
		this.company_name = company_name;
		this.company_rank = company_rank;
		this.company_department = company_department;
		this.address = address;
		this.link = link;
		this.birthday = birthday;
		this.memo = memo;
		this.edit = edit;
		this.writer = writer;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_rank() {
		return company_rank;
	}

	public void setCompany_rank(String company_rank) {
		this.company_rank = company_rank;
	}

	public String getCompany_department() {
		return company_department;
	}

	public void setCompany_department(String company_department) {
		this.company_department = company_department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
