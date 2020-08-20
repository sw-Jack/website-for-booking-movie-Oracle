<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	HttpSession sess = request.getSession();
	String id = (String)sess.getAttribute("id");
	String a_id = (String)sess.getAttribute("a_id");
%>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width",initial-scale="1">
	<title>SGV</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codingBooster.css">
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=YOUR_CLIENT_ID&submodules=geocoder"></script>
  
</head>
<body>
	
	<style type="text/css">
		.jumbotron{
			background-image: url('images/logo.jpg');
			background-size: cover;
			text-shadow: black 0.2em 0.2em 0.2em;
			color: white;
		}
	</style>
	
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header" align="center">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="kgvindex.mo"><img src="images/SGVlogo.png" width="35" height="35"></a>
			</div>
			<div class="collapsed navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="kgvindex.mo">메인<span class="sr-only"></span></a></li>
		
					<li class="active"><a href="cmovie_list.mo">영화<span class="sr-only"></span></a></li>
					
					<li class="active"><a href="cinema_list.mo">영화관<span class="sr-only"></span></a></li>
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">예매/티켓관련<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="reservation.mo">예매하기</a></li>
							<li><a href="viewreser.mo">예매내역확인</a></li>
						</ul>
					</li>
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">스토어<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="store_main.mo">스낵바</a></li>
						</ul>
					</li>
					
					<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">고객센터<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="cquestion_list.mo">1:1문의</a></li>
								<li><a href="notice.mo">공지사항</a></li>
							</ul>
					</li>
					
				</ul>
				<%if(a_id!=null) {%>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">관리자페이지<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="adminIndex.mo">관리자페이지이동</a></li>
								<li><a href="logout.mo">로그아웃</a></li>
							</ul>
						</li>
						</ul>
				<%}
				else if(id==null){
				%>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">로그인<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="loginMemberP.mo">로그인</a></li>
							<li><a href="register.mo">회원가입</a></li>
						</ul>
					</li>
				</ul>
				<%}else{ %>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">내정보<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="logout.mo">로그아웃</a></li>
							<li><a href="getMemberInfo.mo">마이페이지</a></li>
							<li><a href="viewreser.mo">예매내역확인</a></li>
							<li><a href="member_passwd.mo">비밀번호변경</a></li>
						</ul>
					</li>
				</ul>
				<%
				} %>
			</div>
		</div>
	</nav>