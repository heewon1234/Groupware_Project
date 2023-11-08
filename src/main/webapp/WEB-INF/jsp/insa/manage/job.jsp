<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<link rel="stylesheet" type="text/css" href="/css/insa/manage/job.css">
<link rel="stylesheet" type="text/css" href="/css/insa/manage/common.css">
<link rel="stylesheet" type="text/css" href="/css/commons/body_form/left_form/body_form_default.css">
</head>

<body>
	<div class="top">TOP</div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="content_tab">
				<div class="title">
					<h3>직위/직무 관리</h3>
					<hr>
				</div>
				<div class="contents">
					<h4>직위 관리</h4>
					<form action="/job/insertJobTitle">
						<table>
							<tbody>
								<c:forEach var="i" begin="1" end="11">
									<tr>
										<th id="${i}">${i}등급</th>
										<td class="jt_div">
											<c:forEach items="${jobTitleList}" var="job">
												<c:if test="${job.job_rank eq i}">
													<span class="parent_span"> 
														<span><c:out value="${job.position}" /></span> 
														<a class="remove_jt_btn btn hidden">X</a>
													</span>
												</c:if>
											</c:forEach>
										</td>
										<td align="right">
											<span class="add_jt_btn btn">+</span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<button type="submit" class="button_apply">저장</button>
					</form>
	
					<div>
						<span class="h4_span">직무 관리</span>
						<a href="#" id="add_jr_btn">+직무 추가</a>
					</div>
					
					<hr>
					
					<form action="/job/insertJobRole">
						<div id="jr_contents">
							<c:forEach var="job" items="${jobRoleList}">
								<span class="parent_span"> 
									<span>${job.job_name}</span> 
									<a class="remove_jr_btn btn hidden">X</a>
								</span>
							</c:forEach>
						</div>
						<button type="submit" class="button_apply">저장</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/insa/manage/job.js"></script>
</body>
</html>