<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet" href="/css/contact/contact.css" />
<link rel="stylesheet" href="/css/commons/body_form/left_form/body_form_default.css" />
<link rel="stylesheet" href="/css/commons/topForm.css" />

<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script src="/js/contact/contact.js"></script>
<script src="/js/commons/body_form/body_form_default.js"></script>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous"
>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

</head>

<body>
	<div class="modal_form">
		<div class="modal_background"></div>
		<form class="modal_contact_add_form" method="post" id="contact_form">
			<div class="modal_contact_add" id="main_modal">
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
										<input type="text" name="name" class="form-control" placeholder="이름을 입력하세요."
											id="modal_body_content_input_right_name"
											style="font-size: 14px; height: 34px; width: 338px; padding: 0 10px;" maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">이메일</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="email" class="form-control" placeholder="이메일을 입력하세요."
											style="font-size: 14px; height: 34px; width: 338px; padding: 0 10px;" maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">전화</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="phone" class="form-control" placeholder="전화번호를 입력하세요."
											style="font-size: 14px; height: 34px; width: 338px; padding: 0 10px;" maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">태그</div>
								<div class="modal_body_content_input_right tag">
									<div class="modal_tag" onclick="tag_list_opener('nomal')">
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
									<span class="modal_body_content_input_bonus_text"
										onclick="modal_body_content_input_bonus_text()"
									>새 태그 만들기</span>
								</div>
							</div>
						</div>
						<div class="modal_body_content_form_plus">
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">회사</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="company_name" class="form-control" placeholder="회사"
											style="font-size: 14px; height: 34px; max-width: 120px; padding: 0 10px;" maxlength="50"
										>
										<input type="text" name="company_department" class="form-control" placeholder="부서"
											style="font-size: 14px; height: 34px; max-width: 120px; padding: 0 10px; margin: 0 0 0 10px;"
											maxlength="50"
										>
										<input type="text" name="company_rank" class="form-control" placeholder="직급"
											style="font-size: 14px; height: 34px; max-width: 118px; padding: 0 10px; margin: 0 0 0 10px;"
											maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">주소</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="address" class="form-control" placeholder="주소를 입력하세요."
											style="font-size: 14px; height: 34px; width: 378px; padding: 0 10px;" maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">홈페이지</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="link" class="form-control" placeholder="홈페이지 주소를 입력하세요."
											style="font-size: 14px; height: 34px; width: 378px; padding: 0 10px;" maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">생일</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="birthday" class="form-control" placeholder="YYYYMMDD"
											style="font-size: 14px; height: 34px; width: 378px; padding: 0 10px;" maxlength="50"
										>
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


		<form class="modal_contact_edit_form" method="post" id="contact_form_edit">
			<div class="modal_contact_add" id="modal_contact_add_edit">
				<div class="modal_title">주소 수정</div>
				<div class="modal_body">
					<div class="modal_body_content">
						<div class="modal_body_content_form">
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">이름 *</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="name" class="form-control" placeholder="이름을 입력하세요."
											id="modal_body_content_edit_right_name"
											style="font-size: 14px; height: 34px; width: 338px; padding: 0 10px;" maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">이메일</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="email" class="form-control" placeholder="이메일을 입력하세요."
											id="modal_body_content_edit_right_email"
											style="font-size: 14px; height: 34px; width: 338px; padding: 0 10px;" maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">전화</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="phone" class="form-control" placeholder="전화번호를 입력하세요."
											id="modal_body_content_edit_right_phone"
											style="font-size: 14px; height: 34px; width: 338px; padding: 0 10px;" maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">태그</div>
								<div class="modal_body_content_input_right tag">
									<div class="modal_tag" onclick="tag_list_opener('personal')">
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
										<input type="hidden" id="hidden_tag_list_update" name="tag">
										<input type="hidden" id="modal_body_content_edit_right_seq" name="seq">
									</div>
								</div>
								<div class="modal_body_content_input_bonus">
									<span class="modal_body_content_input_bonus_text"
										onclick="modal_body_content_input_bonus_text_update()"
									>새 태그 만들기</span>
								</div>
							</div>
						</div>
						<div class="modal_body_content_form_plus">
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">회사</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="company_name" class="form-control"
											id="modal_body_content_edit_right_companyName" placeholder="회사"
											style="font-size: 14px; height: 34px; max-width: 120px; padding: 0 10px;" maxlength="50"
										>
										<input type="text" name="company_department" class="form-control"
											id="modal_body_content_edit_right_companyDepartment" placeholder="부서"
											style="font-size: 14px; height: 34px; max-width: 120px; padding: 0 10px; margin: 0 0 0 10px;"
											maxlength="50"
										>
										<input type="text" name="company_rank" class="form-control"
											id="modal_body_content_edit_right_companyRank" placeholder="직급"
											style="font-size: 14px; height: 34px; max-width: 118px; padding: 0 10px; margin: 0 0 0 10px;"
											maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">주소</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="address" class="form-control"
											id="modal_body_content_edit_right_address" placeholder="주소를 입력하세요."
											style="font-size: 14px; height: 34px; width: 378px; padding: 0 10px;" maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">홈페이지</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="link" class="form-control"
											id="modal_body_content_edit_right_link" placeholder="홈페이지 주소를 입력하세요."
											style="font-size: 14px; height: 34px; width: 378px; padding: 0 10px;" maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">생일</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<input type="text" name="birthday" class="form-control"
											id="modal_body_content_edit_right_birthday" placeholder="YYYYMMDD"
											style="font-size: 14px; height: 34px; width: 378px; padding: 0 10px;" maxlength="50"
										>
									</div>
								</div>
							</div>
							<div class="modal_body_content_input">
								<div class="modal_body_content_input_left">메모</div>
								<div class="modal_body_content_input_right">
									<div class="input-group mb-0">
										<textarea name="memo" class="modal_body_content_textarea"
											id="modal_body_content_edit_right_memo" placeholder="내용을 입력하세요."
										></textarea>
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
					<button class="button_delete float_left" onclick="modal_update_delete('personal')">삭제</button>
					<button class="button_cancel float_right"
						onclick="modal_cancel_button('contact_update_modal',event)"
					>취소</button>
					<button class="button_apply float_right" onclick="modal_update_apply('personal')">수정</button>
				</div>
			</div>

		</form>

		<div class="modal_contact_read">
			<div class="modal_title" id="read_title"></div>
			<div class="modal_body" id="read_body">
				<div class="modal_body_content_form" id="read_content_form"></div>
			</div>
			<div class="modal_footer">
				<button class="button_cancel" onclick="modal_cancel_button('read_modal',event)">닫기</button>
				<button class="button_apply" onclick="modal_read_edit('personal')" style="margin-left: 14px;">수정</button>
			</div>
		</div>

		<!-- 일반 모달창 -->
		<div class="modal_tag_add" id="modal_tag_add_nomal">
			<div class="modal_title">
				<span>태그 만들기</span>
			</div>
			<div class="modal_body">
				<div class="input-group mb-0">
					<input type="text" id="modal_new_tag_input" class="form-control"
						placeholder="새로 생성할 태그명을 입력하세요" onclick="edit_form_new_tag_deplicate('nomal')"
					>
				</div>
				<div class="modal_tag_duplication_error">
					<img src="/images/contact/dup_error.png"> 태그 이름이 이미 있습니다.
				</div>
				<div class="modal_tag_regex_error">
					<img src="/images/contact/dup_error.png" /> 해당 태그는 사용할 수 없습니다.
				</div>
			</div>
			<div class="modal_footer">
				<button class="button_cancel" id="button_cancel_tag" onclick="button_cancel_tag()">취소</button>
				<button class="button_apply tag permit" id="button_apply_tag" style="margin-left: 14px">저장</button>
			</div>
		</div>
	</div>

	<!-- 글 수정하는 곳에서 태그 만들기 -->
	<div class="modal_tag_add" id="modal_tag_update_nomal">
		<div class="modal_title">
			<span>태그 만들기</span>
		</div>
		<div class="modal_body">
			<div class="input-group mb-0">
				<input type="text" id="modal_new_tag_input_from_edit" class="form-control"
					placeholder="새로 생성할 태그명을 입력하세요." onclick="edit_form_new_tag_deplicate('personal', 'this')"
				>
			</div>
			<div class="modal_tag_duplication_error">
				<img src="/images/contact/dup_error.png"> 태그 이름이 이미 있습니다.
			</div>
			<div class="modal_tag_regex_error">
				<img src="/images/contact/dup_error.png" /> 해당 태그는 사용할 수 없습니다.
			</div>
		</div>
		<div class="modal_footer">
			<button class="button_cancel" id="button_cancel_tag" onclick="button_cancel_tag()">취소</button>
			<button class="button_apply tag permit" id="button_apply_tag_update"
				onclick="button_apply_tag_update('personal')" style="margin-left: 14px"
			>저장</button>
		</div>
	</div>

	<!-- 다이렉트 개인 모달창 -->
	<div class="modal_tag_add" id="modal_tag_add_direct">
		<div class="modal_title">
			<span>태그 만들기</span>
		</div>
		<div class="modal_body">
			<div class="input-group mb-0">
				<input type="text" id="modal_new_tag_input_direct" class="form-control"
					onclick="edit_form_new_tag_deplicate('direct', this)" placeholder="새로 생성할 태그명을 입력하세요."
				>
			</div>
			<div class="modal_tag_duplication_error">
				<img src="/images/contact/dup_error.png"> 태그 이름이 이미 있습니다.
			</div>
			<div class="modal_tag_regex_error">
				<img src="/images/contact/dup_error.png" /> 해당 태그는 사용할 수 없습니다.
			</div>
		</div>
		<div class="modal_footer">
			<button class="button_cancel" onclick="modal_cancel_button('direct_tag_modal', event)">취소</button>
			<button class="button_apply permit tag" id="button_apply_tag_direct" style="margin-left: 14px">저장</button>
		</div>
	</div>

	<!-- 태그 이름 변경 -->
	<div class="modal_tag_edit">
		<div class="modal_title">
			<span>태그 이름 변경</span>
		</div>
		<div class="modal_body">
			<div class="input-group mb-0">
				<input type="text" class="form-control" id="modal_tag_input_edit"
					onkeyup="edit_form_new_tag_deplicate('edit', this)"
				>
			</div>
			<div class="modal_tag_duplication_error">
				<img src="/images/contact/dup_error.png"> 태그 이름이 이미 있습니다.
			</div>
			<div class="modal_tag_regex_error">
				<img src="/images/contact/dup_error.png" /> 해당 태그는 사용할 수 없습니다.
			</div>
		</div>
		<div class="modal_footer">
			<button class="button_cancel" id="button_cancel_tag_edit">취소</button>
			<button class="button_apply tag permit" id="button_apply_tag_edit" style="margin-left: 14px">저장</button>
		</div>
	</div>

	<!-- 태그 삭제 -->
	<div class="modal_tag_delete">
		<div class="modal_title">
			<span>태그 삭제</span>
		</div>
		<div class="modal_body">
			<div class="modal_body_delete_message"></div>
			<div class="modal_body_delete_radio">
				<div class="modal_body_delete_radio_select" id="radio_tag">
					<div class="modal_body_delete_radio_select_left checked"></div>
					<div class="modal_body_delete_radio_select_right checked">태그만 삭제</div>
				</div>
				<div class="modal_body_delete_radio_select" id="radio_all" style="margin-top: 12px;">
					<div class="modal_body_delete_radio_select_left"></div>
					<div class="modal_body_delete_radio_select_right">태그와 태그된 연락처 모두 삭제</div>
				</div>
			</div>
		</div>
		<div class="modal_footer">
			<button class="button_cancel" id="button_cancel_tag_delete">취소</button>
			<button class="button_delete" id="button_delete_tag_delete" style="margin-left: 14px">
				삭제</button>
		</div>
	</div>


	<!-- TOP 꼭 열어보세요.-->
	<div class="top_form">
		<!-- 실제로는 이 곳에 TOP 글씨를 지우시고 TOP을 불러오는 코드를 넣으시면 됩니다. -->
		<div id="top_container"></div>
	</div>

	<!-- 여기서부터 -->

	<div class="body_form">
		<!-- 왼쪽 부분(메뉴) -->
		<div class="left_item">
			<!-- 최상단에 버튼만 있는 부분 -->
			<div class="button_tab">
				<button class="main_button" onclick="modal_open_button('main_modal')">
					<span>작성하기</span>
				</button>
			</div>

			<!-- 메뉴 리스트 있는 부분 -->
			<div class="menu_tab">

				<!-- 일반 메뉴 -->
				<div class="menu_item">
					<img src="/images/commons/body_form/left_item/default/favorites.png" /> <span
						class="menu_item_text"
					>중요 주소록</span>
				</div>

				<!-- 드롭 다운 메뉴 (추가 효과 없음) -->
				<div class="menu_list" id="select_menu_list">
					<div class="menu_list_button plus">
						<div class="menu_list_button_drop">
							<img src="/images/commons/body_form/left_item/default/drop.png" />
						</div>
						<span class="menu_list_button_text">개인 주소록</span>
						<div class="menu_list_button_plus" id="personal_drop_tag_add">
							<img src="/images/commons/body_form/left_item/default/plus.png" />
							<div class="menu_list_button_plus_hover">태그 만들기</div>
						</div>
					</div>
					<div class="menu_list_box">
						<div class="menu_list_item">
							<img src="/images/commons/body_form/left_item/default/company.png"> <span
								class="menu_list_item_text"
							>전체</span>
						</div>
						<c:forEach items="${personalTagList}" var="contact">
							<div class="menu_list_item">
								<img src="/images/commons/body_form/left_item/default/company.png"> <span
									class="menu_list_item_text"
								>${contact.tag} </span>
								<div class="menu_list_item_plus point">
									<img src="/images/commons/body_form/left_item/default/point.png">
									<div class="menu_list_item_plus_hover">태그 편집</div>
								</div>
							</div>
						</c:forEach>
						<div class="menu_list_item">
							<img src="/images/commons/body_form/left_item/default/alarm.png"> <span
								class="menu_list_item_text"
							>미등록 태그</span>
						</div>
					</div>
				</div>

				<!-- 드롭 다운 메뉴 (추가 효과 없음) -->
				<div class="menu_list">
					<div class="menu_list_button plus">
						<div class="menu_list_button_drop">
							<img src="/images/commons/body_form/left_item/default/drop.png" />
						</div>
						<span class="menu_list_button_text">공유 주소록</span>
						<div class="menu_list_button_plus" id="share_drop_tag_add">
							<img src="/images/commons/body_form/left_item/default/plus.png" />
							<div class="menu_list_button_plus_hover">태그 만들기</div>
						</div>
					</div>
					<div class="menu_list_box">
						<div class="menu_list_item">
							<img src="/images/commons/body_form/left_item/default/company.png"> <span
								class="menu_list_item_text"
							>전체</span>
						</div>
						<c:forEach items="${shareTagList}" var="contact">
							<div class="menu_list_item">
								<img src="/images/commons/body_form/left_item/default/company.png"> <span
									class="menu_list_item_text"
								>${contact.tag} </span>
								<div class="menu_list_item_plus point">
									<img src="/images/commons/body_form/left_item/default/point.png">
								</div>
							</div>
						</c:forEach>
						<div class="menu_list_item">
							<img src="/images/commons/body_form/left_item/default/alarm.png"> <span
								class="menu_list_item_text"
							>미등록 태그</span>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 오른쪽 부분(해당 메뉴의 주요 콘텐츠) -->
		<div class="right_item">
			<div class="main_tab">
				<div class="main_top">
					<div class="main_search" id="searchBox">
						<div class="searchBox">
							<div class="searchIconBox">
								<img src="/images/commons/body_form/right_item/default/search.png">
							</div>
							<div class="searchTextBox">
								<input type="text" placeholder="이름, 회사명, 전화번호 검색" id="searchTextBoxInput"
									class="search_input_personal" onkeydown="search(event, 'personal')"
								>
							</div>
						</div>
						<div class="countBox">
							<span id="select_contact_tag">${contactName}</span><span
								style="color: blue; font-weight: bold; margin: 0 0px 0 5px"
							>${personalContactList.size()}</span>개
						</div>
					</div>

					<div class="main_category">
						<div class="main_category_favorite"></div>
						<div class="main_category_name">이름</div>
						<div class="main_category_email">이메일</div>
						<div class="main_category_phone">전화번호</div>
						<div class="main_category_companyName">회사</div>
						<div class="main_category_tag">태그</div>
					</div>
				</div>
				<div class="main_body">
					<c:choose>
						<c:when test="${personalContactList.size() > 0}">
							<c:forEach var="contact" items="${personalContactList}">
								<div class="main_category cursor" onclick="modal_contact_read_add_data(this, 'personal')">
									<div class="main_category_favorite">
										<img src="/images/commons/body_form/right_item/default/favorites.png">
									</div>
									<div class="main_category_seq">${contact.seq}</div>
									<div class="main_category_name">${contact.name}</div>
									<div class="main_category_email">${contact.email}</div>
									<div class="main_category_phone">${contact.phone}</div>
									<div class="main_category_companyName">${contact.company_name}</div>
									<div class="main_category_tag">
										<div class="main_tag_list">
											<c:if test="${not empty contact.tag}">
												<c:set var="tagString" value="${contact.tag}" />
												<c:set var="tagArray" value="${fn:split(tagString, ',')}" />
												<c:forEach var="tag" items="${tagArray}">
													<div class="main_tag_list_item">${tag}</div>
												</c:forEach>
											</c:if>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="main_category">

								<div class="main_category_null">등록된 주소가 없습니다.</div>

							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="menu_list_item_plus_click">
		<div class="menu_list_item_plus_click_item" id="check_item_delete">삭제</div>
		<div class="menu_list_item_plus_click_item" id="check_item_update">이름 변경</div>
	</div>
</body>

</html>