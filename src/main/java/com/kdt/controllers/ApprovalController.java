package com.kdt.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
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
		
		List<ApprovalResponsiblesDTO> managerRBList = appRService.getManagerRBList(docId);
		List<String> managerIdList = new ArrayList<>();
		for(ApprovalResponsiblesDTO dto : managerRBList) {
			managerIdList.add(dto.getApprover_id());
			System.out.println(dto.getApprover_id());
			System.out.println(dto.getApproval_status());
		}
		
		List<MembersDTO> managerList = mService.getManagerList(managerIdList);
		
		model.addAttribute("docId", docId);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("app", app);
		model.addAttribute("filesList", filesList);
		model.addAttribute("managerRBList", managerRBList);
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
	
	@RequestMapping("/lists/complete")
	public String listsComplete(Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<ApprovalDTO> appList = appService.selectCompleteById(userDTO.getId());
		
		model.addAttribute("appList", appList);
		
		return "/approval/document/lists/complete";
	}
	
	@RequestMapping("/lists/progress")
	public String listsProgress(Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<ApprovalDTO> appList = appService.selectProgressById(userDTO.getId());
		
		model.addAttribute("appList", appList);
		
		return "/approval/document/lists/progress";
	}
	
	@RequestMapping("/box/every")
	public String boxEvery(Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<Integer> docIdList = appRService.getDocIdByUserId(userDTO.getId());
		List<ApprovalDTO> appList = appService.selectListByDocId(docIdList);
		
		model.addAttribute("appList", appList);
		
		return "/approval/document/box/every";
	}
	
	@RequestMapping("/box/pending")
	public String boxPending(Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<Integer> docIdList = appRService.getPendingDocIdByUserId(userDTO.getId());
		List<ApprovalDTO> appList = appService.selectListByDocId(docIdList);
		
		model.addAttribute("appList", appList);
		
		return "/approval/document/box/pending";
	}
	
	@RequestMapping("/box/approve")
	public String boxApprove(Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<Integer> docIdList = appRService.getApproveDocIdByUserId(userDTO.getId());
		List<ApprovalDTO> appList = appService.selectListByDocId(docIdList);
		
		model.addAttribute("appList", appList);
		
		return "/approval/document/box/approve";
	}
	
	@RequestMapping("/box/return")
	public String boxReturn(Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<Integer> docIdList = appRService.getReturnDocIdByUserId(userDTO.getId());
		List<ApprovalDTO> appList = appService.selectListByDocId(docIdList);
		
		model.addAttribute("appList", appList);
		
		return "/approval/document/box/return";
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
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		int allCount = appService.getAllCount(userDTO.getId());
		int waitCount = appService.getWaitCount(userDTO.getId());
		int completeCount = appService.getCompleteCount(userDTO.getId());
		int processCount = appService.getProcessCount(userDTO.getId());
		
		int everyCount = appRService.getEveryCount(userDTO.getId());
		int pendingCount = appRService.getPendingCount(userDTO.getId());
		int approveCount = appRService.getApproveCount(userDTO.getId());
		int returnCount = appRService.getReturnCount(userDTO.getId());
		
		model.addAttribute("selectItem", selectItem);
		model.addAttribute("allCount", allCount);
		model.addAttribute("waitCount", waitCount);
		model.addAttribute("completeCount", completeCount);
		model.addAttribute("processCount", processCount);
		model.addAttribute("everyCount", everyCount);
		model.addAttribute("pendingCount", pendingCount);
		model.addAttribute("approveCount", approveCount);
		model.addAttribute("returnCount", returnCount);
		
		return "/approval/document/left_item";
	}
	
	@RequestMapping("/works/workLeave_write")
	public String work_leave_write() {
		return "/approval/document/works/workLeave_write";
	}
	
	@RequestMapping("/works/workPlan_write")
	public String work_plan_write() {
		return "/approval/document/works/workPlan_write";
	}
}
