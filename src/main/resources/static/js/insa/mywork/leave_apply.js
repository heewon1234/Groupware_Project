$(document).ready(function() {
	$(".left_item").load("/insa/manage/left_item?selectItem=leave_apply");
});

$(document).ready(function() {
	$("#top_container").load("/commons/topForm");
});
$(document).ready(function() {
    // 각 체크박스에 대한 이벤트 리스너 추가
    $("input[name='managerID']").on("change", function() {
        // 체크박스 상태에 따라 동작 수행
        if ($(this).prop("checked")) {
            // 체크됐을 때의 동작
            var managerId = $(this).val();
            var managerName = $(this).siblings("span").eq(1).text();
            if($("input[name='managerID']:checked").length > 4){
				 $("#button_apply_tag").addClass("permit");
			}else{
				$("#button_apply_tag").removeClass("permit");
			}
			
        } else {
            // 체크가 해제됐을 때의 동작
            var managerId = $(this).val();
            var managerName = $(this).siblings("span").eq(1).text();
           if ($("input[name='managerID']:checked").length === 0 || $("input[name='managerID']:checked").length > 4) {
            $("#button_apply_tag").addClass("permit");
            }
            else{
				$("#button_apply_tag").removeClass("permit");
			}
        
        }
        
    });
});
var checkedManagerValues = [];
var checkedManagers;
							
					
$(".modalButton_apply").on("click", function() {
	
	// 모달 폼 보이기/숨기기 토글
	$('.modal_tag_add').css('display', 'block');
	$('.modal_background').css('display', 'block');
	
	 $("#button_apply_tag").on("click", function(e) {
		 e.preventDefault();
		 
		  $("#approvalTable .innerTable th").text("");
        // 체크된 checkbox 값을 가져오기
         checkedManagers = $("input[name='managerID']:checked");
        // 비어있는 td에 값 넣기
        checkedManagers.each(function(index, element) {
            var managerName = $(element).siblings("span").eq(1).text();
            $("#app_tr th").eq(index).text(managerName);
        });

        $(".modal_tag_add").hide();
        $(".modal_background").hide();
        
         if (checkedManagers.length > 0 && selectedDates.length > 0) {
            $(".button_apply").removeClass("permit");
        }
		 checkedManagerValues = []; // 배열 초기화

        checkedManagers.each(function(index, element) {
            var managerValue = $(element).val();
            checkedManagerValues.push(managerValue);
        });
		$('#calendartd').css('display', 'block');
});
		 
    });
    // 취소 버튼 클릭 시
    $("#button_cancel_tag").on("click", function(e) {
		if(selectedDates.length > 0){
			alert("휴가신청을 삭제한 뒤 시도해주세요");
			return;
		}
		e.preventDefault();
		$("#approvalTable .innerTable th").text("");
		$('#leave_apply').css('display', 'none');
		$('#calendartd').css('display', 'none');
         $(".modal_tag_add").hide();
         $(".modal_background").hide();
    });




let selectedDates = [];
let selectedbox = [];
let tdarray = [];
// 이름 소속을 가져오는 ajax
$(document).ready(function() {
	loadData();
	function loadData() {
		$.ajax({
			url: '/works_plan/getUserList'
		}).done(function(data) {
			var tableBody = $("#userWorkData").empty();
			var { dates, daysOfWeek, currentMonth } = generateMonthlyDates(); // dates 변수를 전역으로 이동
			for (let i = 0; i < data.length; i++) {
				let member = data[i];
				var tableRow = $('<tr>').addClass('memberRow');
				var nameCell = $('<td>').text(member.name);
				var departmentCell = $('<td>').text(member.organization);
				tableRow.append(nameCell, departmentCell);
			}
			var tableRowDate = $('#tr1'); // 날짜가 들어갈 행
			let num1 = 0;
			dates.forEach(date => {
				let cellDate = $('<td>').text(formatDate(date)).addClass('td' + num1++);
				tableRowDate.append(cellDate);
			});
			var tableRowDays = $('#tr2'); // 요일이 들어갈 행
			var tableRowSelect = $('#tr3');
			let num2 = 0;
			daysOfWeek.forEach(day => {

				let cellDay = $('<td>').text(day).addClass('td' + num2);
				tableRowDays.append(cellDay);
				let td = $('<td>').addClass('td' + num2++);
				tableRowSelect.append(td);
			});



			tableRowSelect.on('click', 'td', function() {
				 var leaveRemainder = document.getElementById('remainder').value;
				if(selectedDates.length >= leaveRemainder){
					alert("보유한 휴가일 이상을 신청하실수 없습니다.");
					return;
				}
				
				let selectedClass = $(this).attr('class'); // 클릭한 td의 클래스 값 가져오기
				let dayText = $(this).text().trim();

				// '토' 또는 '일'인 경우 또는 해당 클래스를 가지는 tr2의 값이 '토' 또는 '일'인 경우 클릭을 허용하지 않음
				if (dayText === '토' || dayText === '일' || tableRowDays.find('.' + selectedClass).text().trim() === '토' || tableRowDays.find('.' + selectedClass).text().trim() === '일') {
					return;
				}

				let isSelected = $(this).hasClass('selected-td');
				$(this).addClass("selected-td")

				let clickedRowText = tableRowDate.find('.' + selectedClass).text().trim();
				let formattedDate = formatDate3(new Date(today.getFullYear(), currentMonth - 1, parseInt(clickedRowText)));

				if (isSelected) {
				} else {
					// 배열에 해당 날짜를 추가
					selectedDates.push(formattedDate);
					tdarray.push(selectedClass)
				}


				$('#selectedDates').empty();
				$('#selectleavetype').empty();
				for (i = 0; i < selectedDates.length; i++) {
					var tableRow = $('<tr>').addClass('div' + i);
					tableRow.append(selectedDates[i]);
					tableRow.append("<button id='deleteleave' class='div" + i + " " + tdarray[i] + "'>X</button>");
					tableRow.append("<br>");
					$('#selectedDates').append(tableRow);
					$('#selectleaveday').text("선택일수" + selectedDates.length + "일");
				}
				$('#selectleavetype').append("<select><option value='연차'>연차</option><option value='경조사'>경조사</option></select>");

				// 선택된 날짜들을 select로 연차 경조사 선택 가능하게 만들기

	
				 if (checkedManagers.length > 0 && selectedDates.length > 0) {
            $(".button_apply").removeClass("permit");
        }
        else{
			arlet("결재자를 우선 선택해주세요");
		}
				if (selectedDates.length > 0) {
					$('#leave_apply').css('display', 'block');
				} else {
					$('#leave_apply').css('display', 'none');
				}
			});

		});
	}
});

