$(document).ready(function() {
			
	$("#summernote").summernote({
		width: '100%',
	    height: null,
	    minHeight: 380,
	    maxHeight: null,
	    focus: true,
	    lang: "ko-KR",
	    placeholder: '내용을 입력하세요 ( 1000 글자 이하 )',
	    callbacks: {
	    	onImageUpload: function (files) {
	        	let formData = new FormData();
	            for (let i = 0; i < files.length; i++) {
	            	formData.append("fileList", files[i]);
	            }
	            $.ajax({
	            	url: "/file/upload",
	                method: "post",
	                data: formData,
	                processData: false, // 인코딩 처리 금지 ( processData, contentType : false = multipart/form-data)
	                contentType: false, // 파일 타입 그대로 유지

	            }).done(function (resp) {	             	
	            	for (let i = 0; i < files.length; i++) {
	                	let img = $("<img>");
	                    img.attr("type", "file");
	                    img.attr("src", resp[i]);
	                    $("#summernote").summernote('insertNode', img[0]);
	                }

	        	})

			},
			onMediaDelete:function($target, editor, $editable){
	        	$.ajax({
	           		url:"/file/deleteServerFile",
	           		data:{"sys_name":$target[0].getAttribute("src").split("/uploads/")[1]}
	           	}) 			
	    	}
			
		}
	});
});

$("#board_title").change(function(){
	$("#header").empty();
	
	if($(this).val()=="게시판선택"){
		$("#header").prop("disabled",true);
		return false;
	}
	
	$("#header").prop("disabled",false);
	
	let selectOption = $("<option>");
	selectOption.prop("selected",true);
	selectOption.append("말머리 없음");
	$("#header").append(selectOption);
	
	$.ajax({
		url:"/header/selectHeader",
		data:{"board_title":$(this).val()}
	}).done(function(resp){
		
		for(let i=0;i<resp.length;i++){
			let option = $("<option>");
			option.append(resp[i]);
			$("#header").append(option);
		}
	})
});

$("#fileAddBtn").on("click",function(){
	if($("input[type=file]").length>5){ // length는 0부터 시작함
		alert("파일 추가는 5개가 최대입니다");
  	  return;
	}

    let div = $("<div>");
	let inputFile=$("<input>")
    inputFile.attr("type","file");
	inputFile.attr("name","fileList");
    let anker = $("<a>");
   	anker.html("X");
    anker.attr("href","#");
         	
    anker.on("click",function(){
    	div.remove();
    })
    div.prepend(inputFile);
    div.append(anker);
    $("#file_box_input_file").append(div);
});

$("input[name='useSurvey']").change(function(){
	if($(this).val()==='true'){
		$("#surveyBox").css("display","block");
	}else{
		$("#surveyBox").css("display","none");
	}
	
})

$(document).on("click",".add_item_btn",function(){
	if($("input[name='items']").length>4){ 
		alert("항목 추가는 5개가 최대입니다");
  	  	return false;
	}
	$(".surveyContents img").attr("src","/images/board/remove.png").attr("class","del_item_btn");
	let surveyContents = $("<div>");
	surveyContents.addClass("surveyContents");
	
	let surveyText = $("<div>");
	surveyText.addClass("surveyText");
	surveyText.html("항목");
	
	let surveyInputBox = $("<div>");
	let itemInput = $("<input>");
	itemInput.attr("placeholder","항목을 입력해주세요");
	itemInput.attr("name","items");
	itemInput.attr("type","text");
	itemInput.attr("maxlength","100");
	itemInput.addClass("form-control");
	surveyInputBox.append(itemInput);
	
	let imgDiv = $("<div>");
	let img = $("<img>");
	if($("input[name='items']").length==4){
		img.attr("src","/images/board/remove.png");
		img.addClass("del_item_btn");
	} else{
		img.attr("src","/images/board/plus.png");
		img.addClass("add_item_btn");
	}
	
	
	imgDiv.append(img);
	
	surveyContents.append(surveyText);
	surveyContents.append(surveyInputBox);
	surveyContents.append(imgDiv);
	
	$("#surveyBox").append(surveyContents);
});

$(document).on("click",".del_item_btn",function(){
	if($("input[name='items']").length==2){
		alert("설문조사 항목은 반드시 2개 이상이어야 합니다");
		return false;
	}
	
	$(this).parents(".surveyContents").remove();
	$(".surveyContents img").attr("src","/images/board/remove.png").attr("class","del_item_btn");
	$(".del_item_btn").last().attr("src","/images/board/plus.png").attr("class","add_item_btn");
	
});

$("input[name='notice']").change(function(){
	if($(this).prop("checked")){
		$(this).val("true");
	} else{
		$(this).val("false");
	}
});

$("#frmBtn").on("click",function(){
	if($("#board_title").val()=="게시판선택"){
		alert("게시판을 선택해주세요");
		return false;
	}

	if($("input[name='title']").val()==""){
		alert("제목을 입력해주세요");
		$("input[name='title']").focus();
		return false;
	}
	
	if($("#summernote").val()==""){
		alert("내용을 입력해주세요");
		return false;
	}
	
	if($("#summernote").val().length>2000){
		console.log($("#summernote").val());
		alert("게시글 내용은 최대 2000자 입니다");
		return false;
	}
	
	if($("input[name='useSurvey']:checked").val()=="true"){

		if($("input[name='survey_question']").val()==''){
			alert("질문을 입력해주세요");
			return false;
		}
		
		let itemValues = [];
		
		let items = $(document).find("input[name='items']");
		items.map((index,item)=>itemValues.push(item.value))
		
		if(itemValues.filter(item=>item != '').length<2){
			alert("설문조사 항목은 2개 이상 입력해야합니다");
			return false;
		}
	}

	if($("#header").val()=="말머리 없음"){
		$("#header").val("");
	}
})

$("#backBtn").on("click",function(){
	location.href="/board/toBoard";
});
