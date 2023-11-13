package com.kdt.dto;

import java.sql.Timestamp;

public class BoardDTO {
	private int seq;
	private String board_title;
	private String title;
	private String writer;
	private Timestamp write_date;
	private String header;
	private boolean notice;
	private String contents;
	private String survey_question;
	private int view_count;
	private boolean favorite;
	public BoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardDTO(int seq, String board_title, String title, String writer, Timestamp write_date, String header,
			boolean notice, String contents, String survey_question, int view_count, boolean favorite) {
		super();
		this.seq = seq;
		this.board_title = board_title;
		this.title = title;
		this.writer = writer;
		this.write_date = write_date;
		this.header = header;
		this.notice = notice;
		this.contents = contents;
		this.survey_question = survey_question;
		this.view_count = view_count;
		this.favorite = favorite;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public boolean isNotice() {
		return notice;
	}
	public void setNotice(boolean notice) {
		this.notice = notice;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getSurvey_question() {
		return survey_question;
	}
	public void setSurvey_question(String survey_question) {
		this.survey_question = survey_question;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public boolean isFavorite() {
		return favorite;
	}
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	
	
	
}
