<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ include file="../top.jsp"%>

<link rel="stylesheet" type="text/css" href="../style.css">
<div class="container">
	<div align="center">
		<b>문의 내용</b>
		<table border="1" width="500" class="table">

			<tr>
				<th bgcolor="white" width="20%">글번호</th>
				<td align="center" width="30%" colspan="3">${getQuestion.question_num}</td>
			</tr>
			
			<tr>
				<th bgcolor="white" width="20%">작성자</th>
				<td align="center" width="30%">${getQuestion.name}</td>
				<th bgcolor="white" width="20%">작성일</th>
				<td align="center" width="30%">${getQuestion.writedate}</td>
			</tr>
			
			<tr>
				<th bgcolor="white" width="20%">글제목</th>
				<td align="center" width="80%" colspan="3">${getQuestion.title}</td>
			</tr>
			
			<tr>
				<th bgcolor="white" width="20%">문의내용</th>
				<td align="center" width="80%" colspan="3" height="300">${getQuestion.content}</td>
			</tr>
			
			<tr bgcolor="white">
				<td align="right" colspan="4">
					<input type="button" value="글목록"
					onclick="window.location='cquestion_list.mo?id=<%=id%>'">
				</td>
			</tr>

		</table>
	</div>
</div>
<%@ include file="../bottom.jsp"%>
