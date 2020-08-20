<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../top.jsp" %>
<script type="text/javascript">
function check(){
	if(!f.title.value){
		alert("제목을 입력하세요.")
		return false;
	}
	if(!f.content.value){
		alert("내용을 입력하세요.")
		return false;
	}
}
</script>
<div class="container">
<form name="f" action="noticeinsertP.mo" method="post" onsubmit="return check()">
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
<%@ include file="../bottom.jsp"%>