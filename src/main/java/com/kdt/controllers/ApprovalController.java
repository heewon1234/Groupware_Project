package com.kdt.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dto.ApprovalDTO;
import com.kdt.dto.ApprovalFilesDTO;
import com.kdt.dto.ApprovalResponsiblesDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.services.ApprovalFilesService;
import com.kdt.services.ApprovalResponsiblesService;
import com.kdt.services.ApprovalService;
import com.kdt.services.JobTitleService;
import com.kdt.services.MembersService;
import com.kdt.services.OrganizationService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/approval/document")
@Controller
public class ApprovalController {
	@Autowired
	private HttpSession session;
	@Autowired
	private ApprovalService appService;
	@Autowired
	private ApprovalFilesService appFService;
	@Autowired
	private ApprovalResponsiblesService appRService;
	@Autowired
	private OrganizationService orgService;
	@Autowired
	private JobTitleService jobService;
	@Autowired
	private MembersService mService;
	

	@RequestMapping("/write")
	public String write(Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<String> managerOrgList = orgService.getManagerOrgList(userDTO.getOrganization());
		List<String> managerPositionList = jobService.getManagerPosition(userDTO.getPosition());
		List<MembersDTO> managerList = mService.getManagerList(managerOrgList, managerPositionList);
 		model.addAttribute("userDTO", userDTO);
		model.addAttribute("managerList", managerList);
		
		return "/approval/document/write";
	}
	
	@RequestMapping("/view")
	public String view(int docId ,Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		ApprovalDTO app = appService.selectByDocId(docId);
		List<ApprovalFilesDTO> filesList = appFService.selectByParentSeq(docId);
		List<String> managerIdList = appRService.getManagerIdList(docId);
		List<MembersDTO> managerList = mService.getManagerList(managerIdList);
		
		model.addAttribute("docId", docId);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("app", app);
		model.addAttribute("filesList", filesList);
		model.addAttribute("managerList", managerList);
		
		return "/approval/document/view";
	}
	
	@RequestMapping("/download")
	public void download(String oriName, String sysName, HttpServletResponse response) throws Exception {

		String uploadPath = "c:/uploads";
		File targetFile = new File(uploadPath+"/"+sysName);

		oriName = new String(oriName.getBytes("utf8"), "ISO-8859-1");
		response.setHeader("content-disposition", "attachement;filename="+oriName);

		try(DataInputStream dis = new DataInputStream(new FileInputStream(targetFile));
				DataOutputStream dos = new DataOutputStream(response.getOutputStream())) {
			byte[] fileContents = dis.readAllBytes();
			dos.write(fileContents);
			dos.flush();
		}
	}
	
	@RequestMapping("/updateStatus")
	public String updateStatus(String status, int doc_id, String userId) throws Exception {
		appRService.updateStatus(new ApprovalResponsiblesDTO(0, doc_id, userId, status));
		
		return "redirect:/approval/document/box/every";
	}
 	
	@RequestMapping("/lists/all")
	public String listsAll(Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<ApprovalDTO> appList = appService.selectById(userDTO.getId());
		
		model.addAttribute("appList", appList);
		
		return "/approval/document/lists/all";
	}
	
	@RequestMapping("/lists/wait")
	public String listsWait(Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<ApprovalDTO> appList = appService.selectWaitById(userDTO.getId());
		
		model.addAttribute("appList", appList);
		
		return "/approval/document/lists/wait";
	}
	
	@RequestMapping("/box/every")
	public String boxEvery(Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<Integer> docIdList = appRService.getDocIdByUserId(userDTO.getId());
		List<ApprovalDTO> appList = appService.selectEveryByDocId(docIdList);
		
		model.addAttribute("appList", appList);
		
		for(Integer docId : docIdList) {
			System.out.println("docId:"+docId);
		}
		
		return "/approval/document/box/every";
	}
	
	@RequestMapping("/insertApproval")
	public String insertApproval(String title, String contents, String doc_type, 
			MultipartFile[] files, @RequestParam(name = "managerID", required = false) String[] managerID) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		ApprovalDTO appdto = new ApprovalDTO(0, userDTO.getId(), title, contents, null, "Wait", doc_type);
		String uploadPath = "c:/uploads";
		
		appService.insert(appdto, files, uploadPath, managerID);
		
		return "redirect:/approval/document/lists/all";
	}
	
	@RequestMapping(value="/left_item")
	public String toLeft_item(String selectItem, Model model) {
		model.addAttribute("selectItem", selectItem);
		
		return "/approval/document/left_item";
	}
}
