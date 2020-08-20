<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/top.jsp"%>
<div align="center">
<form name="f" action="insertCinema.mo" method="post">
<h2>영화관 등록</h2>
<div class="container">
<table class="table">
	<tr class="active" align="center">
		<th>지점명</th><td><input type="text" name="cinema_addr" /></td>
	</tr>
	<tr class="active" align="center">
		<th>상영관 수</th><td><input type="text" name="cinema_size" /></td>
	</tr>
	<tr class="active" align="center">
		<th>담당자</th><td><input type="text" name="cinema_admin" /></td>
	</tr>
	<tr class="active" align="right">
		<td colspan="2"><input type="submit" value="다음" /></td>
	</tr>
</table>
</div>
</form>
</div><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="/bottom.jsp"%>