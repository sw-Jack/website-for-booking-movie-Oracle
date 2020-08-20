<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp"%>
<style type="text/css">
.textdecoration1{text-decoration: line-through;}
</style>

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
	
<%@ include file="../bottom.jsp"%>