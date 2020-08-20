<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp"%>
<link rel="stylesheet" type="text/css" href="../style.css">
<script type="text/javascript">
	function checkBoard() {
		if (f.writer.value == "") {
			alert("이름을 입력하세요")
			f.writer.focus()
			return false;
		}
		if (f.subject.value == "") {
			alert("제목을 입력하세요")
			f.subject.focus()
			return false;
		}
		if (f.content.value == "") {
			alert("내용을 입력하세요")
			f.content.focus()
			return false;
		}
		if (f.passwd.value == "") {
			alert("비밀번호를 입력하세요")
			f.passwd.focus()
			return false;
		}
		return true;
	}
</script>
<div class="container">
	<div align="center">
		<form name="f" action="question_answerP.mo" method="post"
			onsubmit="return checkBoard()">

			<input type="hidden" name="question_num" value="${question_num}"> 
			<input type="hidden" name="re_group" value="${re_group}"> 
			<input type="hidden" name="re_step" value="${re_step}">
			<input type="hidden" name="re_level" value="${re_level}">
			<input type="hidden" name="user_num" value="${user_num}">

			<table border="1" class="table"> 
				<tr bgcolor="white" align="center">
					<th colspan="2">답변달기</th>
				</tr>
				<tr>
				<tr>
					<td width="20%" bgcolor="white" size="50" class="box">작성자</td>
					<td><input type="text" name="name" class="box" value="운영자"
						readOnly></td>
				</tr>
				<tr>
					<th width="20%" bgcolor="white">제목</th>
					<td><input type="text" name="title" size="50" class="box"></td>
				</tr>
				<tr>
					<th width="20%" bgcolor="white">내용</th>
					<td><textarea name="content" rows="10" cols="50"></textarea></td>
				</tr>
				</tr>
				<tr bgcolor="white">
					<td colspan="2" align="center"><input type="submit"
						value="글쓰기"> <input type="button" value="다시작성"
						onclick="window.location='question_answer.mo'"> <input
						type="button" value="목록보기"
						onclick="window.location='question_list.mo'">
			</table>

		</form>
	</div>
</div>
<%@ include file="../bottom.jsp"%>
