<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 삭제</title>
<link rel="stylesheet" href="/css/board/delContents.css" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="text">
			정말 삭제하시겠습니까?
		</div>
		<hr>
		<div class="btns">
			<button id="delContentsBtn">삭제</button>
			<button id="cancelBtn">취소</button>
		</div>
	</div>
	<script src="/js/board/delBoard.js" defer></script>
</body>
</html>