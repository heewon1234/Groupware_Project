<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
	<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
	<link rel="stylesheet" href="/css/commons/topForm.css" />
    <link rel="stylesheet" href="/css/board/mk_board.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/commons/body_form/body_form_default.js"></script>
</head>
<body>
	<div class="background"></div>
    <div class="modal">
        <div>사용자 추가하기</div>
        <div class="member">
            <div class="member_list">
                <div class="member_list_box">
                    <div class="member_list_box_header">직원 목록</div>
                    <div class="member_list_box_body">
                        <div>
                            <div>
                                <img src="/images/commons/body_form/left_item/default/plus.png" />
                                <input type="checkbox"> 관리부
                            </div>
                            <div class="member_dept_detail">
                                <div>
                                    <img src="/images/commons/body_form/left_item/default/plus.png" />
                                    <input type="checkbox"> 인사팀
                                </div>
                            </div>
                            <div class="member_datail">
                                <div>
                                    <input type="checkbox"> 대표이사
                                </div>
                                <div>
                                    <input type="checkbox"> 김대표
                                </div>
                                <div>
                                    <input type="checkbox"> 이대표
                                </div>
                            </div>
                            <div class="member_dept_detail">
                                <div>
                                    <img src="/images/commons/body_form/left_item/default/plus.png" />
                                    <input type="checkbox"> 관리팀
                                </div>
                            </div>
                            <div class="member_datail">
                                <div>
                                    <input type="checkbox"> 대표이사
                                </div>
                                <div>
                                    <input type="checkbox"> 김대표
                                </div>
                                <div>
                                    <input type="checkbox"> 이대표
                                </div>
                            </div>
                            <div>
                                <img src="/images/commons/body_form/left_item/default/plus.png" />
                                <input type="checkbox"> 영업부
                            </div>
                            <div>
                                <img src="/images/commons/body_form/left_item/default/plus.png" />
                                <input type="checkbox"> 생산부
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="member_add">
                <div>
                    <div class="auth_member_list_header">직원 목록</div>
                    <div class="auth_member_list">
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                        <div><span>김대표</span><span>&times;</span></div>
                    </div>
                    <div class="auth_member_reset">
                        <button>모두 취소</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="member_add_buttons">
            <button>취소</button>
            <button>추가</button>
        </div>
    </div>
    <div class="top">TOP</div>
    <div class="body_form">
        <div class="left_item">
            <div class="button_tab">
                <button class="main_button">
                    <span>작성하기</span>
                </button>
            </div>
            <div class="menu_tab">
                <div class="menu_item">
                    <img src="/images/commons/body_form/left_item/default/favorites.png" />
                    <span class="menu_item_text">중요 게시물</span>
                </div>
                <div class="menu_list">
                    <div class="menu_list_button">
                        <img src="/images/commons/body_form/left_item/default/drop.png" />
                        <span class="menu_list_button_text">전사 게시판</span>
                    </div>
                    <div class="menu_list_box">
                        <div class="menu_list_item">
                            <img src="/images/commons/body_form/left_item/default/company.png" />
                            <span class="menu_list_item_text">메뉴 1번</span>
                        </div>
                        <div class="menu_list_item">
                            <img src="/images/commons/body_form/left_item/default/company.png" />
                            <span class="menu_list_item_text">메뉴 2번</span>
                        </div>
                    </div> 
                </div>
                <div class="menu_list">
                    <div class="menu_list_button">
                        <img src="/images/commons/body_form/left_item/default/drop.png" />
                        <span class="menu_list_button_text">그룹 게시판</span>
                    </div>
                    <div class="menu_list_box">
                        <div class="menu_list_item select">
                            <img src="/images/commons/body_form/left_item/default/company.png" />
                            <span class="menu_list_item_text">메뉴 1번</span>
                        </div>
                        <div class="menu_list_item">
                            <img src="/images/commons/body_form/left_item/default/company.png" />
                            <span class="menu_list_item_text">메뉴 2번</span>
                        </div>
                    </div> 
                </div>
                <hr class="div_hr">
                <div class="menu_item">
                    <img src="/images/commons/body_form/left_item/default/plus.png" />
                    <span class="menu_item_text">게시판 만들기</span>
                </div>
                <div class="menu_item">
                    <img src="/images/commons/body_form/left_item/default/trash.png" />
                    <span class="menu_item_text">게시판 관리</span>
                </div>
            </div>
        </div>
        <div class="right_item">
            <div class="test_right">
                <div class="contents_title">게시판 만들기</div>
                <div class="name">
                    <div class="name_text">이름</div>
                    <div class="name_input">
                        <input type="text" placeholder="&nbsp게시판 이름을 입력하세요.">
                    </div>
                </div>
                <div class="board_type">
                    <div class="board_type_text">게시판 종류</div>
                    <div class="board_type_select">
                        <div><input type="radio"> 전사 게시판 - 조직에 속한 모든 사람과 공유 ( 조직없음 제외 )</div>
                        <div><input type="radio"> 그룹 게시판 - 소유자 및 관리자가 지정한 사람과 공유</div>
                    </div>
                </div>
                <div class="name_type">
                    <div class="name_type_text">게시판 유형</div>
                    <div class="name_type_select">
                        <div><input type="radio" name="name_type"> 일반형</div>
                        <div><input type="radio" name="name_type"> 익명형</div>
                    </div>
                </div>
                <div class="authority">
                    <div class="authority_text">사용자 및 권한</div>
                    <div class="authority_member_box">
                        <div class="authority_member_add">
                            <div class="authority_member_count"> 멤버 1 명</div>
                            <div class="authority_member_add_btn"> + 멤버 추가</div>
                        </div>           
                        <div class="authority_member_list_box">
                            <div class="authority_member_list_info">
                                <div>이름</div>
                                <div>기본 권한 : 읽기</div>
                            </div>
                            <div class="authority_member_list">
                                <div class="auth_member">
                                    <div>대표이사</div>
                                    <div>
                                        <div><input type="checkbox">&nbsp;쓰기</div>
                                        <div>x</div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="header">
                    <div class="header_text">말머리 설정</div>
                    <div class="header_type">
                        <div><input type="radio" name="header"> 사용</div>
                        <div><input type="radio" name="header"> 사용 안 함</div>
                    </div>
                </div>
                <div class="buttons">
                    <button>취소</button>
                    <button>생성</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>