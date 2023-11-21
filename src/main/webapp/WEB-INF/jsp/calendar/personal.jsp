<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>Personal Calendar</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<link rel="stylesheet" type="text/css" href="/css/calendar/calendar.css">
<link rel="stylesheet" href="/css/commons/topForm.css" />
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

<style>
#personal {
	background-color: #DAE8F8;
	color: #056AC9;
}
</style>


</head>
<body>
	<div class="top_form">
		<div id="top_container">
			<div id="topFormTop">
				<div class="topFormLogo">
					<a href="/"><img src="/images/commons/logo.png" alt="..."></a>
				</div>
				<div id="topFormMenuSelectDiv">
					오피스 홈 <i id="topFormMenuSelect" class="fa-solid fa-chevron-down"></i>
				</div>
				<div class="topFormIcon">
					<i class="fa-regular fa-comment" onclick="openPopup()"></i> <i
						class="fa-regular fa-bell"></i> <i
						class="fa-regular fa-circle-user" id="topFormLogout"></i>
				</div>
			</div>
			<div id="topFormMenuCollection" style="display: none;">
				<div>전체메뉴</div>
				<div id="topFormSelectMenu">
					<div id="topFormLeft">
						<div class="topFormMenu">
							<a href="/board/toBoard">
								<i class="fa-regular fa-clipboard menuIcon"></i> 게시판
							</a>
						</div>
						<div class="topFormMenu">
							<a href="/calendar/official"> <i
								class="fa-regular fa-calendar-days menuIcon"></i> 일정
							</a>
						</div>
						<div class="topFormMenu">
							<a href="/works/work_leave"> <i
								class="fa-solid fa-sitemap menuIcon"></i> 인사
							</a>
						</div>
					</div>
					<div id="topFormRight">
						<div class="topFormMenu">
							<i class="fa-regular fa-address-book menuIcon"></i> 주소록
						</div>
						<div class="topFormMenu">
							<a href="/approval/document/lists/all"> <i
								class="fa-regular fa-clipboard menuIcon"></i> 결재
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="topFormLogout" style="display: none;">
				<div id="topFormProfile">
					<div id="topFormProfileImg">
						<img src="${userDTO.profile_image}">
					</div>
					<div id="topFormProfileContents">
						<div>${userDTO.organization }${userDTO.position }(${userDTO.name })</div>
						<div style="margin-top: 3px; text-align: left;">${userDTO.id }</div>
					</div>
				</div>
				<div class="topFormLogoutInBottom">
					<div style="margin: 5px;">
						<a href="/members/myInfo" style="font-size: 14px;">내 정보</a>
					</div>
					<div>
						<a href="/members/logout"><button id="topFormLogoutBtn">로그아웃</button></a>
					</div>
				</div>
			</div>
			<div id="topFormrealPopup"></div>

		</div>
	</div>
	<div class="body_form">
		<!-- 왼쪽 부분(메뉴) -->
		<div class="left_item">
			<div class="button_tab">
				<button class="main_button">
					<span>일정 추가</span>
				</button>
			</div>
			<div class="menu_tab">
				<div class="menu_item" id="official">
					<img
						src="/images/commons/body_form/left_item/default/favorites.png" />
					<span class="menu_item_text">회사 일정</span>
				</div>

				<div class="menu_item" id="personal">
					<img class="menu_item_img"
						src="/images/commons/body_form/left_item/default/information.png" />
					<span class="menu_item_text">개인 일정</span>
				</div>
			</div>
		</div>

		<!-- 오른쪽 부분(해당 메뉴의 주요 콘텐츠) -->
		<div class="right_item">
			<div id="calendarBox">
				<div id="calendar"></div>
			</div>

			<div class="modal fade" id="calendarModal" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">일정을 입력하세요.</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form action="/calendar/pcreate" method="post" onsubmit="return validateForm()">
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
</body>

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
			eventClick: function (info){
                if(confirm("'"+ info.event.title +"' 일정을 삭제하시겠습니까 ?")){
                    // 확인 클릭 시
                    info.event.remove();
                    
                    var seq = info.event.extendedProps.seq;
                    
                    $(function deleteData(){
                        $.ajax({
                            url: "/calendar/pdelete",
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
                        url: "/calendar/pupdate",
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
	
	function validateForm() {
		var content = $("#calendar_content").val();
		var start_date = $("#calendar_start_date").val();
		var end_date = $("#calendar_end_date").val();

		if (content == null || content == "") {
			return false;
		} else if (start_date == "" || end_date == "") {
			return false;
		} else if (new Date(end_date) - new Date(start_date) < 0) { // date 타입으로 변경 후 확인
			return false;
		} else { // 정상적인 입력 시
			return true;
		}
    }
	
	$(".main_button").on("click",function(){
		$("#calendarModal").modal("show");
		
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
	});
	
	$(document).ready(function() {
		$('#official').click(function() {
			location.href = "/calendar/official";
		});
		
		$('#personal').click(function() {
			location.href = "/calendar/personal";
		});
		
		$("#topFormrealPopup").load("/chats/chatting");

		let logout = false;

		$("#topFormLogout").on("click", function() {
			if (logout == false) {
				$(".topFormLogout").css("display", "");
				logout = true;
			} else {
				$(".topFormLogout").css("display", "none");
				logout = false;
			}
		});

		let menu = false;

		$("#topFormMenuSelectDiv").on("click", function() {
			if (menu == false) {
				$("#topFormMenuCollection").css("display", "flex");
				menu = true;
			} else {
				$("#topFormMenuCollection").css("display", "none");
				menu = false;
			}
		});
	});
	
	$("#topFormLogoutbtn").on("click", function() {
		let memberout = confirm("로그아웃 하시겠습니까?");
		if (!memberout) {
			return false;
		} else {
			alert("로그아웃 되었습니다.");
		}
	});
	
</script>
</html>