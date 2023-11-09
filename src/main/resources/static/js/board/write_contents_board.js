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