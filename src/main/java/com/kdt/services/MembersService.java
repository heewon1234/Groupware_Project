package com.kdt.services;

import java.util.ArrayList;
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

	public List<MembersDTO> selectAll() {
		return mdao.selectAll();
	}
	
	public List<MembersDTO> selectAllExceptMe(String id) {
		return mdao.selectAllExceptMe(id);
	}

	public void updateWorkForm(String idList, String workForm) {
		String list[] = idList.split(",");

		for (String id : list) {
			MembersDTO dto = new MembersDTO();
			dto.setId(id);
			dto.setWork_form(workForm);
			mdao.updateWorkForm(dto);
		}

		return;
	}

	public void updatePosition(String idList, String position) {
		String list[] = idList.split(",");

		for (String id : list) {
			MembersDTO dto = new MembersDTO();
			dto.setId(id);
			dto.setPosition(position);
			mdao.updatePosition(dto);
		}

		return;
	}

	public void updateOrg(String idList, String org) {
		String list[] = idList.split(",");

		for (String id : list) {
			MembersDTO dto = new MembersDTO();
			dto.setId(id);
			dto.setOrganization(org);
			mdao.updateOrg(dto);
		}

		return;
	}

	public void deleteMember(String idList) {
		String list[] = idList.split(",");

		for (String id : list) {
			mdao.deleteMember(id);
		}

		return;
	}

	public List<String> selectWorkForm() {
		return mdao.selectWorkForm();
	}

	public List<MembersDTO> selectApprovalMembers(String position) {
		return mdao.selectApprovalMembers(position);
	}

	public List<MembersDTO> selectAllNotLogged(String loggedInUserID) {
		return mdao.selectAllNotLogged(loggedInUserID);
	}

	public List<MembersDTO> getMembersByOrganization(String organization, String id) {
		return mdao.getMembersByOrganization(organization, id);
	}

	public MembersDTO loginUser(String id) {
		return mdao.loginUser(id);
	}

	public List<MembersDTO> getUserList() {
		return mdao.getUserList();
	}

	public List<String> getDepartmentList() {
		return mdao.getDepartmentList();
	}

	public List<MembersDTO> getManagerList(List<String> managerOrgList, List<String> managerJobTitleList) {
		List<MembersDTO> managerList = new ArrayList<>();

		for (String org : managerOrgList) {
			for (String position : managerJobTitleList) {
				List<MembersDTO> manager = mdao.getManager(org, position);

				if (manager != null)
					managerList.addAll(manager);
			}
		}

		return managerList;
	}

	public List<MembersDTO> getManagerListById(List<String> managerIdList) {
		List<MembersDTO> managerList = new ArrayList<>();

		for (String id : managerIdList) {

			MembersDTO manager = mdao.selectById(id);

			if (manager != null)
				managerList.add(manager);

		}

		return managerList;
	}
	public int countUser() {
		return mdao.countUser();
	}
	
	public String getJobName(String id) {
		return mdao.getJobName(id);
	}
	
	public int updateMember(MembersDTO dto) {
		return mdao.updateMember(dto);
	}
	
	// 게시판 권한 줄 때 사용
	public List<MembersDTO> selectAllMembers(){
		return mdao.selectAllMembers();
	}
	public List<String> selectAllOrganization(){
		return mdao.selectAllOrganization();
	}

	public List<String> selectByOrganization(String organization){
		return mdao.selectByOrganization(organization);
	}

	public List<String> selectByJobName(MembersDTO dto){
		return mdao.selectByJobName(dto);
	}
	public List<MembersDTO> selectMemberByOrganization(String organization){
		return mdao.selectMemberByOrganization(organization);
	}

	public List<MembersDTO> selectMemberByOrganizationAndJobName(String organization,String job_name){
		Map<String,String> map = new HashMap<>();
		map.put("organization", organization);
		map.put("job_name", job_name);
		return mdao.selectMemberByOrganizationAndJobName(map);
	}

	public MembersDTO selectMemberByName(MembersDTO dto){
		return mdao.selectMemberByName(dto);
	}
	// 게시판 권한 줄 때 사용
}