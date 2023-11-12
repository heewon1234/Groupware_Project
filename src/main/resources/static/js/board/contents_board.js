$("#contentsDelBtn").on("click",function(){
	window.open("/board/toDelContents","","width=300,height=200");
});

$("#backBtn").on("click",function(){
	location.href="/board/toBoard";
});

$("#contentsUpdateBtn").on("click",function(){
	location.href="/board/toEditBoard";
});

$(document).on("click",".replyDelBtn",function(){
	$(this).parents(".reply_list").attr("id","delReplyBox");
	window.open("/reply/toDelReply","","width=300,height=200");
});