package com.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdt.dto.ContactDTO;

@Repository
public class ContactDAO {
	
	@Autowired
	private SqlSession db;
	
	public List<ContactDTO> personalContactSelectAll(ContactDTO dto) {
		return db.selectList("Contact.personal_contact_selectAll", dto);
	}
	
	public List<ContactDTO> personalContactSelectBy(ContactDTO dto) {
		return db.selectList("Contact.personal_contact_selectBy", dto);
	}
	
	public List<ContactDTO> personalContactSelectNull(ContactDTO dto) {
		return db.selectList("Contact.personal_contact_selectNull", dto);
	}
	
	public List<ContactDTO> personalContactTagSelectAll(ContactDTO dto) {
		return db.selectList("Contact.personal_contact_tag_selectAll", dto);
	}
	
	public List<ContactDTO> personalContactTagSelectAllDeplicate(ContactDTO dto) {
		return db.selectList("Contact.personal_contact_tag_selectAll_duplicate", dto);
	}
	
	public int personalContactTagInsert(ContactDTO dto) {
		return db.insert("Contact.personal_contact_tag_insert", dto);
	}
	
	public List<ContactDTO> personalContactRead(ContactDTO dto) {
		return db.selectList("Contact.personal_contact_read", dto);
	}
	
	public int personalContactUpdate(ContactDTO dto) {
		return db.update("Contact.personal_contact_update", dto);
	}
	
	public int personalContactDelete(ContactDTO dto) {
		return db.update("Contact.personal_contact_delete", dto);
	}
	
	public List<ContactDTO> personalContactSearch(String keyword) {
		return db.selectList("Contact.personal_contact_search", keyword);
	}
	
	public int personalContactInsert(ContactDTO dto) {
		return db.insert("Contact.personal_contact_insert", dto);
	}
	
	public List<ContactDTO> shareContactTagSelectAll(ContactDTO dto) {
		return db.selectList("Contact.share_contact_tag_selectAll", dto);
	}

	public List<ContactDTO> shareContactTagSelectAllDeplicate(ContactDTO dto) {
		return db.selectList("Contact.share_contact_tag_selectAll_duplicate", dto);
	}
	
	public int shareContactTagInsert(ContactDTO dto) {
		return db.insert("Contact.share_contact_tag_insert", dto);
	}
	
	public int shareContactInsert(ContactDTO dto) {
		return db.insert("Contact.share_contact_insert", dto);
	}

	
}
