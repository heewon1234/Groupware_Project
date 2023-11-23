<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근무계획 수립</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
* {
	
}

.
weekend-cell {
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
	text-align: center;
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
	grid-column: span 1;
}

.grid-container {
	display: grid;
	grid-template-columns: 1fr 3fr;
}

.grid-item {
	border: 1px solid #ccc;
	padding: 20px;
}

.grid-item:nth-child(1) {
	grid-column: 1;
}

.grid-item:nth-child(2) {
	grid-column: 2;
}

.modal_form {
	width: 100%;
	height: 100%;
	position: fixed;
	z-index: 3;
	top: 0;
	left: 0;
	display: none;
}

.modal_background {
	background: rgba(0, 0, 0, 0.5);
	position: fixed;
	z-index: 4;
	width: 100%;
	height: 100%;
}

.modal_contact_add {
	z-index: 4;
	background-color: white;
	border-radius: 4px;
	padding: 25px;
	width: 500px;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.modal_tag_add {
	z-index: 6;
	background-color: white;
	border-radius: 4px;
	padding: 25px;
	width: 400px;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	display: none;
}

.modal_title {
	width: 100%;
	padding-bottom: 14px;
	border-bottom: 1px solid #ebebeb;
	font-size: 16px;
	font-weight: bold;
	color: #333333;
}

.modal_body {
	width: 100%;
	padding: 20px 0 30px;
	display: flex;
	justify-content: center;
}

.modal_body_content {
	display: table;
	margin-bottom: 13px;
}

.modal_body_content_add {
	padding-bottom: 13px;
	border-bottom: 1px solid #ebebeb;
}

.modal_footer {
	display: flex;
	justify-content: center;
}

.modal_footer.right {
	display: flex;
	justify-content: flex-end;
}

/*.button_cancel.float_left {
	margin-right: auto;
}*/
.button_apply.float_right {
	margin-left: 14px;
}

[type="checkbox"] {
	appearance: none;
	position: relative;
	border: max(2px, 0.1em) solid #D9D9D9;
	background-color: #D9D9D9;
	border-radius: 1.25em;
	width: 2.25em;
	height: 1.25em;
	cursor: pointer;
}

[type="checkbox"]::before {
	content: "";
	position: absolute;
	left: 0;
	width: 1em;
	height: 1em;
	border-radius: 50%;
	transform: scale(0.8);
	background-color: white;
	transition: left 250ms linear;
}

[type="checkbox"]:checked {
	background-color: #2985DB;
	border-color: #2985DB;
}

[type="checkbox"]:checked::before {
	background-color: white;
	left: 1em;
}

[type="checkbox"]:disabled {
	border-color: lightgray;
	opacity: 0.7;
	cursor: not-allowed;
}

[type="checkbox"]:disabled:before {
	background-color: lightgray;
}

[type="checkbox"]:disabled+span {
	opacity: 0.7;
	cursor: not-allowed;
}

#plusIcon {
	filter: invert(45%) sepia(63%) saturate(1946%) hue-rotate(162deg)
		brightness(95%) contrast(101%);
	position: relative;
	bottom: 1px;
	width: 20px;
}

tr, td {
	border: 1px solid #d4d4d4;
}

#update_workPlan select {
	border: none;
}

#update_workPlan_head th {
	border: 1px solid #d4d4d4;
}

#update_workPlan th, #update_workPlan td {
	padding: 10px;
}

#update_workPlan_head, #update_workPlan_body .members, #currentTable {
	background-color: #EFF4FC;
}

.nextMonth {
	position: relative;
	bottom: 2px;
	left: 5px;
}

.prevMonth {
	position: relative;
	bottom: 2px;
	right: 5px;
}

