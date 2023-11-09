<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatting</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<!-- <script src="https://code.jquery.com/jquery-3.7.0.js"></script>  -->
<link rel="stylesheet" type="text/css" href="/css/chat/chatting.css">
<link rel="stylesheet" type="text/css" href="/css/chat/inputText.css">
<link rel="stylesheet" type="text/css" href="/css/chat/chatList.css">
<script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/2.3.3-1/stomp.min.js"></script>

<style>
#groupUserModal {
	display: none;
}
/* Define your modal styles here */
.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: #fff;
	padding: 20px;
}

.close {
	position: absolute;
	right: 10px;
	top: 10px;
	cursor: pointer;
}

.selected {
	background-color: lightblue;
	cursor: pointer;
}
</style>
</head>
<body>

	<!-- 실제 팝업 내용 -->
	<div class="popup" id="popup">
		<input id="loginID" type="hidden" value="${loginId }">
		<div class="top">
			<span class="close-button" onclick="closePopup()">&times;</span>
			<div id="logo" class="dragHandle">logo</div>
			<div class="profile">
				<div class="img">
					<i class="fa-regular fa-circle-user"></i>
				</div>
				<div class="user">
					<div class="name">
						<input id="name" value="${name }" style="border: none;" readonly>
					</div>
					<div class="position">
						<input id="position" value="${position }" style="border: none;"
							readonly>
					</div>
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
							<img id="searchBtn" alt="" src="/images/chats/search.svg"
								onclick="showSearchContainer()">
						</div>
					</div>

					<div class="search_container" style="display: none;">
						<div class="chatroom_search">
							<div id="searchBox">
								<img alt="" src="/images/chats/search.svg""> <input
									id="search_input" type="text" placeholder="채팅방 참여자 검색">
								<img id="x" alt="" src="/images/chats/x.svg"
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
							<img id="searchBtn" alt="" src="/images/chats/search.svg"
								onclick="showSearchContainer()"> <img id="plusButton"
								alt="" src="/images/chats/plus.svg">
						</div>

					</div>
					<div class="search_container" style="display: none;">
						<div class="chatroom_search">
							<div id="searchBox">
								<img alt="" src="/images/chats/search.svg"> <input
									id="search_input" type="text" placeholder="채팅방 참여자 검색">
								<img id="x" alt="" src="/images/chats/x.svg"
									onclick="hideSearchContainer()">
							</div>
						</div>
					</div>
					<div class="chatroom_list" style="margin-top: 13px; height: 410px; overflow-y: auto;"></div>
				</div>
			</div>
		</div>
		<!-- 그룹 선택 모달 창 -->
		<div id="groupUserModal" class="modal">
			<div class="modal-content" style="width: 300px; height: 500px;">
				<span class="close" id="groupModalClose">&times;</span>
				<h2>사용자 선택</h2>
				<ul id="groupUserList">
					<!-- User list will be dynamically added here -->
				</ul>
				<button id="selectUsersButton">선택 완료</button>
				<!-- 선택 완료 버튼 추가 -->
			</div>
		</div>

	</div>
	<script src="/js/chat/chatting.js" type="text/javascript"></script>
	<script src="/js/chat/inputText.js" type="text/javascript"></script>
	<script>
	function generateUUID() {
        // 서버에 UUID를 요청하는 AJAX
        $.ajax({
            url: "/generateUUID", // 해당 URL은 컨트롤러의 RequestMapping에 맞게 변경
            type: "GET",
            success: function (data) {
                // 성공 시 결과를 표시
                $("#uuidResult").text("Generated UUID: " + data);
            },
            error: function () {
                // 실패 시 오류 메시지 표시
                $("#uuidResult").text("Failed to generate UUID.");
            }
        });
    }

	updateGroupChatList();
	//그룹 채팅방 만들기
	   // 모달 열기
	<!-- 모달 열기 -->
	function openGroupModal() {
	    $("#groupUserModal").css("display", "block");
	}

	<!-- 모달 닫기 */
	function closeGroupModal() {
	    $("#groupUserModal").css("display", "none");
	}

	var pendingRoomName = null; // 방 이름을 일시적으로 저장할 변수
	var selectedUsers = []; // 선택한 사용자를 저장할 배열

	<!-- plusButton 클릭 이벤트 처리 -->
	$("#plusButton").on("click", function() {
	    pendingRoomName = prompt("방 이름을 입력하세요");
	    if (pendingRoomName) {
	        openGroupModal();
	    }
	});

	<!-- groupModalClose 클릭 이벤트 처리 -->
	$("#groupModalClose").on("click", closeGroupModal);

	<!-- 예제로 사용자와 체크박스를 모달에 추가 */
	$("#groupUserList").append('<li><input type="checkbox" id="user1"> GroupUser1</li>');
	$("#groupUserList").append('<li><input type="checkbox" id="user2"> GroupUser2</li>');
	$("#groupUserList").append('<li><input type="checkbox" id="user3"> GroupUser3</li');

	<!-- 사용자 선택 및 로직 처리 */
	$("#groupUserList").on("change", "input[type=checkbox]", function() {
	    var userId = $(this).attr("id");
	    if ($(this).is(":checked")) {
	        selectedUsers.push(userId);
	        $(this).parent().addClass("selected");
	    } else {
	        var index = selectedUsers.indexOf(userId);
	        if (index !== -1) {
	            selectedUsers.splice(index, 1);
	        }
	        $(this).parent().removeClass("selected");
	    }
	});

	<!-- 선택한 사용자를 처리할 함수 */
	function handleSelectedUsers() {
	    if (selectedUsers.length === 0) {
	        alert("사용자를 선택하세요.");
	        return;
	    }
	    if (!pendingRoomName) {
	        alert("방 이름을 입력하세요.");
	        return;
	    }

	    // 서버에 UUID를 요청하는 AJAX
	    $.ajax({
	        url: "/generateUUID",
	        type: "GET",
	        success: function (data) {
	            // 성공 시 결과를 표시
	            var generatedUUID = data;

	            for (var i = 0; i < selectedUsers.length; i++) {
	                var roomInfo = {
	                    groupSeq: generatedUUID,
	                    groupName: pendingRoomName,
	                    memberName: selectedUsers[i]
	                };
	                stompClient.send("/app/group/sendMessage", {}, JSON.stringify(roomInfo));
	            }

	            clearSelectedCheckboxes();
	            closeGroupModal();
	        },
	        error: function () {
	            // 실패 시 오류 메시지 표시
	            alert("Failed to generate UUID.");
	        }
	    });
	}

	function clearSelectedCheckboxes() {
	    selectedUsers = []; // Clear the selected users array
	    $("#groupUserList input[type=checkbox]").prop("checked", false); // Uncheck all checkboxes
	    $("#groupUserList li").removeClass("selected"); // Remove the "selected" class
	}
	// "선택 완료" 버튼 클릭 시 선택한 사용자 처리
