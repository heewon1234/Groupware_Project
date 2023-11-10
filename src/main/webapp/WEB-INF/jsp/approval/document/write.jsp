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
</head>
<body>
	<div class="top">TOP</div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="content_tab">
				<div id="submit_div">
					<a id="add_btn">기안하기</a>
				</div>
				<h4>기본 설정</h4>
				<table class="outTable" border='1'>
					<tbody>
						<tr>
							<th>문서 종류</th>
							<td>
								<select name="">
									<option>뭐시기</option>
									<option>저시기</option>
								</select> 
								<select name="">
									<option>지출 결의서</option>
									<option>업무연락</option>
								</select>
							</td>
							<th>작성자</th>
							<td> ${userDTO.organization } ${userDTO.position } ${userDTO.name }</td>
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
										<th></th>
										<th></th>
										<th></th>
										<th></th>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</table>
							</td>
							<th>처리 <span class="modalButton_process btns">+</span>
							</th>
							<td>
								<table class="innerTable" border='1'>
									<tr>
										<th>대표이사</th>
										<th>대표이사</th>
										<th>대표이사</th>
										<th>대표이사</th>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
				</table>

				<h4>제목</h4>
				<div class="input-group">
					<input type="text" class="form-control" id="" name="">
				</div>

				<h4>본문</h4>
				<div class="input-group">
					<textarea></textarea>
				</div>

				<h4>파일 첨부</h4>
				<div class="input-group">
					<input type='file' multiple>
				</div>
			</div>
		</div>
	</div>
	
	<div id="modalContainer_apply" class="modalContainer hidden">
		<form action="">
			<div class="modalContent">
				<div align="center">신청 설정</div>
				<ul>
					<c:forEach var="list" items="${approvalMembers }">
						<li>${list.name } <input type='checkbox'></li>
					</c:forEach>
				</ul>
				<div class="modalButton_div" align="center">
					<button type="submit" class="button_apply">확인</button>
					<button type="button" class="modalButton_apply button_cancel">취소</button>
				</div>
			</div>
		</form>
	</div>
	
	<div id="modalContainer_process" class="modalContainer hidden">
		<form action="">
			<div class="modalContent">
				<div align="center">처리 설정</div>
				<ul>
					<c:forEach var="list" items="${approvalMembers }">
						<li>${list.name } <input type='checkbox'></li>
					</c:forEach>
				</ul>
				<div class="modalButton_div" align="center">
					<button type="submit" class="button_apply">확인</button>
					<button type="button" class="modalButton_process button_cancel">취소</button>
				</div>
			</div>
		</form>
	</div>
	<script src="/js/approval/document/write.js"></script>
</body>
</html>