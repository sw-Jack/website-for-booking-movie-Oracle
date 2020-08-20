<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/top.jsp" %>    
<html>
<head>
  <title>Movie Member</title>
</head>
<body>

<div align="center">
	<hr color="green" width="300">

	
	<h2>회원 찾기</h2>
	
	<form name="f" method="post" action="memberFind.mo">
		<select name="search">
			<option value="id"> 아이디 </option>
			<option value="name"> 이름 </option>
		</select>
		<input type= "text" name="searchString"/>
		<input type= "submit" value="찾기"/>
 	</form>
 	<c:if test="${empty find}">
 	<hr width="300">
 	<table width="700" class="outline">
 		<tr>
 			<th class="m3">번호</th>
 			<th class="m3">아이디</th>
 			<th class="m3">이름</th>
			<th class="m3">이메일</th>
			<th class="m3">생년월일</th>
			<th class="m3">전화번호</th>
			<th class="m3">우편번호</th>
			<th class="m3">주소</th>
			<th class="m3">포인트</th>
			<th class="m3">수정|삭제</th>
 	</tr>
 <c:choose>
	<c:when test="${empty memberFind}">
		<tr>
 			<td colspan="10"> 찾으시는 회원이 없습니다. </td>
 		</tr>
	</c:when>
	<c:otherwise>
	<c:forEach var="dto" items="${memberFind}">
 	

	<tr>
		<td>${dto.user_num}</td>
		<td>${dto.id}</td>
		<td>${dto.name} </td>
		<td>${dto.email}</td>
		<td>${dto.allBirth}</td>
		<td>${dto.allHp}</td>
		<td>${dto.adrr_code}</td>
		<td>${dto.adrr}</td>
		<td>${dto.point}</td>
		<td><a href="memberUpdate.mo?user_num=${dto.user_num}">수정</a>|
			<a href="memberDelete.mo?user_num=${dto.user_num}">삭제</a></td>
	</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
 	</table>
 	</c:if>
	</div>
</body>


</html>
<%@ include file="/bottom.jsp" %>