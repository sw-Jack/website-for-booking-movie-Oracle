<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp"%>
<link rel="stylesheet" type="text/css" href="../style.css">

<script type="text/javascript">
	function updateCheck() {
		return confirm("수정하시겠습니까?")
	}
	function deleteCheck() {
		return confirm("정말로 삭제하시겠습니까?")
	}
</script>

<div class="container">
	<div align="center">
		<b>문의 내역</b>
		<table width="1100">
		<tr>
			<td align="right"><a href="cquestion_insert.mo"><u>문의하기</u></a></td>
		</tr>
		</table>
		<br>
		<table width="700" border="1" class="table">
			<tr bgcolor="white">
				<th>번호</th>
				<th width="40%">제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>수정 </th>
			</tr>
			<c:if test="${empty questionList}">
				<tr>
					<td colspan="6">문의 내역이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="qdto" items="${requestScope.questionList}">
				<tr>
					<td>${qdto.question_num}</td>
					<td><c:if test="${qdto.re_level>0}">
							<img src="images/level.gif" width="${qdto.re_level*10}">
							<img src="images/re.gif">
						</c:if> <a href="cquestion_content.mo?question_num=${qdto.question_num}">${qdto.title}</a></td>
					<td>${qdto.name}</td>
					<td>${qdto.writedate}</td>
					<td><a href="cquestion_update.mo?question_num=${qdto.question_num}" onclick="return updateCheck();">수정</a></td>
				</tr>
			</c:forEach>
		</table>
		</div>
 </div>
<%@ include file="../bottom.jsp"%>	