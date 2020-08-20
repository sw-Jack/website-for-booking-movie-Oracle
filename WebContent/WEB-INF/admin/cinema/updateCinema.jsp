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
	<h4 align="center"> 상 영 관 목 록 </h4>
	<table class="table">
		<tr>
			<th align="right"> <a href="addTheater.mo?cinema_num=${getCinema.cinema_num}">&nbsp&nbsp&nbsp상영관 추가</a></th>
			<th align="right"> <a href="updateAdmin.mo?cinema_num=${getCinema.cinema_num}">&nbsp&nbsp&nbsp관리자 수정</a></th>
		</tr>
	</table>
	<table class="table">
		<tr>
			<th>상영관 이름&nbsp&nbsp&nbsp&nbsp&nbsp</th>
			<th>상영관 등급&nbsp&nbsp&nbsp&nbsp&nbsp</th>
			<th>상영관 좌석&nbsp&nbsp&nbsp&nbsp&nbsp</th>
			<th>수정 / 삭제</th>
		<c:choose>
			<c:when test="${empty listTheater}">
				<tr>
					<th colspan="4">등록된 상영관이 없습니다.</th>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="dto" items="${listTheater}">
					<tr>
						<td>${dto.theater_stage}</td>
						<td>${dto.theater_grade}</td>
						<td>${dto.theater_seatcount}</td>
						<td><a
							href="updateTheater.mo?theater_stage=${dto.theater_stage}&cinema_num=${dto.cinema_num}">수정</a>
							/ <a href="deleteTheater.mo?theater_stage=${dto.theater_stage}&cinema_num=${dto.cinema_num}">삭제</a></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</div>
<%@ include file="../bottom.jsp"%>

