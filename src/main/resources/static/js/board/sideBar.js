$("#left_item").load("/board/sideBar");

$(document).on("click","#toWriteContentsBoardBtn",function(){
	location.href="/board/toWriteContentsBoard";
});

$(document).on("click",".menu_list_item",function(){
	let board_title = $(this).attr("data-boardTitle");
	location.href="/board/toBoard?board_title="+board_title;
});

$(document).on("click","#toMkBoardBtn",function(){
	location.href="/mk_board/toMk_board";
});

$(document).on("click","#toFavoriteBoardBtn",function(){
	location.href="/board/toBoard";
});

$(document).on("click","#toEditBoardBtn",function(){
	location.href="/mk_board/toEditBoard";
});



