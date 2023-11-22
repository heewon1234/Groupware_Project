package com.kdt.dto;

import java.sql.Timestamp;

public class ReplyDTO {
	private int seq;
	private String writer;
	private String name;
	private String contents;
	private String board_title;
	private int parent_seq;
	private Timestamp write_date;
	public ReplyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReplyDTO(int seq, String writer, String contents, String board_title, int parent_seq, Timestamp write_date) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.contents = contents;
		this.board_title = board_title;
		this.parent_seq = parent_seq;
		this.write_date = write_date;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public String getName() {
		return name;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public int getParent_seq() {
		return parent_seq;
	}
	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	
	
}
