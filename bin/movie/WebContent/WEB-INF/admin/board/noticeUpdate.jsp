<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="movie.admin.board.dto.*"%>
<%@ include file="/top.jsp" %>
<%
	NoticeDTO dto = (NoticeDTO)request.getAttribute("getnotice");
%>
<div class="container">
<form name="f" action="noticeupdate.mo" method="post">
<input type="hidden" name="notice_num" value="<%=dto.getNotice_num()%>"/>
	<table class="table">
	<thead>
		<h4>공지사항 수정</h4>
	</thead>
	<tbody>
		<tr>
			<th>글 제목</th><td><input type="text" name="title" size="50" value="<%=dto.getTitle()%>"></td>
		</tr>
		<tr>
			<th>글 내용</th><td><textarea rows="10" cols="50" name="content" ><%=dto.getContent()%></textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="글 쓰기"><input type="reset" value="다시작성"></td>
		</tr>
	</tbody>
	</table>
</form>
</div>
<%@ include file="/bottom.jsp"%>