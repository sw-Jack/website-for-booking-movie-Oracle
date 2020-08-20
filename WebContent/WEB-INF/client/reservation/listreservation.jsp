<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp" %>
<script type="text/javascript">
	function check(){
		var check = confirm('취소하시겠습니까?');
		if(check){
			return true;
		}else{
			return false;
		}
	}
</script>
<div align="center">
	<table width="1200" border="1">
		<tr>
			<b><td align="center">예매번호</td><td align="center">예매자</td><td align="center">영화제목</td><td align="center">상영장소</td><td align="center">상영시간</td><td align="center">좌석</td><td align="center">예매취소</td></b>
		</tr>
		<c:forEach var="reser" items="${clist}">
		<tr>
			<td align="center">${reser.credit_num}</td>
			<td  align="center">${reser.buyer}</td>
			<td align="center">${reser.title}</td>
			<td align="center">${reser.cinema_addr}점&nbsp;&nbsp;${reser.theater_stage}관</td>
			<td align="center">
				${reser.play_year}년&nbsp;${reser.play_month}월&nbsp;${reser.play_day}일&nbsp;&nbsp;${reser.start}&nbsp;~&nbsp;${reser.end}
			</td>
			<td align="center">
				${reser.seat_row}열&nbsp;${reser.seat_num}번
			</td>
			<td align="center">
			<c:if test="${empty name}">
				<a href="cancel.mo?credit_num=${reser.credit_num}" onclick="return check()">예매취소</a>
			</c:if>
			<c:if test="${not empty name}">
				<a href="cancel.mo?credit_num=${reser.credit_num}&name=${name}&hp1=${hp1}&hp2=${hp2}&hp3=${hp3}" onclick="return check()">예매취소</a>
			</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="../bottom.jsp"%>