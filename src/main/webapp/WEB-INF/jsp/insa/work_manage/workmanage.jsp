<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/insa/work_manage/workmanage.css">
<link rel="stylesheet" type="text/css" href="/css/insa/work_manage/common.css">
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
				<div class="div">
					<span style="float: right;"><button id="openModalBtn" class="button_apply">휴가 미생성자</button></span>
				</div>
				<c:choose>
					<c:when test="${not empty list}">
						<div id="myModal" class="modal right_item">
							<div class="modal_title">휴가 미생성자</div>
							<hr>
							<!-- action 및 method 추가 -->
							<form action="/works/joindayupdate">
								<div class="span_title">입사일을 입력하고 생성을 클릭하면, 해당 사용자의 올해 휴가가 생성됩니다.</div>
								<div class="span_title">
								<table border="1" style="width: 100%;">
									<thead>
										<tr>
											<th style="text-align: center;">이름(ID)</th>
											<th style="text-align: center;">입사일</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach var="i" items="${list}">
											<tr>
												<td style="text-align: center;">${i.name}(${i.id})</td>
												<td style="text-align: center;"><input type="text" name="idList" class="idListbox" hidden> <input type="text" name="joindayList" class="joindayListbox" hidden> <input type="hidden" class="id" value="${i.id}">
													<div class="input-group mb-0">
														<input type="text" name="name" class="form-control joinday" placeholder="YYYY-MM-DD" id="modal_body_content_input_right_name" style="font-size: 14px; height: 34px; width: 338px; padding: 0 10px;" maxlength="50">
													</div></td>
											</tr>
										</c:forEach>



									</tbody>
								</table>
								<div style="text-align: center;">
									<button id="leaveCreationForm" type="submit" class="button_apply">생성</button>
									<button id="closeModalBtn" class="button_cancel">취소</button>
								</div>
								</div>
							</form>
						</div>
						<div id="overlay" class="overlay"></div>
					</c:when>
					<c:otherwise>
						<div id="myModal" class="modal right_item">
							<div class="modal_title">휴가 미생성자</div>
							<hr>
							<!-- action 및 method 추가 -->
							<form action="/works/joindayupdate">
								<div class="span_title">입사일을 입력하고 생성을 클릭하면, 해당 사용자의 올해 휴가가 생성됩니다.</div>
								<div class="span_title">
								<table border="1" style="width: 100%;">
									<thead>
										<tr>
											<th style="text-align: center;">이름(ID)</th>
											<th style="text-align: center;">입사일</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="2">
												<div style="text-align: center;">휴가 미생성자가 없습니다</div>
											</td>
										</tr>
									</tbody>
								</table>
								<div style="text-align: center;">
									<button id="leaveCreationForm" type="submit" class="button_apply permit">생성</button>
									<button id="closeModalBtn" class="button_cancel">취소</button>
								</div>
								</div>
							</form>
						</div>
						<div id="overlay" class="overlay"></div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
<script src="/js/insa/work_manage/workmanage.js"></script>
</html>