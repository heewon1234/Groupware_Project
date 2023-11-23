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
.ChatModal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
}

.ChatModal-content {
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

#chattingProfileImg {
	width: 60px;
	border-radius: 50%;
	margin-right: 2px;
	height: 60px;
	overflow: hidden;
}

.chat_send_btn {
    box-shadow: none;
    background-color: #ebebeb;
    border: solid 1px #d6d6d6;
    border-radius: 4px;
    color: #333;
    height: 34px;
    cursor: pointer;
    font-size: 14px;
    width:50px;
}

.chat_send_btn:hover {
    background-color: #d6d6d6;
}
</style>
</head>
<body>

	<!-- 실제 팝업 내용 -->
	<div class="popup" id="popup">
		<input id="loginID" type="hidden" value="${loginId }">
		<div class="chattingTop">
			<span class="close-button" onclick="closePopup()">&times;</span>
			<div id="chattingFormLogo" class="dragHandle"><img id="chattingLogo" src="/images/commons/logo.png"></div>
			<div class="profile">
				<div id="chattingImg">
					<img id="chattingProfileImg" src="${userDTO.profile_image}"
						onerror="this.onerror=null; this.src='/images/commons/person-circle.svg';">
					<input type="text" hidden id="loginUserProfile"
						value="${userDTO.profile_image}"
						onerror="this.onerror=null; this.src='/images/commons/person-circle.svg';">
				</div>
				<div class="chattingUser">
					<div class="chattingName">
						<input id="chattingName" value="${name }" style="border: none; background-color: white;"
							disabled>
					</div>
					<div class="position">
						<input id="position" value="${position }" style="border: none;background-color: white;"
							disabled>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div class="chat_body" id="popupBody">
			<div class="chattingLeft">
				<i class="fa-regular fa-user" onclick="openchatMember()"></i> <i
					class="fa-regular fa-comments" onclick="openchatGroupList()"></i><i
					class="fa-solid fa-bars" onclick="openchatGmember()"
					style="position: absolute; bottom: 0; margin-bottom: 40px;"></i>
			</div>
			<div class="chattingRight" id="roomList">
				<hr>
				<div class="allList">
					<select id="departmentSelect" style="margin-top: 10px">
						<!-- <option selected>부서 선택</option>
						<option value="영업부">영업부</option>
						<option value="관리부">관리부</option>
						<option value="회계부">회계부</option> -->
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
								<img alt="" src="/images/chats/search.svg"
									onclick="searchUser()"> <input id="search_input"
									type="text" placeholder="채팅방 참여자 검색"> <img id="x"
									alt="" src="/images/chats/x.svg"
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
							<img id="searchBtn" src="/images/chats/search.svg"
								onclick="showSearchContainer()"> <img id="plusButton"
								alt="" src="/images/chats/plus.svg">
						</div>

					</div>
					<div class="search_container" style="display: none;">
						<div class="chatroom_search">
							<div id="searchBox">
								<img alt="" src="/images/chats/search.svg"
									onclick="searchGroup()"> <input id="searchGroup_input"
									type="text" placeholder="채팅방 검색" style="border: none;">
								<img id="x" alt="" src="/images/chats/x.svg"
									onclick="hideSearchContainer()">
							</div>
						</div>
					</div>
					<div class="chatroom_list"
						style="margin-top: 13px; height: 410px; overflow-y: auto;"></div>
				</div>
			</div>
		</div>
		<!-- 그룹 선택 모달 창 -->
		<div id="groupUserModal" class="ChatModal">
			<div class="ChatModal-content" style="width: 300px; height: 500px;">
				<span class="close" id="groupModalClose">&times;</span>
				<h2>사용자 선택</h2>
				<ul id="groupUserList">
					<!-- User list will be dynamically added here -->
				</ul>
				<button id="selectUsersButton">선택 완료</button>
				<!-- 선택 완료 버튼 추가 -->
			</div>
		</div>
		<!-- 일대일 채팅방-->
		<div class="box" id="inputJSP" style="display: none;">
			<input id="userName" type="hidden" value="${name}"> <input
				id="loginID" type="hidden" value="${loginId}"> <input
				id="oneSeq" type="hidden" value="${oneSeq}">
			<div class="inputTop" style="padding-top: 10px; padding-left: 5px">
				<span class="close-button" onclick="closeOneChat()">&times;</span>
				<div class="myProfile">
					<div class="ownImg"></div>
					<div class="other">
						<input id="otherName" value="${friendName}" style="border: none;background-color: white; "
							disabled="disabled"> <input id="organization"
							value="${organization} " style="border: none;background-color: white;"
							disabled="disabled">
					</div>
				</div>
			</div>
			<div class="chatForm" style="position: relative;"></div>

			<div id="messageForm">
				<div class="chatBox">
					<div class="inputText" id="inputText" contenteditable="true"></div>
					<div>
						<button id="sendBtn" class="chat_send_btn">전송</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 그룹 채팅방 -->
		<div class="box" id="groupJSP" style="display: none">
			<input id="groupUserName" type="hidden" value="${name}"> <input
				id="loginID" type="hidden" value="${loginId}"> <input
				type="hidden" id="groupSeq" value="${groupSeq}">
			<div class="groupInputTop"
				style="padding-top: 10px; padding-left: 5px">
				<span class="close-button" onclick="closeGroupChat()">&times;</span>
				<div class="myProfile">
					<div class="myimg">
						<i class="fa-regular fa-comment"></i>
					</div>
					<div class="other">
						<input id="groupChatting" value="그룹 채팅 "
							style="border: none; font-weight: bold; font-size: medium; margin-left: -1px;background-color: white;"
							disabled="disabled"> <input id="groupName"
							value="${groupName}" style="border: none;background-color: white;" disabled="disabled">
					</div>
				</div>
			</div>
			<div class="chatGroupForm" style="position: relative;"></div>

			<div id="messageForm">
				<div class="chatBox">
					<div class="inputText" id="groupinputText" contenteditable="true"></div>
					<div>
						<button id="sendGroupBtn" class="chat_send_btn">전송</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script src="/js/chat/chatting.js" type="text/javascript"></script>
	<script src="/js/chat/inputText.js" type="text/javascript"></script>
	<script>
	var stompClient;

    $(document).ready(function () {
        connectWebSocket();

        // 웹소켓 연결 함수
        function connectWebSocket() {
            var socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);

            stompClient.connect({}, function (frame) {
                console.log('WebSocket 연결 성공!');
            }, function (error) {
                console.error('WebSocket 연결 실패: ' + error);
            });
        }
    });
	// 부서 목록을 가져오는 Ajax 요청
	$.ajax({
        url:'/members/getDepartmentList'
     }).done(function(data){
    	 $("#departmentSelect").empty();
	        $("#departmentSelect").append('<option selected>부서 선택</option>');
	        for (let i = 0; i < data.length; i++) {
	            const departmentName = data[i];
	            $("#departmentSelect").append('<option value="' + departmentName + '">' + departmentName + '</option>');
	        }
     });

	//전체 인원수를 불러오는 코드
	$.ajax({
		type : "POST",
		url : "/members/selectAll",
		dataType : "json",
		success : function(data) {
			$(".Thead").html("전체 " + (data.length-1));
		},
		error : function() {
			console.log("새로운 레코드 수를 가져오지 못했습니다.");
		}
	});
	function generateUUID() {
        $.ajax({
            url: "/generateUUID",
            type: "GET",
            success: function (data) {
                $("#uuidResult").text("Generated UUID: " + data);
            },
            error: function () {
                $("#uuidResult").text("Failed to generate UUID.");
            }
        });
    }

	updateGroupChatList();
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
	        $.ajax({
	            url:'/members/getUserList'
	         }).done(function(resp){
	        	 $("#groupUserList").empty();
	            for(let i=0;i<resp.length;i++){
	               const userName = resp[i];
	               $("#groupUserList").append('<li><input type="checkbox" id="' + userName + '">' + userName + '</li>');
	            }
	         });
	    }
	});

	$("#groupModalClose").on("click", closeGroupModal);

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

	function handleSelectedUsers() {
	    if (selectedUsers.length === 0) {
	        alert("사용자를 선택하세요.");
	        return;
	    }
	    if (!pendingRoomName) {
	        alert("방 이름을 입력하세요.");
	        return;
	    }

	    $.ajax({
	        url: "/generateUUID",
	        type: "GET",
	        success: function (data) {
	            var generatedUUID = data;

	            for (var i = 0; i < selectedUsers.length; i++) {
	                var roomInfo = {
	                    groupSeq: generatedUUID,
	                    groupName: pendingRoomName,
	                    memberName: selectedUsers[i]
	                };
	                $.ajax({
	                    url: "/groupChatRooms/createGroupChat",
	                    type: "POST",
	                    contentType: "application/json",
	                    data: JSON.stringify(roomInfo),
	                    success: function (response) {
	                        updateGroupChatList();
	                    },

	                });
	            }

	            clearSelectedCheckboxes();
	            closeGroupModal();
	        },
	        error: function () {
	            alert("Failed to generate UUID.");
	        }
	    });
	    
	}

	function clearSelectedCheckboxes() {
	    selectedUsers = [];
	    $("#groupUserList input[type=checkbox]").prop("checked", false);
	    $("#groupUserList li").removeClass("selected");
	}
