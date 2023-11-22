<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.5/dist/js.cookie.min.js"></script>

</head>
<body>
	<c:choose>
		<c:when test="${loginId==null }">
			<c:if test="${login == false}">
			    <script>
			    	console.log(${login});
			        alert("아이디 또는 비밀번호를 확인해주세요");
			    </script>
			</c:if>
			<form id="myForm" action="/members/login" method="post">
				<div class="loginBox">
					<div class="loginLogo">GroupWare</div>
					<div class="inputID">
						<i class="fa-regular fa-user loginIcon"></i> <input type="text"
							name="id" id="id" placeholder="input your ID" class="idbox">
					</div>
					<div class="inputPW">
						<i class="fa-solid fa-lock loginIcon"></i> <input type="password"
							name="pw" id="pw" placeholder="input your PW" class="pwbox">
					</div>
					<div class="rememberID">
						<input type="checkbox" id="remID">아이디 기억하기
					</div>
					<div class="loginButton">
						<button id="loginbtn">LOGIN</button>
					</div>
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<div class="homeContainer">
				<div id="top_container"></div>
				<div id="menu_contaier">
					<div id="menu" style="text-align: center;">
						<div id="menuList">
							<div>
								<a href="/board/toBoard">
									<div class="main_icon_box">
										<i class="fa-regular fa-clipboard"></i>
									</div>
								</a>
								<div class="title">게시판</div>
							</div>
							<div>
								<a href="/calendar/official">
									<div class="main_icon_box">
										<i class="fa-regular fa-calendar-days"></i>
									</div>
								</a>
								<div class="title">일정</div>
							</div>
							<div>
								<a href="/works/work_leave">
									<div class="main_icon_box">
										<i class="fa-solid fa-sitemap"></i>
									</div>
								</a>
								<div class="title">인사</div>
							</div>
							<div>
								<div class="main_icon_box">
									<i class="fa-regular fa-address-book"></i>
								</div>
								<div class="title">주소록</div>
							</div>
							<div>
								<a href="/approval/document/lists/all">
									<div class="main_icon_box">
										<i class="fa-regular fa-clipboard"></i>
									</div>
								</a>
								<div class="title">결재</div>
							</div>
						</div>
					</div>
				</div>
				<div id="contents">
					<div id="contents1">
						<div id="workcheckContainer">
							<div class="workcheckbox">
								<div class="timebox">
									<div>
										<span id="clock"></span> <span id="statusText"
											class="highlight">출근전</span>
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
								퇴근하기<div class="iconbox">
									<img id="work-out" class="img-disabled" src="/images/insa/work_leave/log-out.svg" alt="...">
								</div>  <span id="currentoutTime">00:00:00</span>
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
						<div class="blank"></div>
						<div id="mailContainer">
							<div>즐겨찾기 게시판</div>
							<c:choose>
								<c:when test="${empty favBoardList }">
									<div class="emptyFavBoardList">게시글이 존재하지 않습니다</div>
								</c:when>
								<c:otherwise>
									<c:forEach items="${favBoardList}" var="i">
										<div>
											<a href="/board/toContentsBoard?board_title=${i.board_title }&seq=${i.seq}">
											${i.title } 
											</a>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div id="calendarContainer">
						<div class="date-wrap">
							<div class="date-month" id="month">
								<div class="button_wrap">
									<img id="month-prev" class="month-move" src="/images/insa/work_plan/chevron-left.svg">
									<span id="month-this"></span>
									<img id="month-next" class="month-move" src="/images/insa/work_plan/chevron-right.svg">
								</div>
							</div>
							<table class="date-month">
								<thead>
									<tr>
										<th>일</th>
										<th>월</th>
										<th>화</th>
										<th>수</th>
										<th>목</th>
										<th>금</th>
										<th>토</th>
									</tr>
								</thead>
								<tbody id="tbl-month">
							
								</tbody>
							</table>
						</div>
						<div class="verticalLine"></div>
						<div id="scheduleList">
							<c:forEach var="lists" items="${list }">
								<div class="schedules">
									<div class="circle"></div>
									<div class="schedule">${lists.schedule }</div>
									<div class="startday">${lists.startday }</div>
								</div>
								<br>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>

	<script>
		$(document).ready(function() {
			$("#top_container").load("/commons/topForm");
			
			var loginId = "<c:out value="${loginId}"/>";
			if (loginId != "") {
                var link = document.createElement("link");
                link.rel = "stylesheet";
                link.type = "text/css";
                link.href = "/css/home/home.css";
                document.head.appendChild(link);
            } else {
                var link1 = document.createElement("link");
                link1.rel = "stylesheet";
                link1.type = "text/css";
                link1.href = "/css/members/login.css";
                document.head.appendChild(link1);
            }
		});
		
		if (${loginId == null}) {
			let inputID = document.getElementById("id");
			let remID = document.getElementById("remID");

			let userID = Cookies.get("remID");
			if (userID) {
				inputID.value = userID;
				remID.checked = true;
			}
			inputID.addEventListener("input", function() {
				if (remID.checked) {
					Cookies.set("remID", inputID.value, {
						expires : 7
					}); // 7일간 저장
				}
			});

			// 체크박스 상태에 따라 쿠키 업데이트
			remID.addEventListener("change", function() {
				if (remID.checked) {
					Cookies.set("remID", inputID.value, {
						expires : 7
					}); // 7일간 저장
				} else {
					Cookies.remove("remID"); // 쿠키 삭제
				}
			}); 
	    }
		
		document.getElementById('myForm').addEventListener('submit', function(event) {
			let id = document.getElementById("id");
			let pw = document.getElementById("pw");
			if (id == null || pw == null) {
			    alert("아이디와 비밀번호를 입력해주세요");
			    event.preventDefault();
			  }
		});
		
	</script>
	<script src="/js/home/home.js"></script>
</body>
</html>