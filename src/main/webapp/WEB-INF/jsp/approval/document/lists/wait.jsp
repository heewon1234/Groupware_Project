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
<title>Insert title here</title>
<!-- BootStrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/commons/body_form/left_form/body_form_default.css">
</head>
<body>
	<div class="top">TOP</div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="content_tab">
				<table id="myTable" class="compact">
					<thead>
						<tr>
							<th>문서번호</th>
							<th>기안자</th>
							<th>제목</th>
							<th>기안일</th>
							<th>문서 종류</th>
							<th>상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${appList }">
							<tr>
								<td>${list.seq}</td>
								<td>${list.id}</td>
								<td>${list.title}</td>
								<td>${list.draft_date}</td>
								<td>${list.doc_type }</td>
								<td>${list.approval_status }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script src="/js/approval/document/table.js"></script>
	<script>
		$(document).ready(function() {
			$(".left_item").load("/approval/document/left_item?selectItem=wait");
		});
	</script>
</body>
</html>