$("#selectUsersButton").on("click", function() {
    handleSelectedUsers();

    updateGroupChatList();
});

// 그룹방을 보여주는 ajax
function updateGroupChatList() {
    $.ajax({
        type: "POST",
        url: "/groupChatRooms/InvitedGroups",
        dataType: "json",
        success: function(data) {
            var $chatroom_list = $(".chatroom_list");

            $chatroom_list.empty();

            data.forEach(function(group) {
                var $row = $("<div>").addClass("table-row");

                var $iconCell = $("<div>")
                        .html('<i class="fa-regular fa-comment"></i>')
                        .css("display", "inline-block");

                var $nameCell = $("<div>")
                        .text(group.groupName)
                        .addClass("groupChat")
                        .css("display", "inline-block")
                        .css("cursor", "pointer");

                $nameCell.on("click", function() {
                    groupChat(group.groupName, group.groupSeq);
                });

                $row.append($iconCell);
                $row.append($nameCell);

                $chatroom_list.append($row);
            });

        },
        error: function(xhr, status, error) {
            console.error("AJAX request failed:", status, error);
        }
    });
}

//그룹채팅방 여는 코드
function groupChat(groupName, groupSeq) {
	/*if (stompClient && stompClient.subscriptions) {
        Object.keys(stompClient.subscriptions).forEach(function (subscriptionId) {
            stompClient.unsubscribe(subscriptionId);
        });
    }*/
	getPreviousGroupMessages(groupSeq);
	$("#groupName").val(groupName);
    $("#groupSeq").val(groupSeq);
	$("#groupJSP").css("display", "block");
	
        stompClient.subscribe('/topic/group/' + groupSeq, function (response) {
            var message = JSON.parse(response.body);
            var messageText = message.message;
            let currentUserName = $("#groupUserName").val();
                var messageContainer = $("<div class='message-container'></div>");
		        var messageRigterContainer = $("<div class='message-righter'></div>");
		    	var userImgContainer = $("<div class='otherImg'>").html(
		    				        '<img class="otherProfileImg" src="' + message.profile_image + '" onerror="this.onerror=null; this.src=\'/images/commons/person-circle.svg\';">'
		    			    ).css("display", "inline-block")
		    	var userIDContainer = $("<div class='user-id'></div>").text(message.userID);
		    	var messageTextContainer = $("<div class='message-text'></div>").html(messageText);
		    	if (currentUserName == message.userID) { 
		            messageContainer.addClass('own-message'); 
		            }
		    	messageRigterContainer.append(userIDContainer).append(messageTextContainer)
		    	messageContainer.append(userImgContainer,messageRigterContainer);
		        $('.chatGroupForm').append(messageContainer);
                groupKeepScrollBottom();
        }, function (error) {
            console.error('Subscription error: ', error);
        });

        $(document).on("click", "#sendGroupBtn", function () {
        	sendGroupMessage(groupSeq);
        });
        $(document).on("keydown", "#groupinputText", function (e) {
            if (e.keyCode === 13 && !e.shiftKey) {
                e.preventDefault();
                sendGroupMessage(groupSeq);
            }
        });
}
//그룹 메시지 전송 함수
function sendGroupMessage(groupSeq) {
    var message = $('#groupinputText').html();
    var groupSeq = $('#groupSeq').val();
    let loginUserProfile = $("#loginUserProfile").val();

    if (message) {
       var userID = $('#groupUserName').val();
       groupKeepScrollBottom();

       var messageType = 'group';

       stompClient.send('/app/group/sendMessage/' + groupSeq,
             {}, JSON.stringify({
                type : "CHAT",
                profile_image:loginUserProfile,
                messageType : messageType,
                userID : userID,
                message : message,
                roomID : groupSeq
             }));

       $('#groupinputText').empty();
    }
}
function getPreviousGroupMessages(groupSeq) {
    $.ajax({
        type: "GET",
        url: "/getPreviousGroupMessages/" + groupSeq,
        dataType: "json",
        success: function(data) {
            var previousMessages = data;
            let currentUserName = $("#groupUserName").val();
            
            for (var i = 0; i < previousMessages.length; i++) {
                var previousMessage = previousMessages[i];
                var messageContainer = $("<div class='message-container'></div>");
                var messageRigterContainer = $("<div class='message-righter'></div>");
			    var userImgContainer = $("<div class='otherImg'>").html(
				        '<img class="otherProfileImg" src="' + previousMessage.profile_image + '" onerror="this.onerror=null; this.src=\'/images/commons/person-circle.svg\';">'
			    ).css("display", "inline-block")
			    var userIDContainer = $("<div class='user-id'></div>").text(previousMessage.userID);
			    var messageTextContainer = $("<div class='message-text'></div>").html(previousMessage.message);
			    if (currentUserName == previousMessage.userID) { 
                    messageContainer.addClass('own-message'); 
                }
			    messageRigterContainer.append(userIDContainer).append(messageTextContainer)
			    messageContainer.append(userImgContainer,messageRigterContainer);
                $('.chatGroupForm').append(messageContainer);
            }
            groupKeepScrollBottom();    
        },
        error: function() {
            console.log("이전 메시지를 불러오지 못했습니다.");
        }
    });
}
	

	
	
