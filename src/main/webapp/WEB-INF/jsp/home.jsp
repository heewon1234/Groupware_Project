<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/home/home.css">
</head>
<body>
	<div class=homeContainer>
		<div id="top_container"></div>
		<div id="menu_contaier">
			<div id="menu">
				<div>
					<div>
						<i class="fa-regular fa-clipboard"></i>
					</div>
					<div class="title">게시판</div>
				</div>
				<div>
					<div>
						<i class="fa-regular fa-calendar-days"></i>
					</div>
					<div class="title">일정</div>
				</div>
				<div>
					<div>
						<i class="fa-regular fa-envelope"></i>
					</div>
					<div class="title">사내메일</div>
				</div>
				<div>
					<div>
						<i class="fa-solid fa-sitemap"></i>
					</div>
					<div class="title">인사</div>
				</div>
				<div>
					<div>
						<i class="fa-regular fa-comment-dots" onclick="openPopup1()"></i>
					</div>
					<div class="title">메신저</div>
				</div>
				<div>
					<div>
						<i class="fa-regular fa-address-book"></i>
					</div>
					<div class="title">주소록</div>
				</div>
				<div>
					<div>
						<i class="fa-regular fa-clipboard"></i>
					</div>
					<div class="title">결재</div>
				</div>
			</div>
		</div>
		<div id="messenger_container"></div>
	</div>
<script src="/js/chat/messenger.js" type="text/javascript"></script>

	<script>
        $(document).ready(function() {
        	$("#top_container").load("/commons/topForm");
        	$("#messenger_container").load("/chats/messenger");
        });
    </script>
</body>
</html>