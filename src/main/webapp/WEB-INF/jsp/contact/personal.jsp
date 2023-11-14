<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>

    <link rel="stylesheet" href="/css/contact/contact.css" />
    <link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />

    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <script src="/js/contact/contact.js"></script>
    <script src="/js/commons/body_form/body_form_default.js"></script>


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

</head>

<body>

    <div class="modal_form">

        <div class="modal_background"></div>

        <form class="modal_contact_add_form" method="post" id="contact_form">
            <div class="modal_contact_add">
                <div class="modal_title">주소 추가</div>
                <div class="modal_body">
                    <div class="modal_body_content">
                        <div class="modal_body_content_toggle">
                            <div class="modal_toggle_button personal select">개인 주소록</div>
                            <div class="modal_toggle_button share">공유 주소록</div>
                        </div>
                        <div class="modal_body_content_form">

                            <div class="modal_body_content_input">
                                <div class="modal_body_content_input_left">이름 *</div>
                                <div class="modal_body_content_input_right">
                                    <div class="input-group mb-0">
                                        <input type="text" name="name" class="form-control" placeholder="이름을 입력하세요." id="modal_body_content_input_right_name" style="font-size: 14px; height: 34px; width: 338px; padding: 0 10px;" maxlength="50">
                                    </div>
                                </div>
                            </div>

                            <div class="modal_body_content_input">
                                <div class="modal_body_content_input_left">이메일</div>
                                <div class="modal_body_content_input_right">
                                    <div class="input-group mb-0">
                                        <input type="text" name="email" class="form-control" placeholder="이메일을 입력하세요." style="font-size: 14px; height: 34px; width: 338px; padding: 0 10px;" maxlength="50">
                                    </div>
                                </div>
                            </div>

                            <div class="modal_body_content_input">
                                <div class="modal_body_content_input_left">전화</div>
                                <div class="modal_body_content_input_right">
                                    <div class="input-group mb-0">
                                        <input type="text" name="phone" class="form-control" placeholder="전화번호를 입력하세요." style="font-size: 14px; height: 34px; width: 338px; padding: 0 10px;" maxlength="50">
                                    </div>
                                </div>
                            </div>

                            <div class="modal_body_content_input">
                                <div class="modal_body_content_input_left">태그</div>
                                <div class="modal_body_content_input_right tag">
                                    <div class="modal_tag">
                                        <span class="modal_tag_left_text">선택</span>
                                        <div class="modal_tag_right_drop">
                                            <img src="/images/commons/body_form/left_item/default/drop.png">
                                        </div>
                                    </div>

                                    <!-- 여기가 리스트 받아오는 곳 -->
                                    <div class="modal_tag_select_list"></div>

                                    <!-- 선택된 리스트들이 들어가는 곳 -->
                                    <div class="modal_tag_list"></div>

                                    <!-- 숨김 처리 하는 태그 리스트 담을 곳 -->
                                    <div class="hidden_tag_list">
                                        <input type="hidden" id="hidden_tag_list" name="tag">
                                    </div>
                                </div>
                                <div class="modal_body_content_input_bonus">
                                    <span class="modal_body_content_input_bonus_text">새 태그 만들기</span>
                                </div>

                            </div>

                        </div>

                        <div class="modal_body_content_form_plus">


                            <div class="modal_body_content_input">
                                <div class="modal_body_content_input_left">회사</div>
                                <div class="modal_body_content_input_right">
                                    <div class="input-group mb-0">
                                        <input type="text" name="companyName" class="form-control" placeholder="회사" style="font-size: 14px; height: 34px; max-width: 120px; padding: 0 10px;" maxlength="50">
                                        <input type="text" name="companyRank" class="form-control" placeholder="부서" style="font-size: 14px; height: 34px; max-width: 120px; padding: 0 10px; margin: 0 0 0 10px;" maxlength="50">
                                        <input type="text" name="companyDepartment" class="form-control" placeholder="직급" style="font-size: 14px; height: 34px; max-width: 118px; padding: 0 10px; margin: 0 0 0 10px;" maxlength="50">
                                    </div>
                                </div>
                            </div>

                            <div class="modal_body_content_input">
                                <div class="modal_body_content_input_left">주소</div>
                                <div class="modal_body_content_input_right">
                                    <div class="input-group mb-0">
                                        <input type="text" name="address" class="form-control" placeholder="주소를 입력하세요." style="font-size: 14px; height: 34px; width: 378px; padding: 0 10px;" maxlength="50">
                                    </div>
                                </div>
                            </div>

                            <div class="modal_body_content_input">
                                <div class="modal_body_content_input_left">홈페이지</div>
                                <div class="modal_body_content_input_right">
                                    <div class="input-group mb-0">
                                        <input type="text" name="link" class="form-control" placeholder="홈페이지 주소를 입력하세요." style="font-size: 14px; height: 34px; width: 378px; padding: 0 10px;" maxlength="50">
                                    </div>
                                </div>
                            </div>

                            <div class="modal_body_content_input">
                                <div class="modal_body_content_input_left">생일</div>
                                <div class="modal_body_content_input_right">
                                    <div class="input-group mb-0">
                                        <input type="text" name="birthday" class="form-control" placeholder="YYYYMMDD" style="font-size: 14px; height: 34px; width: 378px; padding: 0 10px;" maxlength="50">
                                    </div>
                                </div>
                            </div>

                            <div class="modal_body_content_input">
                                <div class="modal_body_content_input_left">메모</div>
                                <div class="modal_body_content_input_right">
                                    <div class="input-group mb-0">
                                        <textarea name="memo" class="modal_body_content_textarea" placeholder="내용을 입력하세요."></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>



                    </div>

                    <div class="modal_body_content_add">

                        <div class="modal_body_content_plus">
                            <img src="/images/contact/plus.png">입력 항목 추가
                        </div>

                        <div class="modal_body_content_minus">
                            <img src="/images/contact/minus.png">추가 입력 항목 닫기
                        </div>
                    </div>

                    <div class="modal_body_content_form_edit">
                        <span class="modal_body_content_form_edit_text bold">편집 허용</span>
                        <fieldset class="modal_body_content_form_edit_toggle">
                            <label> <input role="switch" type="checkbox" class="edit_toggle" />
                            </label>
                        </fieldset>
                        <span class="modal_body_content_form_edit_text" id="edit_toggle_text">허용 안함</span>
                        <input type="hidden" name="edit" id="hidden_edit_toggle_value" value="false">
                    </div>


                </div>
                <div class="modal_footer right">
                    <button class="button_cancel float_left" id="modal_cancel_button">취소</button>
                    <button class="button_apply float_right permit" id="modal_apply_button">저장</button>
                    <button class="button_apply float_right permit" id="modal_apply_list_button">저장 후 계속
                        추가</button>
                </div>
            </div>

        </form>


        <div class="modal_tag_add">
            <div class="modal_title">
                <span>태그 만들기</span>
            </div>
            <div class="modal_body">
                <div class="input-group mb-0">
                    <input type="text" id="modal_new_tag_input" class="form-control" placeholder="새로 생성할 태그명을 입력하세요.">
                </div>
                <div class="modal_tag_duplication_error">
                    <img src="/images/contact/dup_error.png"> 태그 이름이 이미 있습니다.
                </div>
            </div>
            <div class="modal_footer">
                <button class="button_cancel" id="button_cancel_tag">취소</button>
                <button class="button_apply permit" id="button_apply_tag" style="margin-left: 14px">저장</button>
            </div>
        </div>

    </div>

    <!-- TOP 꼭 열어보세요.-->
    <div class="top_form">
        <!-- 실제로는 이 곳에 TOP 글씨를 지우시고 TOP을 불러오는 코드를 넣으시면 됩니다. -->
        TOP
    </div>

    <!-- 여기서부터 -->
    >
    <div class="body_form">
        <!-- 왼쪽 부분(메뉴) -->
        <div class="left_item">
            <!-- 최상단에 버튼만 있는 부분 -->
            <div class="button_tab">
                <button class="main_button" id="modal_open_button">
                    <span>작성하기</span>
                </button>
            </div>

            <!-- 메뉴 리스트 있는 부분 -->
            <div class="menu_tab">

                <!-- 일반 메뉴 -->
                <div class="menu_item">
                    <img src="/images/commons/body_form/left_item/default/favorites.png" /> <span class="menu_item_text">중요 주소록</span>
                </div>

                <!-- 드롭 다운 메뉴 (추가 효과 없음) -->
                <div class="menu_list">
                    <div class="menu_list_button">
                        <div class="menu_list_button_drop">
                            <img src="/images/commons/body_form/left_item/default/drop.png" />
                        </div>
                        <span class="menu_list_button_text">개인 주소록</span>
                    </div>
                    <div class="menu_list_box">
                        <div class="menu_list_item">
                            <img src="/images/commons/body_form/left_item/default/company.png"> <span class="menu_list_item_text">전체</span>
                        </div>

                        <!-- 여기 부분에서 포문 돌리면 될듯 -->
                        <div class="menu_list_item">
                            <img src="/images/commons/body_form/left_item/default/company.png">
                            <span class="menu_list_item_text">예시 태그
                            </span>
                            <div class="menu_list_item_plus point">
                                <img src="/images/commons/body_form/left_item/default/point.png">
                            </div>
                        </div>
                        
                        <!-- 예제 돌릴 곳 -->
                            <c:forEach items="${shareList}" var="contact">
       						 <li>${contact.tag}</li>
       						 </c:forEach>
                        
                        


                        <div class="menu_list_item">
                            <img src="/images/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">미등록 태그</span>
                        </div>
                    </div>
                </div>

                <!-- 드롭 다운 메뉴 (리스트 내용에 마우스 올리면 설정 메뉴 나옴) -->
                <div class="menu_list">
                    <div class="menu_list_button">
                        <div class="menu_list_button_drop">
                            <img src="/images/commons/body_form/left_item/default/drop.png" />
                        </div>
                        <span class="menu_list_button_text">공유 주소록</span>
                    </div>
                    <div class="menu_list_box">
                        <div class="menu_list_item">
                            <img src="/images/commons/body_form/left_item/default/company.png"> <span class="menu_list_item_text">전체</span>
                        </div>

                        <!-- 여기 부분에서 포문 돌리면 될듯 -->
                        <div class="menu_list_item">
                            <img src="/images/commons/body_form/left_item/default/company.png">
                            <span class="menu_list_item_text">예시 태그
                            </span>
                            <div class="menu_list_item_plus point">
                                <img src="/images/commons/body_form/left_item/default/point.png">
                            </div>
                        </div>


                        <div class="menu_list_item">
                            <img src="/images/commons/body_form/left_item/default/alarm.png"> <span class="menu_list_item_text">미등록 태그</span>
                        </div>
                    </div>
                </div>

                <!-- 구별선 -->
                <hr class="div_hr">

                <!-- 일반 메뉴 -->
                <div class="menu_item">
                    <img class="menu_item_img" src="/images/commons/body_form/left_item/default/trash.png" /> <span class="menu_item_text">휴지통</span>
                </div>


            </div>

        </div>

        <!-- 오른쪽 부분(해당 메뉴의 주요 콘텐츠) -->
        <div class="right_item">
            <div class="content_tab">

                <!-- 오른쪽 최상단 콘텐츠 (주로 있길래 만듬) -->
                <div class="content_top">오른쪽 메뉴</div>
                그냥 테스트<br> <br> <span>스팬 태그 테스트</span>
                <div>디브 태그 테스트</div>

            </div>
        </div>
    </div>
</body>

</html>