<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" type="text/css" href="/css/chat/messenger.css">
</head>
<body>
<!-- 실제 팝업 내용 -->
    <div class="popup1" id="popup1">
        <div class="top">
            <span class="close-button" onclick="closeMess()">&times;</span>
            <div id="logo">
                <div>logo</div>
                <div class="logout"><i class="fa-solid fa-right-from-bracket"></i></div>
            </div>
            <div class="profile">
                <div class="img">
                    <i class="fa-regular fa-circle-user"></i>
                </div>
                <div class="user">
                    <div class="name">대표이사</div>
                    <div class="state">근무종료</div>
                </div>
                <div class="bell"><i class="fa-regular fa-bell"></i></div>
            </div>
        </div>
        <hr>
        <div class="body">
            <div id="navi">
                <div class="navi-left">
                    <i class="fa-regular fa-user"></i>
                    <i class="fa-regular fa-comment-dots"></i>
                    <i class="fa-regular fa-folder"></i>
                    <i class="fa-solid fa-envelope"></i>
                </div>
                <div class="navi-right">
                    <i class="fa-solid fa-ellipsis" onclick="toggleMenu()"></i>
                </div>
            </div>
            <div class="menu">
                <div>
                    <div><i class="fa-regular fa-clipboard"></i></div>
                    <div class="title">게시판</div>
                </div>
                <div>
                    <div><i class="fa-regular fa-calendar-days"></i></div>
                    <div class="title">일정</div>
                </div>
                <div>
                    <div><i class="fa-regular fa-envelope"></i></div>
                    <div class="title">사내메일</div>
                </div>
                <div>
                    <div><i class="fa-solid fa-sitemap"></i></div>
                    <div class="title">인사</div>
                </div>
                <div>
                    <div><i class="fa-regular fa-comment-dots"></i></div>
                    <div class="title">메신저</div>
                </div>
                <div>
                    <div><i class="fa-regular fa-address-book"></i></div>
                    <div class="title">주소록</div>
                </div>
                <div>
                    <div><i class="fa-regular fa-clipboard"></i></div>
                    <div class="title">결재</div>
                </div>
            </div>
            <div class="logout"><i class="fa-solid fa-right-from-bracket"></i></div>
        </div>
    </div>
</body>
</html>