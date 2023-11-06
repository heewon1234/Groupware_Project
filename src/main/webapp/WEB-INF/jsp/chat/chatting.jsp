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
						<input id="loginID" value="${loginId }" style="border: none;"
							readonly>
					</div>
					<div class="position" value="${position }" readonly>대표이사</div>
				</div>
			</div>
		</div>
		<hr>
		<div class="chat_body" id="popupBody">
			<div class="left">
				<i class="fa-regular fa-user" onclick="openchatMember()"></i> <i
					class="fa-regular fa-comments" onclick="openchatGroupList()"></i><i
					class="fa-solid fa-bars" onclick="openchatGmember()"
					style="position: absolute; bottom: 0; margin-bottom: 40px;"></i>
			</div>
			<div class="right" id="roomList">
				<hr>
				<div class="allList">
					<select id="departmentSelect" style="margin-top: 10px">
						<option selected>부서 선택</option>
						<option value="영업부">영업부</option>
						<option value="관리부">관리부</option>
						<option value="회계부">회계부</option>
					</select>
					<!-- 부서별 목록 -->
					<div class="Glist">
						<div id="group_list"
							style="margin-top: 13px; height: 410px; overflow-y: auto;"></div>
					</div>
				</div>
				<!-- 친구 목록 -->
				<div class="chatMember_container" style="display: none;">
					<div class="chatBox">
						<div class="Thead"></div>
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
	// 전체 친구 목록
	$
			.ajax({
				type : "POST",
				url : "/members1/selectAll",
				dataType : "json",
				success : function(data) {
					var listData = data.list;
					var OneToOneChatDTOList = data.OneToOneChatDTOList;
					console.log(listData);
					try {
						//member의 리스트
						var $friend_list = $("#friend_list");
						if (listData.length > 0) {
							// 데이터가 존재하는 경우
							for (var i = 0; i < listData.length; i++) {
								var friend = listData[i];
								console.log(friend);

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
								/*// 클로저로 friend.name을 감싸고 openOneChat을 호출
								$nameCell.on("click", (function(name) {
									return function() {
										openOneChat(name,friend.organization,);
										console.log(name); // 콘솔에 이름 출력
									};
								})(friend.name));*/
								$nameCell.on("click", (function (clickedUserName, clickedUserId) {
								    return function () {
								        console.log(clickedUserName);
								        var loginID = $("#loginID").val();
								        var otherID = clickedUserId;

								        var oneSeq = null;
								        for (var i = 0; i < OneToOneChatDTOList.length; i++) {
								            var chatRoom = OneToOneChatDTOList[i];
								            if (chatRoom.loggedInUserID === loginID && chatRoom.otherUserID === otherID) {
								                oneSeq = chatRoom.oneSeq;
								                console.log(oneSeq);
								                break;
								            }
								        }
								        openOneChat(clickedUserName, friend.organization, oneSeq);
								    };
								})(friend.name, friend.id));
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

	
		//부서 목록 불러오기
		$("#departmentSelect").on("change", function() {
			var organization = $(this).val();
			console.log(organization);
			if (organization !== "부서 선택") {
				// 선택한 부서에 따라 회원 목록을 불러와서 표시하는 AJAX 요청을 보냅니다.
				loadMembersByDepartment(organization);
			}
		});
		function loadMembersByDepartment(organization) {
		    $.ajax({
		        type: "POST",
		        url: "/members1/getMembersByOrganization",
		        data: {
		            organization: organization
		        },
		        dataType: "json",
		        success: function(data) {
		            try {
		                var $group_list = $("#group_list"); // group_list에 데이터를 추가하기 위해 선택합니다.

		                if (data.length > 0) {
		                    $group_list.empty(); // 목록 초기화

		                    for (var i = 0; i < data.length; i++) {
		                        var friend = data[i];
		                        console.log(friend);

		                        // <div> 엘리먼트 생성
		                        var $row = $("<div>").addClass("table-row");

		                        // 나머지 부분은 이전과 동일
		                        var $iconCell = $("<div>")
		                            .html('<i class="fa-regular fa-circle-user"></i>')
		                            .css("display", "inline-block");
		                        $iconCell.on("click", function() {
		                            onOneChat();
		                        });
		                        $row.append($iconCell);

		                        var $nameCell = $("<div>").text(friend.name).addClass("oneChat")
		                            .css("display", "inline-block")
		                            .css("cursor", "pointer");
		                        $nameCell.on("click", (function (name) {
		                            return function () {
		                                openOneChat(name,friend.organization);
		                                console.log(name,friend.organization); // 콘솔에 이름 출력
		                            };
		                        })(friend.name));
		                        $row.append($nameCell);

		                        // $group_list에 $row 추가
		                        $group_list.append($row);
		                    }
		                }
		            } catch (error) {
		                // JSON 파싱 오류 처리
		                console.error("JSON 파싱 오류:", error);
		            }
		        },
		        error: function () {
		            console.log("회원 목록을 불러오지 못했습니다.");
		        }
		    });
		}


		// 전체 레코드 수
		$.ajax({
			type : "POST",
			url : "/chats/oneAllRecordCount",
			dataType : "json",
			success : function(data) {
				// 서버에서 반환한 데이터를 가져와서 HTML 엘리먼트에 표시합니다.
				$(".Thead").html("전체 " + data);
			},
			error : function() {
				// 요청 실패 시 처리
				console.log("새로운 레코드 수를 가져오지 못했습니다.");
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

		function openOneChat(friendName, organization) {
			// 이름을 쿼리 매개변수로 전달하여 페이지를 엽니다.
			$.get("/chats/inputText?friendName=" + encodeURIComponent(friendName) + "&organization=" + encodeURIComponent(organization), function(data) {
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
		//실시간 채팅
		var socket = new SockJS('/ws');
		var stompClient = Stomp.over(socket);

		stompClient.connect({}, function(frame) {
			stompClient.subscribe('/topic/oneToOne/{chatId}', function(response) {
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
		//일대일 채팅 메세지 전송
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
		        
		        var messageType = 'one';

		        // 메세지를 서버로 전송
		        stompClient.send('/app/oneToOne/sendMessage', {}, JSON.stringify({
		            type: messageType,
		            sender: sender,
		            message: message
		        }));

		        // 입력 필드 비우기
		        $('#inputText').empty();
		    }
		});

		//그룹 채팅방 만들기
		$(document).on(
				"click",
				"#plusButton",
				function() {
					var roomName = prompt("방 이름을 입력하세요");
					if (roomName) {
						// 사용자로부터 방 이름을 입력받고, ChatRoom 객체를 만듭니다.
						var chatRoom = {
							name : roomName
						};

						// 서버로 ChatRoom 객체를 보냅니다.
						stompClient.send("/app/createRoom", {}, JSON
								.stringify(chatRoom));
					}
				});

	</script>


</body>
</html>