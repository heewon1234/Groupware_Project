var inTimeActivated = false;
var outTimeActivated = false;

$(document).ready(function() {
	$(".left_item").load("/insa/manage/left_item?selectItem=work_leave");
	$("#top_container").load("/commons/topForm");
	
	$.ajax({ // 버튼 클릭 시 ajax로 해당 업무 추가
		url: "/works/workplanlist",
		
	}).done(
		function(resp) {
			
		var currentDate = new Date();

        // 월, 일, 요일을 추출합니다.
        var month = (currentDate.getMonth() + 1).toString().padStart(2, '0'); // 0부터 시작하므로 +1
        var day = currentDate.getDate().toString().padStart(2, '0');
        var dayOfWeek = getDayOfWeek(currentDate.getDay());
        
        $(".div_body").before(month+"월");
        $(".div_header").append(day+"일");
        $(".div_footer").append(dayOfWeek);
        if(resp==""){
			$(".work_plan_list").append("9시 출근");
		}
		else{
			 $(".work_plan_list").append(resp);
		}
       
		});
		
	
});
 function getDayOfWeek(dayNumber) {
        var daysOfWeek = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
        return daysOfWeek[dayNumber];
    }
function workin() {
	updateinTime();
	changeStatus('근무중', this);
	document.getElementById("workin").disabled = true;
	document.getElementById("workout").disabled = false;
	$("#work-out").removeClass("img-disabled");
	 $("#work-in").addClass("img-disabled");
	// 다른 버튼 활성화
	var buttons = document.querySelectorAll('.workbtn');
	for (var i = 0; i < buttons.length; i++) {
		buttons[i].disabled = false;
	}
}
function workout() {
	var userConfirmed = confirm('퇴근은 하루에 한 번만 체크할 수 있습니다. 퇴근하시겠습니까? ');


	if (userConfirmed) {
		updateoutTime();
		changeStatus('근무 종료', this);
		document.getElementById("workout").disabled = true;
		var buttons = document.querySelectorAll('.workbtn');
		for (var i = 0; i < buttons.length; i++) {
			buttons[i].disabled = true;
		}
		$("#work-out").addClass("img-disabled");

	} else {


	}

}


function updateinTime() { // 출근하기 클릭 시 시간 출력
	var currentinTimeArea = document.getElementById("workin");
	if (!inTimeActivated && !outTimeActivated) {
		var currentTimeElement = document
			.getElementById("currentinTime");
		var currentTime = new Date();
		var hours = currentTime.getHours().toString().padStart(2, "0");
		var minutes = currentTime.getMinutes().toString().padStart(2,
			"0");
		var seconds = currentTime.getSeconds().toString().padStart(2,
			"0");
		var newTime = hours + ":" + minutes + ":" + seconds;
		currentTimeElement.innerText = newTime;

		currentinTimeArea.disabled = true; // 영역 비활성화
		inTimeActivated = true;
	}
}
function updateoutTime() { // 퇴근하기 클릭 시 시간 출력
	var currentoutTimeArea = document.getElementById("workout");
	if (inTimeActivated && !outTimeActivated) {
		var currentTimeElement = document
			.getElementById("currentoutTime");
		var currentTime = new Date();
		var hours = currentTime.getHours().toString().padStart(2, "0");
		var minutes = currentTime.getMinutes().toString().padStart(2,
			"0");
		var seconds = currentTime.getSeconds().toString().padStart(2,
			"0");
		var newTime = hours + ":" + minutes + ":" + seconds;
		currentTimeElement.innerText = newTime;

		currentoutTimeArea.disabled = true; // 영역 비활성화
		outTimeActivated = true;
	}
}
function changeStatus(newStatus, button) { // 업무 외출 회의 외근 버튼 클릭 시 해당 버튼 비활성화
	// 모든 버튼을 활성화 상태로 변경
	var buttons = document.querySelectorAll('.workbtn');
	for (var i = 0; i < buttons.length; i++) {
		buttons[i].disabled = false;
	}

	// 선택된 버튼만 비활성화 상태로 변경
	button.disabled = true;

	var statusText = document.getElementById('statusText');
	statusText.textContent = newStatus;

	var currentTimeElement = document.getElementById("currentinTime");
	var currentTime = new Date();
	var hours = currentTime.getHours().toString().padStart(2, "0");
	var minutes = currentTime.getMinutes().toString().padStart(2, "0");
	var newTime = hours + ":" + minutes;

	$.ajax({ // 버튼 클릭 시 ajax로 해당 업무 추가
		url: "/works/insert",
		data: {
			work_type: newStatus
		},
	}).done(
		function(resp) {
			let tr = $("<tr>");
			for (let i = 0; i < resp.length; i++) {
				$("#scheduleList").append("<div class='schedules'><div class='circle''></div><div class='schedule''>"+(resp[i].work_type)
				+ "</div><div class='startday'>"+(resp[i].time)+"</div></div><br>");
				$('#scheduleList').scrollTop($('#scheduleList')[0].scrollHeight);
			}
		});

}

