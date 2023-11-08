package com.kdt.dto;

import java.sql.Timestamp;

public class Mk_BoardDTO {
	private int seq;
	private String board_title;
	private String id;
	private String board_type;
	private String name_type;
	private boolean use_header;
	private Timestamp mk_date;
	public Mk_BoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mk_BoardDTO(int seq, String board_title, String id, String board_type, String name_type, boolean use_header,
			Timestamp mk_date) {
		super();
		this.seq = seq;
		this.board_title = board_title;
		this.id = id;
		this.board_type = board_type;
		this.name_type = name_type;
		this.use_header = use_header;
		this.mk_date = mk_date;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	public String getName_type() {
		return name_type;
	}
	public void setName_type(String name_type) {
		this.name_type = name_type;
	}
	public boolean isUse_header() {
		return use_header;
	}
	public void setUse_header(boolean use_header) {
		this.use_header = use_header;
	}
	public Timestamp getMk_date() {
		return mk_date;
	}
	public void setMk_date(Timestamp mk_date) {
		this.mk_date = mk_date;
	}
	
	
}
