let authority = [];
let headerList = [];

$(".authority_member_add_btn").on("click",function(){
	$(".modal").css("display","block");
})

$("#member_add_cancel").on("click",function(){
	$(".modal").css("display","none");
})

$("#member_add_complete").on("click",function(){
	$(".modal").css("display","none");
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
		authority = authority.filter(item => item.organization !== organization);	
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
	let job_name = $(this).parent("div").text().trim();
	let organization = $(this).parents(".member_dept_detail_box").prev("div").text().trim();
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
	}
	
	
});

$(document).on("change",".name_check",function(){
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
		let parents = $(this).parents(".member_datail_box").prev("div")
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
	
})