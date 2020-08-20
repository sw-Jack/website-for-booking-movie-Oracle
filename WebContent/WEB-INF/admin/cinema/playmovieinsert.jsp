<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp" %>
<div class="container">
<table class="table">
<tr>
<td>
	<table class="table">
		<tr>
		<td>
		<div><h4 style="text-align: center;">영화관 선택</h4></div>
		</td>
		</tr>
		<tr>
		<td colspan="2">
		<c:choose>
			<c:when test="${empty cinemaList}">
				<div><h4>등록된 영화관이 없습니다.</h4></div>
			</c:when>
			<c:otherwise>
			<c:forEach var="dto" items="${cinemaList}">
				<div class="list-group">
					<a href="selectTheater.mo?cinema_num=${dto.cinema_num}" class="list-group-item">${dto.cinema_addr}</a>
				</div>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</td>
		</tr>
	</table>
</td>
<!-- 영화관선택 -->

<td>	
	<table class="table">
		<tr>
		<c:choose>
		<c:when test="${empty theaterList}">
		<td>
			<h4>영화관을 선택하지 않으셨거나, 등록된 상영관이 없습니다.</h4>
			<br>
		</td>
		</c:when>
		<c:otherwise>
		<div class="con-sm-2"><h4 style="text-align: center;">상영관 선택</h4>
		<c:forEach var="tdto" items="${theaterList}">
			<div class="list-group">
				 		<a href="selectMovie.mo?theater_stage=${tdto.theater_stage}&cinema_num=${tdto.cinema_num}" class="list-group-item">${tdto.theater_stage}관</a>
			</div>
		</c:forEach>
		</div>
		</c:otherwise>
		</c:choose>
		</tr>
	</table>
</td>

<!-- 상영관선택 -->
</tr>
</table>
</div>
<%@ include file="../bottom.jsp" %>