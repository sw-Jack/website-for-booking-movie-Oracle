<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,movie.admin.board.dto.*"%>
<%@ include file="../top.jsp" %>
<%
	List<NoticeDTO> list = (List)request.getAttribute("noticeList");
%>
<div class="container">
	<table class="table" border="1">
		<thead>
			<h4>공지사항</h4>
		</thead>
		<tbody>
			<tr>
				<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th>
			</tr>
			<%if(list==null||list.size()==0){ %>
			<tr>
				<th>작성된 공지사항이 없습니다</th>
			</tr>
			<%}else{ %>
			<%for(NoticeDTO dto : list){ %>
			<tr>
				<td>
					<%=dto.getNotice_num()%>
				</td>
				<td width="40%">
					<a href="cnoticecontent.mo?notice_num=<%=dto.getNotice_num()%>"><%=dto.getTitle()%></a>
				</td>
				<td>
					<%=dto.getName()%>
				</td>
				<td>
					<%=dto.getWritedate()%>
				</td>
			</tr>
			<%} 
				}%>
		</tbody>
	</table>
</div>
<%@ include file="../bottom.jsp" %>