var inTimeActivated = false;
var outTimeActivated = false;

$(document).ready(function() {
	$(".left_item").load("/works/left_item?selectItem=work_leave");
});
function workin() {
	updateinTime();
	changeStatus('근무중', this);
	document.getElementById("workin").disabled = true;
	document.getElementById("workout").disabled = false;

	// 다른 버튼 활성화
	var buttons = document.querySelectorAll('.workbtn');
	for (var i = 0; i < buttons.length; i++) {
		buttons[i].disabled = false;
	}
}
function workout() {
	updateoutTime();
	changeStatus('근무 종료', this);
	document.getElementById("workout").disabled = true;
	var buttons = document.querySelectorAll('.workbtn');
	for (var i = 0; i < buttons.length; i++) {
		buttons[i].disabled = true;
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
			tr.html("<td width='100'>"
				+ (resp[resp.length - 1].time) + "</td>"
				+ "<td width='100'>"
				+ (resp[resp.length - 1].work_type)
				+ "</td></tr>")
			$("#workform").append(tr);
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
				let tr = $("<tr>");
				tr.html("<td width='100'>" + (resp[i].time)
					+ "</td>" + "<td width='100'>"
					+ (resp[i].work_type) + "</td></tr>")
				$("#workform").append(tr);
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
				console.log(resp)
				if (resp.length == 0) {

				} else if (resp[resp.length - 1].work_type != "근무 종료") {
					var buttons = document
						.querySelectorAll('.workbtn');
					for (var i = 0; i < buttons.length; i++) {
						buttons[i].disabled = false;
					}
					document.getElementById("workin").disabled = true;
					document.getElementById("workout").disabled = false;
					var statusText = document
						.getElementById('statusText');
					statusText.textContent = resp[0].work_type;
					displayTime("currentinTime", resp[0].work_time);
				}

				else if (resp[resp.length - 1].work_type == "근무 종료") {
					var buttons = document
						.querySelectorAll('.workbtn');
					for (var i = 0; i < buttons.length; i++) {
						buttons[i].disabled = true;
					}
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
// 1초마다 시계 업데이트
setInterval(updateClock, 1000);




// 달력
function prevMonth(date) {
    var target = new Date(date);
    target.setDate(1);
    target.setMonth(target.getMonth() - 1);

    return getYmd(target);
}

function nextMonth(date) {
    var target = new Date(date);
    target.setDate(1);
    target.setMonth(target.getMonth() + 1);

    return getYmd(target);
}

function getYmd(target) {
    // IE에서 날짜 문자열에 0이 없으면 인식 못함
    var month = ('0' + (target.getMonth() + 1)).slice(-2);
    return [target.getFullYear(), month, '01'].join('-');
}

function fullDays(date) {
    var target = new Date(date);
    var year = target.getFullYear();
    var month = target.getMonth();

    var firstWeekDay = new Date(year, month, 1).getDay();
    var thisDays = new Date(year, month + 1, 0).getDate();

    // 월 표시 달력이 가지는 셀 갯수는 3가지 가운데 하나이다.
    // var cell = [28, 35, 42].filter(n => n >= (firstWeekDay + thisDays)).shift();
    var cell = [28, 35, 42].filter(function (n) {
            return n >= (firstWeekDay + thisDays);
        }).shift();

    // 셀 초기화, IE에서 Array.fill()을 지원하지 않아서 변경
    // var days = new Array(cell).fill({date: '', dayNum: '', today: false});
    var days = []
    for (var i = 0; i < cell; i++) {
        days[i] = {
            date: '',
            dayNum: '',
            today: false
        };
    }
    
    var now = new Date();
    var today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    var inDate;
    for (var index = firstWeekDay, i = 1; i <= thisDays; index++, i++) {
        inDate = new Date(year, month, i);
        days[index] = {
            date: i,
            dayNum: inDate.getDay(),
            today: (inDate.getTime() === today.getTime())
        };
    }

    return days;
}


function drawMonth(date) {
        $('#month-this').text(date.substring(0, 7).replace('-', '.'));
        $('#month-prev').data('ym', prevMonth(date));
        $('#month-next').data('ym', nextMonth(date));

        $('#tbl-month').empty();

        var td = '<td class="__REST__ __TODAY__"><a __HREF__>__DATE__</a></td>';
        var href = '/depart/schedule?date=' + date.substring(0, 8);
        var hasEvent;
        var tdClass;
        var week = null;
        var days = fullDays(date);
        for (var i = 0, length = days.length; i < length; i += 7) {
            // 전체 셀을 주단위로 잘라서 사용
            week = days.slice(i, i + 7);
            
            var $tr = $('<tr></tr>');
            week.forEach(function (obj, index) {
                //매주 수요일에 이벤트가 있다고 가정
                hasEvent = (index === 3);
                tdClass = (index === 0)? 'sun': '';
                tdClass = (index === 6)? 'sat': tdClass;
    
                $tr.append(td.replace('__REST__', tdClass)
                            .replace('__TODAY__', (obj['today'])? 'today': '')
                            .replace('__DATE__', obj['date']));
            });
            $('#tbl-month').append($tr);
        }
    }
    
    $(function () {
        var date = (new Date()).toISOString().substring(0, 10);
        drawMonth(date);
    
        $('.month-move').on('click', function (e) {
            e.preventDefault();
    
            drawMonth($(this).data('ym'));
        });
    });