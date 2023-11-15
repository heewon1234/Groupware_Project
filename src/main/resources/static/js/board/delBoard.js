$("#cancelBtn").on("click",function(){
	window.close();
});
		
$("#delContentsBtn").on("click",function(){
	let board_title = opener.document.getElementById("delBoardTitle").innerHTML;
	opener.location.href="/mk_board/delBoard?board_title="+board_title;
	window.close();
});