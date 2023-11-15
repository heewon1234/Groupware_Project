$(".updateBtn").on("click",function(){
	location.href="/mk_board/toEditBoardDetail";
});

$(".delBtn").on("click",function(){
	$(this).parent("div").siblings(".board_index_board_title").attr("id","delBoardTitle");
	window.open("/mk_board/toDelBoard","","width=300,height=200");
});