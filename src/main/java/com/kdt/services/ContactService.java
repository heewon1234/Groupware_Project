package com.kdt.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.dao.ContactDAO;
import com.kdt.dto.ContactDTO;

@Service
public class ContactService {

	@Autowired
	private ContactDAO dao;

	public int personal_total_count(ContactDTO dto) {
		return dao.personal_total_count(dto);
	}
	
	public int share_total_count(ContactDTO dto) {
		return dao.share_total_count(dto);
	}
	
	public int personal_total_count_by_tag_null(ContactDTO dto) {
		return this.dao.personal_total_count_by_tag_null(dto);
	}
	
	public int share_total_count_by_tag_null(ContactDTO dto) {
		return this.dao.share_total_count_by_tag_null(dto);
	}
	
	public int personal_total_count_by_tag(ContactDTO dto) {
		return this.dao.personal_total_count_by_tag(dto);
	}
	
	public int share_total_count_by_tag(ContactDTO dto) {
		return this.dao.share_total_count_by_tag(dto);
	}
	
	public int personal_total_count_by_keyword(ContactDTO dto, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("writer", dto.getWriter());
		param.put("keyword", keyword);
		return dao.personal_total_count_by_keyword(param);
	}
	
	public int share_total_count_by_keyword(ContactDTO dto, String keyword) {
		Map<String, Object> param = new HashMap<>();
		param.put("writer", dto.getWriter());
		param.put("keyword", keyword);
		return dao.share_total_count_by_keyword(param);
	}
	
	public List<ContactDTO> personalContactSelectAll(ContactDTO dto, int Start) {
		Map<String, Object> param = new HashMap<>();
		param.put("writer", dto.getWriter());
		param.put("Start", Start);
		return this.dao.personalContactSelectAll(param);
	}
	
	public List<ContactDTO> shareContactSelectAll(ContactDTO dto, int Start) {
		Map<String, Object> param = new HashMap<>();
		param.put("writer", dto.getWriter());
		param.put("Start", Start);
		return this.dao.shareContactSelectAll(param);
	}

	public List<ContactDTO> personalContactSelectNull(ContactDTO dto, int Start) {
		Map<String, Object> param = new HashMap<>();
		param.put("writer", dto.getWriter());
		param.put("Start", Start);
		return this.dao.personalContactSelectNull(param);
	}
	
	public List<ContactDTO> shareContactSelectNull(ContactDTO dto, int Start) {
		Map<String, Object> param = new HashMap<>();
		param.put("writer", dto.getWriter());
		param.put("Start", Start);
		return this.dao.shareContactSelectNull(param);
	}

	public List<ContactDTO> personalContactSelectBy(ContactDTO dto, int Start) {
		Map<String, Object> param = new HashMap<>();
		param.put("writer", dto.getWriter());
		param.put("tag", dto.getTag());
		param.put("Start", Start);
		return this.dao.personalContactSelectBy(param);
	}
	
	public List<ContactDTO> shareContactSelectBy(ContactDTO dto, int Start) {
		Map<String, Object> param = new HashMap<>();
		param.put("writer", dto.getWriter());
		param.put("tag", dto.getTag());
		param.put("Start", Start);
		return this.dao.shareContactSelectBy(param);
	}
	
	public List<ContactDTO> personalContactSelectBy_TagRenameSelect(ContactDTO dto) {
		return this.dao.personalContactSelectBy_TagRenameSelect(dto);
	}
	
	public List<ContactDTO> shareContactSelectBy_TagRenameSelect(ContactDTO dto) {
		return this.dao.shareContactSelectBy_TagRenameSelect(dto);
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

	public List<ContactDTO> personalContactRead(ContactDTO dto) {
		return this.dao.personalContactRead(dto);
	}

	public List<ContactDTO> shareContactRead(ContactDTO dto) {
		return this.dao.shareContactRead(dto);
	}

	public int personalContactUpdate(ContactDTO dto) {
		return this.dao.personalContactUpdate(dto);
	}

	public int shareContactUpdate(ContactDTO dto) {
		return this.dao.shareContactUpdate(dto);
	}

	public int personalContactDelete(ContactDTO dto) {
		return this.dao.personalContactDelete(dto);
	}

	public int shareContactDelete(ContactDTO dto) {
		return this.dao.shareContactDelete(dto);
	}

	public List<ContactDTO> personalContactSearch(ContactDTO dto, String keyword, int start) {
		Map<String, Object> param = new HashMap<>();
		param.put("writer", dto.getWriter());
		param.put("keyword", keyword);
		param.put("start", start);
		return this.dao.personalContactSearch(param);
	}

	public List<ContactDTO> shareContactSearch(ContactDTO dto, String keyword, int start) {
		Map<String, Object> param = new HashMap<>();
		param.put("writer", dto.getWriter());
		param.put("keyword", keyword);
		param.put("start", start);
		return this.dao.shareContactSearch(param);
	}

	public List<ContactDTO> shareContactSearch(String keyword) {
		return this.dao.shareContactSearch(keyword);
	}

	public int personalContactTagRename(String ori_tagname, String new_tagname, ContactDTO dto) {
		Map<String, String> param = new HashMap<>();
		param.put("ori_tagname", ori_tagname);
		param.put("new_tagname", new_tagname);
		param.put("writer", dto.getWriter());
		return this.dao.personalContactTagRename(param);
	}

	public int shareContactTagRename(String ori_tagname, String new_tagname) {
		Map<String, String> param = new HashMap<>();
		param.put("ori_tagname", ori_tagname);
		param.put("new_tagname", new_tagname);
		return this.dao.shareContactTagRename(param);
	}

	public int personalContactTagReTagList(ContactDTO dto) {
		return this.dao.personalContactTagReTagList(dto);
	}

	public int shareContactTagReTagList(ContactDTO dto) {
		return this.dao.shareContactTagReTagList(dto);
	}

	public int personalContactTagDeleteRadioAllContact(ContactDTO dto) {
		return this.dao.personalContactTagDeleteRadioAllContact(dto);
	}

	public int shareContactTagDeleteRadioAllContact(ContactDTO dto) {
		return this.dao.shareContactTagDeleteRadioAllContact(dto);
	}

	public int personalContactTagDeleteRadioAllTag(ContactDTO dto) {
		return this.dao.personalContactTagDeleteRadioAllTag(dto);
	}

	public int shareContactTagDeleteRadioAllTag(ContactDTO dto) {
		return this.dao.shareContactTagDeleteRadioAllTag(dto);
	}

	public int personalContactTagDeleteRadioTagContact(ContactDTO dto) {
		return this.dao.personalContactTagDeleteRadioTagContact(dto);
	}

	public int shareContactTagDeleteRadioTagContact(ContactDTO dto) {
		return this.dao.shareContactTagDeleteRadioTagContact(dto);
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
