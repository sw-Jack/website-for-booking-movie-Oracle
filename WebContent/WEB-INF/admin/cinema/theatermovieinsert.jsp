<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp" %>
<script src="//code.jquery.com/jquery-1.11.0.min.js">

</script>
<script type="text/javascript">
function openschedule(){
	window.open('listMovie.mo?play_year='+$('#play_year').val()+'&play_month='+$('#play_month').val()+'&play_day='+$('#play_day').val()+
			'&theater_stage='+$('#theater_stage').val()+'&cinema_num='+$('#cinema_num').val());
 }
</script>

<div class="container">
<form name="f" action="selectMovieP.mo" method="post">
<input type="hidden" name="theater_stage" id="theater_stage" value="${requestScope.theater_stage}" />
<input type="hidden" name="cinema_num" id="cinema_num" value="${requestScope.cinema_num}" />
<table class="table">
	<thead>
		<tr>
			<th><h4>${requestScope.theater_stage}관</h4></th>
		</tr>
	</thead>
	<tbody>
	<tr>
		<th width="15%">영화선택</th>
		<td>
			<select name="movie_num">
			<c:forEach var="dto" items="${movieList}">
				<option value=${dto.movie_num}>${dto.title}</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th width="15%">상영일</th>
		<td>
			<select name="play_year" id="play_year">
				<c:forEach var="i" begin="2020" end="2023" step="1">
					<option>${i}</option>
				</c:forEach>
			</select> 년 <select name="play_month" id="play_month">
				<c:forEach var="j" begin="6" end="12" step="1">
					<option>${j}</option>
				</c:forEach>
			</select> 월 <select name="play_day" id="play_day">
				<c:forEach var="k" begin="1" end="31" step="1">
					<option>${k}</option>
				</c:forEach>
			</select> 일
			<a href="javascript:openschedule()">[시간표보기]</a>
		</td>
	</tr>
	<tr>
		<th width="15%">시작시간</th>
		<td>
			<select name="start_time_hour">
				<c:forEach var="i" begin="0" end="24" step="1">
					<option>${i}</option>
				</c:forEach>
			</select> : <select name="start_time_min">
				<c:forEach var="j" begin="0" end="59" step="1">
					<option>${j}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<%-- <tr>
		<th width="15%">종료시간</th>
		<td>
			<select name="end_time_hour">
				<c:forEach var="i" begin="0" end="30" step="1">
					<option>${i}</option>
				</c:forEach>
			</select> : <select name="end_time_min">
				<c:forEach var="j" begin="0" end="59" step="1">
					<option>${j}</option>
				</c:forEach>
			</select>
		</td>
	</tr> --%>
	<tr>
		<td><input type="submit" value="등록"><input type="reset" value="재입력"></td>
	</tr>
	</tbody>
</table>
</form>
</div>
<%@ include file="../bottom.jsp" %>