<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/top.jsp"%>

<div align="center">
	<form name="f" action="prod_update.mo" method="post"
														enctype="multipart/form-data">
		<input type="hidden" name="pnum" value="${getProd.pnum }">															
		<table class="outline" width="600">
			<caption>상품카테고리</caption>
			
			<tr>
				<th class="m2" width="20%">카테고리명</th>
				<td align="left">   
					<input type="text" name="pcategory" 
						value="${getProd.pcategory }" class="box" disabled>
				</td>
			</tr>	
			
			<tr>
				<th class="m2" width="20%">상품명</th>
				<td align="left"><input type="text" name="pname" 
					value="${getProd.pname }" class="box"></td>
			</tr>
			
			<tr>
				<th class="m2" width="20%">상품이미지</th>
				<td align="left">

					<img src="${upPath}/${getProd.pimage}" width="40" height="40">
					<input type="hidden" name="pimage2" value="${getProd.pimage}">			
					<input type="file" name="pimage" class="box">
				</td>
			</tr>
			<tr>
				<th class="m2" width="20%">상품수량</th>
				<td align="left"><input type="text" name="pqty" 
					value="${getProd.pqty}" class="box"></td>
			</tr>
			<tr>
				<th class="m2" width="20%">상품가격</th>
				<td align="left"><input type="text" name="pprice" 
					value="${getProd.pprice}" class="box"></td>
			</tr>
		
			<tr>
				<th class="m2" width="20%">상품소개</th>
				<td align="left">
					<textarea name="pcontent" rows="4" cols="50">${getProd.pcontent}
					</textarea>
				</td>
			</tr>
			<tr>
				<th class="m2" width="20%">상품포인트</th>
				<td align="left"><input type="text" name="ppoint" 
					value="${getProd.ppoint}" class="box"></td>
			</tr>
			<tr>
				<td align="center" colspan="2" class="m1">
					<input type="submit" value="상품수정">
					<input type="reset" value="취소">	
				</td>
			</tr>
		</table>												
	</form>
</div>
<%@ include file="/bottom.jsp"%>