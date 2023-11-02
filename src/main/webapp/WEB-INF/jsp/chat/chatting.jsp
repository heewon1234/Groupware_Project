<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatting</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<link rel="stylesheet" type="text/css" href="/css/chat/chatting.css">
<link rel="stylesheet" type="text/css" href="/css/chat/inputText.css">
<link rel="stylesheet" type="text/css" href="/css/chat/chatList.css">
</head>
<body>
	<!-- 실제 팝업 내용 -->
	<div class="popup" id="popup">
		<div class="top">
			<span class="close-button" onclick="closePopup()">&times;</span>
			<div id="logo" class="dragHandle">logo</div>
			<div class="profile">
				<div class="img">
					<i class="fa-regular fa-circle-user"></i>
				</div>
				<div class="user">
					<div class="name">
						<input id="loginID" type="hidden" value="${loginID }">
					</div>
					<div class="department">대표이사</div>
				</div>
			</div>
		</div>
		<hr>
		<div class="chat_body" id="popupBody">
			<div class="left">
				<i class="fa-regular fa-user" onclick="openchatMember()"></i> <i
					class="fa-regular fa-comment" onclick="openchatList()"></i> <i
					class="fa-regular fa-comments" onclick="openchatGroupList()"></i><i
					class="fa-solid fa-bars"
					style="position: absolute; bottom: 0; margin-bottom: 40px;"></i>
			</div>
			<div class="right" id="roomList">
				<hr>
				<div class="allList">
					<div class="Thead">전체 182</div>
					<div class="Tlist">
						<div class="pimg">
							<i class="fa-regular fa-circle-user"></i>
						</div>
						<div class="person">
							<div class="name">강대리</div>
							<div class="department">총무부</div>
						</div>
					</div>
					<hr>
					<select id="departmentSelect" class="Thead">
						<option selected>부서 선택</option>
						<option value="부서1">부서1</option>
						<option value="부서2">부서2</option>
						<option value="부서3">부서3</option>
					</select>

					<div class="Glist">
						<div class="pimg">
							<i class="fa-regular fa-circle-user"></i>
						</div>
						<div class="person">
							<div class="name">이과장</div>
							<div class="department">총무부</div>
						</div>
					</div>
				</div>
				<!-- 친구 목록 -->
				<div class="chatMember_container" style="display: none;">
					<div class="chatBox">
						<div id="font">친구</div>
						<div class="search_add">
							<img id="searchBtn" alt="" src="/imges/chats/search.svg"
								onclick="showSearchContainer()">
						</div>
					</div>

					<div class="search_container" style="display: none;">
						<div class="chatroom_search">
							<div id="searchBox">
								<img alt="" src="/imges/chats/search.svg""> <input
									id="search_input" type="text" placeholder="채팅방 참여자 검색">
								<img id="x" alt="" src="/imges/chats/x.svg"
									onclick="hideSearchContainer()">
							</div>
						</div>
					</div>
					<div id="friend_list" style="margin-top: 13px; height: 410px; overflow-y: auto;"></div>
				</div>
				<!-- 1대1 채팅 -->
				<div class="chatRoom_container" style="display: none;">
					<div class="chatBox">
						<div id="font">채팅</div>
						<div class="search_add">
							<img id="searchBtn" alt="" src="/imges/chats/search.svg"
								onclick="showSearchContainer()"> <img id="plusButton"
								alt="" src="/imges/chats/plus.svg" onclick="showAlert()">
						</div>

					</div>
					<div class="search_container" style="display: none;">
						<div class="chatroom_search">
							<div id="searchBox">
								<img alt="" src="/imges/chats/search.svg""> <input
									id="search_input" type="text" placeholder="채팅방 참여자 검색">
								<img id="x" alt="" src="/imges/chats/x.svg"
									onclick="hideSearchContainer()">
							</div>
						</div>
					</div>
					<!-- ajax -->
					<div class="chatroom_list" style="margin-top: 8px"></div>
				</div>
				<!-- 그룹 채팅 -->
				<div class="chatGroupRoom_container" style="display: none;">
					<div class="chatBox">
						<div id="font">그룹 채팅</div>
						<div class="search_add">
							<img id="searchBtn" alt="" src="/imges/chats/search.svg"
								onclick="showSearchContainer()"> <img id="plusButton"
								alt="" src="/imges/chats/plus.svg" onclick="showAlert()">
						</div>

					</div>
					<div class="search_container" style="display: none;">
						<div class="chatroom_search">
							<div id="searchBox">
								<img alt="" src="/imges/chats/search.svg""> <input
									id="search_input" type="text" placeholder="채팅방 참여자 검색">
								<img id="x" alt="" src="/imges/chats/x.svg"
									onclick="hideSearchContainer()">
							</div>
						</div>
					</div>
					<div class="chatroom_list"></div>
				</div>
			</div>
		</div>

	</div>
	<script src="/js/chat/chatting.js" type="text/javascript"></script>
	<script src="/js/chat/inputText.js" type="text/javascript"></script>
	<script>
		// 친구 목록
		$.ajax({
    type: "POST",
    url: "/members1/selectAll",
    dataType: "json",
    success: function(data) {
        try {
            var $friend_list = $("#friend_list");

            if (data.length > 0) {
                // 데이터가 존재하는 경우
                for (var i = 0; i < data.length; i++) {
                    var friend = data[i];

                 // 각 <tr> 엘리먼트를 <div>로 래핑
                    var $row = $("<div>").addClass("table-row");

                    // 나머지 부분은 이전과 동일
                    var $iconCell = $("<div>").html('<i class="fa-regular fa-circle-user"></i>').css("display", "inline-block");
                    $iconCell.on("click", function() {
                        onOneChat();
                    });
                    $row.append($iconCell);

                    var $nameCell = $("<div>").text(friend.name).addClass("oneChat").css("display", "inline-block");
                    $nameCell.on("click", function() {
                        onOneChat();
                    });
                    $row.append($nameCell);

                    $friend_list.append($row);

                }
            }
        } catch (error) {
            // JSON 파싱 오류 처리
            console.error("JSON 파싱 오류:", error);
        }
    },
    error: function(xhr, status, error) {
        // 요청 실패 처리
        console.error("요청 실패:", status, error);
    }
});
		// 이름 클릭 이벤트 처리
		$(document).on("mouseenter", "#friend_list td.name", function() {
		    // 마우스를 올렸을 때 스타일 변경
		    $(this).css("text-decoration", "underline");
		}).on("mouseleave", "#friend_list td.name", function() {
		    // 마우스를 내렸을 때 스타일 변경
		    $(this).css("text-decoration", "none");
		});
		
		function onOneChat() {
		    // 클릭된 엘리먼트에 접근
		    var clickedElement = $(this);

		    // 클래스 이름이 oneChat인 엘리먼트를 클릭했는지 확인
		    if (clickedElement.hasClass("oneChat")) {
		        // 클릭된 엘리먼트에 대한 추가 동작 수행
		        // 예를 들어, 클릭된 엘리먼트의 텍스트 가져오기
		        var name = clickedElement.text();
				console.log(name);
		        // 이제 name 또는 다른 정보를 사용하여 추가 작업을 수행할 수 있습니다.
		    }
		}


	</script>
	<!-- <script>
    let ws = new WebSocket("ws://13.125.213.118/chats");

    const currentUserId = $("#loginID").val();
    console.log(currentUserId);

    ws.onmessage = function(e) {
        let data = JSON.parse(e.data);
        console.log(data);
        console.log(Array.isArray(data));
        if (Array.isArray(data)) {
            for (let i = 0; i < data.length; i++) {
                let messageContainer = $("<div>");

                if (data[i].writer === currentUserId) {
                    messageContainer.addClass("right");
                } else {
                    messageContainer.addClass("left");
                }

                messageContainer.append(data[i].writer + " : " + data[i].message+"<br>");
                messageContainer.append(data[i].write_date);
                console.log(data[i].write_date);
                $(".chatForm").append(messageContainer);
                $(".chatForm").append("<br>");
                $(".chatForm").append("<br>");
                keepScrollBottom();
            }
        } else {
            let messageContainer = $("<div>");

            if (data.writer === currentUserId) {
                messageContainer.addClass("right");
            } else {
                messageContainer.addClass("left");
            }

            messageContainer.append(data.writer + " : " + data.message);
            messageContainer.append(data.write_date);
            $(".chatForm").append(messageContainer);
            $(".chatForm").append("<br>");
            keepScrollBottom();
        }
    }

    
	$(".input").on("keydown", function(e) {
		if (e.keyCode == 13) {
			let msg = $("#input").html();
			$("#input").html("");
			ws.send(msg);
			 $(".chatForm").append("<br>"); 
			return false;
		}
	});
        function keepScrollBottom() {
            let contents = document.getElementsByClassName("contents")[0];
            contents.scrollTop = contents.scrollHeight;
        }
        function getCurrentTime() {
            const now = new Date();
            const hours = now.getHours();
            const minutes = String(now.getMinutes()).padStart(2, '0');
            const ampm = hours >= 12 ? '오후' : '오전';
            const displayHours = hours % 12 === 0 ? 12 : hours % 12;
            return `${ampm} ${displayHours}:${minutes}`;
        }
        
        function sendMessage() {
            let msg = $(".chatForm").html().trim(); 
            if (msg !== "") {
                let lineDiv = $("<div>");
                lineDiv.addClass("line");
                lineDiv.attr("display", "none");

                let chatDiv = $("<div>");
                chatDiv.addClass("line-chat");
                chatDiv.html(msg);

                let divTime = $("<div>").text(getCurrentTime());
                divTime.addClass("time");
                lineDiv.append(divTime);
                lineDiv.append(chatDiv);

                $(".chatForm").append(lineDiv);

                $(".chatForm").html("");
                keepScrollBottom();
            }

            return false; 
        }
        $("#sendBtn").on("click", function () {
            sendMessage();
        });

        $(".chatForm").on("keydown", function (event) {
            setTimeout(function () {
                console.log($(this).text());
                console.log($(this).html());
                if ($(this).html().trim() !== "") {
                    $("#sendBtn").css("background-color", "#ffeb3f");
                } else {
                    $("#sendBtn").css("background-color", "");
                }
            }.bind(this), 0);
            if (event.key === "Enter" && !event.shiftKey) {
                event.preventDefault();
                sendMessage();
                $(this).text("");
                $("#sendBtn").css("background-color", "");
            }

        });

        $("#remBtn").on("click", function () {
            $(".container").remove();
        });



    </script> -->

</body>
</html>