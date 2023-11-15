
/* 기존에 사용하던 메뉴 선택했을때 선택된 페이지 파란색으로 배경색 바꾸는 효과 주는거
$(document).ready(function() {
    $('.menu_item, .menu_list_item').click(function() {
        $('.menu_item, .menu_list_item').removeClass('select');
        $(this).addClass('select');
    });
}); 
*/



// 드롭 다운 메뉴 펼치고 접으면서 사진 회전
$(document).ready(function() {
    $('.menu_list_button').click(function() {
        // 클릭된 menu_list_button 요소의 부모인 menu_list에서 menu_list_box를 찾아 현재 상태를 확인하고 변경
        var menuListBox = $(this).siblings('.menu_list_box');
        var menuListImages = $(this).find('.menu_list_button_drop img');
        
        
        if (menuListBox.css('display') === 'none' || menuListBox.css('display') === '') {
            menuListBox.css('display', 'block');
            menuListImages.css('transform', '');
            menuListImages.css('transition', 'all ease 0.2s');
        } else {
            menuListBox.css('display', 'none');
            menuListImages.css('transform', 'rotate( 180deg )');
            menuListImages.css('transition', 'all ease 0.2s');
            // menu_list_button
        }
    });
});