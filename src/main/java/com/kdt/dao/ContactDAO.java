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
	
	public List<ContactDTO> personalContactTagSelectAll(ContactDTO dto) {
		return db.selectList("Contact.personal_contact_tag_selectAll", dto);
	}
	
	public List<ContactDTO> personalContactTagSelectAllDeplicate(ContactDTO dto) {
		return db.selectList("Contact.personal_contact_tag_selectAll_duplicate", dto);
	}
	
	public int personalContactTagInsert(ContactDTO dto) {
		return db.insert("Contact.personal_contact_tag_insert", dto);
	}
	
	public int personalContactInsert(ContactDTO dto) {
		return db.insert("Contact.personal_contact_insert", dto);
	}
	
	public int shareContactInsert(ContactDTO dto) {
		return db.insert("Contact.share_contact_insert", dto);
	}

	
}
