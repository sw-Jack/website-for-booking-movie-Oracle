<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/top.jsp" %> 

<div align="center">
	<hr color="green" width="300">

	<h2>회원 목록보기</h2>

 <c:if test="${empty memberList}">
			<tr>
				<td colspan="6">등록된 회원이 없습니다.</td>
			</tr>
		</c:if>

 	<hr color="green" width="300">
 	<table width="700" class="outline">
 		<tr>
 			<th class="m3">번호</th>
 			<th class="m3">아이디</th>
 			<th class="m3">이름</th>
			<th class="m3">이메일</th>
			<th class="m3">전화번호</th>
			<th class="m3">수정|삭제</th>
 		</tr>
<c:forEach var="dto" items="${memberList}">
 		<tr>
			<td>${dto.user_num}</td>
			<td><a href="memberContent.mo?user_num=${dto.user_num} ">${dto.id}</a></td>
			<td>${dto.name}</td>
			<td>${dto.email}</td>
			<td>${dto.allHp}</td>
			<td><a href="memberUpdate.mo?user_num=${dto.user_num}">수정</a>|
				<a href="memberDelete.mo?user_num=${dto.user_num}">삭제</a></td>
		</tr>
</c:forEach>
 	</table>
 	
	
	</div>
<%@ include file="/bottom.jsp" %>