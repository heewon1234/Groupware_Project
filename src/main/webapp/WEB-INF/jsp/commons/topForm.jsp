<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <!-- <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> -->
    <link rel="stylesheet" type="text/css" href="/css/commons/topForm.css">
</head>
<body>
    <div id="top">
        <div class="logo">logo</div>
        <div class="icon">
            <i class="fa-regular fa-comment" onclick="openPopup()"></i>
            <i class="fa-regular fa-bell"></i>
            <i class="fa-regular fa-circle-user" id="logout"></i>
        </div>
    </div>
    <div class="logout" style="display:none;"><a href="/members/logout"><button id="logoutbtn">LOGOUT</button></a></div>
    <div id="realPopup"></div>
    
    
	
	<script>
        $(document).ready(function() {
        	$("#realPopup").load("/chats/chatting");
        	
        	let logout = false;
        	
        	$("#logout").on("click",function(){
        		if(logout == false) {
        			$(".logout").css("display","flex");
                	logout = true;
        		} else {
        			$(".logout").css("display","none");
                	logout = false;
        		}
            });
        });
        
        $("#logoutbtn").on("click",function(){
    		let memberout = confirm("로그아웃 하시겠습니까?");
    		if(!memberout){
    			return false;
    		} else {
    			alert("로그아웃 되었습니다.");
    		}
    	});
        
    </script>
</body>
</html>