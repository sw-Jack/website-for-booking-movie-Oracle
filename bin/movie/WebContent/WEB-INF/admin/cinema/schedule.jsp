<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width",initial-scale="1">
	<title>SGV 영화 상영 시간표</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codingBooster.css">
</head>
<body>
	<div class="container">
		<table class="table">
		<thead>
			<h4>${play_year}년 ${play_month}월 ${play_day}일 시간표</h4>
		</thead>
		<tbody>
		<tr>
			<th>시작시간</th><th>종료시간</th><th>영화제목</th>
		</tr>
		<c:forEach var="dto" items="${playlist}">
			<tr>
			<td>${dto.start_time_hour}시 ${dto.start_time_min}분</td>
			<td>${dto.end_time_hour}시 ${dto.end_time_min}분</td>
			<td>${dto.movie_name}
			</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>
</body>
</html>