// "선택 완료" 버튼 클릭 시 선택한 사용자 처리
$("#selectUsersButton").on("click", function() {
    handleSelectedUsers();

    // 여기에서 원하는 ajax 요청을 추가하세요.
    updateGroupChatList();
});

// 그룹방을 보여주는 ajax
function updateGroupChatList() {
    $.ajax({
        type: "POST",
        url: "/groupChatRooms/groupSelectAll",
        dataType: "json",
        success: function(data) {
            // 성공 시 실행되는 콜백 함수
            console.log("group방 업데이트" + data);
            var $chatroom_list = $(".chatroom_list");

            // 데이터를 비우고 새로 받아온 데이터로 업데이트
            $chatroom_list.empty();

            // groupName을 사용하여 각 그룹을 friend_list에 추가
            data.forEach(function(group) {
                var $row = $("<div>").addClass("table-row");

                // 그룹 아이콘 (여기서는 임의로 설정한 아이콘 사용)
                var $iconCell = $("<div>")
                        .html('<i class="fa-regular fa-comment"></i>')
                        .css("display", "inline-block");

                // 그룹 이름을 표시하는 셀
                var $nameCell = $("<div>")
                        .text(group.groupName)
                        .addClass("groupChat")
                        .css("display", "inline-block")
                        .css("cursor", "pointer");

                // 클릭 이벤트 처리
                $nameCell.on("click", function() {
                    // 클릭 시 실행할 로직 추가
                    console.log("Clicked group:", group.groupName);
                    groupChat(group.groupName, group.groupSeq);
                    console.log(group.groupSeq);
                });

                // 행에 아이콘과 그룹 이름 셀을 추가
                $row.append($iconCell);
                $row.append($nameCell);

                // friend_list에 행 추가
                $chatroom_list.append($row);
            });

            // 여기서 받아온 데이터(data)를 사용하여 화면에 표시하는 로직을 추가할 수 있음
        },
        error: function(xhr, status, error) {
            // 실패 시 실행되는 콜백 함수
            console.error("AJAX request failed:", status, error);
        }
    });
}

