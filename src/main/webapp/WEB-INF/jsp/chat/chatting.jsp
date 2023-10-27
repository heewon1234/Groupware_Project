<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatting</title>
<style>
/* 팝업을 감싸는 불투명 배경 스타일 */
.popup-background {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5); /* 불투명 배경 */
}

/* 팝업 스타일 */
.popup {
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

/* 팝업 내 닫기 버튼 스타일 */
.close-button {
	position: absolute;
	top: 10px;
	right: 10px;
	cursor: pointer;
}
</style>
</head>
<body>
	<button onclick="openPopup()">팝업 열기</button>

<!-- 팝업을 감싸는 불투명 배경 -->
<div class="popup-background" id="popupBackground"></div>

<!-- 실제 팝업 내용 -->
<div class="popup" id="popup">
    <span class="close-button" onclick="closePopup()">&times;</span>
    <h2>간단한 팝업</h2>
    <p>팝업 내용을 입력하세요.</p>
</div>

<script>
    function openPopup() {
        document.getElementById("popupBackground").style.display = "block";
        document.getElementById("popup").style.display = "block";
    }

    function closePopup() {
        document.getElementById("popupBackground").style.display = "none";
        document.getElementById("popup").style.display = "none";
    }
</script>
</body>
</html>