function updateClock() { // 시계 출력
	var now = new Date();
	var options = {
		hour12: false
	};
	var time = now.toLocaleTimeString('en-US', options);
	document.getElementById("clock").innerHTML = time;
}
function updateWorkformTable() { // 화면에 근무중 업무중 등 출력하는 기능
	$.ajax({
		url: "/works/list",
	}).done(
		function(resp) {
			for (let i = 0; i < resp.length; i++) {
				$("#scheduleList").append("<div class='schedules'><div class='circle''></div><div class='schedule''>"+(resp[i].work_type)
				+ "</div><div class='startday'>"+(resp[i].time)+"</div></div><br>");
				$('#scheduleList').scrollTop($('#scheduleList')[0].scrollHeight);
			}
		});
}
function displayTime(elementId, timeString) {
	var currentTimeElement = document.getElementById(elementId);
	var currentTime = new Date(timeString);
	var hours = currentTime.getHours().toString().padStart(2, "0");
	var minutes = currentTime.getMinutes().toString().padStart(2, "0");
	var seconds = currentTime.getSeconds().toString().padStart(2, "0");
	var newTime = hours + ":" + minutes + ":" + seconds;
	currentTimeElement.innerText = newTime;
}

function work_inout() {
	$.ajax({
		url: "/works/list",
	})
		.done(
			function(resp) {
				if (resp.length == 0) {

				} else if (resp[resp.length - 1].work_type != "근무 종료") {
					var buttons = document
						.querySelectorAll('.workbtn');
					for (var i = 0; i < buttons.length; i++) {
						buttons[i].disabled = false;
						if (resp[resp.length - 1].work_type.trim().includes(buttons[i].textContent.trim())) {
							buttons[i].disabled = true;
						}$("#work-in").addClass("img-disabled");
						$("#work-out").removeClass("img-disabled");
					}
					document.getElementById("workin").disabled = true;
					document.getElementById("workout").disabled = false;
					var statusText = document
						.getElementById('statusText');
					statusText.textContent = resp[resp.length - 1].work_type;
					displayTime("currentinTime", resp[0].work_time);
				}

				else if (resp[resp.length - 1].work_type == "근무 종료") {
					var buttons = document
						.querySelectorAll('.workbtn');
					for (var i = 0; i < buttons.length; i++) {
						buttons[i].disabled = true;
					}
					$("#work-out").addClass("img-disabled");
	 				$("#work-in").addClass("img-disabled");
					document.getElementById("workin").disabled = true;
					document.getElementById("workout").disabled = true;
					var statusText = document
						.getElementById('statusText');
					statusText.textContent = resp[resp.length - 1].work_type;

					// 사용 예시
					displayTime("currentoutTime", resp[resp.length - 1].work_time);
					displayTime("currentinTime", resp[0].work_time);
				}
			});
}

updateWorkformTable()
$(document).ready(function() {
	work_inout();
});
$(".worktime_update").on("click", function() {
	alert("업데이트중입니다");
});
// 1초마다 시계 업데이트
setInterval(updateClock, 1000);