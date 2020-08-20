<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../top.jsp"%>
<script type="text/javascript">
	function updateCheck() {
		return confirm("수정하시겠습니까?")
	}
	function deleteCheck() {
		return confirm("정말로 삭제하시겠습니까?")
	}
</script>
<div class="container">
	<div align="center">
		<hr color="black" width="300">
		<h2>영화 리스트</h2>
		<table class="table" width="99%">

			<c:if test="${empty movieList}">
				<tr>
					<td colspan="8">등록된 영화가 없습니다.</td>
				</tr>
			</c:if>
			<tr align="center">
				<c:set value="0" var="row" />
				<%
					int row = -1;
				%>
				<c:forEach var="mvdto" items="${movieList}">


					<%
						row++;
							if (row % 5 == 0) {
					%>
					<tr>
					</tr>
					<%
						}
					%>
					<td align="center"><a
						href="movie_view.mo?movie_num=${mvdto.movie_num}"> <img
							src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${mvdto.image}" width="130" height="180"></a><br>
						${mvdto.title}<br> <a
						href="movie_update.mo?movie_num=${mvdto.movie_num}"
						onclick="return updateCheck();">수정</a> | <a
						href="movie_delete.mo?movie_num=${mvdto.movie_num}"
						onclick="return deleteCheck();">삭제</a></td>
				</c:forEach>
			</tr>
		</table>
		<c:set var="startPage" value="${requestScope.startPage}" />
		<c:set var="pageBlock" value="${requestScope.pageBlock}" />
		<c:set var="endPage" value="${requestScope.endPage}" />
		<c:set var="pageCount" value="${requestScope.pageCount}" />
		<c:if test="${startPage>pageBlock}">
			<a href="movie_list.mo?pageNum=${startPage-pageBlock}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href="movie_list.mo?pageNum=${i}">[${i}]</a>
		</c:forEach>
		<c:if test="${endPage>pageBlock}">
			<a href="moive_list.mo?pageNum=${startPage+pageBlock}">[다음]</a>
		</c:if>
		<c:if test="${not empty param.mode}">
			<h2>영화 검색</h2>
		</c:if>
		<form name="f" method="post" action="movie_find.mo">
			<select name="search">
				<option value="title">제목</option>
				<option value="director">감독</option>
			</select> 
			<input type="text" name="searchString"> 
			<input type="hidden" name="mode" value="${param.mode}"> 
			<input type="submit" value="찾기">
		</form>
	</div>
</div>

<%@ include file="../bottom.jsp"%>
