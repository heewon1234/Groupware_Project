<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script src="/js/insa/mywork/work_current.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
* {
	box-sizing: border-box;
}

#work_current_contents {
	width: 100%;
}

.month {
	min-width: 1200px;
	width: 98%;
}

.week-row {
	display: flex;
	width: 100%;
}

.day {
	width: 17%;
	height: 100px;
	text-align: left;
	border: 1px solid #eaeaea;
	padding-left: 10px;
}

.dayTitle {
	height: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
	padding-left: 0px;
}

.holiday {
	color: red;
}

.weekend .day-number {
	color: blue;
}
.prevMonth{
margin-right: 3px;
}
.nextMonth{
margin-left: 3px;
}
.currentMonth{
    position: relative;
    top: 1px;
}
</style>
</head>
<body>
	<div id="top_container"></div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="main_tab">
				<div id="work_current_contents">
					<div
						style="margin-bottom: 10px; display: flex; align-items: center;">
						<div style="margin-top: 3px;">
							<img class="prevMonth"
								src="/images/insa/work_plan/chevron-left.svg">
						</div>
						<div class="currentMonth"></div>
						<div style="margin-top: 3px;">
							<img class="nextMonth"
								src="/images/insa/work_plan/chevron-right.svg">
						</div>
					</div>
					<div class="month">
						<div class="week-days">
							<div class="week-row">
								<div class="day dayTitle">일요일</div>
								<div class="day dayTitle">월요일</div>
								<div class="day dayTitle">화요일</div>
								<div class="day dayTitle">수요일</div>
								<div class="day dayTitle">목요일</div>
								<div class="day dayTitle">금요일</div>
								<div class="day dayTitle">토요일</div>
							</div>
							<div class="cDate">
								<!-- 날짜 데이터가 들어갈 자리 -->
							</div>
						</div>
					</div>
					<c:forEach var="list" items="${WorkPlanList}" varStatus="loop">
						<input id="listLen" type="hidden" value="${fn:length(WorkPlanList)}  ">
						<input type="hidden" value="${list.user_names}">
						<input type="hidden" id="updateWorkContents_${loop.index}"
							value="${list.work_types}">
						<input type="hidden" id="updateWorkTime_${loop.index}" value="${list.work_days}">
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<script>
		$("#top_container").load("/commons/topForm");
		$(document)
				.ready(
						function() {
							let currentMonthDate = new Date();
							changeMonth(currentMonthDate);

							function changeMonth(currentDate) {
								let year = currentDate.getFullYear();
								let month = currentDate.getMonth() + 1; // 0부터 시작하므로 1을 더해줌
								let formattedMonth = year + "년 " + month + "월";

								$('.currentMonth').each(function() {
									$(this).text(formattedMonth);
								});

								let tableBody = $('.cDate');
								tableBody.empty();

								let firstDay = new Date(year, month - 1, 1);
								let lastDay = new Date(year, month, 0);
								let currentRow = $('<div class="week-row">');

								let startDayIndex = firstDay.getDay();

								for (let i = 0; i < startDayIndex; i++) {
									currentRow.append($('<div class="day">'));
								}

								for (let i = 1; i <= lastDay.getDate(); i++) {
									let dayCell = $('<div class="day">');
									let dayNumber = $(
											'<div class="day-number">').text(i);
									
									let workTime = $('<div class="work-time">');

									let currentDay = new Date(year, month - 1,
											i).getDay();
								    if (currentDay === 0) {
								    	dayNumber.addClass('holiday');
								    } else if (currentDay === 6) {
								        dayCell.addClass('weekend');
								    }
									if (currentDay === 0) {
										workTime.text('휴일');
									} else if (currentDay === 6) {
										workTime.text('휴무일');
									} else {
								        let listLen = $("#listLen").val();
								        console.log(listLen);
					                  	 workTime.text('정 9:00 ~ 18:00');
										for (let j = 0; j < listLen; j++) {
											console.log(j);
								            var updateDate = $("#updateWorkTime_" + j).val();
								            var numbersOnly = updateDate.replace(/\D/g, '');
								            var updateYear = numbersOnly.substring(0, 4);
								            var updateMonth = numbersOnly.substring(4, 6);
								            var updateDay = numbersOnly.substring(6, 8);
								            console.log(updateYear+":"+updateMonth+":"+updateDay);
								            if (year == updateYear && month == updateMonth && i == updateDay) {
								                let updateWorkContents = $("#updateWorkContents_" + j).val(); // updateWorkContents 변수 정의

								                if (updateWorkContents == "10시 출근") {
								                    let updateWorkTime2 = "정2 10:00 ~ 19:00";
								                    workTime.text(updateWorkTime2);
								                } else {
								                    let updateWorkTime = $("#updateWorkContents_" + j).val(); // updateWorkTime 변수 정의
								                    workTime.text(updateWorkTime);
								                }
								            }
								        }

						            }

									dayCell.append(dayNumber);
									dayCell.append(workTime);
									currentRow.append(dayCell);

									if (startDayIndex === 6) {
										tableBody.append(currentRow);
										currentRow = $('<div class="week-row">');
										startDayIndex = 0;
									} else {
										startDayIndex++;
									}
								}

								if (startDayIndex !== 0) {
									for (let i = startDayIndex; i < 7; i++) {
										currentRow
												.append($('<div class="day">'));
									}
									tableBody.append(currentRow);
								}
							}

							$('.prevMonth').click(
									function() {
										currentMonthDate
												.setMonth(currentMonthDate
														.getMonth() - 1);
										changeMonth(currentMonthDate);
									});

							$('.nextMonth').click(
									function() {
										currentMonthDate
												.setMonth(currentMonthDate
														.getMonth() + 1);
										changeMonth(currentMonthDate);
									});

						});
	</script>
</body>
</html>