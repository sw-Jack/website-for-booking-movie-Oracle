<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ include file="/top.jsp" %>
<div align="center">
	<h2>${getCinema.cinema_addr}점 상영관 등록</h2>
	<c:forEach var="i" begin="1" end="${getCinema.cinema_size}" step="1">
	<c:set value="0" var="finish"/>
<div class="container">
<h3>${getCinema.cinema_addr}점 ${i}번째관</h3>
<table class="table">
<c:forEach var="tdto" items="${listTheater}">

<c:if test="${i==tdto.theater_stage}">
<c:set value="${i}" var="finish"/>
</c:if>
</c:forEach>
<c:choose>
	<c:when test="${finish!=0}">
	<tr><th colspan="2">등록완료</th></tr>
	<hr>
	</c:when>
	<c:otherwise>
	<form name="f" action="insertTheater.mo" method="post">
	<input type="hidden" name="cinema_num" value="${getCinema.cinema_num}"/>
	<tr class="active" align="center">
		<th>관이름(숫자입력)</th><td><input type="text" name="theater_stage" size="80"/>
	</tr>
	<tr class="active" align="center">
		<th>등급</th>
		<td>
		<select name="theater_grade">
			<option value="2D">2D</option>
			<option value="3D">3D</option>
			<option value="imax">IMAX</option>
		</select>
		</td>
	</tr>
	<tr class="active" align="center">
		<th>좌석수</th><td><input type="text" name="theater_seatcount" size="80" /></td>
	</tr>
	<tr class="active" align="right">
		<td colspan="2"><input type="submit" value="다음" /></td>
	</tr>
	</form>
</c:otherwise>
</c:choose>
</table>
</div>
<hr>
</c:forEach>
</div>
<%@ include file="/bottom.jsp" %>