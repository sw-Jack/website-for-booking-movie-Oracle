<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp"%>
<div align="center">
<form name="f" action="insertCinemaP.mo" method="post">
<h2>SGV ${viewCinema.cinema_addr}점</h2>
<div class="container">
<table class="table">
	<tr class="active" align="center">
		<th>지점명</th><td>${viewCinema.cinema_addr}점</td>
	</tr>
	<tr class="active" align="center">
		<th>상영관 수</th><td>총 ${viewCinema.cinema_size}관</td>
	</tr>
	<tr class="active" align="center">
		<th>담당자</th><td>${viewCinema.cinema_admin}</td>
	</tr>
	<c:forEach var="thdto" items="${viewTheater}">
	<tr>
	<div class="container">
	<table class="table">
	<tr class="active" align="center">
		<th colspan="2">${thdto.theater_stage}관</th>
	</tr>
	<tr class="active" align="center">
		<th>수용인원</th><td>${thdto.theater_seatcount}</td>
	</tr>
	<tr class="active" align="center">
		<th>상영영화종류</th><td>${thdto.theater_grade}</td>
	</tr>
	</table>
	</div>
	</tr>
	</c:forEach>
</table>
</div>
</form>
</div>
<%@ include file="../bottom.jsp"%>