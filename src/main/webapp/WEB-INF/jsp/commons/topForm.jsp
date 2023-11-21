<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<!-- <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> -->
<link rel="stylesheet" type="text/css" href="/css/commons/topForm.css">
</head>
<body>
	<div id="topFormTop">
		<div class="topFormLogo">
			<a href="/"><img src="/images/commons/logo.png" alt="..."></a>
		</div>
		<div id="topFormMenuSelectDiv">
			오피스 홈 <i id="topFormMenuSelect" class="fa-solid fa-chevron-down"></i>
		</div>
		<div class="topFormIcon">
			<i class="fa-regular fa-comment" onclick="openPopup()"></i> <i
				class="fa-regular fa-bell"></i> <i class="fa-regular fa-circle-user"
				id="topFormLogout"></i>
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
				<img src="${userDTO.profile_image}" onerror="this.onerror=null; this.src='/images/commons/person-circle.svg';">
			</div>
			<div id="topFormProfileContents">
				<div>${userDTO.organization }</div>
				<div>${userDTO.position }(${userDTO.name })</div>
				<div>${userDTO.id }</div>
			</div>
		</div>
		<div class="topFormLogoutInBottom">
			<div style="margin: 5px;">
				<a href="/members/myInfo" style="font-size: 14px;">내 정보</a>
			</div>
			<div>
				<a href="/members/logout"><button id="topFormLogoutBtn" class="button_cancel">로그아웃</button></a>
			</div>
		</div>
	</div>
	<div id="topFormrealPopup"></div>


	<script>
		$(document).ready(function() {
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
</body>
</html>