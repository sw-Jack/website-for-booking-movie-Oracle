<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.* ,movie.admin.theater.dto.*"%>
<%@include file="../top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.textdecoration1 {
	text-decoration: line-through;
}
</style>

<div class="container">
	<h1>영화관</h1>
	<hr>
	<div align="center">
		<table class="table">
			<tr>
				<div class="list-group">
					<c:forEach var="cdto" items="${clist}">
						<td align="center">
						<div align="center">
								<b><a class="list-group-item"
									href="viewCinema.mo?cinema_num=${cdto.cinema_num}&mlist=${mlist}"
									<c:if test="${cdto.cinema_num eq cinema_num}">style="color: red"</c:if>>SGV${cdto.cinema_addr}&nbsp;&nbsp;&nbsp;&nbsp;</a></b>
						</div>
						</td>
					</c:forEach>
				</div>
			</tr>
		</table>
		
		<c:choose>
			<c:when test="${empty mlist}">
				<h4>영화관을 선택하세요</h4>
			</c:when>
			<c:otherwise>
				<c:choose>
				<c:when test="${empty playlist}">
					<h4>상영 예정인 영화가 없습니다.</h4>
				</c:when>
				<c:otherwise>
				
				<table>
			<tr>
				<div align="center" class="list-group">
				<c:forEach var="i" begin="${now_day}" end="${now_day+4}" step="1">
				<c:if test="${i eq selectday}">
				<td>
					<a class="list-group-item" href="#">
					<font color="red">
					${now_year}.${now_month}.${i}
					</font>
					</a>
				</td>
				</c:if>
				<c:if test="${i ne selectday}">
				<td>
					<a class="list-group-item" href="cinema_views.mo?cinema_num=${cinema_num}&mlist=${mlist}&selectday=${i}">
					${now_year}.${now_month}.${i}
					</a>
				</td>
				</c:if>
				</c:forEach>
				</div>
			</tr>
			</table>
				
				<c:forEach var="cdto" items="${clist}">
					<form name="f">
					<div class="list-group">
						<table class="table">
							<c:forEach var="mdto" items="${mlist}">
									<c:if test="${cinema_num eq cdto.cinema_num}">
										<input type="hidden" name="movie_num" value="${mdto.movie_num}">
										<input type="hidden" name="cinema_num" value="${cdto.cinema_num}">
										<tr>
											<td><b>${mdto.title}</b>&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<td><font color="gray">${mdto.genre} / ${mdto.time}분 /
											${mdto.opendate_year}.${mdto.opendate_month}.${mdto.opendate_day} 개봉<br>
											</font></td>
										</tr>
										<c:forEach var="tdto" items="${tList}">
										<tr>
											<td colspan="2"><h5>▶&nbsp;${tdto.theater_grade}&nbsp;|&nbsp; ${tdto.theater_stage}관&nbsp; |&nbsp; 총
													${tdto.theater_seatcount}석</h5></td>
										</tr>
										<tr>
										<c:forEach var="playdto" items="${playlist}">
											<c:if test="${playdto.theater_stage eq tdto.theater_stage and playdto.movie_num eq mdto.movie_num}">
											<td>
											<table class="table">
											<tr><td>
											<div align="center"><a class="list-group-item" href="selectseat.mo?play_num=${playdto.play_num}" onmouseover="this.style.color='red';" onmouseleave="this.style.color='black'">${playdto.start}</a></div>&nbsp;&nbsp;
											</td></tr>
											</table>
											</td>
											</c:if>
										</c:forEach>
									</tr>
									</c:forEach>
								</c:if>
							</c:forEach>
						</table>
						</div>
					</form>
				</c:forEach>
				</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<%@include file="../bottom.jsp"%>
