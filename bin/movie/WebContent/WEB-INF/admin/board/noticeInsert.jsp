<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/top.jsp" %>
<div class="container">
<form name="f" action="noticeinsert.mo" method="post">
	<table class="table">
	<thead>
		<h4>공지사항 쓰기</h4>
	</thead>
	<tbody>
		<tr>
			<th>글 제목</th><td><input type="text" name="title" size="50"></td>
		</tr>
		<tr>
			<th>글 내용</th><td><textarea rows="10" cols="50" name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="글 쓰기"><input type="reset" value="다시작성"></td>
		</tr>
	</tbody>
	</table>
</form>
</div>
<%@ include file="/bottom.jsp"%>