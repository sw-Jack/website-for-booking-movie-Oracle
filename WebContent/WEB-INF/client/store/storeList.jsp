<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../top.jsp" %>
<div class="container">
		<div align="center">
			<table class="table" width="99%">
					<tr align="center">
						<div style="position: absolute;">
							<div style="position: relative; top: 116px; left: 236px;">
								<a href="store_list.mo?ccode=combo"><img src="images/comboset.PNG" width="150" height="100"></a>
							</div>
							<div style="position: relative; top: 18px; left: 408px;">
								<a href="store_list.mo?ccode=popcorn"><img src="images/popcorn.PNG" width="150" height="100"></a>
							</div>
							<div style="position: relative; top: -84px; left:580px;">
								<a href="store_list.mo?ccode=beverage"><img src="images/beverage.PNG" width="150" height="100"></a>
							</div>
							<div style="position: relative; top: -183px; left: 752px;">
								<a href="store_list.mo?ccode=snack"><img src="images/snack.PNG" width="150" height="100"></a>
							</div>
						</div>
						<td>
							<img src="images/storemain.PNG" width="950" height="500">
						</td>
					</tr>
			</table>
		</div>
	</div>
	<div class="container">
		<div align="center">
			<h1 align="left">스낵바</h1>
			<table class="table" width="99%">
				<c:if test="${empty prodList}">
					<tr>
						<td colspan="8">등록된 상품이 없습니다.</td>
					</tr>
				</c:if>
				<%
					int row = 0;
				%>
				
					<tr align="center">
					<c:forEach var="pddto" items="${prodList}">
						<td align="left">
						<a href="prod_view.mo?pnum=${pddto.pnum}"><h4>${pddto.pname}</h4></a>
						<a href="prod_view.mo?pnum=${pddto.pnum}"><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${pddto.pimage}" width="280" height="280"></a><br>
							
							</td>
					<%
						row++;
							if (row % 3 == 0) {
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
