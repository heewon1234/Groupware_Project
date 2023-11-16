<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>즐겨찾기</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/board_favorite.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/commons/body_form/body_form_default.js"></script>
    
	
</head>
<body>
	<div class="top" id="top_container"></div>
    <div class="body_form">
        <div class="left_item" id="left_item">
            
        </div>
        <div class="right_item">
            <div class="content_tab">
                <div class="searchBox">
                    <div class="searchIconBox">
                        <img src="/images/board/search.png">
                    </div>
                    <div class="searchTextBox">
                        <input type="text" placeholder="게시글 검색">
                    </div>
                </div>
                <div class="board_list">
                    <div class="board_top">
                        <select name="board_title">
                            <option value="all">모든 게시글</option>
                            <option value="mine">내가 쓴 게시글</option>
                        </select>
                    </div>
                    <div class="board_body">
                        <div class="board_contents_list">
                            <div class="favorite">
                                <img src="/images/board/notFavorite.png">
                            </div>
                            <div class="board_contents_title">[중요]글1</div>
                            <div class="board_contents_writer">대표이사</div>
                            <div class="view_count">2</div>
                            <div class="board_title">테스트1</div>
                            <div class="board_contents_write_date">00:06</div>
                        </div>
                        <div class="board_contents_list">
                            <div class="favorite">
                                <img src="/images/board/notFavorite.png">
                            </div>
                            <div class="board_contents_title">[중요]글1</div>
                            <div class="board_contents_writer">대표이사</div>
                            <div class="view_count">2</div>
                            <div class="board_title">테스트1</div>
                            <div class="board_contents_write_date">00:06</div>
                        </div>
                    </div>
                </div>
                <div class="board_bottom">

                </div>
            </div>
        </div>
    </div>
	<script src="/js/board/sideBar.js" defer></script>
    <script src="/js/board/favorite_board.js" defer></script>
</body>
</html>