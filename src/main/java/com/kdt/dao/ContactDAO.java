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
	
	public int personal_total_count(ContactDTO dto) {
		return db.selectOne("Contact.personal_total_count", dto);
	}
	
	public int share_total_count(ContactDTO dto) {
		return db.selectOne("Contact.share_total_count", dto);
	}
	
	public int personal_total_count_by_tag_null(ContactDTO dto) {
		return db.selectOne("Contact.personal_total_count_by_tag_null", dto);
	}
	
	public int share_total_count_by_tag_null(ContactDTO dto) {
		return db.selectOne("Contact.share_total_count_by_tag_null", dto);
	}
	
	public int personal_total_count_by_tag(ContactDTO dto) {
		return db.selectOne("Contact.personal_total_count_by_tag", dto);
	}
	
	public int share_total_count_by_tag(ContactDTO dto) {
		return db.selectOne("Contact.share_total_count_by_tag", dto);
	}
	
	public int personal_total_count_by_keyword(Map<String, Object> param) {
		return db.selectOne("Contact.personal_total_count_by_keyword", param);
	}
	
	public int share_total_count_by_keyword(Map<String, Object> param) {
		return db.selectOne("Contact.share_total_count_by_keyword", param);
	}
	
	public List<ContactDTO> personalContactSelectAll(Map<String, Object> param) {
		return db.selectList("Contact.personal_contact_selectAll", param);
	}
	
	public List<ContactDTO> shareContactSelectAll(Map<String, Object> param) {
		return db.selectList("Contact.share_contact_selectAll", param);
	}
	
	public List<ContactDTO> personalContactSelectNull(Map<String, Object> param) {
		return db.selectList("Contact.personal_contact_selectNull", param);
	}
	
	public List<ContactDTO> shareContactSelectNull(Map<String, Object> param) {
		return db.selectList("Contact.share_contact_selectNull", param);
	}
	
	public List<ContactDTO> personalContactSelectBy(Map<String, Object> param) {
		return db.selectList("Contact.personal_contact_selectBy", param);
	}
	
	public List<ContactDTO> shareContactSelectBy(Map<String, Object> param) {
		return db.selectList("Contact.share_contact_selectBy", param);
	}
	
	public List<ContactDTO> personalContactSelectBy_TagRenameSelect(ContactDTO dto) {
		return db.selectList("Contact.personalContactSelectBy_TagRenameSelect", dto);
	}
	
	public List<ContactDTO> shareContactSelectBy_TagRenameSelect(ContactDTO dto) {
		return db.selectList("Contact.shareContactSelectBy_TagRenameSelect", dto);
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
		return db.selectList("Contact.personal_contact_search", param);
	}
	
	public List<ContactDTO> shareContactSearch(Map<String, Object> param) {
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
