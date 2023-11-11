function closeOneChat() {
    $("#inputJSP").css("display", "none");
    // 구독 해제
    if (stompClient) {
        stompClient.unsubscribe();
    }
    $('.chatForm').empty();
}
//----------------------------
const inputTop = $(".inputTop");
const inputJSP = $("#inputJSP");

let isDragging5 = false;
let offsetX5, offsetY5;

// 마우스 다운 이벤트를 처리합니다.
inputTop.on('mousedown', (e) => {
    isDragging5 = true;

    // 로고 내부에서 클릭한 위치를 계산하여 offsetX5, offsetY5로 설정합니다.
    offsetX5 = e.clientX - inputJSP[0].getBoundingClientRect().left + 50;
    offsetY5 = e.clientY - inputJSP[0].getBoundingClientRect().top - 280;

    // 마우스 이벤트의 기본 동작을 막아 마우스 커서가 고정됩니다.
    e.preventDefault();
});

// 마우스 이동 이벤트를 처리합니다.
$(document).on('mousemove', (e) => {
    if (isDragging5) {
        // 팝업 창을 마우스 포인터 위치로 이동합니다.
        const newX5 = e.clientX - offsetX5;
        const newY5 = e.clientY - offsetY5;

        inputJSP.css('left', newX5 + 'px');
        inputJSP.css('top', newY5 + 'px');
    }
});

$(document).on('mouseup', () => {
    isDragging5 = false;
});
//----------------------------
