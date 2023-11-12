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

<c:choose>
	<c:when test="${loginId==null}">
		<link rel="stylesheet" type="text/css" href="/css/members/login.css">
	</c:when>
	<c:otherwise>
		<link rel="stylesheet" type="text/css" href="/css/home/home.css">
	</c:otherwise>
</c:choose>
</head>
<body>
	<c:choose>
		<c:when test="${loginId==null }">
			<form action="/members/login" method="post">
				<div class="loginBox">
					<div class="logo">GroupWare</div>
					<div class="inputID">
						<i class="fa-regular fa-user icon"></i> <input type="text"
							name="id" id="id" placeholder="input your ID" class="idbox">
					</div>
					<div class="inputPW">
						<i class="fa-solid fa-lock icon"></i> <input type="password"
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
			<div class=homeContainer>
				<div id="top_container"></div>
				<div id="menu_contaier">
					<div id="menu" style="text-align: center;">
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
						<div>
							<div class="main_icon_box">
								<i class="fa-solid fa-sitemap"></i>
							</div>
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
		</c:otherwise>
	</c:choose>

	<script>
		$(document).ready(function() {
			$("#top_container").load("/commons/topForm");
		});
		
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
	</script>
</body>
</html>