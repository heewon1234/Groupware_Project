$("#cancelBtn").on("click",function(){
	window.close();
});
		
$("#delContentsBtn").on("click",function(){
	let seq = opener.document.getElementById('boardSeq').innerHTML.split("글번호")[1].trim();
	opener.location.href="/board/delContents?seq="+seq;
	window.close();
});