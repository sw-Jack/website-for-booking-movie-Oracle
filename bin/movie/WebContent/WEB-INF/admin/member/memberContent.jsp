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

	<h2>회원 상세보기</h2>


 	<hr color="green" width="300">
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
			
 		</tr>
 		<tr>
			<td>${memberContent.user_num}></td>
			<td>${memberContent.id}</td>
			<td>${memberContent.name}</td>
			<td>${memberContent.email}</td>
			<td>${memberContent.allBirth}</td>
			<td>${memberContent.allHp}</td>
			<td>${memberContent.adrr_code}</td>
			<td>${memberContent.adrr}</td>
			<td>${memberContent.point}</td>
			
		</tr>
 
 	</table>
 	
	
	</div>
</body>


</html>
<%@ include file="/bottom.jsp" %>