function groupChat(groupName, groupSeq) {
    // 페이지를 열 때 그룹 이름과 그룹 시퀀스를 쿼리 매개변수로 전달합니다.
    $.get("/chats/groupInputText", {
        groupName: groupName,
        groupSeq: groupSeq
    }, function(data) {
        // 받은 데이터로 팝업 창을 생성하고 body에 추가합니다.
        var popup = document.createElement("div");
        popup.className = "groupChat popup";
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
	
	//----------------------------
	function keepScrollBottom() {
        let contents = document.getElementsByClassName("chatForm")[0];
        contents.scrollTop = contents.scrollHeight;
     }
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
									$nameCell
											.on(
													"click",
													(function(clickedUserName,
															clickedUserId) {
														return function() {
															console
																	.log(clickedUserName);//이게 문제임
															var loginID = $(
																	"#loginID")
																	.val();
															var otherID = clickedUserId;
															var organization = friend.organization;

															var oneSeq = null;
															for (var i = 0; i < OneToOneChatDTOList.length; i++) {
																var chatRoom = OneToOneChatDTOList[i];
																if (chatRoom.loggedInUserID === loginID
																		&& chatRoom.otherUserID === otherID) {
																	oneSeq = chatRoom.oneSeq;
																	console
																			.log(oneSeq);
																	openOneChat(
																			clickedUserName,
																			friend.organization,
																			oneSeq);
																	getPreviousMessages(oneSeq);
																	break;
																}
															}

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
		function getPreviousMessages(oneSeq) {
		    $.ajax({
		        type: "GET",
		        url: "/getPreviousMessages/" + oneSeq,
		        dataType: "json",
		        success: function(data) {
		            console.log("시퀀스" + oneSeq);
		            console.log(data);
		            console.log(data[0]);
		            // 서버에서 받은 데이터를 JSON으로 파싱
		            var previousMessages = data;
		            for (var i = 0; i < previousMessages.length; i++) {
		                var previousMessage = previousMessages[i];
		                var previousMessageContainer = $("<p><strong>" + previousMessage.userID + "</strong> - " + previousMessage.message + "</p>");
		                $('.chatForm').append(previousMessageContainer);
		                keepScrollBottom();
		            }
		        },
		        error: function() {
		            console.log("이전 메시지를 불러오지 못했습니다.");
		        }
		    });
		}
		//부서 목록 불러오기
		$("#departmentSelect").on("change", function() {
			var organization = $(this).val();
			console.log(organization);
			if (organization !== "부서 선택") {
				// 선택한 부서에 따라 회원 목록을 불러와서 표시하는 AJAX 요청을 보냅니다.
				var oneSeq = 0;
				loadMembersByDepartment(organization, oneSeq); // 초기에 oneSeq를 0로 전달
			}
		});

		function loadMembersByDepartment(organization, oneSeq) {
			$
					.ajax({
						type : "POST",
						url : "/members1/getMembersByOrganization",
						data : {
							organization : organization,
							oneSeq : oneSeq
						},
						dataType : "json",
						success : function(data) {
							var groupMembers = data.members;
							var OneToOneChatDTOList = data.OneToOneChatDTOList;
							console.log(groupMembers);
							console.log(OneToOneChatDTOList);
							try {
								var $group_list = $("#group_list"); // group_list에 데이터를 추가하기 위해 선택합니다.
								console.log("그룹 : " + $group_list)

								if (groupMembers.length > 0) {
									$group_list.empty(); // 목록 초기화

									for (var i = 0; i < groupMembers.length; i++) {
										var friend = groupMembers[i];
										console.log(friend);

										// <div> 엘리먼트 생성
										var $row = $("<div>").addClass(
												"table-row");

										// 나머지 부분은 이전과 동일
										var $iconCell = $("<div>")
												.html(
														'<i class="fa-regular fa-circle-user"></i>')
												.css("display", "inline-block");
										$iconCell.on("click", function() {
											onOneChat();
										});
										$row.append($iconCell);

										var $nameCell = $("<div>").text(
												friend.name)
												.addClass("oneChat").css(
														"display",
														"inline-block").css(
														"cursor", "pointer");
										/*$nameCell.on("click", (function(name, oneSeq) {
										    return function() {
										        openOneChat(name, friend.organization,
										                oneSeq);
										        console.log(name, friend.organization); // 콘솔에 이름 출력
										    };
										})(friend.name, oneSeq));*/
										$nameCell
												.on(
														"click",
														(function(
																clickedUserName,
																clickedUserId) {
															return function() {
																var loginID = $(
																		"#loginID")
																		.val();
																var otherID = clickedUserId;
																var organization = friend.organization;

																var oneSeq = null;
																for (var i = 0; i < OneToOneChatDTOList.length; i++) {
																	var chatRoom = OneToOneChatDTOList[i];
																	if (chatRoom.loggedInUserID === loginID
																			&& chatRoom.otherUserID === otherID) {
																		oneSeq = chatRoom.oneSeq;
																		console
																				.log(oneSeq);
																		openOneChat(
																				clickedUserName,
																				friend.organization,
																				oneSeq); // openOneChat 함수 호출 위치 수정
																				getPreviousMessages(oneSeq);
																		break;
																	}
																}
															};
														})(friend.name,
																friend.id));

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
						error : function() {
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

		function openOneChat(friendName, organization, oneSeq) {
			// 이름을 쿼리 매개변수로 전달하여 페이지를 엽니다.
			$.get("/chats/inputText?friendName="
					+ encodeURIComponent(friendName) + "&organization="
					+ encodeURIComponent(organization) + "&oneSeq="
					+ encodeURIComponent(oneSeq), function(data) {
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
		
		
		//실시간 채팅
		var socket = new SockJS('/ws');
		var stompClient = Stomp.over(socket);

		stompClient.connect({}, function(frame) {
			var oneSeq = $('#oneSeq').val();
			stompClient.subscribe('/topic/oneToOne/{oneSeq}',
					function(response) {
						console.log('Received message: ' + response.body);
						var message = JSON.parse(response.body);
						var messageText = message.message;

						// 타입이 'CHAT'일 때만 메시지를 화면에 표시
						if (message.type === 'CHAT') {
							var messageContainer = $("<p><strong>"
									+ message.userID + "</strong> - "
									+ messageText + "</p>");
							$('.chatForm').append(messageContainer);
							keepScrollBottom();
						}
					});
		});
		//일대일 채팅 메세지 전송
		$(document).on(
				"click",
				"#sendBtn",
				function() {
					var message = $('#inputText').text().trim();
					var oneSeq = $('#oneSeq').val();
					var currentTime = new Date(); 
					var time = new Date().getTime(); 
					var hours = currentTime.getHours();
					var minutes = currentTime.getMinutes();
					var write_date;

					if (hours < 12) {
					    write_date = hours + ":" + (minutes < 10 ? "0" : "") + minutes + " AM";
					} else {
					    if (hours > 12) {
					        hours -= 12;
					    }
					    write_date = hours + ":" + (minutes < 10 ? "0" : "") + minutes + " PM";
					}


					if (message) {
						var userID = $('#userName').val();
						var messageContainer = $("<p><strong>" + userID
								+ "</strong> - " + message + "</p>"+"<p>"+write_date+"</p>");

						// 타입이 'CHAT'일 때만 메시지를 오른쪽에 표시
						if (message.type === 'CHAT') {
							messageContainer.addClass('right'); // 오른쪽으로 표시
						}
						$('.chatForm').append(messageContainer);
						keepScrollBottom();

						var messageType = 'one';

						// 메세지를 서버로 전송
						stompClient.send('/app/oneToOne/sendMessage/' + oneSeq,
								{}, JSON.stringify({
									type : "CHAT",
									messageType : messageType,
									userID : userID,
									message : message,
									oneSeq : oneSeq//이거 roomID를 바꿨음 테스트 확인바람
								}));

						// 입력 필드 비우기
						$('#inputText').empty();
					}
				});
		


	</script>


</body>
</html>