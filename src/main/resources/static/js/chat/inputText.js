
//----------------------------
//일대일 채팅 움직이는 것
const inputTop = document.querySelector(".inputTop");
const inputJSP = document.getElementById("inputJSP");

let isDragging5 = false;
let startX, startY, offsetX5, offsetY5;

inputTop.addEventListener('mousedown', (e) => {
    isDragging5 = true;
    startX = e.pageX - inputJSP.offsetLeft;
    startY = e.pageY - inputJSP.offsetTop;

    e.preventDefault();
});

document.addEventListener('mousemove', (e) => {
    if (isDragging5) {
        offsetX5 = e.pageX - startX;
        offsetY5 = e.pageY - startY;

        inputJSP.style.left = offsetX5 + 'px';
        inputJSP.style.top = offsetY5 + 'px';
    }
});

document.addEventListener('mouseup', () => {
    isDragging5 = false;
});


document.addEventListener('mouseup', () => {
    isDragging5 = false;
});


//----------------------------
//그룹 채팅 움직이는 것
const groupInputTop = document.querySelector(".groupInputTop");
const groupJSP = document.getElementById("groupJSP");

let isDragging10 = false;
let startX1, startY1, offsetX10, offsetY10;

groupInputTop.addEventListener('mousedown', (e) => {
    isDragging10 = true;
    startX1 = e.pageX - groupJSP.offsetLeft;
    startY1 = e.pageY - groupJSP.offsetTop;

    e.preventDefault();
});

document.addEventListener('mousemove', (e) => {
    if (isDragging10) {
        offsetX10 = e.pageX - startX1;
        offsetY10 = e.pageY - startY1;

        groupJSP.style.left = offsetX10 + 'px';
        groupJSP.style.top = offsetY10 + 'px';
    }
});

document.addEventListener('mouseup', () => {
    isDragging10 = false;
});


document.addEventListener('mouseup', () => {
    isDragging10 = false;
});
