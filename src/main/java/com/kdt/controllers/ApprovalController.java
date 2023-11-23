package com.kdt.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.kdt.dto.ApplyLeaveDTO;
import com.kdt.dto.ApprovalDTO;
import com.kdt.dto.ApprovalFilesDTO;
import com.kdt.dto.ApprovalResponsiblesDTO;
import com.kdt.dto.MembersDTO;
import com.kdt.dto.WorkPlanDTO;
import com.kdt.dto.WorksDTO;
import com.kdt.services.ApprovalFilesService;
import com.kdt.services.ApprovalResponsiblesService;
import com.kdt.services.ApprovalService;
import com.kdt.services.JobTitleService;
import com.kdt.services.MembersService;
import com.kdt.services.OrganizationService;
import com.kdt.services.WorkPlanService;
import com.kdt.services.WorksService;

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
	@Autowired
	private WorkPlanService wpService;
	@Autowired
	private WorksService wService;
	@Autowired
	private Gson gson;


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
	public String view(int docId, Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		ApprovalDTO app = appService.selectByDocId(docId);
		List<ApprovalFilesDTO> filesList = appFService.selectByParentSeq(docId);

		List<ApprovalResponsiblesDTO> managerRBList = appRService.getManagerRBList(docId);
		List<String> managerIdList = new ArrayList<>();
		
		for(ApprovalResponsiblesDTO dto : managerRBList) {
			managerIdList.add(dto.getApprover_id());
		}

		List<MembersDTO> managerList = mService.getManagerListById(managerIdList);
		
		System.out.println("managerList:"+managerList);

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
		String ID = appService.getID(doc_id);
		int result = appRService.capplyleave(doc_id);
		if(result==0) {
			String work_types = appService.work_type(doc_id);
			int count = appService.count(doc_id);
			WorksDTO dto = new WorksDTO(ID,count);
			if(work_types != null) {
				if(work_types.equals("연차")) {
					wService.updateyleave(dto);
					wService.updateleave(dto);
				}
				else if(work_types.equals("경조사")) {
					wService.updatefleave(dto);
					wService.updateleave(dto);
				}
				else {
					return "redirect:/approval/document/box/every";
				}
				return "redirect:/approval/document/box/every";
			}
		}
		return "redirect:/approval/document/box/every";
	}
	
	@RequestMapping("/delete")
	public String delete(int doc_id) throws Exception {
		appService.delete(doc_id);
		
		
		return "redirect:/approval/document/lists/all";
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

	@RequestMapping("/box/approval")
	public String boxApproval(Model model) throws Exception {
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		List<Integer> docIdList = appRService.getApprovalDocIdByUserId(userDTO.getId());
		List<ApprovalDTO> appList = appService.selectListByDocId(docIdList);

		model.addAttribute("appList", appList);

		return "/approval/document/box/approval";
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
		ApprovalDTO appdto = new ApprovalDTO(0, userDTO.getId(), title, contents, null, "대기", doc_type);
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
		int approvalCount = appRService.getApprovalCount(userDTO.getId());
		int returnCount = appRService.getReturnCount(userDTO.getId());

		model.addAttribute("selectItem", selectItem);
		model.addAttribute("allCount", allCount);
		model.addAttribute("waitCount", waitCount);
		model.addAttribute("completeCount", completeCount);
		model.addAttribute("processCount", processCount);
		model.addAttribute("everyCount", everyCount);
		model.addAttribute("pendingCount", pendingCount);
		model.addAttribute("approvalCount", approvalCount);
		model.addAttribute("returnCount", returnCount);

		return "/approval/document/left_item";
	}
	@ResponseBody
	@RequestMapping(value="/works/workLeave_write")
	public String work_leave_write(String title,String work_type,String work_day,String approvalname,String contents)throws Exception {
		String ID = (String) session.getAttribute("loginId");
		MembersDTO userDTO = (MembersDTO) session.getAttribute("userDTO");
		String name = userDTO.getName();
		contents =" 신청인 : " + name + contents  ;
		System.out.println("controller" + contents);
		System.out.println(userDTO.getName());
		ApprovalDTO adto = new ApprovalDTO(0, ID, title,contents, null, null, "휴가신청");
		int seq = appService.leaveinsert(adto);
		ApprovalResponsiblesDTO ardto = new ApprovalResponsiblesDTO(0,seq,approvalname,null);
		ApplyLeaveDTO aldto = new ApplyLeaveDTO(0,ID,name,work_type,work_day,seq,null);
		
		appService.aleaveinsert(aldto);
		appRService.arleaveinsert(ardto);
		return "/works/work_leave";
	}
	
	@RequestMapping("/works/workPlan_write")
	@ResponseBody
	public String work_plan_write(@RequestBody Map<String, Object> requestData) {
	    Gson gson = new Gson();
	    
	    String approvalJson = gson.toJson(requestData.get("approval")); 
	    ApprovalDTO approvalDTO = gson.fromJson(approvalJson, ApprovalDTO.class);
	    
	    String workPlanJson = gson.toJson(requestData.get("workPlan")); 
	    WorkPlanDTO workPlanDTO = gson.fromJson(workPlanJson, WorkPlanDTO.class); 
	    String workDays = workPlanDTO.getWork_days();
	    String workTypes = workPlanDTO.getWork_types();
	    String userNames = workPlanDTO.getUser_names();

	    workDays = workDays.substring(1, workDays.length() - 1); // Remove square brackets
	    workTypes = workTypes.substring(1, workTypes.length() - 1);
	    userNames = userNames.substring(1, userNames.length() - 1);

	    String[] workDaysArray = workDays.split(",");
	    String[] workTypesArray = workTypes.split(",");
	    String[] userNamesArray = userNames.split(",");
	    System.out.println(workDaysArray);
	    System.out.println(workTypesArray);
	    System.out.println(userNamesArray);

	    int length = workDaysArray.length;
	    System.out.println(length);

	    List<WorkPlanDTO> newDTOList = new ArrayList<>();
	    for (int i = 0; i < length; i++) {
	        String workDay = workDaysArray[i].replaceAll("[\"']", "");
	        String workType = workTypesArray[i].replaceAll("[\"']", "");
	        String userName = userNamesArray[i].replaceAll("[\"']", "");

	        WorkPlanDTO newDTO = new WorkPlanDTO(0, null, userName, workType, workDay, 0, null);
	        newDTOList.add(newDTO);
	        System.out.println("가변 dto : "+newDTO);
	    }

	    for (WorkPlanDTO dto : newDTOList) {
	        dto.setId(workPlanDTO.getId());
	    }
	    System.out.println("새로운리스트 : "+newDTOList);

	    
	    
	    List<String> managerIDList = (List<String>) requestData.get("managerID");
	    String[] managerID = managerIDList.toArray(new String[0]);

	    System.out.println(approvalDTO);
	    System.out.println(requestData.get("workPlan"));
	    
	    appService.insertWorkPlan(approvalDTO, newDTOList, managerID);

	    return "/works_plan/workPlan";
	}

}
