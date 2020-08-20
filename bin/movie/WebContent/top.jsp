<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width",initial-scale="1">
	<title>SGV 관리자 페이지</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/codingBooster.css">
</head>
<body>
	<style type="text/css">
		.jumbotron{
			background-image: url('${pageContext.request.contextPath }/images/spy.jpg');
			background-size: cover;
			text-shadow: black 0.2em 0.2em 0.2em;
			color: white;
		}
	</style>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.mo">CV</a>
			</div>
			<div class="collapsed navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="index.mo">메인<span class="sr-only"></span></a></li>
		
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">매출 관리<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">년,월,일 매출 관리</a></li>
							<li><a href="#">영화별 매출 관리</a></li>
							<li><a href="#">영화관별 매출</a></li>
							<li><a href="#">상품 매출</a></li>
						</ul>
					</li>
					
					<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">영화 관리<span class="caret"></span></a>
						<ul class="dropdown-menu">
								<li><a href="movie_insert.mo">영화추가</a></li>
								<li><a href="movie_list.mo">영화리스트</a></li>
						</ul>
					</li>
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">영화관 관리<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="insertCinema.mo">영화관 추가</a></li>
							<li><a href="listCinema.mo">영화관 목록</a></li>
							<li><a href="#">상영 영화 관리</a></li>
							<ul class="dropdown-menu1">
								<li><a href="insertmovie.mo">상영 영화 추가</a></li>
								<li><a href="listMovie.mo">상영 영화 목록</a></li>
							</ul>
						</ul>
					</li>
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">게시판 관리<span class="caret"></span></a>
						<ul class="dropdown-menu">
						<li><a href="#">공지사항</a></li>
							<ul class="dropdown-menu1">
								<li><a href="noticeinsert.mo">공지사항 추가</a></li>
								<li><a href="noticelist.mo">공지사항 목록</a></li>
							</ul>						
							<li><a href="#">1:1 문의</a></li>
							<ul class="dropdown-menu2">
								<li><a href="question_list.mo">1:1 문의 확인</a></li>
							</ul>								
							<li><a href="#">리뷰</a></li>
							<ul class="dropdown-menu3">
								<li><a href="#">리뷰 확인</a></li>
								<li><a href="#">리뷰 삭제</a></li>
							</ul>
						</ul>
					</li>
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">회원 관리<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="memberList.mo">회원 보기</a></li>
							<li><a href="memberFind.mo">회원 찾기</a></li>
							<li><a href="#">회원정보 조회</a></li>
							<ul class="dropdown-menu1">
								<li><a href="#">예매내역확인</a></li>
								<li><a href="#">정보조회</a></li>
							</ul>
						</ul>
					</li>
					
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">상품 관리<span class="caret"></span></a>
						<ul class="dropdown-menu">
						<li><a href="#">카테고리 관리</a></li>
							<ul class="dropdown-menu1">
								<li><a href="cate_input.mo">카테고리 추가</a></li>
								<li><a href="cate_list.mo">카테고리 보기</a></li>
							</ul>
							<li><a href="#">상품 관리</a></li>
							<ul class="dropdown-menu2">
								<li><a href="prod_input.mo">상품 추가</a></li>
								<li><a href="prod_list.mo">상품 목록</a></li>
								<li><a href="prod_find.mo">상품 찾기</a></li>
							</ul>
						</ul>
					</li>
					
				</ul>
				
			</div>
		</div>
	</nav>