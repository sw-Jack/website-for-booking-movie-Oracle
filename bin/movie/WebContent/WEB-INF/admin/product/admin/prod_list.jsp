<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ include file="/top.jsp" %>
<script type="text/javascript"> 
	function checkDel(pnum, pimage){
		var isDel = window.confirm("정말로 삭제하시겠습니까?");
		if (isDel){
			location.href="prod_delete.mo?pnum="+pnum + "&pimage="+pimage;
		}
	}
</script>
	<div align="center">
		<table  class="outline" width="99%">
			<tr>
				<th>번호</th>
				<th>상품카테고리</th>
				<th>상품명</th>
				<th>상품이미지</th>
				<th>상품가격</th>
				<th>상품수량</th>
				<th>상품포인트</th>
				<th>수정|삭제</th>
			</tr>

<c:choose>
	<c:when test="${empty prodList}">
		<tr><td colspan="8">등록된 상품이 없습니다.</td></tr>	
	</c:when>
	<c:otherwise>
	<c:forEach var="pdto" items="${prodList}">
		<tr>
				<td>${pdto.pnum}</td>
				<td>${pdto.pcategory}</td>
				<td>${pdto.pname}</td>			
				<td>
					<a href="prod_content.mo?pnum=${pdto.pnum}">
						<img src="${upPath}/${pdto.pimage}" width="40" height="40">
					</a>
				</td>
				<td>${pdto.pprice}</td>
				<td>${pdto.pqty}</td>
				<td>${pdto.ppoint}
				<td>
				<a href="prod_update.mo?pnum=${pdto.pnum}">수정</a>|
				<a href="javascript:checkDel('${pdto.pnum}','${pdto.pimage}');">삭제</a></td>
			</tr>
	</c:forEach>
	</c:otherwise>
</c:choose>
				 	
		</table>
	</div>
<%@ include file="/bottom.jsp" %>










