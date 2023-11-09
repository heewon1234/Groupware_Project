package com.kdt.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kdt.dao.BoardDAO;
import com.kdt.dto.AuthorityDTO;
import com.kdt.dto.HeaderDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.Mk_BoardDTO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO bdao;
	
	@Autowired
	private Gson gson;
	
	// sideBar 관련 
	public List<Mk_BoardDTO> select_board_type_group(){
		return bdao.select_board_type_group();
	}
	
	public List<Mk_BoardDTO> select_board_type_all(){
		return bdao.select_board_type_all();
	}
	//
	
	//Mk_board insert 관련
	@Transactional
	public void Mk_boardInsert(Mk_BoardDTO dto, String headerList, String authorityList) {
		bdao.Mk_boardInsert(dto);
		String sys_board_title = "board_"+dto.getSeq();
		
		String sql = "create table "+sys_board_title+"(\r\n"
				+ "	seq int auto_increment primary key,\r\n"
				+ "	title varchar(300) not null,\r\n"
				+ "	writer varchar(30) not null,\r\n"
				+ "	write_date timestamp not null default now(),\r\n"
				+ "	header varchar(150), \r\n"
				+ "	notice boolean not null,\r\n"
				+ "	contents varchar(6000) not null,\r\n"
				+ "	servey_question varchar(300),\r\n"
				+ "	view_count int not null default 0\r\n"
				+ ");";
		bdao.createTable(sql);
		
		List<AuthorityDTO> authorityMember = gson.fromJson(authorityList, new TypeToken<List<AuthorityDTO>>() {}.getType());
		for(AuthorityDTO auth : authorityMember) {
			bdao.authorityInsert(new AuthorityDTO(0,auth.getId(),sys_board_title,dto.getBoard_title(),auth.getAuthority()));
		}
		
		String[] headers = gson.fromJson(headerList, String[].class);
		for(String header:headers) {
			bdao.headerInsert(new HeaderDTO(0,sys_board_title,header));
		}
	}
	//
	
	// MemberService로 가시오
	public List<String> selectAllOrganization(){
		return bdao.selectAllOrganization();
	}
	
	public List<String> selectByOrganization(String organization){
		return bdao.selectByOrganization(organization);
	}
	
	public List<String> selectByJobName(String job_name){
		return bdao.selectByJobName(job_name);
	}
	public List<MembersDTO> selectMemberByOrganization(String organization){
		return bdao.selectMemberByOrganization(organization);
	}
	
	public List<MembersDTO> selectMemberByOrganizationAndJobName(String organization,String job_name){
		Map<String,String> map = new HashMap<>();
		map.put("organization", organization);
		map.put("job_name", job_name);
		return bdao.selectMemberByOrganizationAndJobName(map);
	}
	
	public MembersDTO selectMemberByName(MembersDTO dto){
		return bdao.selectMemberByName(dto);
	}
	//
	
}
