function openPopup() {
    document.getElementById("popup").style.display = "block";
}

function closePopup() {
    document.getElementById("popup").style.display = "none";
}

document.getElementById('departmentSelect').addEventListener('change', function () {
    var selectedDepartment = this.value; // 선택된 부서의 값을 가져옵니다.
    alert('선택한 부서: ' + selectedDepartment);
});

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
    $(".chatRoom_container").css("display", "none");
    $(".chatGroupRoom_container").css("display", "none");
    $(".search_container").css("display", "none");
}

function openchatList() {
    $(".chatRoom_container").css("display", "block");
    $(".allList").css("display", "none");
    $(".chatMember_container").css("display", "none");
    $(".chatGroupRoom_container").css("display", "none");
    $(".search_container").css("display", "none");
}
function openchatGroupList() {
    $(".chatGroupRoom_container").css("display", "block");
    $(".allList").css("display", "none");
    $(".chatMember_container").css("display", "none");
    $(".chatRoom_container").css("display", "none");
    $(".search_container").css("display", "none");
}
//검색차
function showSearchContainer() {
    $(".search_container").css("display", "block");
}

function hideSearchContainer() {
    $(".search_container").css("display", "none");
}
//inputText
		/* let isDrag = false;
		        let offsetX3, offsetY3;
		        const input_container = document.getElementById("inputJSP");
		        const inputTop = document.querySelector(".inputTop");

		        inputTop.addEventListener("mousedown", (e) => {
		            isDrag = true;
		            offsetX3 = e.clientX3 - input_container.getBoundingClientRect().left;
		            offsetY3 = e.clientY3 - input_container.getBoundingClientRect().top;
		        });

		        document.addEventListener("mousemove", (e) => {
		            if (isDrag) {
		                const newX = e.clientX3 - offsetX3;
		                const newY = e.clientY3 - offsetY3;

		                input_container.style.left = newX + "px";
		                input_container.style.top = newY + "px";
		            }
		        });

		        document.addEventListener("mouseup", () => {
		            isDrag = false;
		        });*/

