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
<script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/2.3.3-1/stomp.min.js"></script>
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
					<div id="friend_list"
						style="margin-top: 13px; height: 410px; overflow-y: auto;"></div>
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
								<img alt="" src="/imges/chats/search.svg"> <input
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
		$
				.ajax({
					type : "POST",
					url : "/members1/selectAll",
					dataType : "json",
					success : function(data) {
						try {
							var $friend_list = $("#friend_list");

							if (data.length > 0) {
								// 데이터가 존재하는 경우
								for (var i = 0; i < data.length; i++) {
									var friend = data[i];

									// 각 <tr> 엘리먼트를 <div>로 래핑
									var $row = $("<div>").addClass("table-row");

									// 나머지 부분은 이전과 동일
									var $iconCell = $("<div>")
											.html(
													'<i class="fa-regular fa-circle-user"></i>')
											.css("display", "inline-block");
									$iconCell.on("click", function() {
										onOneChat();
									});
									$row.append($iconCell);

									var $nameCell = $("<div>")
											.text(friend.name).addClass(
													"oneChat").css("display",
													"inline-block").css(
													"cursor", "pointer");
									// 클로저로 friend.name을 감싸고 openOneChat을 호출
									$nameCell.on("click", (function(name) {
										return function() {
											openOneChat(name);
											console.log(name); // 콘솔에 이름 출력
										};
									})(friend.name));
									$row.append($nameCell);

									$friend_list.append($row);
								}
							}
						} catch (error) {
							// JSON 파싱 오류 처리
							console.error("JSON 파싱 오류:", error);
						}
					},
				});

		// 이름 클릭 이벤트 처리
		$(document).on("mouseenter", "#friend_list td.name", function() {
			// 마우스를 올렸을 때 스타일 변경
			$(this).css("text-decoration", "underline");
		}).on("mouseleave", "#friend_list td.name", function() {
			// 마우스를 내렸을 때 스타일 변경
			$(this).css("text-decoration", "none");
		});

		function openOneChat(friendName) {
			// 이름을 쿼리 매개변수로 전달하여 페이지를 엽니다.
			$.get("/chats/inputText?friendName="
					+ encodeURIComponent(friendName), function(data) {
				// data를 팝업 div에 추가하고 팝업을 표시합니다.
				var popup = document.createElement("div");
				popup.className = "oneChat popup";
				popup.style.width = "400px";
				popup.style.height = "650px";
				popup.style.display = "block";
				popup.style.position = "fixed";
				popup.style.top = "50%";
				popup.style.left = "50%";
				popup.style.transform = "translate(-50%, -50%)";
				popup.style.backgroundColor = "#fff";
				popup.style.padding = "20px";
				popup.style.boxShadow = "0 0 10px rgba(0, 0, 0, 0.2)";
				popup.style.zIndex = "1000";
				popup.innerHTML = data;

				// body 요소에 팝업 div 추가
				document.body.appendChild(popup);
			});
		}

		function closeOneChat() {
			// 팝업 닫기
			var popup = document.querySelector(".oneChat.popup");
			if (popup) {
				popup.style.display = "none";
				// body에서 팝업 div 제거
				document.body.removeChild(popup);
			}
		}
		function keepScrollBottom() {
			let contents = document.getElementsByClassName("chatForm")[0];
			contents.scrollTop = contents.scrollHeight;
		}
		var socket = new SockJS('/ws');
		var stompClient = Stomp.over(socket);

		stompClient.connect({}, function(frame) {
			stompClient.subscribe('/topic/chat', function(response) {
				console.log('Received message: ' + response.body);
				var message = JSON.parse(response.body);
				var messageText = message.message;

				// 타입이 'CHAT'일 때만 메시지를 화면에 표시
				if (message.type === 'CHAT') {
					var messageContainer = $("<p><strong>" + message.sender
							+ "</strong> - " + messageText + "</p>");
					$('.chatForm').append(messageContainer);
					keepScrollBottom();
				}
			});
		});
		// 메세지 전송
		$(document).on("click", "#sendBtn", function() {
    var message = $('#inputText').text().trim();
    if (message) {
        var sender = $('#loginID').val();
        var messageContainer = $("<p><strong>" + sender + "</strong> - " + message + "</p>");

        // 타입이 'CHAT'일 때만 메시지를 오른쪽에 표시
        if (message.type === 'CHAT') {
            messageContainer.addClass('right'); // 오른쪽으로 표시
        }

        $('.chatForm').append(messageContainer);
        keepScrollBottom();

        // 입력 필드 비우기
        $('#inputText').empty();
    }
});


		/*// ws
		var stompClient = null;

		function connect() {
			var socket = new SockJS('/ws');
			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				console.log('Connected: ' + frame);
			});
		}

		connect();

		$(document).on(
				"click",
				"#sendBtn",
				function() {
					var message = $("#inputText").text().trim();
					if (message) {
						stompClient.send("/app/chat/message", {}, JSON
								.stringify({
									'sender' : $("#loginID").val(),
									'content' : message,
									'type' : 'CHAT'
								}));

						// 메시지를 chatForm에 추가
						var messageContainer = $("<p>").html(
								"<strong></strong> - " + message);
						messageContainer.find("strong").text(
								$("#loginID").val()); // 발신자 설정

						$(".chatForm").append(messageContainer);
						keepScrollBottom();

						// 입력 필드 비우기
						$("#inputText").empty();
					}
				});*/

		/*const stompClient = Stomp.over(new SockJS('/ws')); // /ws는 엔드포인트 경로

		stompClient.connect({}, function(frame) {
		    console.log('Connected: ' + frame);
		    stompClient.subscribe('/topic/chat', function(response) {
		        const message = JSON.parse(response.body);
		        // 메시지 처리 로직을 구현
		        // 예를 들어, 수신한 메시지를 채팅 창에 표시하는 등의 로직을 추가하세요.
		        let lineDiv = $("<div>");
		        lineDiv.addClass("line");
		        lineDiv.attr("display", "none");

		        let chatDiv = $("<div>");
		        chatDiv.addClass("line-chat");
		        chatDiv.text(message.message); // 수신한 메시지 내용을 표시

		        let divTime = $("<div>").text(getCurrentTime());
		        divTime.addClass("time");
		        lineDiv.append(divTime);
		        lineDiv.append(chatDiv);

		        $(".chatForm").append(lineDiv);
		        keepScrollBottom();
		    });
		});

		$("#sendBtn").on("click", function () {
		    let msg = $(".inputText").text().trim(); // inputText의 내용 가져오기
		    if (msg !== "") {
		        let lineDiv = $("<div>");
		        lineDiv.addClass("line");
		        lineDiv.attr("display", "none");

		        let chatDiv = $("<div>");
		        chatDiv.addClass("line-chat");
		        chatDiv.text(msg); // 메시지 내용 설정

		        let divTime = $("<div>").text(getCurrentTime());
		        divTime.addClass("time");
		        lineDiv.append(divTime);
		        lineDiv.append(chatDiv);

		        $(".chatForm").append(lineDiv);

		        $(".inputText").text(""); // inputText 초기화
		        keepScrollBottom();

		        // sendMessage 함수를 호출하여 메시지를 전송
		        sendMessage(msg);
		    }

		    return false;
		});

		function sendMessage(message) {
		    stompClient.send("/app/chat", {}, JSON.stringify({ message: message }));
		}*/
	</script>


</body>
</html>