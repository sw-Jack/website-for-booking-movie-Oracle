<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../top.jsp"%>
<script type="text/javascript">
	function check(){
		if(!f.pname.value){
			alert("상품명을 입력하세요");
			return false;
		}
		if(!f.pprice.value){
			alert("가격을 입력하세요");
			return false;
		}
		if(isNaN(f.pprice.value)){
			alert("가격은 숫자만 입력하세요")
			return false;
		}
		if(!f.pqty.value){
			alert("수량을 입력하세요");
			return false;
		}
		if(isNaN(f.pqty.value)){
			alert("수량은 숫자만 입력하세요")
			return false;
		}
		if(!f.pcontent.value){
			alert("상품소개를 입력하세요")
			return false;
		}
		if(!f.ppoint.value){
			alert("포인트를 입력하세요")
			return false;
		}
		if(!f.pimage.value){
			alert("상품 사진을 선택하세요")
			return false;
		}
	}
</script>
	
<c:choose>
	<c:when test="${empty cateList}">
		<script type="text/javascript">
			alert("카테고리를 먼저 등록해 주세요")
			location.href="cate_input.mo"
		</script>	
	</c:when>
	<c:otherwise>
		<div align="center">
	<form name="f" action="prod_inputP.mo" method="post" enctype="multipart/form-data" onsubmit="return check()">
		<table class="outline" width="600">
			<caption>상품등록카테고리</caption>
			<tr>
				<th class="m2" width="20%">카테고리</th>
				<td align="left">   
					<select name="pcategory">

					<c:forEach var="dto" items="${cateList}">
						<option value="${dto.cname}">${dto.cname} </option>
					</c:forEach>			
					</select>
				</td>
			</tr>	
			<tr>
				<th class="m2" width="20%">상품명</th>
				<td align="left"><input type="text" name="pname" class="box"></td>
			</tr>
			
			<tr>
				<th class="m2" width="20%">상품가격</th>
				<td align="left"><input type="text" name="pprice" class="box"></td>
			</tr>
			
			<tr>
				<th class="m2" width="20%">상품수량</th>
				<td align="left"><input type="text" name="pqty" class="box"></td>
			</tr>
			
			<tr>
				<th class="m2" width="20%">상품이미지</th>
				<td align="left"><input type="file" name="pimage" class="box"></td>
			</tr>
			
			<tr>
				<th class="m2" width="20%">상품소개</th>
				<td align="left">
					<textarea name="pcontent" rows="4" cols="50">
					</textarea>
				</td>
			</tr>
			
			<tr>
				<th class="m2" width="20%">상품 적립 포인트</th>
				<td align="left"><input type="text" name="ppoint" class="box"></td>
			</tr>
			
			<tr>
				<td align="center" colspan="2" class="m1">
					<input type="submit" value="상품등록">
					<input type="reset" value="취소">	
				</td>
			</tr>
			
		
		</table>
	
	</form>

</div>
	</c:otherwise>
</c:choose>
	
<%@ include file="../../bottom.jsp"%>
	