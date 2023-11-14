<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근무계획 수립</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<link rel="stylesheet" type="text/css"
	href="/css/commons/body_form/left_form/body_form_default.css">
<style>
* {
	
}

.weekend-cell {
	color: #a6a6a6;
}

.jeng {
	padding: 7px;
	padding-left: 7px;
	padding-right: 7px;
}

.jeng-info {
	display: none;
	background-color: #f2f2f2;
	padding: 10px;
	border: 1px solid #d4d4d4;
	border-radius: 5px;
	top: -40px; /* 바로 위에 표시되도록 조절 */
}

.works_days {
	padding: 5px;
}

#group_UserList {
	overflow-x: auto;
	white-space: nowrap;
}

#group_UserList div, #gridContainer .gridItem:nth-child(4) div {
	display: inline-block;
	margin-right: 10px;
}

#gridContainer {
	display: grid;
	grid-template-columns: 1fr 2fr;
}

.gridItem {
	padding: 20px;
	border: 1px solid #ccc;
	text-align: center;
}

.gridItem:nth-child(odd) {
	/* 1, 3번째 div에 추가 스타일 적용 */
	grid-column: span 1; /* 한 열만 차지하도록 설정 */
}
</style>
</head>
<body>
	<div id="top_container" style="width: 100%;"></div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div id="work_contents" style="margin-left: 25px;">
				<!-- 현재 월을 나타내는 부분 -->
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
				<div>
					<button type="button" onclick="workPlanUpdate()">근무계획변경</button>
				</div>
				<table border="1">
					<thead>
						<tr id="tr1">
							<!-- 데이터 추가하는 부분 -->
							<th rowspan="2" style="padding: 5px;">이름</th>
							<th rowspan="2" style="padding: 5px;">소속</th>
							<!-- 두 번째 줄의 데이터 추가 -->
							<!-- 날짜 데이터 -->
							<!-- <th id="monthDataRow"></th> -->
						</tr>
						<tr id="tr2">
							<!-- 요일 데이터 -->
							<!-- <th id="monthDaysRow"></th> -->
						</tr>
					</thead>

					<tbody id="userWorkData">
					</tbody>
				</table>
				<div id="allUserCount" style="margin-top: 10px;"></div>
			</div>
			<!-- 수정 div -->
			<div id="work_contents_update" style="display: none">
				<div style="font-weight: bold;">근무계획 변경</div>
				<div>
					<div style="font-weight: bold;">적용기간</div>
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
				</div>
				<div>
					<div style="font-weight: bold;">적용대상</div>
					<div>
						<select id="department_Select" style="margin-top: 10px">
						</select>
						<div id="group_UserList" style="padding: 0px;">
							<!-- User list will be dynamically added here -->
						</div>
					</div>
				</div>
				<div>
					<button id="selectFinished" onclick="selectFinished()">선택완료</button>
					<button id="reset" onclick="reset()" style="display: none">재설정</button>
				</div>
				<!-- 추가 설정 -->
				<div id="set-up" style="display: none">
					<div id="gridContainer">
						<div class="gridItem">적용 기간</div>
						<div class="gridItem"></div>
						<div class="gridItem">적용 대상</div>
						<div class="gridItem"></div>
					</div>

					<div>
						<div>근무계획</div>
						<table id="update_workPlan">
							<thead>
								<tr id="update_workPlan_head">
								</tr>
							</thead>
							<tbody id="update_workPlan_body">

							</tbody>

						</table>
					</div>
					<div>
						<button>기안하기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>

	
	function updateWorkPlanTable(userNames) {
	    var tableHeader = $("#update_workPlan_head").empty();
	    var tableCol = $('<th>');
	    tableHeader.append(tableCol);

	    userNames.forEach(name => {
	        const tableHeaderCell = $('<th>').text(name);
	        tableHeader.append(tableHeaderCell);
	    });

	    var tableBody = $("#update_workPlan_body").empty();

	    dates.forEach(date => {
	        var tableRow = $('<tr>');
	        const cellDate = $('<td>').text(formatDate2(date));
	        tableRow.append(cellDate);

	        userNames.forEach(userName => {
	            const cellDate1 = $('<td>');
	            const select = $('<select>');
	            select.append('<option value="10시출근">10시출근</option>');
	            select.append('<option value="9시출근" selected>9시 출근</option>');
	            select.append('<option value="휴무일">휴무일</option>');
	            select.append('<option value="휴일">휴일</option>');
	            select.append('<option value="변경 안 함">변경 안 함</option>');
	            cellDate1.append(select); // Append the select element to the cell
	            tableRow.append(cellDate1);
	        });

	        tableBody.append(tableRow);
	    });
	}


	const today = new Date();
	const currentYear = today.getFullYear();
	const currentMonth = today.getMonth() + 1;

	const dates = generateDatesForMonth(currentYear, currentMonth);

	function generateDatesForMonth(year, month) {
	    const firstDayOfMonth = new Date(year, month - 1, 1);
	    const lastDayOfMonth = new Date(year, month, 0);

	    const dates = [];

	    for (let day = firstDayOfMonth; day <= lastDayOfMonth; day.setDate(day.getDate() + 1)) {
	        dates.push(new Date(day));
	    }

	    return dates;
	}

	function formatDate2(date) {
        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const day = date.getDate();
        return month + "월 "+ day + "일";
    }

	 function workPlanUpdate() {
	        $('#work_contents').css("display", "none");
	        $('#work_contents_update').css("display", "block");
	    }
	    function selectFinished() {
	        $('#set-up').css("display", "block");
	        $('#reset').css("display", "block");
	        $('#selectFinished').css("display", "none");
	    }
	    function reset() {
	        $('#set-up').css("display", "none");
	        $('#selectFinished').css("display", "block");
	        $('#reset').css("display", "none");
	    }
	    
	    function formatDate1(date) {
	        const year = date.getFullYear();
	        const month = date.getMonth() + 1;
	        const day = date.getDate();
	        return year + "년 " + month + "월 "+ day + "일";
	    }

	    function updateCurrentMonth(year, month) {
	        var gridItem2 = $("#gridContainer .gridItem:nth-child(2)");

	        const firstDayOfMonth = new Date(year, month - 1, 1);
	        const lastDayOfMonth = new Date(year, month, 0);

	        const formattedFirstDay = formatDate1(firstDayOfMonth);
	        const formattedLastDay = formatDate1(lastDayOfMonth);
	        
	        const newText = formattedFirstDay + " ~ " + formattedLastDay;
	        gridItem2.text(newText);
	    }
	    


	    $(document).ready(function () {
	        // 현재 날짜 정보
	        const today = new Date();
	        let currentMonth = today.getMonth() + 1; // 1부터 시작하는 월

	        // 초기화 함수 호출
	        updateMonth();
	        updateData(); // 페이지 로딩 시 데이터 초기화
	        updateCurrentMonth();
	        updateCurrentMonth(today.getFullYear(),currentMonth);

	        // 왼쪽 버튼 클릭 시 이벤트 처리
	        $(".prevMonth").click(function () {
	            currentMonth--;
	            updateMonth();
	            updateData(); // 월 변경 시 데이터 갱신
	            updateCurrentMonth(today.getFullYear(), currentMonth);
	        });

	        // 오른쪽 버튼 클릭 시 이벤트 처리
	        $(".nextMonth").click(function () {
	            currentMonth++;
	            updateMonth();
	            updateData(); // 월 변경 시 데이터 갱신
	            updateCurrentMonth(today.getFullYear(), currentMonth);
	        });

	        // 월 갱신 함수
	        function updateMonth() {
	            // 날짜를 표시할 엘리먼트
	            const currentMonthElement = $(".currentMonth");

	            // 이전 달로 이동했을 때 처리
	            if (currentMonth < 1) {
	                currentMonth = 12;
	                today.setFullYear(today.getFullYear() - 1);
	            }
	            // 다음 달로 이동했을 때 처리
	            else if (currentMonth > 12) {
	                currentMonth = 1;
	                today.setFullYear(today.getFullYear() + 1);
	            }

	            // 현재 월 표시
	            currentMonthElement.text(today.getFullYear() + "년 " + currentMonth + "월");
	            
	        }
	       

	        // 데이터 갱신 함수
	        function updateData() {
	            // AJAX request to get user list
	            $.ajax({
	                url: '/works_plan/getUserList',
	                data: { month: currentMonth }, // 월 정보 전달
	            }).done(function (data) {
	                var tableBody = $("#userWorkData").empty();
	                var { dates, daysOfWeek } = generateMonthlyDates(); // dates 변수를 전역으로 이동

	                for (let i = 0; i < data.length; i++) {
	                    const member = data[i];
	                    var tableRow = $('<tr>').addClass('memberRow');
	                    var nameCell = $('<td>').text(member.name);
	                    var departmentCell = $('<td>').text(member.organization);
	                    tableRow.append(nameCell, departmentCell);

	                    // 주말을 제외하고 정을 추가하는 td 코드를 생성하여 추가
	                    dates.forEach(date => {
	                        const scheduleCell = createScheduleCell(date);
	                        tableRow.append(scheduleCell);
	                    });

	                    tableBody.append(tableRow);
	                }

	                // 업데이트할 행과 열 선택
	                var tableRowDate = $('#tr1');
	                var tableRowDays = $('#tr2');

	                // 기존 내용 제거
	                tableRowDate.empty();
	                tableRowDays.empty();

	                // 날짜 업데이트
	                dates.forEach(date => {
	                    const cellDate = $('<td>').text(formatDate(date)).addClass('works_days');
	                    tableRowDate.append(cellDate);
	                });

	                // 요일 업데이트
	                daysOfWeek.forEach(day => {
	                    const cellDay = $('<td>').text(day).addClass('works_days');
	                    tableRowDays.append(cellDay);
	                });
	                
	            });
	        }
	    });

