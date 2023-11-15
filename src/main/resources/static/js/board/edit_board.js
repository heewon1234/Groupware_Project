$(".updateBtn").on("click",function(){
	let board_title = $(this).parent("div").siblings(".board_index_board_title").html().trim();
	location.href="/mk_board/toEditBoardDetail?board_title="+board_title;
});

$(".delBtn").on("click",function(){
	$(this).parent("div").siblings(".board_index_board_title").attr("id","delBoardTitle");
	window.open("/mk_board/toDelBoard","","width=300,height=200");
});