$(document).on("click", "#deleteleave", function(e) {
	e.preventDefault();

	// 클릭한 버튼의 클래스를 가져옴
	var buttonClass = $(this).attr('class');

	var classArray = buttonClass.split(' ');

	var dateToRemove = $("." + classArray[0]).text().trim().substring(0, 10);
	var tdRemove = classArray[1];

	// selectedDates 배열에서 해당 날짜를 찾아 제거
	var indexToRemove = selectedDates.indexOf(dateToRemove);
	var indextotdRemove = tdarray.indexOf(tdRemove);
	if (indexToRemove !== -1) {
		selectedDates.splice(indexToRemove, 1);
	}
	if (indextotdRemove !== -1) {
		tdarray.splice(indextotdRemove, 1);
	}
	// 클래스와 일치하는 엘리먼트를 제거
	$("." + classArray[0]).remove(); // div
	$("#tr3 ." + classArray[1]).removeClass('selected-td'); //td
	$('#selectleaveday').text("선택일수" + selectedDates.length + "일");
	 if (checkedManagers.length > 0 && selectedDates.length > 0) {
            $(".button_apply").removeClass("permit");
        }
	if (selectedDates.length > 0) {
		$('#leave_apply').css('display', 'block');
	} else {
		$('#leave_apply').css('display', 'none');
	}

});

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
	return month + "월 " + day + "일";
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
	let year = date.getFullYear();
	let month = date.getMonth() + 1;
	let day = date.getDate();
	return year + "년 " + month + "월 " + day + "일";
}
function formatDate3(date) {
	let year = date.getFullYear();
	let month = date.getMonth() + 1;
	let day = date.getDate();
	return year + '-' + (month < 10 ? '0' : '') + month + '-' + (day < 10 ? '0' : '') + day;
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
updateCurrentMonth(today.getFullYear(), currentMonth);

// 왼쪽 버튼 클릭 시 이벤트 처리
/*$(".prevMonth").click(function() {
	currentMonth--;
	updateMonth();
	updateData(today.getFullYear(), currentMonth); // 이전 월 변경 시 데이터 갱신
	updateCurrentMonth(today.getFullYear(), currentMonth);
});

// 오른쪽 버튼 클릭 시 이벤트 처리
$(".nextMonth").click(function() {
	currentMonth++;
	updateMonth();
	updateData(today.getFullYear(), currentMonth); // 다음 월 변경 시 데이터 갱신
	updateCurrentMonth(today.getFullYear(), currentMonth);
});
*/

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
	}).done(function(data) {
		var tableBody = $("#userWorkData").empty();
		var tableRow = $(".memberRow").empty();
		var tableRowDate = $("#tr1").empty();
		var tableRowDays = $("#tr2").empty();

		// 기존에 선택된 td 요소의 클래스 제거
		$('#tr3 .selected-td').removeClass('selected-td');


		var { dates, daysOfWeek } = updateMonthlyDates(updateYear, updateMonth);
		for (let i = 0; i < data.length; i++) {
			let member = data[i];
			// var tableRow = $('<tr>').addClass('memberRow');
			var nameCell = $('<td>').text(member.name);
			var departmentCell = $('<td>').text(member.organization);
			tableRow.append(nameCell, departmentCell);
		}
		let num = 0;
		dates.forEach(date => {
			let cellDate = $('<td>').text(formatDate(date)).addClass('td' + num);
			tableRowDate.append(cellDate);
		});

		daysOfWeek.forEach(day => {
			let cellDay = $('<td>').text(day).addClass('td' + num++);
			tableRowDays.append(cellDay);
		});

	});
}
$(document).ready(function() {
	$("#submit_button").on("click", function() {
		let title = document.getElementById('work_leave_title').value; // 휴가신청
		let work_type = $('#selectleavetype select option:selected').val(); // 경조사인지 연차인지
		let approvalname = checkedManagerValues.join(',');
		$("#work_days").val(selectedDates.join(','));
		let work_day = document.getElementById('work_days').value;
		document.getElementById('work_leave_contents').value = " 휴가 유형 : "+ work_type + " 휴가신청 날짜 : " + work_day;
		let contents = document.getElementById('work_leave_contents').value;
		
		$.ajax({
			url: '/approval/document/works/workLeave_write',
			data: {
			title: title,
			work_type:work_type,
			work_day:work_day,
			approvalname:approvalname,
			contents : contents
		},
		}).done(function(resp) {
			alert("휴가가 신청되었습니다");
			window.location.href = resp;
		});
	});
})

