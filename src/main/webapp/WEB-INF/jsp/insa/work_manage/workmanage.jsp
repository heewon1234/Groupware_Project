<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/insa/work_manage/workmanage.css">
<link rel="stylesheet" type="text/css"
	href="/css/insa/manage/common.css">
<link rel="stylesheet" type="text/css"
	href="/css/commons/body_form/left_form/body_form_default.css">
</head>
<body>
	<div class="top">TOP</div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="title">
				<h3>2023-01-01~2023-12-31</h3>
			</div>
			<table border="1" style="width: 100%;">
				<thead>
					<tr>
						<th rowspan="2">이름</th>
						<th rowspan="2">입사일</th>
						<th rowspan="2">올해 생성</th>
						<th colspan="3">생성내역</th>
						<th colspan="4">사용현황</th>
						<th rowspan="2">잔여</th>
					</tr>
					<tr>
						<th>정기</th>
						<th>포상</th>
						<th>기타</th>
						<th>연차</th>
						<th>포상</th>
						<th>월차</th>
						<th>경조사</th>
					</tr>

				</thead>
				<tbody id="workbox">
				</tbody>
			</table>
			<div class="div"></div>
		</div>
	</div>
	<script src="/js/insa/work_manage/workmanage.js"></script>
</body>
</html>