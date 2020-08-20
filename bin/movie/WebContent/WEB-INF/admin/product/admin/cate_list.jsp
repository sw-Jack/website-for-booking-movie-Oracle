<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*,movie.admin.category.dto.*"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ include file="/top.jsp" %>

<body>
	<div align="center">
<h3>카테고리 목록</h3>
<a href="shop_index.bo">메인</a>
<table border="1" width="300">
<tr><th>번호</th><th >카테고리명</th><th>삭제</th></tr>
<tr>

<c:choose>
	<c:when test="${empty cateList}">
		<tr>
			<td colspan="4">등록된 카테고리가 없습니다.</td>
		</tr>
	</c:when>
	
	<c:otherwise>
		<c:forEach var="dto" items="${cateList}">
			<tr>
			<td align="center">${dto.cnum}</td>
			<td align="center">${dto.cname}</td>
			<td><a href="cate_delete.mo?cnum=${dto.cnum}">삭제</a></td>
		</tr>
		
		</c:forEach>
	</c:otherwise>
</c:choose>




</table>
</div>
</body>
<%@ include file="/bottom.jsp" %>