<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,movie.admin.theater.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp" %>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	function check(){
		return confirm("메인으로 돌아가시겠습니까?")
	}
</script>
<div class="container">
	<div align="center">
		<form name="f" method="POST" action="kgvindex.mo" onsubmit="return check()">
			<h3>예매가 완료 되었습니다.</h3>
			<table class="table" border="1">
  				<tr class="active">
  					<td rowspan="8">
  						<img
							src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${mvdto.image}" width="130" height="180">
  					</td>
					<td colspan="2" align=center><h3>결제정보확인</h3></td>
 				</tr>
				<tr class="active">
					<td width="150">예매번호</td>
					<td>
						${playdto.movie_num}
					</td>
					
				</tr>
				<tr class="active">
					<td width="150" class="m3">영화</td>
					<td width="150">
						${mvdto.title}
					</td>
  				</tr>
  				<tr class="active">
					<td width="150" class="m3">구매자</td>
					<td width="150">
						${buyer}
					</td>
  				</tr>
  				<tr class="active">
					<td width="150" class="m3">극장</td>
					<td width="150" class="m3">
						${playdto.cinema_addr}점 ${playdto.theater_stage}관
					</td>
  				</tr>
  				<tr class="active">
  					<td width="150" class="m3">일시</td>
  					<td width="150" class="m3">
  						${playdto.play_year}년 ${playdto.play_month}월 ${playdto.play_day}일 
  						${playdto.start}
  					</td>
  					
   				</tr>
  				<tr class="active">
					<td width="150" class="m3">좌석</td>
					<td width="150" class="m3">
						<c:forEach var="seat" items="${seatlist}">
							${seat.seat_row}열 ${seat.seat_num}번<br>
						</c:forEach>
					</td>
  				</tr>
  				
  				<tr class="active">
					<td width="150">결제금액</td>
					<td width="150">
						<%List<Theater_seatDTO> seatlist = (List<Theater_seatDTO>)request.getAttribute("seatlist");
							int total = seatlist.size()*9000;
						%>
						<%=total%>원
					</td>
  				</tr>
  				
					<td colspan="3" align="center">
						<input type="submit" value="메인으로">
					</td>
  				</tr>
  			</table>
		</form>
	</div>
</div>
 <%@ include file="../bottom.jsp"%>