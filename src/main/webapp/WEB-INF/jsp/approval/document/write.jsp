<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>결재 작성하기</title>
<!-- BootStrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/approval/document/write.css">
<link rel="stylesheet" type="text/css" href="/css/commons/body_form/left_form/body_form_default.css">
<style>
.modal_title {
	width: 100%;
	padding-bottom: 14px;
	border-bottom: 1px solid #ebebeb;
	font-size: 16px;
	font-weight: bold;
	color: #333333;
}
</style>
</head>
<body>

	<div class="top_container"></div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="content_tab">
				<form action="/approval/document/insertApproval" method="post" enctype="multipart/form-data">
					<div id="submit_div">
						<button type="submit" id="submit_btn" class="button_apply permit">기안하기</button>
					</div>
					<h4>기본 설정</h4>
					<table id="setting" class="outTable" border='1'>
						<tbody>
							<tr>
								<th>문서 종류</th>
								<td>
									<select name="doc_type">
										<option>지출결의서</option>
										<option>업무연락</option>
									</select>
								</td>
								<th>작성자</th>
								<td>${userDTO.organization } ${userDTO.position } ${userDTO.name }</td>
							</tr>
						</tbody>
					</table>

					<h4>결재선</h4>
					<table id="approvalTable" class="outTable" border='1'>
						<tbody>
							<tr>
								<th>신청 <img id="plusIcon" class="modalButton_apply" src="/images/approval/plus-circle.svg">
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

					<h4>제목</h4>
					<div class="input-group">
						<input type="text" class="form-control" id="title" name="title">
					</div>

					<h4>본문</h4>
					<div class="input-group">
						<textarea name="contents" id="contents"></textarea>
					</div>

					<h4>파일 첨부</h4>
					<div class="input-group">
						<input type="file" name="files" multiple>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="modalContainer_apply" class="modalContainer hidden">
		<div class="modalContent" style="padding: 25px;">
			<div class="modal_title">신청 설정</div>
			<div class="managerList">
				<ul>
					<c:forEach var="list" items="${managerList }">
						<li><span>${list.organization }</span>
							<span>${list.name }</span>
							<span hidden>${list.id }</span>
							<input type='checkbox' class="id_checkBox" value='${list.id}'>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="modalButton_div" align="center">
				<button type="button" id="cancle_btn" class="modalButton_apply button_cancel">취소</button>
				<button type="button" id="apply_btn" class="button_apply">확인</button>
			</div>
		</div>
	</div>
	<script src="/js/approval/document/write.js"></script>
	<script>
		$(document).ready(function() {
			$(".top_container").load("/commons/topForm");
		});
	
		$(document).ready(function() {
			$(".left_item").load("/approval/document/left_item");
		});
	</script>
</body>
</html>