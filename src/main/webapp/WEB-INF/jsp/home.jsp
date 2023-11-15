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
			<form action="/members/login" method="post">
				<div class="loginBox">
					<div class="loginLogo">GroupWare</div>
					<div class="inputID">
						<i class="fa-regular fa-user loginIcon"></i> <input type="text"
							name="id" id="id" placeholder="input your ID" class="idbox">
					</div>
					<div class="inputPW">
						<i class="fa-solid fa-lock loginIcon"></i> <input type="password"
							name="pw" placeholder="input your PW" class="pwbox">
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
								<div class="main_icon_box">
									<i class="fa-regular fa-clipboard"></i>
								</div>
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
								<div class="main_icon_box">
									<i class="fa-regular fa-envelope"></i>
								</div>
								<div class="title">사내메일</div>
							</div>
							<div class="title">주소록</div>
						</div>
						<div>
							<a href="/approval/document/lists/all">
							<div class="main_icon_box">
								<i class="fa-regular fa-clipboard"></i>
							</div>
							<div class="title">결재</div>
							</a>
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
								<div class="main_icon_box">
									<i class="fa-regular fa-clipboard"></i>
								</div>
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
										출근하기<br> <br> <span id="currentinTime">00:00:00</span>
									</button>
									<button id="workout" onclick="workout()" disabled>
										퇴근하기<br> <br> <span id="currentoutTime">00:00:00</span>
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
						<div id="mailContainer">메일이 일정 내에 안될거같지만 일단은 메일 들어가는 자리입니다</div>
					</div>
					<div id="calendarContainer">
						<div class="date-wrap">
							<div class="date-month">
								<span id="month-this">2022.05</span>
								<div class="button_wrap">
									<button type="button" id="month-prev" class="month-move"
										data-ym="2022-04-01"><</button>
									<button type="button" id="month-next" class="month-move"
										data-ym="2022-06-01">></button>
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
									<tr>
										<td class="sun"><a>1</a></td>
										<td class=""><a href="/news/schedule?date=2022-05-02">2</a></td>
										<td class=""><a>3</a></td>
										<td class=""><a>4</a></td>
										<td class="today"><a>5</a></td>
										<td class=""><a>6</a></td>
										<td class="sat"><a>7</a></td>
									</tr>
									<!-- 행 반복 -->
									<tr>
										<td class="sun"><a>29</a></td>
										<td class=""><a>30</a></td>
										<td class=""><a>31</a></td>
										<td class=""><a></a></td>
										<td class=""><a></a></td>
										<td class=""><a></a></td>
										<td class="sat"></a></td>
									</tr>
								</tbody>
							</table>
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
	</script>
	<script src="/js/home/home.js"></script>
</body>
</html>