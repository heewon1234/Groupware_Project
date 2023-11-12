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

let updateSuccess = true;

$(document).on("click",".replyUpdateBtn",function(){
	
	if(!updateSuccess){
		alert("댓글이 수정중입니다. \n취소하고 눌러주세요");
	}
	updateSuccess = false;
	
	let replyContentsBox = $(this).parent(".reply_list_edit_buttons").prev(".reply_list_contents");
	let contentsPrev = replyContentsBox.html().trim();
	contentsPrev = contentsPrev.replaceAll("<br>","");
	replyContentsBox.html("");
	
	let textarea = $("<textarea>");
	textarea.attr("name","contents");
	textarea.val(contentsPrev);
	textarea.css("width","100%").css("height","100%").css("resize","none");
	replyContentsBox.append(textarea);
	
	$(this).css("display","none");	
	$(this).siblings(".replyDelBtn").css("display","none");
	$(this).siblings(".updateCancelBtn").css("display","inline");
	$(this).siblings(".updateSuccessBtn").css("display","inline");
});