let board_title = $(".contents_info_datail>div:nth-child(3)").html().trim();
let parent_seq = $("#boardSeq").html().split("글번호")[1].trim();

let voteResult = (resp) => {
	$("#surveyResult").empty();
	if($(".backVoteDiv").length>0){$(".backVoteDiv").remove();}

	let totalCount = resp[0].totalVote;
	for(let i=0;i<resp.length;i++){
		let ratio = 0;
		if(totalCount!=0){
			ratio = (resp[i].vote / resp[i].totalVote) * 100;
		}
		let divBox = $("<div>");
		divBox.addClass("survey_item");
				
		let divItem = $("<div>");
		divItem.append(resp[i].items);
			
		let graphDiv = $("<div>");
		graphDiv.addClass("graph");
						
		let colorDiv = $("<div>");
		if(ratio==0){
			colorDiv.css("width","100%").css("height","100%").css("background-color","gainsboro");
		} else{
			colorDiv.css("width",ratio+"%").css("height","100%").css("background-color","rgb(104, 183, 251)");
		}				
		
			
		graphDiv.append(colorDiv);
			
		let ratioBox = $("<div>");
		ratioBox.addClass("ratioBox");
		ratioBox.append(ratio.toFixed(2)+"% ( "+resp[i].vote+" 명 )");
			
		divBox.append(divItem);
		divBox.append(graphDiv);
		divBox.append(ratioBox);
		$("#surveyResult").append(divBox);
		$("#surveyResultBox").css("display","block");
		$("#surveyVoteBox").css("display","none");
	}
};

$("#showResultBtn").on("click",function(){
	$.ajax({
		url:"/survey/voteList",
		data:{parent_seq:parent_seq}
	}).done(function(resp){
		voteResult(resp);
		let box = $("<div>");
		let hr=$("<hr>");
		let btnDiv = $("<div>");
		btnDiv.css("margin-top","10px");
		btnDiv.css("text-align","center");
		let button = $("<button>");
		button.addClass("backVoteBtn");
		button.append("투표하기");
		btnDiv.append(button);
		box.addClass("backVoteDiv");
		box.append(hr);
		box.append(btnDiv);
		$("#surveyResultBox").append(box);
		
	});
});

$(document).on("click",".backVoteBtn",function(){
	$("#surveyResultBox").css("display","none");
	$("#surveyVoteBox").css("display","block");
});

if($(".contents").attr("data-vote")=='true'){
	
	$.ajax({
		url:"/survey/voteList",
		data:{parent_seq:parent_seq}
	}).done(function(resp){
		voteResult(resp);
	});
}

$("#contentsDelBtn").on("click",function(){
	window.open("/board/toDelContents","","width=300,height=200");
});

$("#backBtn").on("click",function(){
	location.href="/board/toBoard";
});

$("#contentsUpdateBtn").on("click",function(){
	location.href="/board/toEditContentsBoard?seq="+parent_seq;
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

$("#voteSurveyBtn").on("click",function(){
	let voteItem = $(document).find("input[name='item']:checked");	
	if(!voteItem.length){
		alert("항목을 선택해주세요");
		return false;
	} 
	
	let seq = voteItem.attr("data-index");
	$.ajax({
		url:"/survey/voteCountUpdate",
		data:{
			"seq":seq,
			"parent_seq":parent_seq
		}
	}).done(function(resp){
		alert("투표가 완료되었습니다");
		voteItem.attr("data-index","true");
		
		voteResult(resp);
	});
	
	
});


