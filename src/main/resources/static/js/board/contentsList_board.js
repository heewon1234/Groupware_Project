
$(document).ready(function() {
    var myInput = $('#searchTextBoxInput');
    var searchBox = $('.searchBox');

    myInput.on('focus', function() {
        searchBox.addClass('focus');
    });

    myInput.on('blur', function() {
        searchBox.removeClass('focus');
    });
});

$(".favorite").on("click",function(){
	let favDiv = $(this);
	let imgSrc = $(this).children("img").attr("src");
	let notFav = "/images/board/notFavorite.png";
	let fav = "/images/commons/body_form/left_item/default/favorites.png";
	let board_title = $(this).siblings(".board_title").html().trim();
	
	if(imgSrc===notFav){ // 즐겨찾기 등록
		let parent_seq = $(this).attr("data-index");
		$(this).children("img").attr("src",fav);		
		$.ajax({
			url:"/favorite/insertFav",
			data:{parent_seq:parent_seq,
			ori_board_title:board_title},
			method:"POST"
		}).done(function(resp){
			alert("즐겨찾기가 등록되었습니다");
		}).catch(function(resp){
			alert("즐겨찾기 등록에 실패하였습니다");
		});
		
		
	} else if(imgSrc===fav){ // 즐겨찾기 제거
		$(this).children("img").attr("src",notFav);
		let parent_seq = $(this).attr("data-index");
		
		$.ajax({
			url:"/favorite/delFavContents",
			data:{parent_seq:parent_seq,ori_board_title:board_title},
			method:"POST"
		}).done(function(resp){
			alert("즐겨찾기가 제거되었습니다");
			favDiv.removeAttr("data-seq");
			reload();
		}).catch(function(resp){
			alert("즐겨찾기 제거에 실패하였습니다");
		});
	}
});

$("#searchBtn").on("click",function(){
	$("#searchFrm").submit();
});