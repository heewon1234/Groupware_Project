$(document).ready(function() {
	$("#left_item").load("/board/sideBar");
			
	$("#summernote").summernote({
		width: 1500,
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
	surveyInputBox.append(itemInput);
	
	let imgDiv = $("<div>");
	let img = $("<img>");
	img.attr("src","/images/commons/body_form/left_item/default/plus.png");
	img.addClass("add_item_btn");
	imgDiv.append(img);
	
	surveyContents.append(surveyText);
	surveyContents.append(surveyInputBox);
	surveyContents.append(imgDiv);
	
	$("#surveyBox").append(surveyContents);
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
	
	if($("#header").val()=="말머리 없음"){
		$("#header").val("");
	}
})