//----------------------------일대일
		$
				.ajax({
					type : "POST",
					url : "/members/selectAllNotLogged",
					dataType : "json",
					success : function(data) {
						var listData = data.list;
						var OneToOneChatDTOList = data.OneToOneChatDTOList;
						try {
							var $friend_list = $("#friend_list");
							if (listData.length > 0) {
								for (var i = 0; i < listData.length; i++) {
									var friend = listData[i];
									var $row = $("<div>").addClass("table-row");

									var $iconCell = $("<div>").html(
										    '<img class="selectAllProfileImg" src="' + friend.profile_image + '" onerror="this.onerror=null; this.src=\'/images/commons/person-circle.svg\';">'
										).css("display", "inline-block").addClass("iconCell");
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
															clickedUserId,profileImg) {
														return function() {
															var loginID = $(
																	"#loginID")
																	.val();
															var otherID = clickedUserId;
															var organization = friend.organization;
															$(".ownImg").html("");
															$(".ownImg").append(
																    $("<div class='oneImg'>").html(
																        '<img class="ownProfileImg" src="' + profileImg + '" onerror="this.onerror=null; this.src=\'/images/commons/person-circle.svg\';">'
																    ).css("display", "inline-block")
																);

															var oneSeq = null;
															for (var i = 0; i < OneToOneChatDTOList.length; i++) {
																var chatRoom = OneToOneChatDTOList[i];
																if (chatRoom.loggedInUserID === loginID
																		&& chatRoom.otherUserID === otherID) {
																	oneSeq = chatRoom.oneSeq;
																	openOneChat(
																			clickedUserName,
																			friend.organization,
																			oneSeq);
																	getPreviousMessages(oneSeq);
																	break;
																}
															}

														};
													})(friend.name, friend.id,friend.profile_image));
									$row.append($nameCell);
									$friend_list.append($row);
								}
							}
						} catch (error) {
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
		            var previousMessages = data;
		            let currentUserName = $("#userName").val();
		            for (var i = 0; i < previousMessages.length; i++) {
		                let previousMessage = previousMessages[i];
		                var messageContainer = $("<div class='message-container'></div>");
		                var messageRigterContainer = $("<div class='message-righter'></div>");
					    var userImgContainer = $("<div class='otherImg'>").html(
						        '<img class="otherProfileImg" src="' + previousMessage.profile_image + '" onerror="this.onerror=null; this.src=\'/images/commons/person-circle.svg\';">'
					    ).css("display", "inline-block")
					    var userIDContainer = $("<div class='user-id'></div>").text(previousMessage.userID);
					    var messageTextContainer = $("<div class='message-text'></div>").html(previousMessage.message);
					    if (currentUserName == previousMessage.userID) { 
		                    messageContainer.addClass('own-message'); 
		                }
					    messageRigterContainer.append(userIDContainer).append(messageTextContainer)
					    messageContainer.append(userImgContainer,messageRigterContainer);
		                $('.chatForm').append(messageContainer);
		            }
		            keepScrollBottom();
		        },
		        error: function() {
		            console.log("이전 메시지를 불러오지 못했습니다.");
		        }
		    });
		}
		//부서 목록 불러오기
		$("#departmentSelect").on("change", function() {
			var organization = $(this).val();
			if (organization !== "부서 선택") {
				var oneSeq = 0;
				loadMembersByDepartment(organization, oneSeq);
			}
		});

		function loadMembersByDepartment(organization, oneSeq) {
			$.ajax({
						type : "POST",
						url : "/members/getMembersByOrganization",
						data : {
							organization : organization,
							oneSeq : oneSeq
						},
						dataType : "json",
						success : function(data) {
							var groupMembers = data.members;
							var OneToOneChatDTOList = data.OneToOneChatDTOList;
							try {
								var $group_list = $("#group_list");
								if (groupMembers.length > 0) {
									$group_list.empty(); // 목록 초기화

									for (var i = 0; i < groupMembers.length; i++) {
										var friend = groupMembers[i];
										var $row = $("<div>").addClass(
												"table-row");

										var $iconCell = $("<div>").html(
											    '<img class="selectAllProfileImg" src="' + friend.profile_image + '" onerror="this.onerror=null; this.src=\'/images/commons/person-circle.svg\';">'
											).css("display", "inline-block").addClass("iconCell");
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
										$nameCell
												.on(
														"click",
														(function(
																clickedUserName,
																clickedUserId,profileImg) {
															return function() {
																var loginID = $(
																		"#loginID")
																		.val();
																var otherID = clickedUserId;
																var organization = friend.organization;
																$(".ownImg").html("");
																$(".ownImg").append(
																	    $("<div class='oneImg'>").html(
																	        '<img class="ownProfileImg" src="' + profileImg + '" onerror="this.onerror=null; this.src=\'/images/commons/person-circle.svg\';">'
																	    ).css("display", "inline-block")
																	);
																var oneSeq = null;
																for (var i = 0; i < OneToOneChatDTOList.length; i++) {
																	var chatRoom = OneToOneChatDTOList[i];
																	if (chatRoom.loggedInUserID === loginID
																			&& chatRoom.otherUserID === otherID) {
																		oneSeq = chatRoom.oneSeq;
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
																friend.id,friend.profile_image));

										$row.append($nameCell);

										$group_list.append($row);
									}
								}
							} catch (error) {
								console.error("JSON 파싱 오류:", error);
							}
						},
						error : function() {
							console.log("회원 목록을 불러오지 못했습니다.");
						}
					});
		}

		$(document).on("mouseenter", "#friend_list td.name", function() {
			$(this).css("text-decoration", "underline");
		}).on("mouseleave", "#friend_list td.name", function() {
			$(this).css("text-decoration", "none");
		});

		function openOneChat(friendName, organization, oneSeq) {
			/*if (stompClient && stompClient.subscriptions) {
		        Object.keys(stompClient.subscriptions).forEach(function (subscriptionId) {
		            stompClient.unsubscribe(subscriptionId);
		        });
		    }*/
			
			let currentUserName = $("#otherName").val(friendName);
		    $("#organization").val(organization);
		    $("#oneSeq").val(oneSeq);
		    $("#inputJSP").css("display", "block");

		            stompClient.subscribe('/topic/oneToOne/' + oneSeq, function (response) {
		                var message = JSON.parse(response.body);
		                var messageText = message.message;
		                let currentUserName = $("#userName").val();
		                if (message.type === 'CHAT') {
		                    var messageContainer = $("<div class='message-container'></div>");
		                    var messageRigterContainer = $("<div class='message-righter'></div>");
		    			    var userImgContainer = $("<div class='otherImg'>").html(
		    				        '<img class="otherProfileImg" src="' + message.profile_image + '" onerror="this.onerror=null; this.src=\'/images/commons/person-circle.svg\';">'
		    			    ).css("display", "inline-block")
		    			    var userIDContainer = $("<div class='user-id'></div>").text(message.userID);
		    			    var messageTextContainer = $("<div class='message-text'></div>").html(messageText);
		    			    if (currentUserName == message.userID) { 
		                        messageContainer.addClass('own-message'); 
		                    }
		    			    messageRigterContainer.append(userIDContainer).append(messageTextContainer)
		    			    messageContainer.append(userImgContainer,messageRigterContainer);
		                    $('.chatForm').append(messageContainer);
		                    keepScrollBottom();
		                }
		            }, function (error) {
		                console.error('Subscription error: ', error);
		            });

		            $(document).on("click", "#sendBtn", function () {
		                sendMessage(oneSeq);
		            });
		            $(document).on("keydown", "#inputText", function (e) {
		                if (e.keyCode === 13 && !e.shiftKey) {
		                    e.preventDefault();
		                    sendMessage(oneSeq);
		                }
		            });





		}

		function sendMessage(oneSeq) {
		    var message = $('#inputText').html();
            var oneSeq = $('#oneSeq').val();
            let loginUserProfile = $("#loginUserProfile").val();
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
               keepScrollBottom();

               var messageType = 'one';

               stompClient.send('/app/oneToOne/sendMessage/' + oneSeq,
                     {}, JSON.stringify({
                        type : "CHAT",
                        profile_image:loginUserProfile,
                        messageType : messageType,
                        userID : userID,
                        message : message,
                        roomID : oneSeq
                     }));

               $('#inputText').empty();
		    }
		}
		function closeOneChat() {
			var oneSeq = $("#oneSeq").val();
		    $('.chatForm').empty();
		    if (stompClient) {
		        stompClient.unsubscribe('/topic/oneToOne/' + oneSeq);
		    }
		    $("#inputJSP").css("display", "none");
		}
		function closeGroupChat() {
			var groupSeq = $("#groupSeq").val();
		    $('.chatGroupForm').empty();
		    if (stompClient) {
		        stompClient.unsubscribe('/topic/group/' + groupSeq);
		    }
		    $("#groupJSP").css("display", "none");
		}
		function keepScrollBottom() {
	        let contents = $(".chatForm")[0];
	        contents.scrollTop = contents.scrollHeight;
	     }
		function groupKeepScrollBottom() {
	        let contents = $(".chatGroupForm")[0];
	        contents.scrollTop = contents.scrollHeight;
	     }

	</script>
</body>
</html>