<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/top.jsp" %>
<div align="center">
<table class="table">
<tr>
	<th>영화관번호</th><th>영화관주소</th><th>상영관수</th><th>관리자</th><th>수정 / 삭제</th>
</tr>
<c:choose>
<c:when test="${empty listCinema}">
<tr>
	<th colspan="5">등록된 영화관이 없습니다.</th>
</tr>
</c:when>
<c:otherwise>
<c:forEach var="dto" items="${listCinema}">
<tr>
	<td>${dto.cinema_num}</td>
	<td><a href="viewCinema.mo?cinema_num=${dto.cinema_num}">${dto.cinema_addr}점</a></td>
	<td>${dto.cinema_size}</td>
	<td>${dto.cinema_admin}</td>
	<td><a href="updateCinema.mo?cinema_num=${dto.cinema_num}">수정</a> /
		<a href="deleteCinema.mo?cinema_num=${dto.cinema_num}">삭제</a>
	</td>
</tr>
</c:forEach>	
</c:otherwise>
</c:choose>
</table>
</div>
<%@ include file="/bottom.jsp" %>