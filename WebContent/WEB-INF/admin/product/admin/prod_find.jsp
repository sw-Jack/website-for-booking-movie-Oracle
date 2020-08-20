<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../top.jsp" %>    
<script type="text/javascript">
	function check(){
		if(!f.searchString.value){
			alert("검색어를 입력하세요.");
			return false;
		}
	}
	
</script>
<div align="center">
	<hr color="green" width="300">

	
	<h2>상품 찾기</h2>
	<table>
	<form name="f" method="post" action="prod_findP.mo" onsubmit="return check()">
		<tr >
			<th align="center">카테고리&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;상품명</th>
		</tr>	
		<tr>
			<td align="center">
					<select name="search">
					<c:forEach var="dto" items="${cateList}">
						<option value="${dto.cname}">${dto.cname} </option>
					</c:forEach>			
					</select>
					<input type= "text" name="searchString"/>
					<input type= "submit" value="찾기"/>
			</td>
		</tr>
	</form>
	</table>
	<c:if test="${empty find}">
 	<hr width="300">
 	<table width="700" class="outline">
 		<tr>
 			<th class="m3">상품번호</th>
 			<th class="m3">카테고리</th>
 			<th class="m3">상품명</th>
 			<th class="m3">상품가격</th>
			<th class="m3">상품수량</th>
			<th class="m3">상품이미지</th>
			<th class="m3">상품소개</th>
			<th class="m3">상품적립포인트</th>
			<th class="m3">수정|삭제</th>
 	</tr>
 <c:choose>
	<c:when test="${empty findProd}">
		<tr>
 			<td colspan="10"> 찾으시는 상품이 없습니다. </td>
 		</tr>
	</c:when>
	<c:otherwise>
	<c:forEach var="pdto" items="${findProd}">
	<tr>
		<td>${pdto.pnum}</td>
		<td>${pdto.pcategory} </td>
		<td>${pdto.pname}</td>
		<td>${pdto.pprice}</td>
		<td>${pdto.pqty}</td>
		<td><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${pdto.pimage}" width="40" height="40"></td>
		<td>${pdto.pcontent}</td>
		<td>${pdto.ppoint}</td>
		<td><a href="prod_update.mo?pnum=${dto.user_num}">수정</a>|
			<a href="prod_delete.mo?pnum=${dto.user_num}">삭제</a></td>
	</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
 	</table>
 	</c:if>
	
</div>
<%@ include file="../../bottom.jsp" %>