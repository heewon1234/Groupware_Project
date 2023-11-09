let authority = [];
let headerList = [];
let tempAuthority = [];

$("#member_add_complete").on("click",function(){
	tempAuthority = authority;
	$(".modal").css("display","none");
	$("#authority_member_list").empty();
	
	for(let i=0;i<authority.length;i++){
		let authDiv = $("<div>");
		authDiv.addClass("auth_member"); 
		authDiv.attr("data-index",i);
		let NameDiv = $("<div>");
		NameDiv.html(authority[i].name);
		
		let div = $("<div>");
		
		let inputDiv = $("<div>");
		let input = $("<input>");
		input.attr("type","checkbox");
		inputDiv.append(input);
		inputDiv.append("&nbsp;"+"쓰기");
		
		let delDiv = $("<div>");
		delDiv.append("x");
		delDiv.addClass("authDelBtn");

		div.append(inputDiv);
		div.append(delDiv);
		
		authDiv.append(NameDiv);
		authDiv.append(div);
		
		$("#authority_member_list").append(authDiv);	
	}
	
	
	
	
})

$("#member_add_cancel").on("click",function(){
	authority = tempAuthority;
	$(".modal").css("display","none");
	
	$("#authority_member_list").empty();
	
	for(let i=0;i<authority.length;i++){
		let authDiv = $("<div>");
		authDiv.addClass("auth_member"); 
		authDiv.attr("data-index",i);
		let NameDiv = $("<div>");
		NameDiv.html(authority[i].name);
		
		let div = $("<div>");
		
		let inputDiv = $("<div>");
		let input = $("<input>");
		input.attr("type","checkbox");
		inputDiv.append(input);
		inputDiv.append("&nbps;쓰기");
		
		let delDiv = $("<div>");
		delDiv.append("x");
		delDiv.addClass("authDelBtn");
		div.append(inputDiv);
		div.append(delDiv);
		
		authDiv.append(NameDiv);
		authDiv.append(div);
		
		$("#authority_member_list").append(authDiv);	
	}
	
})

$(".authority_member_add_btn").on("click",function(){
	$(".modal").css("display","block");
	authority = [];
	$("#member_list_box_body").find("input[type='checkbox']").prop("checked",false);
	$("#auth_member_list").empty();
})

// 모달창 - 멤버 추가 버튼 이벤트
$(".add_dept_btn").on("click",function(){
	let inputCheck = $(this).parent("div");
	let inputCheckBoolean = $(this).siblings("input").prop("checked");
	if($(this).attr("src")=="/images/commons/body_form/left_item/default/plus.png"){
		$(this).attr("src","/images/board/del.png");
		let organization = $(this).parent("div").text().trim();
		let orgDiv = $(this).parent("div");
		$(this).attr("src","/images/board/del.png");
		$.ajax({
			url:"/board/selectByOrganization",
			data:{"organization":organization}
		}).done(function(resp){
			let completeDiv = $("<div>");
			completeDiv.addClass("member_dept_detail_box");
			for(let i=0;i<resp.length;i++){
				let member_dept_detail = $("<div>");
				member_dept_detail.addClass("member_dept_detail");
				let div = $("<div>");
			
				let img = $("<img>");
				img.addClass("add_member_btn");
				img.attr("src","/images/commons/body_form/left_item/default/plus.png");
			
				let input = $("<input>");
				input.attr("type","checkbox");
				input.addClass("jobName_check");
				
				div.append("&nbsp;");
				div.append(img);
				div.append("&nbsp;");
				div.append(input);
				div.append("&nbsp;"+resp[i]);
			
				member_dept_detail.append(div);
				completeDiv.append(member_dept_detail);
			}
			orgDiv.after(completeDiv);	
			if(inputCheckBoolean){
				inputCheck = inputCheck.next(".member_dept_detail_box");
				inputCheck.find("input[type='checkbox']").prop("checked",true);
			}		
		});

	} else{
		$(this).attr("src","/images/commons/body_form/left_item/default/plus.png");
		$(this).parent("div").next(".member_dept_detail_box").remove();
	}

	

	
});

