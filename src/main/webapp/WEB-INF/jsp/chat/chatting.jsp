<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatting</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="test.css">
</head>
<body>
	<button onclick="openPopup()">팝업 열기</button>
	<!-- 실제 팝업 내용 -->
	<div class="popup" id="popup">
		<div class="top m-2 mb-0">

			<span class="close-button" onclick="closePopup()">&times;</span>
			<div id="logo" class="dragHandle">logo</div>
			<div class="profile">
				<div class="img">
					<i class="fa-regular fa-circle-user"></i>
				</div>
				<div class="user">
					<div class="name">대표이사</div>
					<div class="state">근무종료</div>
				</div>
			</div>
		</div>
		<hr>
		<div class="body">
			<div class="left">
				<i class="fa-regular fa-user"></i><i class="fa-regular fa-comment"></i><i
					class="fa-regular fa-comments"></i><i class="fa-solid fa-bars"
					style="position: absolute; bottom: 0; margin-bottom: 40px;"></i>
			</div>
			<div class="right">
				<hr>
				<div style="font-size: medium;">
					<div class="mt-2">전체 182</div>
					<div class="Tlist m-2">
						<div class="pimg">
							<i class="fa-regular fa-circle-user"></i>
						</div>
						<div class="person mx-1">
							<div class="name">강대리</div>
							<div class="department">총무부</div>
						</div>
					</div>
					<hr>
					<select id="departmentSelect" class="mt-2">
						<option selected>부서 선택</option>
						<option value="부서1">부서1</option>
						<option value="부서2">부서2</option>
						<option value="부서3">부서3</option>
					</select>

					<div class="Glist m-2">
						<div class="mt-2"></div>
						<div class="pimg">
							<i class="fa-regular fa-circle-user"></i>
						</div>
						<div class="person mx-1">
							<div class="name">이과장</div>
							<div class="department">총무부</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="test.js" type="text/javascript"></script>

</body>
</html>