let prevHeader = [];
let board_title = $("#prevBoardTitle").val();
$(".authority").css("display","none");
$(".header").css("display","none");

$.ajax({ //기존 읽기 / 쓰기 권한 담아두기
	url:"/authority/selectAuthMember",
	data:{board_title:board_title},
	method:"POST"
}).done(function(resp){
	authority = resp;
	$(".authority").css("display","block");
});

$.ajax({ // 말머리 담아두기
	url:"/header/selectHeader",
	data:{"board_title":board_title},
	method:"POST"
}).done(function(resp){
	headerList = [...resp];
	prevHeader = [...resp];
	console.log(prevHeader);
	console.log(headerList);
	$(".header").css("display","block");
});

$("#editFormBtn").on("click",function(){
	console.log(prevHeader);
	console.log(headerList);
	let change = false;
	if(prevHeader.length==headerList.length){
		for(let i=0;i<prevHeader.length;i++){
			console.log("i"+prevHeader[i]);
			for(let j=0;j<prevHeader.length;j++){
				console.log("j"+headerList[j]);
				if(prevHeader[i]!=headerList[j]){
					change = true;
					break;
					console.log("같음");
				}
			}
			if(change){break;}
		}
		if(change){$("#changeHeader").val("true")}
	} else{
		$("#changeHeader").val("true");
	}
	console.log("2이전:"+$("#changeHeader").val());
	console.log("2이전:"+prevHeader.length);
	console.log("2이후:"+headerList.length);
	
	if($("#board_title_input").val()==""){
		alert("게시판 이름을 입력하세요");
		$("#board_title_input").focus();
		return false;
	};
	
	if($("input[name='header']:checked").val()=='true' && headerList.length==0){
		alert("말머리를 추가하거나 사용 안함 설정으로 변경해주세요");
		return false;
	}
	
	if(authority.length==0){
		alert("권한은 1명 이상 반드시 추가해야 합니다");
		return false;
	}
	
	let authorityDisplay = $(".authority").css("display");
	let headerDisplay = $(".header").css("display");
	if(authorityDisplay == 'none' || headerDisplay == 'none'){
		alert("페이지가 완전히 로딩되지 않았습니다");
		return false;
	}
	
	let authorityList = authority.map(item=>{
		delete item.name;
		delete item.organization;
		delete item.job_name;
		delete item.position;		
		return item;
	});
	
	$("#authorityList").val(JSON.stringify(authorityList));
	$("#headerList").val(JSON.stringify(headerList));


	$("#frm").submit();
});

