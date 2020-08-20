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
	<h2>상영관 추가</h2>

	<form name="f" action="addTheaterP.mo" method="post">
	<input type="hidden" name="cinema_num" value="${getCinema.cinema_num}"/>
	<input type="hidden" name="cinema_addr" value="${getCinema.cinema_addr}"/>
		<table class="table">
			<tr class="active" align="center">
				<th>관이름(숫자만입력)</th>
				<td><input type="text" name="theater_stage" size="80" /></td>
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
			<th>좌석열의 갯수</th><td><input type="text" name="seat_row" size="80" /></td>
			</tr>
			<tr class="active" align="center">
				<th>한열에 들어갈 좌석의 수</th><td><input type="text" name="seat_num" size="80" /></td>
			</tr>
			<tr class="active" align="right">
				<td colspan="2"><input type="submit" value="완료" /></td>
			</tr>
		</table>
	</form>


</div>
</div>
<%@ include file="../bottom.jsp"%>