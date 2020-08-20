<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp"%>

	<div class="container">
		<div align="center">
			<h1 align="left">배우 상세</h1>
			<table class="table" width="99%">
				<c:if test="${empty mvdto}">
					<tr>
						<td colspan="8">등록된 배우가 없습니다.</td>
					</tr>
				</c:if>
			
				
					<tr align="center">
						<td align="left">
							<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${mvdto.actorimage1}" width="120" height="180" align="left">
								<font size="3" color="green">${mvdto.actor}</font><br>
								<font color="red">${mvdto.actor1role}</font> <br><br>
								${mvdto.actor1past}</td>
						<td align="left"> 
							<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${mvdto.actorimage2}" width="120" height="180" align="left">
								<font size="3" color="green">${mvdto.actor2}</font><br>
								<font color="red">${mvdto.actor2role}</font> <br><br>
								${mvdto.actor2past}</td>
							
				
					</tr> 
						
						<tr align="center">
						<td align="left">
							<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${mvdto.actorimage3}" width="120" height="180" align="left">
								<font size="3" color="green">${mvdto.actor3}</font><br>
								<font color="red">${mvdto.actor3role}</font> <br><br>
								${mvdto.actor3past}</td>
						<td align="left">
							<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${mvdto.actorimage4}" width="120" height="180" align="left">
								<font size="3" color="green">${mvdto.actor4}</font><br>
								<font color="red">${mvdto.actor4role}</font> <br><br>
								${mvdto.actor4past}</td>
							
				
					</tr>
						
						<tr align="center">
						<td align="left">
							<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${mvdto.actorimage5}" width="120" height="180" align="left">
								<font size="3" color="green">${mvdto.actor5}</font><br>
								<font color="red">${mvdto.actor5role}</font> <br><br>
								${mvdto.actor5past}</td>
						<td align="left">
							<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${mvdto.actorimage6}" width="120" height="180" align="left">
								<font size="3" color="green">${mvdto.actor6}</font><br>
								<font color="red">${mvdto.actor6role}</font> <br><br>
								${mvdto.actor6past}</td>
							
				
			</table>
			<c:if test="${not empty param.mode}">
				<h2>영화 검색</h2>
			</c:if>
		</div>
	</div>
<%@ include file="../bottom.jsp"%>