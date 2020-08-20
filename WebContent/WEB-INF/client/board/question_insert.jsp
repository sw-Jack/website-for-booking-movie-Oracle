<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp"%>
<html>
<head>

<title>문의사항</title>
</head>
<body>
<div align="center">

<h3>1:1문의</h3>
	<form name="f" action="cquestion_insertP.mo" method="post">
	<input type="hidden" value="${id}" name="id"/>
		<table border="1">
		
		
		<tr>
			<th>작성자</th>
			<td>${id}</td>
		</tr>
		
		<tr>
			<th>글제목</th>
			<td><input type="text" name="title" size="50" class="box"></td>
		</tr>
		
		<tr>
				<th>문의내용</th>
				<td><textarea name="content" rows="10" cols="50"></textarea></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="문의하기">
				<input type="reset" value="다시작성">
			</td>
		</tr>
		</table>
	
	</form>
</div>
</body>
</html>
<%@ include file="../bottom.jsp"%>	