button, select {
	cursor: pointer;
}
#current_table{
min-width: 1180px;
}
#work_contents{
width: 100%;
}
#current_table{
width: 98%;
}
.jeng,.weekend-cell{
text-align: center;
}
</style>
</head>
<body>
	<div id="top_container"></div>
	<div class="body_form">
		<div class="left_item"></div>
		<div class="right_item">
			<div class="main_tab">
				<div id="work_contents">
				<input id="userName" type="hidden" value="${name}">
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
						<button class="button_apply" type="button"
							onclick="workPlanUpdate()" style="margin-bottom: 10px;">근무계획변경</button>
					</div>
					<table border="1" id="current_table">
						<thead id="currentTable">
							<tr id="tr1">
								<!-- 데이터 추가하는 부분 -->
								<th class="left_none" rowspan="2" style="padding: 5px;">이름</th>
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
					<div style="font-weight: bold; margin-bottom: 10px;">근무계획 변경</div>
					<div>
						<div style="font-weight: bold; margin-bottom: 10px;">적용기간</div>
						<div
							style="margin-bottom: 10px; display: flex; align-items: center;">
							<div style="margin-top: 3px;">
								<img class="prevMonth"
									src="/images/insa/work_plan/chevron-left.svg">
							</div>
							<div class="currentMonth"></div>
							<input class="cMonth" type="hidden">
							<input class="cYear" type="hidden">
							<div style="margin-top: 3px;">
								<img class="nextMonth"
									src="/images/insa/work_plan/chevron-right.svg">
							</div>
						</div>
					</div>
					<div>
						<div style="font-weight: bold;">적용대상</div>
						<div>
							<select class="form-select form-select-sm"
								aria-label="Small select example" id="department_Select"
								style="margin-top: 10px; margin-bottom: 10px; padding: 2px;">
							</select>
							<div id="group_UserList"
								style="padding: 0px; margin-bottom: 10px;">
								<!-- User list will be dynamically added here -->
							</div>
						</div>
					</div>
					<div>
						<button class="button_apply" id="selectFinished"
							onclick="selectFinished()" disabled>선택완료</button>
						<button class="button_apply" id="reset" onclick="reset()"
							style="display: none">재설정</button>
					</div>
					<!-- 추가 설정 -->
					<div id="set-up"
						style="display: none; margin-top: 10px; margin-bottom: 10px;">
						<div id="gridContainer">
							<div class="gridItem">적용 기간</div>
							<div class="gridItem"></div>
							<div class="gridItem">적용 대상</div>
							<div class="gridItem"></div>
						</div>

						<div>
							<div
								style="margin-top: 10px; margin-bottom: 10px; font-weight: bold;">근무계획</div>
							<table id="update_workPlan">
								<thead>
									<tr id="update_workPlan_head" style="text-align: center;">
									</tr>
								</thead>
								<tbody id="update_workPlan_body">

								</tbody>

							</table>
						</div>
						<div>
							<button class="button_apply" id="openModalBtn"
								style="margin-top: 20px;" disabled>기안하기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal_form">
		<div class="modal_background"></div>
		<input id="loginID" type="hidden" value="${loginId }"> <input
			type="hidden" id=doc_type name="doc_type" value="근무계획변경"> <input
			type="hidden" id="work_plan_title" name="title" value="근무계획변경">
		<input type="hidden" id="work_plan_contents" name="contents">

		<input type="hidden" id="user_names" name="user_names"> <input
			type="hidden" id="work_types" name="work_types"> <input
			type="hidden" id="work_days" name="work_days">

		<div class="modal_contact_add">
			<div class="modal_title">결재</div>
			<div class="modal_body">
				<div class="modal_body_content">
					<div class="grid-container">
						<div class="grid-item" id="request">
							신청 <img id="plusIcon" src="/images/chats/plus-circle.svg">
						</div>
						<div class="grid-item manager"></div>
					</div>
				</div>
			</div>
			<div class="modal_footer" style="width: 100%;">
				<button type="button" class="button_cancel"
					id="modal_cancel_button">닫기</button>
				<button class="button_apply float_right" id="modal_apply_button"
					disabled>확인</button>
			</div>
		</div>

		<div class="modal_tag_add">
			<div class="modal_title">
				<span>신청 설정</span>
			</div>
			<div class="modal_body">
				<c:choose>
					<c:when test="${fn:length(managerList) == 0}">
						<span>표시할 상사가 없습니다.</span>
					</c:when>
					<c:otherwise>
						<ul>
							<c:forEach var="list" items="${managerList }">
								<li>
									<span>${list.organization }</span> 
									<span>${list.name }</span> 
									<span hidden>${list.id }</span> 
									<input name="managerID" type='checkbox' class="id_checkBox" value='${list.id}'>
								</li>
							</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="modal_footer">
				<button type="button" class="button_cancel" id="button_cancel_tag">취소</button>
				<button type="button" class="button_apply" id="button_apply_tag"
					style="margin-left: 14px">확인</button>
			</div>
		</div>
	</div>


	<script>
	$(".mCheckbox").on("click", function() {
		   let count = $(".mCheckbox:checked").length;

		   if (count > 4) {
		      $(this).prop("checked", false);
		   }
		});
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
	$(document).ready(function() {
		// 이름 소속을 가져오는 ajax
		$.ajax({
		    url: '/works_plan/getUserList'
		}).done(function (data) {
		    var tableBody = $("#userWorkData").empty();
		    var { dates, daysOfWeek, currentMonth } = generateMonthlyDates(); // dates 변수를 전역으로 이동
		    for (let i = 0; i < data.length; i++) {
		        let member = data[i];
		        var tableRow = $('<tr>').addClass('memberRow');
		        var nameCell = $('<td>').text(member.name);
		        var departmentCell = $('<td>').text(member.organization);
		        tableRow.append(nameCell, departmentCell);

		        // 주말을 제외하고 정을 추가하는 td 코드를 생성하여 추가
		        dates.forEach(date => {
		            let scheduleCell = createScheduleCell(date);
		            tableRow.append(scheduleCell);
		        });

		        tableBody.append(tableRow);
		    }

		    var tableRowDate = $('#tr1'); // 날짜가 들어갈 행
		    dates.forEach(date => {
		        let cellDate = $('<td>').text(formatDate(date)).addClass('works_days');
		        tableRowDate.append(cellDate);
		    });
		        var tableRowDays = $('#tr2'); // 요일이 들어갈 행
		    daysOfWeek.forEach(day => {
		        let cellDay = $('<td>').text(day).addClass('works_days');
		        tableRowDays.append(cellDay);
		    });

		});
		
	let tag_list_open = false;

	// img 버튼 눌렀을때 모달 창 띄우는거
	    $('#openModalBtn').click(function() {
	    	$('.modal_form').css('display', 'block');
	    	$('#modal_apply_button').prop('disabled', true);
	    });

	// 모달창에서 취소 누르면 모달창 꺼지는거
	    $('#modal_cancel_button').click(function() {
	    	$('.grid-item.manager').empty();
	    	$('#openModalBtn').prop('disabled', true);
	        $('.modal_form').css('display', 'none');
	        $('#update_workPlan_body select').val('9시 출근');
	    });
	

	// 태그 선택 박스 이외에 다른 곳이 클릭 됐을 때 
	$(document).on("click", function(event) {
	    if (tag_list_open) {
	        if (!$(event.target).closest('.modal_tag').length) {
	            $('.modal_tag_select_list').css('display', 'none');
	            tag_list_open = false;
	        }
	    }
	});

	// 새 태그 만들기 클릭 했을때 --이게나는 플러스 버튼
	$(document).on("click", "#plusIcon", function() {
	    $('.modal_contact_add').css('z-index', '3');
	    $('.modal_contact_add').css('border', 'solid 1px #707070');
	    $('.modal_contact_add').css('box-shadow', '2px 5px 4px 0 rgba(0,0,0,.16)');
	    $('.modal_tag_add').css('display', 'block');
	});

	// 새 태그 만들기 취소 했을 때
	$(document).on("click", "#button_cancel_tag", function() {
		$('input[name="managerID"]').prop('checked', false);
		$('#openModalBtn').prop('disabled', true);
	    $('.modal_contact_add').css('z-index', '4');
	    $('.modal_contact_add').css('border', '');
	    $('.modal_contact_add').css('box-shadow', '');
	    $('.modal_tag_add').css('display', 'none');
	});

	// 사람 설정
	var managerIDs = [];
	$(document).on("click", "#button_apply_tag", function() {
	var selectedNames = [];
		var checkboxes = document.querySelectorAll('input[name="managerID"]:checked');
	    
	    var gridItem = document.querySelector('.manager');

	    //db에 넣음?
	    

	    // 선택된 체크박스에서 이름 가져와서 배열에 추가
	    checkboxes.forEach(function(checkbox) {
	        var listItem = checkbox.parentElement;
	        var name = listItem.querySelector('span:nth-child(2)').textContent;
	        var managerID = listItem.querySelector('span:nth-child(3)').textContent;
	        selectedNames.push(name);
	        managerIDs.push(managerID);
	       
	    });
	    const applyButton = $('#modal_apply_button');

	    if (selectedNames.length > 0) {
	        applyButton.prop('disabled', false); // 확인 버튼 활성화
	    } else {
	        applyButton.prop('disabled', true); // 확인 버튼 비활성화
	    }
	    gridItem.innerHTML = "";
	    gridItem.innerHTML = selectedNames.join(', ');
	    $('.modal_contact_add').css('z-index', '4');
	    $('.modal_contact_add').css('border', '');
	    $('.modal_contact_add').css('box-shadow', '');
	    $('.modal_tag_add').css('display', 'none');

	});
	// submit 확인임
	    $('#modal_apply_button').on('click', function() {
	    	var workPlanTitleValue = $("#work_plan_title").val();
	    	var workPlanContentsValue = $("#work_plan_contents").val();
	    });
	
	//----------------------------------------------------------------
	
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



// 날짜 데이터를 생성하는 함수
function generateMonthlyDates() {
    let today = new Date();
    let currentMonth = today.getMonth() + 1; // 0-based index
    let currentYear = today.getFullYear();

    let firstDayOfMonth = new Date(currentYear, currentMonth - 1, 1);
    let lastDayOfMonth = new Date(currentYear, currentMonth, 0);

    let dates = [];
    let daysOfWeek = [];

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
	
	
	
let selectedValues = [];

document.getElementById('update_workPlan_body').addEventListener('change', function(event) {
    const target = event.target;
    let logUser = $("#userName").val();

    if (target.tagName === 'SELECT') {
        const selectedValue = target.value;
        const date = target.closest('tr').querySelector('td:first-child').textContent;
        const columnIndex = Array.from(target.closest('tr').children).indexOf(target.closest('td'));
        const headerText = $('#update_workPlan_head').children().eq(columnIndex).text();

        const existingIndex = selectedValues.findIndex(item => item.date === date && item.user === headerText);

        if (logUser === headerText) {
            if (selectedValue === '9시 출근') {
                if (existingIndex !== -1) {
                    selectedValues.splice(existingIndex, 1);
                    updateLists(selectedValues);
                }
            } else {
                if (existingIndex !== -1) {
                    selectedValues[existingIndex] = { date, user: headerText, value: selectedValue };
                } else {
                    selectedValues.push({ date, user: headerText, value: selectedValue });
                }
                updateLists(selectedValues);
            }
        } else {
            target.value = '9시 출근';
            alert('다른 사용자의 근무 변경은 불가능합니다.');
        }
    }
});

function updateLists(selectedValues) {
    let user_names = [];
    let work_types = [];
    let work_days = [];

    selectedValues.forEach(selectedValue => {
        user_names.push(selectedValue.user);
        work_types.push(selectedValue.value);
        work_days.push(currentYear + "년 " + selectedValue.date);
    });

    $('#user_names').val(JSON.stringify(user_names));
    $('#work_types').val(JSON.stringify(work_types));
    $('#work_days').val(JSON.stringify(work_days));

    $("#work_plan_contents").val(selectedValues.map(selectedValue =>
        '날짜: ' + selectedValue.date + ', 이름: ' + selectedValue.user + ', 근무변경요청: ' + selectedValue.value + '\n'
    ).join(''));
}


    $('#modal_apply_button').on('click', function() {
    	var approval = {
    		id:$("#loginID").val(),
    	    title: $("#work_plan_title").val(),
    	    contents: $("#work_plan_contents").val(),
    	    doc_type: $("#doc_type").val()
    	};

    	var workplan = {
    		id:$("#loginID").val(),
    	    user_names: $('#user_names').val(),
    	    work_types: $('#work_types').val(),
    	    work_days: $('#work_days').val()
    	    
    	};
    	
        
    	var data = {
                approval: approval,
                workPlan: workplan,
                managerID: managerIDs
            };
    	$.ajax({
    	    url: '/approval/document/works/workPlan_write',
    	    method: 'POST',
    	    contentType: 'application/json',
    	    data: JSON.stringify(data),
    	    success: function(response) {
    	        window.location.href = response;
    	    }
    	});
    });

    function updateWorkPlanTable(userNames,dates) {
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
            const cellDate = $('<td>').text(formatDate2(date)).addClass('members');
            tableRow.append(cellDate);

            userNames.forEach(userName => {
                const cellDate1 = $('<td>');
                const select = $('<select>').css('padding', '2px');
                select.append('<option value="10시 출근">10시 출근</option>');
                select.append('<option value="9시 출근" selected>9시 출근</option>');
                select.append('<option value="휴무일">휴무일</option>');
                select.append('<option value="휴일">휴일</option>');
                cellDate1.append(select);
                tableRow.append(cellDate1);
            });

            tableBody.append(tableRow);
        });
    }


		$('#update_workPlan_body').on('change', 'select', function() {
			$('#openModalBtn').prop('disabled', true);
		    let noChange = true;
		    $('#update_workPlan_body select').each(function() {
		        if ($(this).val() !== '9시 출근') {
		            noChange = false;
		            return false; // Break the loop since a change is found
		        }
		    });

		    if (noChange) {
		        $('#openModalBtn').prop('disabled', true);
		    } else {
		        $('#openModalBtn').prop('disabled', false);
		    }
		});

	let today = new Date();
	let currentYear = today.getFullYear();
	let currentMonth = today.getMonth() + 1;

	let dates = generateDatesForMonth(currentYear, currentMonth);

	function generateDatesForMonth(year, month) {
	    let firstDayOfMonth = new Date(year, month - 1, 1);
	    let lastDayOfMonth = new Date(year, month, 0);

	    let dates = [];

	    for (let day = firstDayOfMonth; day <= lastDayOfMonth; day.setDate(day.getDate() + 1)) {
	        dates.push(new Date(day));
	    }

	    return dates;
	}
	

	function formatDate2(date) {
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        return month + "월 "+ day + "일";
    }
	 
	    
	    function formatDate1(date) {
	        let year = date.getFullYear();
	        let month = date.getMonth() + 1;
	        let day = date.getDate();
	        return year + "년 " + month + "월 "+ day + "일";
	    }

	    function updateCurrentMonth(year, month) {
	        var gridItem2 = $("#gridContainer .gridItem:nth-child(2)");

	        let firstDayOfMonth = new Date(year, month - 1, 1);
	        let lastDayOfMonth = new Date(year, month, 0);

	        let formattedFirstDay = formatDate1(firstDayOfMonth);
	        let formattedLastDay = formatDate1(lastDayOfMonth);
	        
	        let newText = formattedFirstDay + " ~ " + formattedLastDay;
	        gridItem2.text(newText);
	    }
	    


	        // 현재 날짜 정보
	        /*const today = new Date();
	        let currentMonth = today.getMonth() + 1; // 1부터 시작하는 월*/
	    //$(document).ready(function() {
	        // 초기화 함수 호출
	        updateMonth();
	        updateData(); // 페이지 로딩 시 데이터 초기화
	        updateCurrentMonth();
	        updateCurrentMonth(today.getFullYear(),currentMonth);
	        
	        // 왼쪽 버튼 클릭 시 이벤트 처리
	        $(".prevMonth").click(function () {
	            currentMonth--;
	            updateMonth();
	            updateData(today.getFullYear(),currentMonth); // 이전 월 변경 시 데이터 갱신
	            updateCurrentMonth(today.getFullYear(), currentMonth);
	            let dates = generateDatesForMonth(today.getFullYear(), currentMonth);
	            updateWorkPlanTable(userNames, dates);
	        });

	        // 오른쪽 버튼 클릭 시 이벤트 처리
	        $(".nextMonth").click(function () {
	            currentMonth++;
	            updateMonth();
	            updateData(today.getFullYear(),currentMonth); // 다음 월 변경 시 데이터 갱신
	            updateCurrentMonth(today.getFullYear(), currentMonth);
	            //loadMembersByDepartment(organization, function(userNames) {
	                let dates = generateDatesForMonth(today.getFullYear(), currentMonth);
	                updateWorkPlanTable(userNames, dates);
	           // });
	        });
	        

	        // 월 갱신 함수
	        function updateMonth() {
	            // 날짜를 표시할 엘리먼트
	            let currentMonthElement = $(".currentMonth");

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
	            $(".cMonth").val(today.getFullYear());
	            $(".cYear").val(currentMonth);
	        }
	       

	        function updateMonthlyDates(updateYear, updateMonth) {
	            let firstDayOfMonth = new Date(updateYear, updateMonth - 1, 1);
	            let lastDayOfMonth = new Date(updateYear, updateMonth, 0);

	            let dates = [];
	            let daysOfWeek = [];

	            for (let day = firstDayOfMonth; day <= lastDayOfMonth; day.setDate(day.getDate() + 1)) {
	                dates.push(new Date(day));
	                daysOfWeek.push(day.toLocaleDateString('ko-KR', { weekday: 'short' }));
	            }
	            
	            return { dates, daysOfWeek };
	        }


	        function updateData(updateYear, updateMonth) {
	            $.ajax({
	                url: '/works_plan/getUserList',
	                data: { month: currentMonth },
	            }).done(function (data) {
	                var tableBody = $("#userWorkData").empty();
	                var tableRowDate = $("#tr1").empty();
	                var tableRowDays = $("#tr2").empty();
	                
	                var nameHeader = $('<th>').addClass('left_none').attr('rowspan', '2').css('padding', '5px').text('이름');
	                var departmentHeader = $('<th>').attr('rowspan', '2').css('padding', '5px').text('소속');
	                tableRowDate.append(nameHeader, departmentHeader);
	                
	                var { dates, daysOfWeek } = updateMonthlyDates(updateYear,updateMonth);
	                for (let i = 0; i < data.length; i++) {
	                	let tableRow = $('<tr>').addClass('memberRow');
	                    let member = data[i];
	                    var nameCell = $('<td>').text(member.name);
	                    var departmentCell = $('<td>').text(member.organization);
	                    tableRow.append(nameCell, departmentCell);

	                    // 주말을 제외하고 정을 추가하는 td 코드를 생성하여 추가
	                    dates.forEach(date => {
	                        var scheduleCell = createScheduleCell(date);
	                        tableRow.append(scheduleCell);
	                    });

	                    tableBody.append(tableRow);
	                }

	                dates.forEach(date => {
	                    let cellDate = $('<td>').text(formatDate(date)).addClass('works_days');
	                    tableRowDate.append(cellDate);
	                });
	                daysOfWeek.forEach(day => {
	                    let cellDay = $('<td>').text(day).addClass('works_days');
	                    tableRowDays.append(cellDay);
	                });
	                
	                
	                
	            });
	        }
	    //});   


	$("#top_container").load("/commons/topForm");
	$(".left_item").load("/insa/manage/left_item?selectItem=workPlan");
	

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

    // getDepartmentList를 통해 데이터 가져오기
    $.ajax({
        url:'/members/getDepartmentList'
    }).done(function(data) {
        // 성공적으로 데이터를 가져온 경우
        $("#department_Select").empty();
        $("#department_Select").append('<option selected>부서 선택</option>');
        for (let i = 0; i < data.length; i++) {
            let departmentName = data[i];
            $("#department_Select").append('<option value="' + departmentName + '">' + departmentName + '</option>');
        }
    });
    let userNames = [];
    // select 요소의 변경 감지하여 버튼 활성화/비활성화 처리
    $("#department_Select").on("change", function() {
        var organization = $(this).val();
        if (organization !== "부서 선택") {
            loadMembersByDepartment(organization);
            $('#selectFinished').prop('disabled', false);
        } else {
            $('#selectFinished').prop('disabled', true); 
        }
        
        
    });

    // 페이지 로드 시 초기 상태 설정 (버튼은 비활성화)
    $('#selectFinished').prop('disabled', true);



function loadMembersByDepartment(organization) {
let userNames = [];
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
        //updateWorkPlanTable(userNames);
        let dates = generateDatesForMonth(today.getFullYear(), currentMonth);
        updateWorkPlanTable(userNames, dates);
    });
}


});

</script>

</body>
</html>