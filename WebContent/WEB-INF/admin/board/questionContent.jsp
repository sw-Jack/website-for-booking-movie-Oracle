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
				<th bgcolor="white" width="20%">글내용</th>
				<td align="center" width="80%" colspan="3">${getQuestion.content}</td>
			</tr>
			<tr bgcolor="white">
				<td align="right" colspan="4"><input type="button" value="답변달기"
					onclick="window.location='question_answer.mo?question_num=${getQuestion.question_num}&re_group=${getQuestion.re_group}&re_step=${getQuestion.re_step}&re_level=${getQuestion.re_level}&user_num=${getQuestion.user_num}'">
					<input type="button" value="글삭제"
					onclick="window.location='question_delete.mo?question_num=${getQuestion.question_num}'">
					<input type="button" value="글목록"
					onclick="window.location='question_list.mo'"></td>
			</tr>

		</table>
	</div>
</div>
<%@ include file="../bottom.jsp"%>















