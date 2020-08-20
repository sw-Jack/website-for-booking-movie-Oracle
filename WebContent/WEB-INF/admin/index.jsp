<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="top.jsp" %>
<div class="container">
<table class="table" width="1200">
<thead>
	<h4>관리자 페이지</h4>
</thead>
<tbody>
	<tr>
		<th>영화 관리</th><th>영화관 관리</th><th>게시판 관리</th><th>회원 관리</th><th>상품 관리</th><th>상영관 관리</th>
	</tr>
	<tr>
		<td><a href="movie_insert.mo">영화 추가</a></td><td><a href="insertCinema.mo">영화관 추가</a></td><td><a href="noticeinsert.mo">공지사항 추가</a></td><td><a href="memberList.mo">회원보기</a></td><td><a href="cate_input.mo">카테고리 추가</a></td><td><a href="insertmovie.mo">상영영화 추가</a></td>
	</tr>
	<tr>
		<td><a href="movie_list.mo">영화 리스트</a></td><td><a href="listCinema.mo">영화관 목록</a></td><td><a href="noticelist.mo">공지사항 목록</a></td><td><a href="memberFind.mo">회원찾기</a></td><td><a href="cate_list.mo">카테고리 보기</a></td>
	</tr>
	<tr>
		<td></td><td></td><td><a href="question_list.mo">1:1문의 확인</a></td><td><a href="#">예매내역확인</a></td><td><a href="prod_input.mo">상품 추가</a></td><td></td>
	</tr>
	<tr>
		<td></td><td></td><td></td><td></td><td><a href="prod_list.mo">상품 목록</a></td><td></td>
	</tr>
	<tr>
		<td></td><td></td><td></td><td></td><td><a href="prod_find.mo">상품 찾기</a></td><td></td>
	</tr>
</tbody>
</table>
</div>
<%@ include file="bottom.jsp" %>
