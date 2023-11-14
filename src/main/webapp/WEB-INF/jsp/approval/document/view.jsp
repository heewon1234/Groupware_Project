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

	<div id="top_container"></div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="content_tab">
				<form action="/approval/document/updateStatus" method="post">
					<div id="submit_div">
						<input type='text' hidden name="doc_id" value="${docId }">
						<input type='text' hidden name="userId" value="${userDTO.id }">
						<button type="submit" class="button_apply">기안하기</button>
					</div>

					<h4>결재선</h4>
					<table id="approvalTable" class="outTable" border='1'>
						<tbody>
							<tr>
								<th>신청</th>
								<td>
									<table class="innerTable" border='1'>
										<tr>
											<c:forEach var="manager" items="${managerList}">
                            					<th>
                            						${manager.name}
                            						<c:if test="${manager.id eq userDTO.id}">
													    <select name="status">
													    	<option>미결</option>
													        <option>결재</option>
													        <option>반려</option>
													    </select>
													</c:if>
                            					</th>
                       						</c:forEach>
										</tr>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<c:forEach var="manager" items="${managerList}">
                            					<td>${manager.organization}</td>
                       						</c:forEach>
										</tr>
									</table>
								</td>
							</tr>
						</tbody>
					</table>

					<h4>제목</h4>
					<div class="input-group">
						<input type="text" class="form-control" value="${app.title }" readonly>
					</div>

					<h4>본문</h4>
					<div class="input-group">
						<textarea readonly>
							${app.contents }
						</textarea>
					</div>

					<h4>파일 첨부</h4>
					<div>
						<c:forEach var="file" items="${filesList }">
							<a href="/approval/document/download?oriName=${file.ori_name}&sysName=${file.sys_name}">
								${file.ori_name} 
							</a><br>
						</c:forEach>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="/js/approval/document/write.js"></script>
	<script>
		$(document).ready(function() {
			$("#top_container").load("/commons/topForm");
		});
	</script>
</body>
</html>