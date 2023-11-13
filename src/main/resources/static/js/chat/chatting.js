function openPopup() {
    $("#popup").css("display", "block");
}

function closePopup() {
    $("#popup").css("display", "none");
}

function oneChatPopup() {
    document.getElementById("inputJSP").style.display = "block";
}

// 로고 요소와 팝업 요소를 선택합니다.
const logo = document.getElementById('logo');
const container = document.getElementById('popup');

let isDragging = false;
let offsetX, offsetY;

// 마우스 다운 이벤트를 처리합니다.
logo.addEventListener('mousedown', (e) => {
    isDragging = true;

    // 로고 내부에서 클릭한 위치를 계산하여 offsetX, offsetY로 설정합니다.
    offsetX = e.clientX - container.getBoundingClientRect().left - 200;
    offsetY = e.clientY - container.getBoundingClientRect().top - 330;

    // 마우스 이벤트의 기본 동작을 막아 마우스 커서가 고정됩니다.
    e.preventDefault();
});

// 마우스 이동 이벤트를 처리합니다.
document.addEventListener('mousemove', (e) => {
    if (isDragging) {
        // 팝업 창을 마우스 포인터 위치로 이동합니다.
        const newX = e.clientX - offsetX;
        const newY = e.clientY - offsetY;

        container.style.left = newX + 'px';
        container.style.top = newY + 'px';
    }
});
document.addEventListener('mouseup', () => {
    isDragging = false;
});
function openchatMember() {
    $(".chatMember_container").css("display", "block");
    $(".allList").css("display", "none");
    //$(".chatRoom_container").css("display", "none");
    $(".chatGroupRoom_container").css("display", "none");
    $(".search_container").css("display", "none");
}
function openchatGmember() {
    $(".chatMember_container").css("display", "none");
    $(".allList").css("display", "block");
    //$(".chatRoom_container").css("display", "none");
    $(".chatGroupRoom_container").css("display", "none");
    $(".search_container").css("display", "none");
}


function openchatGroupList() {
    $(".chatGroupRoom_container").css("display", "block");
    $(".allList").css("display", "none");
    $(".chatMember_container").css("display", "none");
    //$(".chatRoom_container").css("display", "none");
    $(".search_container").css("display", "none");
}
//검색차
function showSearchContainer() {
    $(".search_container").css("display", "block");
}

function hideSearchContainer() {
    var searchValue = $("#search_input");
    var groupValue = $("#searchGroup_input");
    searchValue.val('');
    groupValue.val('');
    $(".search_container").css("display", "none");
}

function searchUser() {
    // 입력된 검색어 가져오기
    var searchValue = $("#search_input").val();

    // AJAX를 사용하여 검색어를 컨트롤러로 전송
    $.ajax({
        url: "/chats/searchUser", // 실제 컨트롤러의 URL로 변경
        type: "POST", // 예시로 POST로 지정, 필요에 따라 변경
        contentType: "application/x-www-form-urlencoded",
        data: { searchValue: searchValue }, // 검색어를 객체 형태로 전송
        success: function (response) {
			var listData = response.searchList;
			var OneToOneChatDTOList = response.OneToOneChatDTOList;
			console.log(response);
			$("#friend_list").empty();
			
			listData.forEach(function(friend) {
    console.log(friend.name);
    var $row = $("<div>").addClass("table-row");
    var $iconCell = $("<div>")
        .html('<i class="fa-regular fa-circle-user"></i>')
        .css("display", "inline-block");

    var $nameCell = $("<div>")
        .text(friend.name)
        .addClass("oneChat")
        .css("display", "inline-block")
        .css("cursor", "pointer");

    $nameCell.on(
        "click",
        (function (clickedUserName, clickedUserId) {
            return function () {
                console.log(clickedUserName);
                var loginID = $("#loginID").val();
                var otherID = clickedUserId;
                var organization = friend.organization;
                var oneSeq = null;
                for (var i = 0; i < OneToOneChatDTOList.length; i++) {
                    var chatRoom = OneToOneChatDTOList[i];
                    if (chatRoom.loggedInUserID === loginID && chatRoom.otherUserID === otherID) {
                        oneSeq = chatRoom.oneSeq;
                        console.log(oneSeq);
                        openOneChat(clickedUserName, organization, oneSeq);
                        getPreviousMessages(oneSeq);
                        break;
                    }
                }
            };
        })(friend.name, friend.id)
    );

    $row.append($iconCell);
    $row.append($nameCell);

    $("#friend_list").append($row);
    
});

},

        error: function (error) {
            console.error("Search failed:", error);
        }
    });
}

function searchGroup() {
    var groupValue = $("#searchGroup_input").val();
    $.ajax({
        type: "POST",
        url: "/groupChatRooms/searchGroup",
        contentType: "application/x-www-form-urlencoded",
        data: { groupValue: groupValue },
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