$(document).ready(function() {
	$("#top_container").load("/commons/topForm");
	$(".left_item").load("/works/manage_left_item?selectItem=workPlan");
	
// 주말을 제외한 정을 추가하는 함수
function createScheduleCell(date) {
    if (dayIsWeekend(date)) {
        // 주말인 경우
        if (isSaturday(date)) {
            return $('<td>').text('휴무').addClass('weekend-cell');
        } else if (isSunday(date)) {
            return $('<td>').text('휴일').addClass('weekend-cell');
        } else {
            return $('<td>'); // 다른 주말인 경우 비어있는 td 반환
        }
    } else {
        return $('<td>').text('정').addClass('jeng');
    }
}

// 토요일인지 확인하는 함수
function isSaturday(date) {
    return date.getDay() === 6;
}

// 일요일인지 확인하는 함수
function isSunday(date) {
    return date.getDay() === 0;
}

// 주말인지 확인하는 함수 (토요일 또는 일요일인지 확인)
function dayIsWeekend(date) {
    return isSaturday(date) || isSunday(date);
}

// 이름 소속을 가져오는 ajax
$.ajax({
    url: '/works_plan/getUserList'
}).done(function (data) {
    var tableBody = $("#userWorkData").empty();
    var { dates, daysOfWeek, currentMonth } = generateMonthlyDates(); // dates 변수를 전역으로 이동
    for (let i = 0; i < data.length; i++) {
        const member = data[i];
        var tableRow = $('<tr>').addClass('memberRow');
        var nameCell = $('<td>').text(member.name);
        var departmentCell = $('<td>').text(member.organization);
        tableRow.append(nameCell, departmentCell);

        // 주말을 제외하고 정을 추가하는 td 코드를 생성하여 추가
        dates.forEach(date => {
            const scheduleCell = createScheduleCell(date);
            tableRow.append(scheduleCell);
        });

        tableBody.append(tableRow);
    }

    var tableRowDate = $('#tr1'); // 날짜가 들어갈 행
    dates.forEach(date => {
        const cellDate = $('<td>').text(formatDate(date)).addClass('works_days');
        tableRowDate.append(cellDate);
    });
    var tableRowDays = $('#tr2'); // 요일이 들어갈 행
    daysOfWeek.forEach(day => {
        const cellDay = $('<td>').text(day).addClass('works_days');
        tableRowDays.append(cellDay);
    });
    
});

// 날짜 데이터를 생성하는 함수
function generateMonthlyDates() {
    const today = new Date();
    const currentMonth = today.getMonth() + 1; // 0-based index
    const currentYear = today.getFullYear();

    const firstDayOfMonth = new Date(currentYear, currentMonth - 1, 1);
    const lastDayOfMonth = new Date(currentYear, currentMonth, 0);

    const dates = [];
    const daysOfWeek = [];

    for (let day = firstDayOfMonth; day <= lastDayOfMonth; day.setDate(day.getDate() + 1)) {
        dates.push(new Date(day));
        daysOfWeek.push(day.toLocaleDateString('ko-KR', { weekday: 'short' }));
    }
    return { dates, daysOfWeek, currentMonth };
}

// 날짜를 문자열로 변환하는 함수
function formatDate(date) {
    return date.getDate(); // 날짜의 일자만 반환
}
//총 인원을 가져오는 ajax
$.ajax({
    url: '/members/countUser'
}).done(function (data) {
    // Update the content of the div with the received data
    $("#allUserCount").text("총 인원 : " + data);
});
//마우스를 올린 경우
$(document).on('mouseenter', '.jeng', function(e) {
    // "정"에 관한 정보를 표시
    console.log("정");
    $("#jeng-info").css({
        display: "block",
        top: e.pageY - 50, // 마우스 현재 위치보다 조금 위에 나타나도록 조절
        left: e.pageX
    });
});

// 마우스를 떼는 경우
$(document).on('mouseleave', '.jeng', function() {
    // 정보를 숨김
    $("#jeng-info").css("display", "none");
});
// 부서 목록을 가져오는 Ajax 요청
$.ajax({
    url:'/members/getDepartmentList'
 }).done(function(data){
	 $("#department_Select").empty();
        $("#department_Select").append('<option selected>부서 선택</option>');
        for (let i = 0; i < data.length; i++) {
            const departmentName = data[i];
            $("#department_Select").append('<option value="' + departmentName + '">' + departmentName + '</option>');
        }
 });
$("#department_Select").on("change", function() {
	var organization = $(this).val();
	if (organization !== "부서 선택") {
		loadMembersByDepartment(organization); // 초기에 oneSeq를 0로 전달
	}
});

function loadMembersByDepartment(organization) {
var userNames = [];
    $.ajax({
        url: '/members/selectAll',
        // 필요한 데이터 전달
        data: { organization: organization }, // 예시로, 실제 데이터에 따라 수정이 필요합니다.
        // 성공적으로 데이터를 받아왔을 때 수행할 동작
    }).done(function(resp) {
        // 기존 데이터를 비우고 새로운 데이터로 갱신
        $("#group_UserList").empty();
        $("#gridContainer .gridItem:nth-child(4)").empty();

        // 받아온 멤버들을 순회하며 userNames 배열에 추가
        for (let i = 0; i < resp.length; i++) {
            const user = resp[i];
            // 조건을 확인하여 해당 부서의 멤버들만 추가
            if (user.organization === organization) {
                var liElement = $('<div>').attr('id', user.name).text(user.name);
                $("#group_UserList").append(liElement.clone());
                $("#gridContainer .gridItem:nth-child(4)").append(liElement);
                userNames.push(user.name);
            }
        }

        // 데이터를 모두 추가한 뒤 updateWorkPlanTable 함수 호출
        updateWorkPlanTable(userNames);
    });
}















});

</script>

</body>
</html>