package com.kdt.dao;

import java.util.List;
import java.util.Map;

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
	
	public List<ContactDTO> shareContactSelectAll(ContactDTO dto) {
		return db.selectList("Contact.share_contact_selectAll", dto);
	}
	
	public List<ContactDTO> personalContactSelectNull(ContactDTO dto) {
		return db.selectList("Contact.personal_contact_selectNull", dto);
	}
	
	public List<ContactDTO> shareContactSelectNull(ContactDTO dto) {
		return db.selectList("Contact.share_contact_selectNull", dto);
	}
	
	public List<ContactDTO> personalContactSelectBy(ContactDTO dto) {
		return db.selectList("Contact.personal_contact_selectBy", dto);
	}
	
	public List<ContactDTO> shareContactSelectBy(ContactDTO dto) {
		return db.selectList("Contact.share_contact_selectBy", dto);
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
	
	public List<ContactDTO> shareContactRead(ContactDTO dto) {
		return db.selectList("Contact.share_contact_read", dto);
	}
	
	public int personalContactUpdate(ContactDTO dto) {
		return db.update("Contact.personal_contact_update", dto);
	}
	
	public int shareContactUpdate(ContactDTO dto) {
		return db.update("Contact.share_contact_update", dto);
	}
	
	public int personalContactDelete(ContactDTO dto) {
		return db.delete("Contact.personal_contact_delete", dto);
	}
	
	public int shareContactDelete(ContactDTO dto) {
		return db.delete("Contact.share_contact_delete", dto);
	}
	
	public List<ContactDTO> personalContactSearch(Map<String, Object> param) {
		System.out.println(param);
		return db.selectList("Contact.personal_contact_search", param);
	}
	
	public List<ContactDTO> shareContactSearch(Map<String, Object> param) {
		System.out.println(param);
		return db.selectList("Contact.share_contact_search", param);
	}
	
	public List<ContactDTO> shareContactSearch(String keyword) {
		return db.selectList("Contact.share_contact_search", keyword);
	}
	
	public int personalContactInsert(ContactDTO dto) {
		return db.insert("Contact.personal_contact_insert", dto);
	}
	
	public int personalContactTagRename(Map<String, String> param) {
		return db.update("Contact.personal_contact_tag_rename", param);
	}
	
	public int shareContactTagRename(Map<String, String> param) {
		return db.update("Contact.share_contact_tag_rename", param);
	}
	
	public int personalContactTagReTagList(ContactDTO dto) {
		return db.update("Contact.personal_contact_tag_retag_list", dto);
	}
	
	public int shareContactTagReTagList(ContactDTO dto) {
		return db.update("Contact.share_contact_tag_retag_list", dto);
	}
	
	public int personalContactTagDeleteRadioAllContact(ContactDTO dto) {
		return db.delete("Contact.personal_tag_delete_radio_all_contact", dto);
	}
	
	public int shareContactTagDeleteRadioAllContact(ContactDTO dto) {
		return db.delete("Contact.share_tag_delete_radio_all_contact", dto);
	}
	
	public int personalContactTagDeleteRadioAllTag(ContactDTO dto) {
		return db.delete("Contact.personal_tag_delete_radio_all_tag", dto);
	}
	
	public int shareContactTagDeleteRadioAllTag(ContactDTO dto) {
		return db.delete("Contact.share_tag_delete_radio_all_tag", dto);
	}
	
	public int personalContactTagDeleteRadioTagContact(ContactDTO dto) {
		return db.delete("Contact.personal_tag_delete_radio_tag_contact", dto);
	}
	
	public int shareContactTagDeleteRadioTagContact(ContactDTO dto) {
		return db.delete("Contact.share_tag_delete_radio_tag_contact", dto);
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
