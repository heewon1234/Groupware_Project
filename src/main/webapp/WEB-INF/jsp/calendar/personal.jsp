<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>Calendar</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<link rel="stylesheet" type="text/css" href="/css/calendar/calendar.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/fullcalendar@5.7.0/main.min.css">
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/fullcalendar@5.7.0/main.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- bootstrap 4 -->
<!-- <link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" type="text/css" href="/css/commons/topForm.css">

<script src="/js/chat/chatting.js" type="text/javascript"></script>

<style>
.left_item {
    float: left;
    width: 276px;
    height: 100%;
    position: fixed;
    background-color: #EFF4FC; 
    z-index: 2;    
}

.menu_tab {
    overflow-y:auto;
    overflow-x: hidden;
    padding: 0 24px 30px;
    height: 100%;
}

.menu_item {
    height: 38px;
    width: 228px;
    color: #333333;
    display: flex;
    align-items: center;
    cursor: pointer;
    padding: 0px 0px 0px 10px;   
}

.menu_item:hover {
    background-color: #F7FAFF;
}

.menu_item img {
    max-width: 16px;
    margin: 0px 8px 0px 0px;
}

.menu_item_text {
    font-size: 15px;
}

.right_item {
	float: left;
	width: calc(100% - 276px);
	height: calc(100% - 60px);
	min-width: 950px;
	margin-left: 276px;
	padding-left: 20px;
	overflow: auto;
}
</style>


</head>
<body>
	<div id="top_container"></div>
	
	<div class="body_form">
        <!-- 왼쪽 부분(메뉴) -->
        <div class="left_item">
            <!-- 메뉴 리스트 있는 부분 -->
            <div class="menu_tab">

                <!-- 일반 메뉴 -->
                <div class="menu_item">
                    <img src="/images/commons/body_form/left_item/default/favorites.png" />
                    <span class="menu_item_text">중요 주소록</span>
                </div>

                <!-- 일반 메뉴 -->
                <div class="menu_item">
                    <img class="menu_item_img" src="/images/commons/body_form/left_item/default/information.png" />
                    <span class="menu_item_text">일반 메뉴</span>
                </div>
            </div>
        </div>

        <!-- 오른쪽 부분(해당 메뉴의 주요 콘텐츠) -->
        <div class="right_item">
            <div class="content_tab">

                <div id="calendarBox">
		<div id="calendar"></div>
	</div>

	<div class="modal fade" id="calendarModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">일정을 입력하세요.</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="/calendar/create" method="post">
					<div class="modal-body">
						<div class="form-group">
							<label for="taskId" class="col-form-label">일정 내용</label> <input
								type="text" class="form-control" id="calendar_content"
								name="calendar_content"> <label for="taskId"
								class="col-form-label">시작 날짜</label> <input type="date"
								class="form-control" id="calendar_start_date"
								name="calendar_start_date"> <label for="taskId"
								class="col-form-label">종료 날짜</label> <input type="date"
								class="form-control" id="calendar_end_date"
								name="calendar_end_date">
						</div>
					</div>

					<div class="modal-footer">
						<button class="btn btn-warning" id="addCalendar">추가</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal" id="sprintSettingModalClose">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>

            </div>
        </div>
    </div>
	
	



<script type="text/javascript">

	let list = ${list};
	var events = [];
	list.forEach(function(item) {
	    events.push({
	        title: item.schedule,
	        start: item.startday,
	        end: item.endday,
	        seq: item.seq
	    });
	});
	
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
			timeZone : 'UTC',
			initialView : 'dayGridMonth',
			events : events,
			headerToolbar : {
				center : 'addEventButton' // headerToolbar에 버튼을 추가
			},
			customButtons : {
				addEventButton : { // 추가한 버튼 설정
					text : "일정 추가", // 버튼 내용
					click : function() { // 버튼 클릭 시 이벤트 추가
						$("#calendarModal").modal("show"); // modal 나타내기

						$("#addCalendar").off("click").on(
								"click",
								function() { // modal의 추가 버튼 클릭 시
									var content = $("#calendar_content").val();
									var start_date = $("#calendar_start_date")
											.val();
									var end_date = $("#calendar_end_date")
											.val();

									//내용 입력 여부 확인
									if (content == null || content == "") {
										alert("내용을 입력하세요.");
									} else if (start_date == ""
											|| end_date == "") {
										alert("날짜를 입력하세요.");
									} else if (new Date(end_date)
											- new Date(start_date) < 0) { // date 타입으로 변경 후 확인
										alert("종료일이 시작일보다 먼저입니다.");
									} else { // 정상적인 입력 시
										var obj = {
											"title" : content,
											"start" : start_date,
											"end" : end_date
										}//전송할 객체 생성

										//서버로 해당 객체를 전달해서 DB 연동 가능
										calendar.addEvent(obj);
										$("#calendarModal").modal("hide");
									}
								});
					}
				}
			},
			eventClick: function (info){
                if(confirm("'"+ info.event.title +"' 일정을 삭제하시겠습니까 ?")){
                    // 확인 클릭 시
                    info.event.remove();
                    
                    var seq = info.event.extendedProps.seq;
                    
                    $(function deleteData(){
                        $.ajax({
                            url: "/calendar/delete",
                            method: "POST",
                            data: {
                            	seq:seq
                            }
                        })
                    })
                }

                
            },
            
            eventDrop: function (info){
                var seq = info.event.extendedProps.seq;
                var title = info.event._def.title;
                var start = info.event._instance.range.start;
                var end = info.event._instance.range.end;
                
                var startTimestamp = start.toISOString().slice(0, 19).replace('T', ' ');
                var endTimestamp = end.toISOString().slice(0, 19).replace('T', ' ');

                $(function deleteData() {
                    $.ajax({
                        url: "/calendar/update",
                        method: "POST",
                        data: {
                        	seq:seq,
                        	title:title,
                        	start:startTimestamp,
                        	end:endTimestamp
                        }
                    })
                })
            },
			
			editable : true, // false로 변경 시 draggable 작동 x
			displayEventTime : false
		});
		calendar.render();
	});
	
	
	
	let logout = false;
	
	$("#logout").on("click", function() {
		if (logout == false) {
			$(".logout").css("display", "flex");
			logout = true;
		} else {
			$(".logout").css("display", "none");
			logout = false;
		}
	});
	
	
	$("#logoutbtn").on("click", function() {
		let memberout = confirm("로그아웃 하시겠습니까?");
		if (!memberout) {
			return false;
		} else {
			alert("로그아웃 되었습니다.");
		}
	});
	
	$(document).ready(function() {
		$("#top_container").load("/commons/topForm")
	});
	
	
</script>

</body>
</html>