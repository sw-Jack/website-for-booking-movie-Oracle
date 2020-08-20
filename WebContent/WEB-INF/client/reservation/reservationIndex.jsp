<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp"%>
<style type="text/css">
.textdecoration1{text-decoration: line-through;}
</style>
<%
session.removeAttribute("name");
session.removeAttribute("tel1");
session.removeAttribute("tel2");
session.removeAttribute("tel3");
%>
<script type="text/javascript">
	function check(){
		var f = document.f;
		
		if(!f.cinema_num.value){
			alert("영화관을 선택해 주세요!");
			return false;
		}
		if(!f.movie_num.value){
			alert("영화를 선택해 주세요!");
			return false;
		}
	}
</script>
<div class="container">
<h1>영화예매</h1>
<table class="table">
<c:if test="${empty clist}">
<script type="text/javascript">
	alert("상영중인 영화가 없습니다!!!");
	location.href="kgvindex.mo";
</script>
</c:if>
<c:if test="${not empty clist}">
<tr>
<td>
	<table class="table">
		<tr>
			<td>
				<c:if test="${not empty mlist}">
					<div class="list-group">
					<c:forEach var="mdto" items="${mlist}">
						<a href="cselectMovie.mo?movie_num=${mdto.movie_num}&cinema_num=${cinema_num}" class="list-group-item"<c:if test="${mdto.movie_num eq movie_num}">style="color: red"</c:if>>${mdto.title}</a>
					</c:forEach>				
					</div>
				</c:if>
			</td>
		</tr>
	</table>
</td>
<td>
	<table class="table" >
		<tr>
			<td>
				<div class="list-group">
				<c:forEach var="dto" items="${clist}">
						<a href="reserselectc.mo?cinema_num=${dto.cinema_num}&movie_num=${movie_num}" class="list-group-item" <c:if test="${dto.cinema_num eq cinema_num}">style="color: red"</c:if>>${dto.cinema_addr}</a>
				</c:forEach>
				</div>
			</td>
		</tr>
	</table>
</td>

</c:if>
<td><!-- 달력출력부분 -->
	<form name="f" action="cinemaplaylist.mo" method="post" onsubmit="return check()">
	<input type="hidden" value="${cinema_num}" name="cinema_num"/>
	<input type="hidden" value="${movie_num}" name="movie_num"/>
	<select name="play_year" id="play_year">
				<c:forEach var="i" begin="2017" end="2100" step="1">
					<option <c:if test="${play_year eq i}">selected</c:if>>${i}</option>
				</c:forEach>
			</select> 년
			 <select name="play_month" id="play_month">
				<c:forEach var="j" begin="1" end="12" step="1">
					<option <c:if test="${play_month eq j}">selected</c:if>>${j}</option>
				</c:forEach>
			</select> 월 
			<select name="play_day" id="play_day">
				<c:forEach var="k" begin="1" end="31" step="1">
					<option <c:if test="${play_day eq k}">selected</c:if>>${k}</option>
				</c:forEach>
			</select> 일
			<input type="submit" value="시간표조회" >
	</form>
</td>
<td>
<c:choose>
<c:when test="${empty playlist}">
	<td><h4>상영 예정된 영화가 없습니다.</h4></td>
</c:when>
<c:otherwise>
	<table class="table">
	
			<div class="list-group">
			<c:forEach var="dto" items="${playlist}">
				<tr>
					<c:choose>
						<c:when test="${nowtime>=dto.start_time and now_year>dto.play_year}">
							<td align="right">
								<div class="list-group-item">
								<p class="textdecoration1">
									${dto.start}~${dto.end}&nbsp;&nbsp;&nbsp;
									${dto.movie_name}&nbsp;&nbsp;&nbsp;
									${dto.cinema_addr}<br>
									${dto.theater_stage}관
								</p>
								</div>
							</td>
						</c:when>
						<c:when test="${nowtime>=dto.start_time and now_month>dto.play_month}">
							<td align="right">
								<div class="list-group-item">
								<p class="textdecoration1">
									${dto.start}~${dto.end}&nbsp;&nbsp;&nbsp;
									${dto.movie_name}&nbsp;&nbsp;&nbsp;
									${dto.cinema_addr}<br>
									${dto.theater_stage}관
								</p>
								</div>
							</td>
						</c:when>
						<c:when test="${nowtime>=dto.start_time and now_day>dto.play_day}">
							<td align="right">
								<div class="list-group-item">
								<p class="textdecoration1">
									${dto.start}~${dto.end}&nbsp;&nbsp;&nbsp;
									${dto.movie_name}&nbsp;&nbsp;&nbsp;
									${dto.cinema_addr}<br>
									${dto.theater_stage}관
								</p>
								</div>
							</td>
						</c:when>
						<c:otherwise>
							<td align="right">
								<a href="selectseat.mo?play_num=${dto.play_num}" onmouseover="this.style.color='red';" onmouseleave="this.style.color='black'" class="list-group-item">
									${dto.start}~${dto.end}&nbsp;&nbsp;&nbsp;
									${dto.movie_name}&nbsp;&nbsp;&nbsp;
									${dto.cinema_addr}<br>
									${dto.theater_stage}관
								</a>
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
			</div>
	</table>
</tr>
</c:otherwise>
</c:choose>
</td>
</tr>
</table>
</div>
<%@ include file="../bottom.jsp"%>