$(document).on("click",".add_member_btn",function(){
	let inputCheck = $(this).parent("div");
	let inputCheckBoolean = $(this).siblings("input").prop("checked");
	if($(this).attr("src")=="/images/commons/body_form/left_item/default/plus.png"){
		$(this).attr("src","/images/board/del.png");
		let job_name = $(this).parent("div").text().trim();
		let jobDiv = $(this).parent("div");
		let completeDiv = $("<div>");
		completeDiv.addClass("member_datail_box");
		$.ajax({
			url:"/board/selectByJobName",
			data:{"job_name":job_name}
		}).done(function(resp){
			for(let i=0;i<resp.length;i++){
			
				let member_detail = $("<div>");
				member_detail.addClass("member_datail");
		
				let div = $("<div>");
		
				let input = $("<input>");
				input.attr("type","checkbox");
				input.addClass("name_check");
		
				div.append(input);
				div.append("&nbsp;" + resp[i]);
		
				member_detail.append(div);
				completeDiv.append(member_detail);
			}
			
			jobDiv.after(completeDiv);
			
			if(inputCheckBoolean){
				inputCheck = inputCheck.next(".member_datail_box");
				inputCheck.find("input[type='checkbox']").prop("checked",true);
			}		
			
		});
	} else{
		$(this).attr("src","/images/commons/body_form/left_item/default/plus.png");
		$(this).parent("div").next(".member_datail_box").remove();
	}
		
})

// input check 이벤트

$(document).on("change",".organization_check",function(){
	let organization = $(this).parent("div").text().trim();
	authority = authority.filter(item => item.organization !== organization);
	if($(this).prop('checked')){		
		$.ajax({
			url:"/board/selectMemberByOrganization",
			data:{"organization":organization}
		}).done(function(resp){
			for(let i=0;i<resp.length;i++){
				authority.push({"id":resp[i].id,"name":resp[i].name,"authority":"읽기","organization":resp[i].organization,"job_name":resp[i].job_name,"position":resp[i].position});
			}
			
			$("#auth_member_list").empty();
			for(let i=0;i<authority.length;i++){
				let div = $("<div>");
				let spanName = $("<span>");
				spanName.append(authority[i].name);
				let spanDel = $("<span>");
				spanDel.append("&times;");
				div.append(spanName);
				div.append(spanDel);
				$("#auth_member_list").append(div);
			}
		})
		
		let inputCheck = $(this).parent("div").next(".member_dept_detail_box");
		inputCheck.find("input[type='checkbox']").prop("checked",true);
		
	} else{	
		let inputCheck = $(this).parent("div").next(".member_dept_detail_box");
		inputCheck.find("input[type='checkbox']").prop("checked",false);
		
		$("#auth_member_list").empty();
		for(let i=0;i<authority.length;i++){
			let div = $("<div>");
			let spanName = $("<span>");
			spanName.append(authority[i].name);
			let spanDel = $("<span>");
			spanDel.append("&times;");
			div.append(spanName);
			div.append(spanDel);
			$("#auth_member_list").append(div);
		}
	}
	
})

$(document).on("change",".jobName_check",function(){
	let parents = $(this).parents(".member_dept_detail_box").prev("div");
	
	let job_name = $(this).parent("div").text().trim();
	let organization = $(this).parents(".member_dept_detail_box").prev("div").text().trim();
	
	let inputBox = $(this).parents(".member_dept_detail_box");
	let inputLength = inputBox.find("input[type='checkbox']");
	let inputCheckLength = inputLength.filter(':checked');
	
	if(inputLength.length === inputCheckLength.length){
		parents.find("input[type='checkbox']").prop("checked",true);
	}
	
	if($(this).prop('checked')){
		$.ajax({
			url:"/board/selectMemberByOrganizationAndJobName",
			data:{"job_name":job_name,"organization":organization}
		}).done(function(resp){
			for(let i=0;i<resp.length;i++){
				authority.push({"id":resp[i].id,"name":resp[i].name,"authority":"읽기","organization":resp[i].organization,"job_name":resp[i].job_name,"position":resp[i].position});
			}
			
			$("#auth_member_list").empty();
			for(let i=0;i<authority.length;i++){
				let div = $("<div>");
				let spanName = $("<span>");
				spanName.append(authority[i].name);
				let spanDel = $("<span>");
				spanDel.append("&times;");
				div.append(spanName);
				div.append(spanDel);
				$("#auth_member_list").append(div);
			}
		})
		let inputCheck = $(this).parent("div").next(".member_datail_box");
		console.log(inputCheck);
		inputCheck.find("input[type='checkbox']").prop("checked",true);
	} else{		
		authority = authority.filter(item=>item.job_name!==job_name && item.authority !== authority);
		let inputCheck = $(this).parent("div").next(".member_datail_box");
		inputCheck.find("input[type='checkbox']").prop("checked",false);
		
		$("#auth_member_list").empty();
		for(let i=0;i<authority.length;i++){
			let div = $("<div>");
			let spanName = $("<span>");
			spanName.append(authority[i].name);
			let spanDel = $("<span>");
			spanDel.append("&times;");
			div.append(spanName);
			div.append(spanDel);
			$("#auth_member_list").append(div);
		}
		parents.find("input[type='checkbox']").prop("checked",false);
	}
	
	
});

