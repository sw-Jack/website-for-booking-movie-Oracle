<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../top.jsp"%>

<div align="center">
	<form name="f" action ="cate_inputP.mo" method="post">
		<table border="1">
			<cation>카테고리 등록</cation>
			
			
			<tr>
				<th>카테고리 이름</th>
				<td><input type="text" name="cname"></td>
			</tr>
			<tr>
				<th>카테고리 코드</th>
				<td><input type="text" name="ccode"></td>
			</tr>
			
			<tr>
				<td cospan="2" align="center">
					<input type="submit" value="등록">
					<input type="reset" value="취소">
				</td>
			</tr>
		
		</table>
	</form>
</div>
<%@ include file="../../bottom.jsp"%>