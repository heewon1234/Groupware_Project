<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>결재 작성하기</title>
<!-- BootStrap -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/insa/mywork/leave_apply.css">
<link rel="stylesheet" type="text/css"
	href="/css/commons/body_form/left_form/body_form_default.css">
</head>
<body>
	<form action="/approval/document/insertApproval" method="post"
		enctype="multipart/form-data">
		<div id="top_container"></div>
		<div class="body_form">
			<div class="left_item"></div>
			<div class="right_item">
				<div class="content_tab">
					<div id="submit_div">
						<button type="submit" class="button_apply">기안하기</button>
					</div>
					<div class="upbox">
						<h4>기본 설정</h4>
						<table class="outTable" border='1'>
							<tbody>
								<tr>
									<th>문서 종류</th>
									<td>휴가 신청</td>
									<th>작성자</th>
									<td>${userDTO.organization }${userDTO.position }
										${userDTO.name }</td>
								</tr>
							</tbody>
						</table>

						<h4>결재선</h4>
						<table id="approvalTable" class="outTable" border='1'>
							<tbody>
								<tr>
									<th>신청 <span class="modalButton_apply btns">+</span>
									</th>
									<td>
										<table class="innerTable" border='1'>
											<tr>
												<th id="app_th1" class="app"></th>
												<th id="app_th2" class="app"></th>
												<th id="app_th3" class="app"></th>
												<th id="app_th4" class="app"></th>
											</tr>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
											<tr>
												<td id="app_td1" class="app"></td>
												<td id="app_td2" class="app"></td>
												<td id="app_td3" class="app"></td>
												<td id="app_td4" class="app"></td>
											</tr>
										</table>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<h4>휴가 현황</h4>
					<c:forEach var="i" items="${list }">
							<h4>잔여 휴가 ${i.leave_remainder}일</h4>
					</c:forEach>
					
					<div  
						style="margin-bottom: 10px; display: flex; align-items: center;">
						<div style="margin-top: 3px;">
							<img class="prevMonth"
								src="/images/insa/work_plan/chevron-left.svg">
						</div>
						<div class="currentMonth"></div>
						<div style="margin-top: 3px;">
							<img class="nextMonth"
								src="/images/insa/work_plan/chevron-right.svg">
						</div>
					</div>
					<div id="calendartd" style="margin-left: 24px">
						<table border="1" id="current_table" style="width: 90%;">
							<thead id="currentTable">
								<tr id="tr1">
									
								</tr>
								<tr id="tr2">
									
								</tr>
								<tr id="tr3">
								
								</tr>
							</thead>

							<tbody id="userWorkData">
							
							</tbody>
						</table>
					</div>

					<div id="leave_apply" style="display: none">
						<h4>휴가 신청</h4>
						<hr>
						<!-- 선택된 날짜들을 출력할 엘리먼트 -->
						<div id="selectedDates">
						</div>
						<!-- 연차, 경조사 선택을 위한 select 엘리먼트 -->
						<select id="dateSelect">
						</select>
						<div class="leave_select">
							<input id="loginID" type="hidden" value="${loginId }"> 
							<input type="hidden" id=doc_type name="doc_type" value="휴가신청">
							<input type="hidden" id="work_plan_title" name="title" value="휴가신청"> 
							<input type="hidden" id="work_plan_contents" name="contents"> 
							<input type="hidden" id="user_names" name="user_names"> 
							<input type="hidden" id="work_types" name="work_types"> 
							<input type="hidden" id="work_days" name="work_days">
						</div>
					</div>

					<h4>파일 첨부</h4>
					<div class="input-group">
						<input type="file" name="files" multiple>
					</div>
				</div>
			</div>
		</div>

		<div class="modal_tag_add ">
			<!-- 신청버튼 클릭 시 -->
			<div class="modal_title">
				<span>신청 설정</span>
			</div>
			<div class="modal_body">
				<ul>
					<c:forEach var="list" items="${managerList }">
						<li><span>${list.organization }</span> <span>${list.name }</span>
							<span hidden>${list.id }</span> <input type='checkbox'
							name='managerID' value='${list.id}'></li>
					</c:forEach>
				</ul>
			</div>
			<div class="modal_footer">
				<button type="button" class="button_cancel" id="button_cancel_tag">취소</button>
				<button type="button" class="button_apply" id="button_apply_tag"
					style="margin-left: 14px">확인</button>
			</div>
		</div>
		<div class="modal_background"></div>
	</form>
	<script src="/js/insa/mywork/leave_apply.js"></script>
</body>
</html>