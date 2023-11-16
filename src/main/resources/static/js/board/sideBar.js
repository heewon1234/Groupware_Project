$("#left_item").load("/mk_board/sideBar");

$(document).on("click","#toWriteContentsBoardBtn",function(){
	location.href="/board/toWriteContentsBoard";
});

$(document).on("click",".menu_list_item",function(){
	let board_title = $(this).attr("data-boardTitle");
	$.ajax({
		url:"/authority/isExistAuth",
		method:"POST",
		data:{board_title:board_title}
	}).done(function(resp){
		if(resp){
			location.href="/board/toBoard?board_title="+board_title;
		} else{
			alert("게시판에 접근할 수 있는 권한이 없습니다");
			return false;
		}
		
	})
	
});

$(document).on("click","#toMkBoardBtn",function(){
	location.href="/mk_board/toMk_board";
});

$(document).on("click","#toFavoriteBoardBtn",function(){
	location.href="/board/toBoard?board_title=중요게시물";
});

$(document).on("click","#toEditBoardBtn",function(){
	location.href="/mk_board/toEditBoard";
});

$(document).ready(function() {
    $(document).on("click",".menu_list_button",function() {
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


