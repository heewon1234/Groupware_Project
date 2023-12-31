<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KakaoTalk(group)</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<style>
.myProfile {
	display: flex;
	align-items: center; /* 세로 중앙 정렬 */
}

.myimg i {
	font-size: 40px;
}

.other {
	display: flex;
	flex-direction: column; /* 내부 요소들을 세로로 나열 */
	margin-left: 10px; /* 내부 요소 사이의 간격 조정 */
}

#otherName {
	font-size: medium;
	font-weight: bold;
	margin-left: -3px;
}

#organization {
	font-size: small;
}

.inputTop {
	height: 10%
}
</style>
</head>
<body>
	<div class="box" id="inputJSP">
		<input id="userName" type="hidden" value="${name}"> <input
			id="loginID" type="hidden" value="${loginId}"> <input
			id="oneSeq" value="${groupSeq}">
		<div class="inputTop" style="padding-top: 10px; padding-left: 10px">
			<span class="close-button" onclick="closeOneChat()">&times;</span>
			<div class="myProfile">
				<div class="myimg">
					<i class="fa-regular fa-comment"></i>
				</div>
				<div class="other">
					<input id="otherName" value="${groupName}" style="border: none;"
						disabled="disabled"> <!-- <input id="organization"
						value="${organization} " style="border: none;" disabled="disabled"> -->
				</div>
			</div>
		</div>
		<div class="chatForm" style="position: relative;"></div>
		
		<div id="messageForm">
			<div class="chatBox">
				<div class="inputText" id="inputText" contenteditable="true"
					style="padding: 10px"></div>
				<div>
					<button id="sendBtn">전송</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>