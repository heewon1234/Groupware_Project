<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/insa/mywork/work_leave.css">
<link rel="stylesheet" type="text/css" href="/css/insa/manage/common.css">
</head>
<body>
	<div id="top_container"></div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="title">
				<h3>올해 근무 정보</h3>
			</div>
			<c:forEach var="i" items="${list}">
			<div class="upbox">
				<div class="work">
					<div class="spanbox">
						<span>근태현황</span>
					</div>

					<div class="workbox">

						<div class="worklate">
							<span>지각</span><br> <br>${i.work_late }회
						</div>
						<div class="workearly">
							<span>조기 퇴근</span> <br> <br>${i.work_early }회
						</div>
						<div class="notcheck">
							<span>퇴근 미체크</span><br> <br>${i.work_notcheck }회
						</div>
						<div class="worknone">
							<span>결근</span> <br> <br>${i.work_truancy}회
						</div>

					</div>
				</div>
				<div class="leave">
					<div class="spanbox">
						<span>휴가현황</span>
					</div>
					<div class="leavebox">
						<div class="allleave">
							<span>총 휴가</span><br> <br> ${i.leave_day}일
						</div>
						<div class="useleave">
							<span>사용 휴가</span><br> <br> ${i.leave_use}일
						</div>
						<div class="remainder">
							<span>잔여 휴가</span><br> <br> ${i.leave_remainder}일
						</div>
					</div>
				</div>
				<div class="worktime">
					<div class="spanbox">
						<span>근무시간</span>
					</div>
					<div class="worktimebox">
						<div class="workday">
							<span>근무 일수</span> <br> <br> ${i.all_workday}일
						</div>
						<div class="allworktile">
							<span>총 근무 시간</span> <br> <br> ${i.averagehours }:${i.averageminute } 시간
						</div>
					</div>
				</div>

			</div>
			</c:forEach>
			<div class="middlebox"></div>

			<div class="title">
				<h3>오늘 근무 현황</h3>
			</div>


			<div class="downbox">
				<div class="plan">
					<div class="spanbox">
						<span>근무계획</span>
					</div>
					<div class="workplanbox">
						<div class="calendar_icon">
							
							<div class="div_body">
								<div class="div_header"></div>
								<div class="div_footer"></div>
							</div>
							
						</div>
						<div class="work_plan_list">
							
						</div>
					</div>
				</div>
				<div class="check">
					<div class="spanbox">
						<span>근무체크</span>
					</div>
					<div class="workcheckbox">
						<div class="timebox">
							<div>
								<span id="clock"></span> <span id="statusText" class="highlight">출근전</span>
							</div>

						</div>
						<div class="workinoutbox">
							<button id="workin" onclick="workin()">
								출근하기
								<div class="iconbox">
									<img id="work-in" src="/images/insa/work_leave/log-in.svg" alt="...">
								</div> 
								<span id="currentinTime">00:00:00</span>
							</button>
							<button id="workout" onclick="workout()" disabled>
								퇴근하기
								<div class="iconbox">
									<img id="work-out" class="img-disabled" src="/images/insa/work_leave/log-out.svg" alt="...">
								</div>  
								<span id="currentoutTime">00:00:00</span>
							</button>
						</div>
						<div class="workbtnbox">
							<button disabled onclick="changeStatus('업무중', this)"
								class="workbtn">업무</button>
							<button disabled onclick="changeStatus('외출중', this)"
								class="workbtn">외출</button>
							<button disabled onclick="changeStatus('회의중', this)"
								class="workbtn">회의</button>
							<button disabled onclick="changeStatus('외근중', this)"
								class="workbtn">외근</button>
						</div>
					</div>
				</div>
				<div class="current">
					<div class="spanbox">
						<span>근무현황</span>
					</div>
					<div class="workcurrent">
						<div id="scheduleList">
								
						</div>
						<button class="worktime_update">근무시간 수정</button>
					</div>

				</div>
			</div>
		</div>
	</div>
	<script src="/js/insa/mywork/work_leave.js"></script>
</body>
</html>