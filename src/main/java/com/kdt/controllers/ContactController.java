package com.kdt.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdt.constants.Constants;
import com.kdt.dto.ContactDTO;
import com.kdt.services.ContactService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/contact/")
@Controller
public class ContactController {

	@Autowired
	private ContactService Service;

	@Autowired
	private HttpSession session;

	@RequestMapping("personal")
	public String personal(ContactDTO dto, Model model, String cPage) {
		session.setAttribute("pagingType", "personal");
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);

		List<ContactDTO> personalTagList = Service.personalContactTagSelectAll(dto);
		List<ContactDTO> shareTagList = Service.shareContactTagSelectAll(dto);

		model.addAttribute("personalTagList", personalTagList);
		model.addAttribute("shareTagList", shareTagList);

		// 페이징
		int contactCurrentPage = 1;
		if (cPage != null) {
			contactCurrentPage = Integer.parseInt(cPage);
		}

		session.setAttribute("contactCurrentPage", contactCurrentPage);
		int start = contactCurrentPage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1;
		int end = contactCurrentPage * Constants.RECORD_COUNT_PER_PAGE;

		model.addAttribute("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
		model.addAttribute("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);

		if (dto.getTag() == null || dto.getTag().equals("개인 주소록")) {
			List<ContactDTO> personalContactList = Service.personalContactSelectAll(dto, start);
			int personalContactCount = Service.personal_total_count(dto);

			model.addAttribute("personalContactList", personalContactList);
			model.addAttribute("contactName", "개인 주소록");
			model.addAttribute("replyListSize", personalContactCount);

		}

		else {
			if (dto.getTag().equals("미등록 태그")) {
				List<ContactDTO> personalContactList = Service.personalContactSelectNull(dto, start);
				int personalContactCount = Service.personal_total_count_by_tag_null(dto);

				model.addAttribute("personalContactList", personalContactList);
				model.addAttribute("contactName", dto.getTag());
				model.addAttribute("replyListSize", personalContactCount);
			}

			else {
				List<ContactDTO> personalContactList = Service.personalContactSelectBy(dto, start);
				int personalContactCount = Service.personal_total_count_by_tag(dto);

				model.addAttribute("personalContactList", personalContactList);
				model.addAttribute("contactName", dto.getTag());
				model.addAttribute("replyListSize", personalContactCount);
			}
		}

		return "contact/personal";
	}

	@RequestMapping("share")
	public String share(ContactDTO dto, Model model, String cPage) {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);

		List<ContactDTO> personalTagList = Service.personalContactTagSelectAll(dto);
		List<ContactDTO> shareTagList = Service.shareContactTagSelectAll(dto);

		model.addAttribute("personalTagList", personalTagList);
		model.addAttribute("shareTagList", shareTagList);

		// 페이징
		int contactCurrentPage = 1;
		if (cPage != null) {
			contactCurrentPage = Integer.parseInt(cPage);
		}

		session.setAttribute("contactCurrentPage", contactCurrentPage);
		int start = contactCurrentPage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1;
		int end = contactCurrentPage * Constants.RECORD_COUNT_PER_PAGE;

		model.addAttribute("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
		model.addAttribute("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);

		if (dto.getTag() == null || dto.getTag().equals("공유 주소록")) {
			List<ContactDTO> shareContactList = Service.shareContactSelectAll(dto, start);
			int shareContactCount = Service.share_total_count(dto);

			model.addAttribute("shareContactList", shareContactList);
			model.addAttribute("contactName", "공유 주소록");
			model.addAttribute("replyListSize", shareContactCount);
		}

		else {

			if (dto.getTag().equals("미등록 태그")) {
				List<ContactDTO> shareContactList = Service.shareContactSelectNull(dto, start);
				int shareContactCount = Service.share_total_count_by_tag_null(dto);

				model.addAttribute("shareContactList", shareContactList);
				model.addAttribute("contactName", dto.getTag());
				model.addAttribute("replyListSize", shareContactCount);
			}

			else {
				List<ContactDTO> shareContactList = Service.shareContactSelectBy(dto, start);
				int shareContactCount = Service.share_total_count_by_tag(dto);

				model.addAttribute("shareContactList", shareContactList);
				model.addAttribute("contactName", dto.getTag());
				model.addAttribute("replyListSize", shareContactCount);

			}
		}
		return "contact/share";
	}

	@ResponseBody
	@RequestMapping("personalContactTagSelectAll")
	public List<ContactDTO> personalContactTagSelectAll(ContactDTO dto) {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		return this.Service.personalContactTagSelectAll(dto);
	}

	@ResponseBody
	@RequestMapping("shareContactTagSelectAll")
	public List<ContactDTO> shareContactTagSelectAll(ContactDTO dto) {
		return this.Service.shareContactTagSelectAll(dto);
	}

	@ResponseBody
	@RequestMapping("personalContactTagSelectAllDeplicate")
	public List<ContactDTO> personalContactTagSelectAllDeplicate(ContactDTO dto) {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		return Service.personalContactTagSelectAllDeplicate(dto);
	}

	@ResponseBody
	@RequestMapping("shareContactTagSelectAllDeplicate")
	public List<ContactDTO> shareContactTagSelectAllDeplicate(ContactDTO dto) {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		return Service.shareContactTagSelectAllDeplicate(dto);
	}

	@ResponseBody
	@RequestMapping("personalContactTagInsert")
	public int personalContactTagInsert(ContactDTO dto) {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		return Service.personalContactTagInsert(dto);
	}

	@RequestMapping("shareContactInsert")
	public String shareContactInsert(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		Service.shareContactInsert(dto);
		return "redirect:/contact/share";
	}

	@ResponseBody
	@RequestMapping("shareContactTagInsert")
	public int shareContactTagInsert(ContactDTO dto) {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		return Service.shareContactTagInsert(dto);
	}

	@RequestMapping("personalContactTagInsertDirect")
	public String personalContactTagInsertDirect(ContactDTO dto) {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		Service.personalContactTagInsert(dto);
		return "redirect:/contact/personal";
	}

	@RequestMapping("shareContactTagInsertDirect")
	public String shareContactTagInsertDirect(ContactDTO dto) {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		Service.shareContactTagInsert(dto);
		return "redirect:/contact/share";
	}

	@RequestMapping("personalContactInsert")
	public String personalContactInsert(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		Service.personalContactInsert(dto);
		return "redirect:/contact/personal";
	}

	@ResponseBody
	@RequestMapping("personalContactInsertList")
	public void personalContactInsertList(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		Service.personalContactInsert(dto);
	}

	@ResponseBody
	@RequestMapping("shareContactInsertList")
	public void shareContactInsertList(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		Service.shareContactInsert(dto);
	}

	@ResponseBody
	@RequestMapping("personalContactRead")
	public List<ContactDTO> personalContactRead(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		List<ContactDTO> personalContactList = Service.personalContactRead(dto);
		return personalContactList;
	}

	@ResponseBody
	@RequestMapping("shareContactRead")
	public Map<String, Object> shareContactRead(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		List<ContactDTO> shareContactList = Service.shareContactRead(dto);

		Map<String, Object> result = new HashMap<>();
		result.put("shareContactList", shareContactList);
		result.put("id", id);

		return result;
	}

	@RequestMapping("personalContactUpdate")
	public String personalContactUpdate(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		Service.personalContactUpdate(dto);
		return "redirect:/contact/personal";
	}

	@RequestMapping("shareContactUpdate")
	public String shareContactUpdate(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		Service.shareContactUpdate(dto);
		return "redirect:/contact/share";
	}

	@RequestMapping("personalContactDelete")
	public String personalContactDelete(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		Service.personalContactDelete(dto);
		return "redirect:/contact/personal";
	}

	@RequestMapping("shareContactDelete")
	public String shareContactDelete(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		Service.shareContactDelete(dto);
		return "redirect:/contact/share";
	}

	@RequestMapping("personalContactSearch")
	public String personalContactSearch(@RequestParam("keyword") String keyword, Model model, String cPage)
			throws Exception {
		ContactDTO dto = new ContactDTO();

		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);

		// 페이징
		int contactCurrentPage = 1;
		if (cPage != null) {
			contactCurrentPage = Integer.parseInt(cPage);
		}

		session.setAttribute("contactCurrentPage", contactCurrentPage);
		int start = contactCurrentPage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1;
		int end = contactCurrentPage * Constants.RECORD_COUNT_PER_PAGE;

		List<ContactDTO> personalContactList = Service.personalContactSearch(dto, keyword, start);

		model.addAttribute("personalContactList", personalContactList);
		model.addAttribute("contactName", "검색된 항목");
		model.addAttribute("hiddenKeyword", keyword);

		model.addAttribute("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
		model.addAttribute("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);

		List<ContactDTO> personalTagList = Service.personalContactTagSelectAll(dto);
		List<ContactDTO> shareTagList = Service.shareContactTagSelectAll(dto);

		model.addAttribute("personalTagList", personalTagList);
		model.addAttribute("shareTagList", shareTagList);

		int personalContactCount = Service.personal_total_count_by_keyword(dto, keyword);

		model.addAttribute("personalContactList", personalContactList);
		model.addAttribute("replyListSize", personalContactCount);

		return "contact/personal";
	}

	@RequestMapping("shareContactSearch")
	public String shareContactSearch(@RequestParam("keyword") String keyword, Model model, String cPage)
			throws Exception {
		ContactDTO dto = new ContactDTO();

		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);

		// 페이징
		int contactCurrentPage = 1;
		if (cPage != null) {
			contactCurrentPage = Integer.parseInt(cPage);
		}

		session.setAttribute("contactCurrentPage", contactCurrentPage);
		int start = contactCurrentPage * Constants.RECORD_COUNT_PER_PAGE - (Constants.RECORD_COUNT_PER_PAGE - 1) - 1;
		int end = contactCurrentPage * Constants.RECORD_COUNT_PER_PAGE;

		List<ContactDTO> shareContactList = Service.shareContactSearch(dto, keyword, start);

		model.addAttribute("shareContactList", shareContactList);
		model.addAttribute("contactName", "검색된 항목");
		model.addAttribute("hiddenKeyword", keyword);

		model.addAttribute("recordCountPerPage", Constants.RECORD_COUNT_PER_PAGE);
		model.addAttribute("naviCountPerPage", Constants.NAVI_COUNT_PER_PAGE);
		
		List<ContactDTO> personalTagList = Service.personalContactTagSelectAll(dto);
		List<ContactDTO> shareTagList = Service.shareContactTagSelectAll(dto);

		model.addAttribute("personalTagList", personalTagList);
		model.addAttribute("shareTagList", shareTagList);

		int shareContactCount = Service.share_total_count_by_keyword(dto, keyword);

		model.addAttribute("personalContactList", shareContactList);
		model.addAttribute("replyListSize", shareContactCount);
		return "contact/share";
	}

	@RequestMapping("personalContactTagRename")
	public String personalContactTagRename(@RequestParam("ori_tagname") String ori_tagname,
			@RequestParam("new_tagname") String new_tagname) throws Exception {

		ContactDTO dto = new ContactDTO();
		String id = (String) session.getAttribute("loginId");

		dto.setWriter(id);
		dto.setTag(ori_tagname);

		Service.personalContactTagRename(ori_tagname, new_tagname, dto);

		List<ContactDTO> personalContactList = Service.personalContactSelectBy_TagRenameSelect(dto);

		for (int i = 0; i < personalContactList.size(); i++) {
			String[] toTagList = personalContactList.get(i).getTag().split(",");
			List<String> tagList = new ArrayList<>(Arrays.asList(toTagList));

			for (int k = 0; k < tagList.size(); k++) {
				if (tagList.get(k).equals(ori_tagname)) {
					tagList.set(k, new_tagname);
				}
			}

			String newTagList = String.join(",", tagList);

			dto.setSeq(personalContactList.get(i).getSeq());
			dto.setTag(newTagList);

			Service.personalContactTagReTagList(dto);
		}
		return "redirect:/contact/personal";
	}

	@RequestMapping("shareContactTagRename")
	public String shareContactTagRename(@RequestParam("ori_tagname") String ori_tagname,
			@RequestParam("new_tagname") String new_tagname) throws Exception {

		ContactDTO dto = new ContactDTO();
		String id = (String) session.getAttribute("loginId");

		dto.setWriter(id);
		dto.setTag(ori_tagname);

		Service.shareContactTagRename(ori_tagname, new_tagname);
		List<ContactDTO> shareContactList = Service.shareContactSelectBy_TagRenameSelect(dto);

		for (int i = 0; i < shareContactList.size(); i++) {
			String[] toTagList = shareContactList.get(i).getTag().split(",");
			List<String> tagList = new ArrayList<>(Arrays.asList(toTagList));

			for (int k = 0; k < tagList.size(); k++) {
				if (tagList.get(k).equals(ori_tagname)) {
					tagList.set(k, new_tagname);
				}
			}

			String newTagList = String.join(",", tagList);

			dto.setSeq(shareContactList.get(i).getSeq());
			dto.setTag(newTagList);

			Service.shareContactTagReTagList(dto);
		}
		return "redirect:/contact/share";
	}

	@RequestMapping("personalContactTagDeleteRadioAll")
	public String personalContactTagDeleteRadioAll(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		Service.personalContactTagDeleteRadioAllContact(dto);
		Service.personalContactTagDeleteRadioAllTag(dto);
		return "redirect:/contact/personal";
	}

	@RequestMapping("shareContactTagDeleteRadioAll")
	public String shareContactTagDeleteRadioAll(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		Service.shareContactTagDeleteRadioAllContact(dto);
		Service.shareContactTagDeleteRadioAllTag(dto);
		return "redirect:/contact/share";
	}

	@RequestMapping("personalContactTagDeleteRadioTag")
	public String personalContactTagDeleteRadioTag(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		String keyword = dto.getTag();

		Service.personalContactTagDeleteRadioAllTag(dto);

		List<ContactDTO> personalContactList = Service.personalContactSelectBy_TagRenameSelect(dto);

		for (int i = 0; i < personalContactList.size(); i++) {
			String[] toTagList = personalContactList.get(i).getTag().split(",");
			List<String> tagList = new ArrayList<>(Arrays.asList(toTagList));

			for (int k = 0; k < tagList.size(); k++) {
				if (tagList.get(k).equals(keyword)) {
					tagList.remove(k);
				}
			}

			String newTagList = String.join(",", tagList);
			dto.setSeq(personalContactList.get(i).getSeq());
			dto.setTag(newTagList);

			Service.personalContactTagDeleteRadioTagContact(dto);
		}
		return "redirect:/contact/personal";
	}

	@RequestMapping("shareContactTagDeleteRadioTag")
	public String shareContactTagDeleteRadioTag(ContactDTO dto) throws Exception {
		String id = (String) session.getAttribute("loginId");
		dto.setWriter(id);
		String keyword = dto.getTag();

		Service.shareContactTagDeleteRadioAllTag(dto);

		List<ContactDTO> shareContactList = Service.shareContactSelectBy_TagRenameSelect(dto);

		for (int i = 0; i < shareContactList.size(); i++) {
			String[] toTagList = shareContactList.get(i).getTag().split(",");
			List<String> tagList = new ArrayList<>(Arrays.asList(toTagList));

			for (int k = 0; k < tagList.size(); k++) {
				if (tagList.get(k).equals(keyword)) {
					tagList.remove(k);
				}
			}

			String newTagList = String.join(",", tagList);
			dto.setSeq(shareContactList.get(i).getSeq());
			dto.setTag(newTagList);

			Service.shareContactTagDeleteRadioTagContact(dto);
		}
		return "redirect:/contact/share";
	}

}
