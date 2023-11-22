<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/insa/work_manage/workstatistics.css">
<link rel="stylesheet" type="text/css" href="/css/insa/manage/common.css">
</head>
<body>
	<div id="top_container"></div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="main_tab">
				<div class="title">
					<h3>2023년</h3>
				</div>
				<table border="1" id="table">
					<thead>
						<tr>
							<th colspan="2">이름 / 소속</th>
							<th colspan="3">휴가</th>
							<th colspan="4">근태</th>
							<th colspan="3">근무시간</th>
						</tr>
						<tr>
							<th>이름(ID)</th>
							<th>소속</th>
							<th>생성</th>
							<th>사용</th>
							<th>잔여</th>
							<th>지각</th>
							<th>조기퇴근</th>
							<th>퇴근미체크</th>
							<th>결근</th>
							<th>출근일수</th>
							<th>총근무시간</th>
							<th>일 평균</th>
						</tr>
					</thead>
					<tbody id="workbox">

					</tbody>
				</table>
				<div class="div"></div>
			</div>
		</div>
	</div>

	<script src="/js/insa/work_manage/workstatistics.js"></script>
</body>
</html>