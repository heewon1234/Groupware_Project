<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<!-- DataTables -->
<link href="https://cdn.datatables.net/v/dt/dt-1.13.6/datatables.min.css" rel="stylesheet">
<script src="https://cdn.datatables.net/v/dt/dt-1.13.6/datatables.min.js"></script>
<title>임직원 관리 게시판</title>
<!-- BootStrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/insa/manage/members.css">
<link rel="stylesheet" type="text/css" href="/css/insa/manage/common.css">
</head>
<body>
	<div class="top">TOP</div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="content_tab">
				<div class="title">
					<h3>임직원 관리</h3>
					<hr class="title_hr">
				</div>
				<c:if test="${rank <= 5}">
    				<div id="add_div">
						<a id="add_btn" class="btns">사용자 추가</a>
					</div>
				</c:if>
				<form id="signupForm" action="/members/signup" method="post">
				<div id="createUser" style="display:none;">
					<table id="createUserTable" class="compact">
					<thead>
						<tr>
							<th>이름</th>
							<th>ID</th>
							<th>비밀번호</th>
							<th>근로형태</th>
							<th>소속조직</th>
							<th>직위</th>
							<th>직무</th>
							<th>상태</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" name="name" id="name" placeholder="이름 입력"></td>
							<td><input type="text" name="id" id="id" placeholder="아이디 입력"></td>
							<td><input type="password" name="pw" id="pw" placeholder="비밀번호 입력"></td>
							<td>
								<select class="signupSelect" name="workForm">
									<option disabled selected>근로 형태</option>
									<c:forEach var="workForm" items="${workFormList }">
										<option>${workForm }</option>
									</c:forEach>
								</select>
    						</td>
							<td>
								<select class="signupSelect" name="org">
									<option disabled selected>소속 조직</option>
									<c:forEach var="list" items="${orgList }">
										<option>${list.organization }</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<select class="signupSelect" name="position">
									<option disabled selected>직위</option>
									<c:forEach var="list" items="${jobTitleList }">
										<option>${list.position }</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<select class="signupSelect" name="jobName">
									<option disabled selected>직무</option>
									<c:forEach var="list" items="${jobRoleList }">
										<option>${list.job_name }</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<button type="submit">저장</button>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
				</form>
				
				<div id="update_div">
					<span> 
						<input type="checkbox" id="all_check"> 
						<span id="checked_count">0</span>
					</span>
					<a class="modalButton modalButton_workForm btns invisbale">근로형태 수정</a> 
					<a class="modalButton modalButton_org btns invisbale">소속조직 수정</a> 
					<a class="modalButton modalButton_position btns invisbale">직위 수정</a> 
					<a class="modalButton modalButton_delete btns invisbale">삭제</a>
				</div>
						
				<table id="myTable" class="compact">
					<thead>
						<tr>
							<th>이름</th>
							<th>ID</th>
							<th>근로형태</th>
							<th>휴대전화</th>
							<th>소속조직</th>
							<th>직위</th>
							<th>직무</th>
	
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${membersList }">
							<tr>
								<td><input type="checkbox" class="chk_box" value="${list.id }"> ${list.name}</td>
								<td>${list.id}</td>
								<td>${list.work_form}</td>
								<td>${list.phone}</td>
								<td>${list.organization}</td>
								<td>${list.position}</td>
								<td>${list.job_name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<!-- 근로형태 설정 모달창 -->
	<div id="modalContainer_workForm" class="modalContainer hidden">
		<form action="/members/updateWorkForm">
			<div class="modalContent">
				<div align="center">근로형태 설정</div>
				<input type="text" name="idList" class="updateID" hidden> 
				<select name="workForm">
					<c:forEach var="workForm" items="${workFormList }">
						<option>${workForm }</option>
					</c:forEach>
				</select>
				<div class="modalButton_div" align="center">
					<button type="submit" class="button_apply">제출</button>
					<button type="button" class="modalButton_workForm button_cancel">닫기</button>
				</div>
			</div>
		</form>
	</div>

	<!-- 소속조직 설정 모달창 -->
	<div id="modalContainer_org" class="modalContainer hidden">
		<form action="/members/updateOrg">
			<div class="modalContent">
				<div align="center">소속조직 설정</div>
				<input type="text" name="idList" class="updateID" hidden> 
				<select name="org">
					<c:forEach var="list" items="${orgList }">
						<option>${list.organization }</option>
					</c:forEach>
				</select>
				<div class="modalButton_div" align="center">
					<button type="submit" class="button_apply">제출</button>
					<button type="button" class="modalButton_org button_cancel">닫기</button>
				</div>
			</div>
		</form>
	</div>
	
	<!-- 직위 설정 모달창 -->
	<div id="modalContainer_position" class="modalContainer hidden">
		<form action="/members/updatePosition">
			<div class="modalContent">
				<div align="center">직위 설정</div>
				<input type="text" name="idList" class="updateID" hidden> 
				<select name="position">
					<c:forEach var="list" items="${jobTitleList }">
						<option>${list.position }</option>
					</c:forEach>
				</select>
				<div class="modalButton_div" align="center">
					<button type="submit" class="button_apply">제출</button>
					<button type="button" class="modalButton_position button_cancel">닫기</button>
				</div>
			</div>
		</form>
	</div>

	<!-- 삭제 모달창 -->
	<div id="modalContainer_delete" class="modalContainer hidden">
		<form action="/members/deleteMember">
			<div class="modalContent">
				<div align="center">정말로 삭제하시겠습니까?</div>
				<input type="text" name="idList" class="updateID" hidden>
				<div class="modalButton_div" align="center">
					<button type="submit" class="button_apply">확인</button>
					<button type="button" class="modalButton_delete button_cancel">취소</button>
				</div>
			</div>
		</form>
	</div>
	<script src="/js/insa/manage/members.js"></script>
</body>
</html>