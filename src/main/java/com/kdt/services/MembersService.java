package com.kdt.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.MembersDAO;
import com.kdt.dto.MembersDTO;

@Service
public class MembersService {
	@Autowired
	public MembersDAO mdao;
	
	public boolean isMember(String id, String pw) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("pw", pw);
		return this.mdao.isMember(param);
	}
	
	public boolean idCheck(String id) {
		return this.mdao.idCheck(id);
	}
	
	public void signup(MembersDTO dto) {
		this.mdao.signup(dto);
	}
	
	public String getOrganization(String id) {
		return this.mdao.getOrganization(id);
	}
	
	//
	//
	//
	public List<MembersDTO> selectAll() {
		return mdao.selectAll();
	}
	
	public void updateWorkForm(String idList, String workForm) {
		String list[] = idList.split(",");
		
		for(String id : list) {
			MembersDTO dto = new MembersDTO();
			dto.setId(id);
			dto.setWork_form(workForm);
			mdao.updateWorkForm(dto);
		}
		
		return;
	}
	
	public void updatePosition(String idList, String position) {
		String list[] = idList.split(",");
		
		for(String id : list) {
			MembersDTO dto = new MembersDTO();
			dto.setId(id);
			dto.setPosition(position);
			mdao.updatePosition(dto);
		}
		
		return;
	}
	
	public void updateOrg(String idList, String org) {
		String list[] = idList.split(",");
		
		for(String id : list) {
			MembersDTO dto = new MembersDTO();
			dto.setId(id);
			dto.setOrganization(org);
			mdao.updateOrg(dto);
		}
		
		return;
	}
	
	public void deleteMember(String idList) {
		String list[] = idList.split(",");
		
		for(String id : list) {
			mdao.deleteMember(id);
		}
		
		return;
	}
	
	public List<String> selectWorkForm() {
		return mdao.selectWorkForm();
	}
}
