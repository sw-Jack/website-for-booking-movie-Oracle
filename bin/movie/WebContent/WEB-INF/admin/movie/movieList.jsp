<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/top.jsp"%>
<script type="text/javascript">
			function updateCheck(){
				return confirm("수정하시겠습니까?")
			}
			function deleteCheck(){
				return confirm("정말로 삭제하시겠습니까?")
			}
			</script>
<div class="container">
<div align="center">
	<h2>영화 리스트</h2>
	<table class="table" width="99%">

		<c:if test="${empty movieList}">
			<tr>
				<td colspan="8">등록된 영화가 없습니다.</td>
			</tr>
		</c:if>
		<tr align="center">
			<c:set value="0" var="row"/>
			<%
				int row = -1;
			%>
			<c:forEach var="mvdto" items="${movieList}">
			
			
				<%row++; 
				if(row%5==0){%>
					<tr>
					</tr>
				<%} %>
				<td align="center"><a href="movie_view.mo?movie_num=${mvdto.movie_num}">
					<img src="${image}/${mvdto.image}" width="130" height="180"></a><br>
					${mvdto.title}<br> 
					<a href="movie_update.mo?movie_num=${mvdto.movie_num}" onclick="return updateCheck();">수정</a> | 
					<a href="movie_delete.mo?movie_num=${mvdto.movie_num}" onclick="return deleteCheck();">삭제</a>
				</td>
			</c:forEach>
		</tr>
	</table>
	<%
	int count = (int)request.getAttribute("count");
	int pageSize = (int)request.getAttribute("pageSize");
	int currentPage = (int) request.getAttribute("currentPage");
		if (count>0){
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			int pageBlock = 3;
			int startPage = (currentPage-1)/pageBlock * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage>pageCount) endPage = pageCount;
			if (startPage>pageBlock){%>
			<a href="movie_list.mo?pageNum=<%=startPage-3%>">[이전]</a>	
<%		} 
			for(int i=startPage; i<=endPage; ++i){%>
				<a href="movie_list.mo?pageNum=<%=i%>">[<%=i%>]</a>
<%		}
			if (endPage < pageCount){%>
			<a href="movie_list.mo?pageNum=<%=startPage+3%>">[다음]</a>		
<%		}%>			
	
<%	} %>	
</div>
</div>

<%@ include file="/bottom.jsp"%>
