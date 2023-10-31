function openPopup1() {
    document.getElementById("popup").style.display = "block";
}

function closePopup() {
    document.getElementById("popup").style.display = "none";
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

// 마우스 업 이벤트를 처리합니다.
document.addEventListener('mouseup', () => {
    isDragging = false;
});

// 클릭된 메뉴가 열려있는지 여부를 나타내는 변수
var isMenuOpen = false;

// 클릭된 메뉴를 열거나 닫는 함수
function toggleMenu() {
    var menu = document.querySelector(".menu");
    
    if (!isMenuOpen) {
        // 닫혀있으면 엽니다.
        menu.style.display = "grid"; // 그대로 유지 (그리드 모양)
    } else {
        // 열려있으면 닫습니다.
        menu.style.display = "none";
    }
    
    // isMenuOpen 변수 업데이트
    isMenuOpen = !isMenuOpen;
}

// 초기 상태에서 메뉴를 숨김
toggleMenu();


