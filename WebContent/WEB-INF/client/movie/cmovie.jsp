<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../top.jsp" %>
	<div class="container">
		<div align="center">
			<hr color="black" width="300">
			<h1 align="left">무비 차트</h1>
			<hr width="600">
			<table class="table" width="99%">
				<c:if test="${empty cmovieList}">
					<tr>
						<td colspan="8">등록된 영화가 없습니다.</td>
					</tr>
				</c:if>
				<%
					int row = 0;
				%>
				
					<tr align="center">
					<c:forEach var="mvdto" items="${cmovieList}">
						<td align="left">
						
						<a href="cmovie_view.mo?movie_num=${mvdto.movie_num}"> <img style="border: 7px solid black;border-radius: 7px;-moz-border-radius: 7px;-khtml-border-radius: 7px;-webkit-border-radius: 7px;"
								src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${mvdto.image}" width="180" height="280"></a><br>
							<a href="cmovie_view.mo?movie_num=${mvdto.movie_num}"><h4>${mvdto.title}</h4></a><br>
							예매횟수 ${mvdto.resercount} | 평점 ${mvdto.score} <br><br>
							<a href="cselectMovie.mo?movie_num=${mvdto.movie_num}"> <img src="images/reserbutton1.jpg"
								border="0" width="100" height="30"></a>
							</td>
					<%
						row++;
							if (row % 5 == 0) {
					%>
					</tr>
					<tr>
					<%
						}
					%>
					</c:forEach>
					</tr>
			</table>
			<c:if test="${not empty param.mode}">
				<h2>영화 검색</h2>
			</c:if>
		</div>
	</div>


<%@ include file="../bottom.jsp" %>
