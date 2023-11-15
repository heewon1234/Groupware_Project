package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.ContactDAO;
import com.kdt.dto.ContactDTO;

@Service
public class ContactService {

	@Autowired
    private ContactDAO dao;
	
	public List<ContactDTO> personalContactSelectAll(ContactDTO dto) {
		return this.dao.personalContactSelectAll(dto);
	}
	
	public List<ContactDTO> personalContactSelectBy(ContactDTO dto) {
		return this.dao.personalContactSelectBy(dto);
	}
	
	public List<ContactDTO> personalContactTagSelectAll(ContactDTO dto) {
		return this.dao.personalContactTagSelectAll(dto);
	}
	
	public List<ContactDTO> personalContactTagSelectAllDeplicate(ContactDTO dto) {
		return this.dao.personalContactTagSelectAllDeplicate(dto);
	}
	
	public int personalContactTagInsert(ContactDTO dto) {
		return this.dao.personalContactTagInsert(dto);
	}
	
	public int personalContactInsert(ContactDTO dto) {
		return this.dao.personalContactInsert(dto);
	}
	
	public List<ContactDTO> shareContactTagSelectAll(ContactDTO dto) {
		return this.dao.shareContactTagSelectAll(dto);
	}
	
	public List<ContactDTO> shareContactTagSelectAllDeplicate(ContactDTO dto) {
		return this.dao.shareContactTagSelectAllDeplicate(dto);
	}
	
	public int shareContactTagInsert(ContactDTO dto) {
		return this.dao.shareContactTagInsert(dto);
	}
	
	public int shareContactInsert(ContactDTO dto) {
		return this.dao.shareContactInsert(dto);
	}
}
