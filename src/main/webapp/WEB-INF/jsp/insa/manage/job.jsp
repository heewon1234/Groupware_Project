<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<!-- BootStrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/insa/manage/job.css">
<link rel="stylesheet" type="text/css" href="/css/insa/manage/common.css">
</head>

<body>
	<div class="top_container"></div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="content_tab">
				<div class="title">
					<h3>직위/직무 관리</h3>
					<hr class="title_hr">
				</div>
				<div class="contents">
					<span class="h4_span">직위 관리</span>
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
														<span class="jobPosition_span"><c:out value="${job.position}" /></span> 
														<a class="remove_jt_btn btns hidden">
															<i class="fa-solid fa-xmark fa-sm"></i>
														</a>
													</span>
												</c:if>
											</c:forEach>
										</td>
										<td align="right">
											<i class="fa-solid fa-circle-plus" style="color: #bec3c9;"></i>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<button type="submit" id="apply_jobTitle" class="button_apply">저장</button>
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
									<span class="jobName_span">${job.job_name}</span> 
									<a class="remove_jr_btn btns hidden">
										<i class="fa-solid fa-xmark fa-sm"></i>
									</a>
								</span>
							</c:forEach>
						</div>
						<button type="submit" id="apply_jobRole" class="button_apply">저장</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="/js/insa/manage/job.js"></script>
	<script>
	
	</script>
</body>
</html>