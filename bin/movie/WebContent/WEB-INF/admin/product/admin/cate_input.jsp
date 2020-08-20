<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/top.jsp"%>

<div align="center">
	<form name="f" action ="cate_input.mo" method="post">
		<table border="1">
			<cation>카테고리 등록</cation>
			
			
			<tr>
				<th>카테고리 이름</th>
				<td><input type="text" name="cname"></td>
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
<%@ include file="/bottom.jsp"%>