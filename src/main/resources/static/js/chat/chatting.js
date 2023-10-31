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

// 마우스 업 이벤트를 처리합니다.
document.addEventListener('mouseup', () => {
    isDragging = false;
});
function openchatPage() {
    var chatPageURL = "/chats/inputText";

    var xhr = new XMLHttpRequest();
    xhr.open("GET", chatPageURL, true);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var parser = new DOMParser();
            var doc = parser.parseFromString(xhr.responseText, "text/html");
            var newBody = doc.body;

            // 팝업 창의 popupBody에 새로운 body 내용을 업데이트합니다.
            var popupBody = document.getElementById("popupBody");
            popupBody.innerHTML = newBody.innerHTML;
        }
    };

    xhr.send();
}





