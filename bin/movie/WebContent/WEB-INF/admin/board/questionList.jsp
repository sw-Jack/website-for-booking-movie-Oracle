<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/top.jsp"%>
<link rel="stylesheet" type="text/css" href="../style.css">
<div class="container">
	<div align="center">
		<b>문의 내역</b>
		<table width="700" border="1" class="table">
			<tr bgcolor="white">
				<th>번호</th>
				<th width="40%">제목</th>
				<th>작성자</th>
				<th>작성일</th>
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
						</c:if> <a href="question_content.mo?question_num=${qdto.question_num}">${qdto.title}</a></td>
					<td>${qdto.name}</td>
					<td>${qdto.writedate}</td>
					
				</tr>
			</c:forEach>
		</table>
		<table width="700" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td colspan="4" height="5"></td>
			</tr>
			<tr>
				<td align="center"></td>
			</tr>
			<tr align="center">
			</tr>
		</table>
		<c:set var="startPage" value="${requestScope.startPage}" />
		<c:set var="pageBlock" value="${requestScope.pageBlock}" />
		<c:set var="endPage" value="${requestScope.endPage}" />
		<c:set var="pageCount" value="${requestScope.pageCount}" />

		<c:if test="${startPage>pageBlock}">
			<a href="question_list.mo?pageNum=${startPage-pageBlock}">[이전]</a>
		</c:if>

		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href="question_list.mo?pageNum=${i}">[${i}]</a>
		</c:forEach>

		<c:if test="${endPage>pageBlock}">
			<a href="question_list.mo?pageNum=${startPage+pageBlock}">[다음]</a>
		</c:if>

	</div>
</div>
<%@ include file="/bottom.jsp"%>