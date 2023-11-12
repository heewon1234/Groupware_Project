$("#cancelBtn").on("click",function(){
	window.close();
});
		
$("#delContentsBtn").on("click",function(){
	let seq = opener.document.getElementById('delReplyBox').getAttribute("data-index");
	let parent_seq = opener.document.getElementById('boardSeq').innerHTML.split("글번호")[1].trim();
	opener.location.href="/reply/delReply?seq="+seq+"&parent_seq="+parent_seq;
	window.close();
});