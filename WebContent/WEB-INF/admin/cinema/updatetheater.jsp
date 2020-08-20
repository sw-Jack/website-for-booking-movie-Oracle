<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp"%>
<div align="center">
	<table class="table">
		<tr>
			<th>${getCinema.cinema_addr}점</th>
			<th>관리자 ${getCinema.cinema_admin}</th>
		</tr>
	</table>
	<h2>${getTheater.theater_stage}상영관 수정</h2>

	<form name="f" action="updateTheaterP.mo" method="post">
	<input type="hidden" name="cinema_num" value="${getCinema.cinema_num}">
	<input type="hidden" name="theater_stage" value="${getTheater.theater_stage}">
		<table class="table">
			<tr class="active" align="center">
				<th>등급</th>
				<td>
				<select name="theater_grade">
					<option value="2D" <c:if test="${getTheater.theater_grade eq '2D'}">selected</c:if>>2D</option>
					<option value="3D" <c:if test="${getTheater.theater_grade eq '3D'}">selected</c:if>>3D</option>
					<option value="IMAX" <c:if test="${getTheater.theater_grade eq 'IMAX'}">selected</c:if>>IMAX</option>
				</select>
				
				</td>
			</tr>
			<tr class="active" align="center">
			<th>좌석열의 갯수</th><td><input type="text" name="seat_row" size="80" value="${getTheater.seat_row}"/></td>
			</tr>
			<tr class="active" align="center">
				<th>한열에 들어갈 좌석의 수</th><td><input type="text" name="seat_num" size="80" value="${getTheater.seat_num}"/></td>
			</tr>
			<tr class="active" align="right">
				<td colspan="2"><input type="submit" value="완료" /></td>
			</tr>
		</table>
	</form>


</div>
</div>
<%@ include file="../bottom.jsp"%>