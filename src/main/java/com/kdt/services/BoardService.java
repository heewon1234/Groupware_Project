package com.kdt.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.BoardDAO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.Mk_BoardDTO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO bdao;
	
	public List<Mk_BoardDTO> select_board_type_group(){
		return bdao.select_board_type_group();
	}
	
	public List<Mk_BoardDTO> select_board_type_all(){
		return bdao.select_board_type_all();
	}
	
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
