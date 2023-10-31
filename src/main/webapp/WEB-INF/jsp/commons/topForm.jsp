<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/commons/topForm.css">
</head>
<body>
    <div id="top">
        <div class="logo">logo</div>
        <div class="icon">
            <i class="fa-regular fa-comment" onclick="openPopup()"></i>
            <i class="fa-regular fa-bell"></i>
            <i class="fa-regular fa-circle-user"></i>
        </div>
    </div>
    <div id="realPopup"></div>
    
	
	<script>
        $(document).ready(function() {
        	$("#realPopup").load("/chats/chatting");
        });
    </script>
</body>
</html>