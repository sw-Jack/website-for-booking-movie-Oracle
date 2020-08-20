<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp"%>
<script type="text/javascript">
function subm(){
	document.getElementById('f').submit();
}
function nonlogin(){
	alert("로그인 후 이용 가능합니다.");
	location.href="loginMemberP.mo";
}
function upsubm(){
	document.d.submit();
}
</script>
<div class="container">
	<div align="center">
			<table class="class" width="99%">
				<h2 align="left">영화 상세 정보</h2>
				<hr color="black" width="100%">
					<tr>
						<td align="center" colspan="2"><iframe width="100%"
								height="450" src="${mvdto.trailer}" frameborder="0"
								allowfullscreen></iframe> </td>
					</tr>
					<tr>
						<td align="center" rowspan="3" width="5%">
						<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${mvdto.image}" border="0" width="180" height="280">
						</td>
						<td width="35%" align="left"><h3>${mvdto.title}</h3><br>
						<h4>예매 횟수 <b>${mvdto.resercount}</b> | 관람 평점 <b>${mvdto.score}</b></h4>
						</td>
					
					</tr>
					<tr>
						
						<td width="35%" align="left">장르: ${mvdto.genre} |
							개봉: ${mvdto.opendate_year}. ${mvdto.opendate_month}.
							${mvdto.opendate_day} | ${mvdto.time}분 | ${mvdto.grade} | ${mvdto.state}</td>
					</tr>
					<tr>
						<td width="35%" align="left">감독: ${mvdto.director} |
							${mvdto.actor}, ${mvdto.actor2}, ${mvdto.actor3},
							${mvdto.actor4}, ${mvdto.actor5}, ${mvdto.actor6}..<a href="actor_view.mo?movie_num=${mvdto.movie_num}"><u><b>더보기</b></u></a></td>
							
					</tr>
					<tr>
					<td align="center"><br>
					<a href="cselectMovie.mo?movie_num=${mvdto.movie_num}">
					<img src="images/reserbutton1.jpg" border="0" width="180" height="40"></a>
					</td>
					</tr>
				<tr>
					<td colspan="4" class="m1" align="left">
					<h3><br>줄거리</h3>
					<hr color="black" width="100%">
					${mvdto.story}</td>
				</tr>
			</table>
			<hr color="black" width="100%">
			<h4>한줄평</h4>
			<form id="f" action="inputreview.mo" method="post">
			<input type="hidden" name="review_writer" value="${id}"/>
			<input type="hidden" name="movie_num" value="${mvdto.movie_num}"/>
			<table>
			<tbody>
				<tr>
				<td><h4>평점</h4></td>
				<td>
				<select name="review_score">
					<c:forEach var="i" begin="1" end="10" step="1">
						<option  <c:if test="${i==10}">selected</c:if>>${i}</option>
					</c:forEach>
				</select>
				</td>
				<td>
				<c:choose>
				<c:when test="${empty id}">
					<textarea cols="130" rows="5" name="review_content" disabled="disabled" placeholder="로그인 후 이용 가능합니다."></textarea>
				</td>
				<td>
					<a href="javascript:nonlogin();"><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/enter.png" border="0" width="100" height="100"></a>
				</td>	
				</c:when>
				<c:otherwise>
					<textarea cols="130" rows="5" name="review_content"></textarea></td>
					<td>
					<a href="javascript:subm();"><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/enter.png" width="100" height="100"></a>
					</td>
				</c:otherwise>
				</c:choose>
				</td>
				</tr>
				</tbody>
			</form>
			</table>
			<hr color="black" width="100%">
			<table align="left">
				<c:forEach var="dto" items="${reviewList}">
				<c:choose>
				<c:when test="${update_num ne dto.review_num}">
					<tr>
						<td rowspan="2"><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${dto.writer_image}" width="70" height="70">&nbsp;</td>
						<td align="left"><font size="3"><b>${dto.review_writer}</b></font>&nbsp;&nbsp;<font color="#C0C0C0">${dto.review_date}</font>
							&nbsp;&nbsp;평점:<font color="red">${dto.review_score}</font>점 &nbsp;&nbsp;
							<c:choose>
							<c:when test="${id eq dto.review_writer}">
							<a href="review_update.mo?review_num=${dto.review_num}&movie_num=${mvdto.movie_num}">수정</a>/
							<a href="review_delete.mo?review_num=${dto.review_num}&movie_num=${mvdto.movie_num}&id=${id}">삭제</a></c:when>
							<c:when test="${a_id ne null}">
							<a href="review_delete.mo?review_num=${dto.review_num}&movie_num=${mvdto.movie_num}&id=${id}&a_id=${a_id}">삭제</a>
							</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td align="left">${dto.review_content}</td>
					</tr>
				</c:when>
				<c:otherwise>
				<c:if test="${dto.review_writer ne id}"><%response.sendRedirect("kgvindex.mo"); %></c:if>
				<form name="d" id="d" action="review_updateP.mo" method="post">
				<input type="hidden" name="movie_num" value="${dto.movie_num}"/>
				<input type="hidden" name="review_num" value="${dto.review_num}"/>
					<tr>
						<td rowspan="2">
				<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${dto.writer_image}" width="70" height="70">&nbsp;</td>
						<td align="left"><font size="3"><b>${dto.review_writer}</b></font>&nbsp;&nbsp;
						<select name="review_score">
							<c:forEach var="i" begin="1" end="10" step="1">
								<option <c:if test="${i==10}">selected</c:if>>${i}</option>
							</c:forEach>
				</select><br>
							<textarea name="review_content" cols="100" rows="3">${dto.review_content}</textarea></td><td>
							<a href="javascript:upsubm();"><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/enter.png" width="50" height="70"></a>
						</td>
					</tr>
				</form>
				</c:otherwise>
				</c:choose>
					<tr>
					<td colspan="2">  &nbsp;</td>
					</tr>
					<tr>
					<td colspan="2">  &nbsp;</td>
					</tr>
					
				</c:forEach>
			</table>
			
		</div>
</div>
<%@ include file="../bottom.jsp"%>

