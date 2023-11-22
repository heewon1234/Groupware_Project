// 왼쪽에서 목록 선택하면 해당 부분 select 되는것
// $(document).ready(function() {
//    $('.menu_item, .menu_list_item').click(function() {
//        $('.menu_item, .menu_list_item').removeClass('select');
//        $(this).addClass('select');
//        console.log("셀렉트 콘솔");
//    });
// });

// 메뉴 열고 닫고, 화살표 이미지 회전
$(document).ready(function() {
    $(".menu_list_button").click(function() {
        let parent = $(this).closest('.menu_list');
        let child = parent.find('.menu_list_box');

        child.toggle();
        if (child.css('display') === 'none' || child.css('display') === '') {
            parent.find('.menu_list_button_drop img').css('transform', 'rotate( 180deg )');
        }
        else {
            parent.find('.menu_list_button_drop img').css('transform', '');
        }
    });
});

// 드롭다운 메뉴 + 버튼 테스트
//$(document).ready(function() {
//    $(".menu_list_button_plus").on('click', function() {
//        console.log("드롭 다운 메뉴에 있는 + 버튼 테스트");
//        return false;
//    });
// });

// 드롭다운 메뉴  버튼 테스트
//$(document).ready(function() {
//    $(".menu_list_item_plus.point").on('click', function() {
//        console.log("드롭 다운 메뉴에 있는 ... 버튼 테스트 ");
//        return false;
//    });
// });

