<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="top.jsp"%>
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
   </ol>
   
  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox" align="center">
    <div class="item active">
      <img src="images/onward.jpg" alt="시각장애인용 설명란" width="800" height="500">
      <div class="carousel-caption">
      </div>
    </div>
    <div class="item">
      <img src="${pageContext.request.contextPath}/images/intruder.jpg" alt="..." width="800" height="500">
      <div class="carousel-caption">
      </div>
    </div>
      <div class="item">
      <img src="images/gonetime.jpg" alt="..." width="800" height="500">
      <div class="carousel-caption">
      </div>
    </div>
    <div class="item">
      <img src="${pageContext.request.contextPath}/images/showman.jpg" alt="..." width="800" height="500">
      <div class="carousel-caption">
      </div>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
	<div class="container">
		<div align="center">
			<table class="table" width="99%">
				<c:if test="${empty cmovieList}">
					<tr>
						<td colspan="8">등록된 영화가 없습니다.</td>
					</tr>			
				</c:if>
					<tr align="center">
					<c:forEach var="mvdto" items="${cmovieList}">
						<td align="left">
						<div style="position: absolute;">
							<div style="position: relative; top: 1px; left: 1px;">
								<img src="images/best.png" width="50" height="50"></img>
							</div>
						</div> 
						<br>
						<br>
						<a href="cmovie_view.mo?movie_num=${mvdto.movie_num}"> <img style="border: 7px solid black;border-radius: 7px;-moz-border-radius: 7px;-khtml-border-radius: 7px;-webkit-border-radius: 7px;"
								src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${mvdto.image}" width="180" height="280"></a><br>
							<a href="cmovie_view.mo?movie_num=${mvdto.movie_num}"><h4>${mvdto.title}</h4></a><br>
							예매횟수 ${mvdto.resercount} | 평점 ${mvdto.score} <br><br>
							<a href="cselectMovie.mo?movie_num=${mvdto.movie_num}"> <img src="images/reserbutton1.jpg"
								border="0" width="100" height="30"></a>
							</td>
					</c:forEach>
					</tr>
			</table>
			<c:if test="${not empty param.mode}">
				<h2>영화 검색</h2>
			</c:if>
		</div>
	</div>
<%@ include file="bottom.jsp"%>