$(document).on("change",".name_check",function(){
	let parents = $(this).parents(".member_datail_box").prev("div");
	
	let inputBox = $(this).parents(".member_datail_box");
	let inputLength = inputBox.find("input[type='checkbox']");
	let inputCheckLength = inputLength.filter(':checked');
	
	if(inputLength.length === inputCheckLength.length){
		parents.find("input[type='checkbox']").prop("checked",true);
	}
	
	
	let name = $(this).parent("div").text().trim();
	let job_name = $(this).parents(".member_datail_box").prev("div").text().trim();
	console.log(job_name);
	let organization = $(this).parents(".member_dept_detail_box").prev("div").text().trim();
	if($(this).prop("checked")){
		$.ajax({
			url:"/board/selectMemberByName",
			data:{"organization":organization,"job_name":job_name,"name":name}
		}).done(function(resp){
			authority.push({"id":resp.id,"name":resp.name,"authority":"읽기","organization":resp.organization,"job_name":resp.job_name,"position":resp.position});
			
			$("#auth_member_list").empty();
			for(let i=0;i<authority.length;i++){
				let div = $("<div>");
				let spanName = $("<span>");
				spanName.append(authority[i].name);
				let spanDel = $("<span>");
				spanDel.append("&times;");
				div.append(spanName);
				div.append(spanDel);
				$("#auth_member_list").append(div);
			}
		})
	} else{
		
		console.log(parents.text().trim());
		authority = authority.filter(item=>item.job_name!==job_name && item.authority !== authority && item.name !== name);
		$("#auth_member_list").empty();
		for(let i=0;i<authority.length;i++){
			let div = $("<div>");
			let spanName = $("<span>");
			spanName.append(authority[i].name);
			let spanDel = $("<span>");
			spanDel.append("&times;");
			div.append(spanName);
			div.append(spanDel);
			$("#auth_member_list").append(div);
		}
		parents.find("input[type='checkbox']").prop("checked",false);
	
	}
	
});

$("#auth_member_reset_btn").on("click",function(){
	$("#member_list_box_body").find("input[type='checkbox']").prop("checked",false);
	authority = [];
	$("#auth_member_list").empty();
})


// 멤버 제거
$(document).on("click",".authDelBtn",function(resp){
	let index = $(this).parents(".auth_member").attr("data-index");
	console.log(index);
	authority.splice(index,1);
	$(this).parents(".auth_member").remove();
});

//말머리 추가
$(".header_input_check").change(function(){
	console.log($(this).val());
	if($(this).val()=='true'){
		$("#headerBox").css("display","block");
	} else{
		$("#headerBox").css("display","none");
	}
});

$("#header_add_input").on("keydown",function(e){
	if(e.key=='Enter'){return false;}
})

$("#header_add_btn").on("click",function(){
	let text = $("#header_add_input").val().trim();
	if(text==""){
		alert("값을 입력해주세요");
		$("#header_add_input").focus();
		return false;
	}
	
	for(let i=0;i<headerList.length;i++){
		if(headerList[i]===text){
			alert("말머리가 중복되었습니다");
			$("#header_add_input").val("");
			$("#header_add_input").focus();
			return false;
		}
	}
	
	$("#header_add_input").val("");
	headerList.push(text);
	
	let boxDiv = $("<div>");
	boxDiv.addClass("header_list_box");
	boxDiv.attr("data-index",headerList.length-1);
	
	let listDiv = $("<div>");
	listDiv.addClass("header_list");
	
	let textDiv = $("<div>");
	let span = $("<span>");
	span.append("-&nbsp;");
	textDiv.append(span);
	textDiv.append(text);
	
	let imgDiv = $("<div>");
	let img = $("<img>");
	img.attr("src","/images/board/del.png");
	img.addClass("header_del_btn");
	imgDiv.append(img);
	
	listDiv.append(textDiv);
	listDiv.append(imgDiv);
	
	boxDiv.append(listDiv);

	$("#header_list_border").append(boxDiv);

});

$(document).on("click",".header_del_btn",function(){
	let index = $(this).parents(".header_list_box").attr("data-index");
	$(this).parents(".header_list_box").remove();
	headerList.splice(index,1);
	console.log(headerList);
})

//submit
$("#frmBtn").on("click",function(){
	
	if($("#board_title_input").val()==""){
		alert("게시판 이름을 입력하세요");
		$("#board_title_input").focus();
		return false;
	}
	
	
	let authorityList = authority.map(item=>{
		delete item.name;
		delete item.organization;
		delete item.job_name;
		delete item.position;
		
		return item;
	})
	$("#authorityList").val(JSON.stringify(authorityList));
	$("#headerList").val(JSON.stringify(headerList));
	$("#frm").submit();
})