<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/board_favorite.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/commons/body_form/body_form_default.js" defer></script>
</head>
<body>
	<div class="top">TOP</div>
    <div class="body_form">
        <div class="left_item" id="left_item">
            
        </div>
        <div class="right_item">
        	<div class="content_tab">
            	<form id="searchFrm" action="/board/search">
                	<div class="main_search" id="searchBox">
                        <div class="searchBox">
                           	<div class="searchIconBox">
                                <img src="/images/commons/body_form/left_item/default/search.png" id="searchBtn">
                            </div>
                            <div class="searchTextBox">
                                <input type="text" placeholder="제목 / 작성자 검색" id="searchTextBoxInput" maxlength='30' name="searchText">
                            </div>
                        </div>
                    </div>
                </form>
                <div class="board_list">
                    <div class="board_top">
                        <select name="board_title">
                            <option value="all">모든 게시글</option>
                            <option value="mine">내가 쓴 게시글</option>
                        </select>
                    </div>
                    <div class="board_body">
                    	<c:choose>
                    		<c:when test="${boardContentsList.size()==0 }">
                    			<div class="board_contents_list" style="display:flex;align-items:center;justify-content:center;">게시글 없음</div>
                    		</c:when>
                    		<c:otherwise>
                    			<c:if test="${noticeList.size()>0}">
                    				<c:forEach items="${noticeList }" var="i">
                    					<div class="board_contents_list" id="noticeList">
                            				<div class="favorite" data-index='${i.seq }'>
                            					<img src="/images/board/notice.png" >
                            				</div>
                            				<div class="board_contents_title">
                            					<a href="/board/toContentsBoard?seq=${i.seq }">
                            						<c:if test="${i.header==''}">
                            							<span>${i.header }&nbsp;&nbsp;</span>
                            						</c:if>
                            						${i.title }
                            					</a>
                            				</div>
                           					<div class="board_contents_writer">
                           						<c:choose>
                            						<c:when test="${name_type eq 'identity' }">
                            								${i.writer }
                            						</c:when>
                            						<c:otherwise>
                            								익명
                            						</c:otherwise>
                            					</c:choose>
                           					</div>
                            				<div class="view_count">${i.view_count }</div>
                          	  				<div class="board_title">${i.board_title}</div>
                            				<div class="board_contents_write_date">${i.write_date }</div>
                        				</div>
                    				</c:forEach>
                    			</c:if>
                    			<c:forEach var="i" items="${boardContentsList}">
                    				<div class="board_contents_list">
                            			<div class="favorite" data-index='${i.seq }'>
                            				<c:if test="${board_title ne '중요게시물' }">
                            					<c:choose>
                            						<c:when test="${i.favorite}">
                            							<img src="/images/commons/body_form/left_item/default/favorites.png" >
                            						</c:when>
                            						<c:otherwise>
                            							<img src="/images/board/notFavorite.png">
                            						</c:otherwise>
                            					</c:choose> 	
                            				</c:if>                           				
                            			</div>
                            			<div class="board_contents_title">
                            				<c:choose>
                            					<c:when test="${board_title == '중요게시물'}">
                            						<a href="/board/toContentsBoard?seq=${i.seq }&board_title=${i.board_title}">
                            							<c:if test="${i.header==''}">
                            								<span>${i.header }&nbsp;&nbsp;</span>
                            							</c:if>
                            							${i.title }
                            						</a>
                            					</c:when>
                            					<c:otherwise>
                            						<a href="/board/toContentsBoard?seq=${i.seq }">
                            							<c:if test="${i.header==''}">
                            								<span>${i.header }&nbsp;&nbsp;</span>
                            							</c:if>
                            							${i.title }
                            						</a>
                            					</c:otherwise>
                            				</c:choose>
                            			</div>
                           				<div class="board_contents_writer">
                           					<c:choose>
                            					<c:when test="${name_type eq 'identity' or (board_title eq '중요게시물' and i.name_type eq 'identity')}">
                            							${i.writer }
                            					</c:when>
                            					<c:otherwise>
                            							익명
                            					</c:otherwise>
                            				</c:choose>
                           				</div>
                            			<div class="view_count">${i.view_count }</div>
                          	  			<div class="board_title">${i.board_title}</div>
                            			<div class="board_contents_write_date">${i.write_date }</div>
                        			</div>
                    			</c:forEach>
                    		</c:otherwise>
                    	</c:choose>
                    </div>
                </div>
                <div id="boardListFooter">

                </div>
            </div>
        </div>
    </div>
	<script src="/js/board/sideBar.js" defer></script>
    <script src="/js/board/contentsList_board.js" defer></script>
    <script>
    	if("${auth}"!=''){
    		alert("해당 게시판에 접근할 수 있는 권한이 없습니다");
    	}
    
		if(${recordTotalCount}>0){
			let boardListFooter = document.getElementById("boardListFooter");
			let recordTotalCount = ${recordTotalCount};
			let recordCountPerPage = ${recordCountPerPage};
			let naviCountPerPage = ${naviCountPerPage};
			let currentPage = ${currentPage};
		
			let pageTotalCount = 0;

			if((recordTotalCount%recordCountPerPage)>0){
				pageTotalCount = Math.floor( recordTotalCount/recordCountPerPage ) + 1;
			} else {
				pageTotalCount =  recordTotalCount/ recordCountPerPage;
			}

			if(currentPage<1){currenReplyPage=1;}
			else if(currentPage>pageTotalCount){currentPage=pageTotalCount;}
		
			let startNavi = Math.floor(( currentPage - 1 ) / naviCountPerPage) * naviCountPerPage + 1;
			let endNavi = startNavi + naviCountPerPage - 1;
			if(endNavi > pageTotalCount) {endNavi = pageTotalCount;}

			let needPrev = true;
			let needNext = true;
		
			if( startNavi == 1 ) { needPrev=false; }
			if( endNavi == pageTotalCount ) { needNext = false; }
		
			if(needPrev) {
				$("#boardListFooter").append("<a href='/board/toBoard?cPage="+(startNavi-1)+">"+ "<<"+ "</a>");
			}
			for(let i = startNavi; i<=endNavi; i++) {
				$("#boardListFooter").append("<a href='/board/toBoard?cPage="+ i +"' class='naviNum'>" + i + "</a>");
			}
			if(needNext) {$("#boardListFooter").append("<a href='/board/toBoard?cPage="+(endNavi+1)+"&board_title=${board_title}'>"+ ">>"+ "</a>");}
		
			let childNum = currentPage;
			if(childNum>10){childNum = childNum-9;}
			$(".naviNum:nth-child("+childNum+")").css("color","red").css("text-decoration","underline");
		}
	</script>
</body>
</html>