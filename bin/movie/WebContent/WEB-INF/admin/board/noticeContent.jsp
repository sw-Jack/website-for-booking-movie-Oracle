<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="movie.admin.board.dto.*"%>
<%@ include file="/top.jsp" %>
<script type="text/javascript">
			function updateCheck(){
				return confirm("수정하시겠습니까?")
			}
			function deleteCheck(){
				return confirm("정말로 삭제하시겠습니까?")
			}
</script>
<%
	NoticeDTO dto = (NoticeDTO)request.getAttribute("getnotice");
%>
<div class="container">
	<table class="table">
		<thead>
			<h4>공지사항</h4>
		</thead>
		<tbody>
			<tr>
				<th>제목</th><td colspan="3"><%=dto.getTitle()%></td>
			</tr>
			<tr>
				<th>작성자</th><td><%=dto.getName()%></td><th>작성일</th><td><%=dto.getWritedate()%></td>
			</tr>
			<tr>
				<th>내용</th><td colspan="3"><%=dto.getContent()%></td>
			</tr>
			<tr>
				<td colspan="4">
					<a href="noticeupdate.mo?notice_num=<%=dto.getNotice_num()%>" onclick="return updateCheck();">수정</a> |
					<a href="noticedelete.mo?notice_num=<%=dto.getNotice_num()%>" onclick="return deleteCheck();">삭제</a> |
					<a href="noticelist.mo" onclick="return deleteCheck();">목록</a>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<%@ include file="/bottom.jsp" %>