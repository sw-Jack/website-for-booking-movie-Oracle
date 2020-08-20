<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<footer style="background-color: #000000; color: #ffffff">
		<div class="container">
			<br>
			<div class="row">
				 <div class="col-sm-2" style="text-align: center;"><h5>Copyright &copy; 2017</h5><h5>aa</h5></div>
				 <div class="col-sm-4"><h4>대표자 소개</h4><p>영화관 예매 페이지 입니다. 반갑습니다.</p></div>
				 <div class="col-sm-2"><h4 style="text-align: center;">네비게이션</h4>
				 	<div class="list-group">
				 		<a href="#" class="list-group-item">메인</a>
				 		<a href="#" class="list-group-item">매출</a>
				 		<a href="#" class="list-group-item">영화목록</a>
				 	</div>
				 </div>
				 <div class="col-sm-2"><h4 style="text-align: center;">SNS</h4>
				 	<div class="list-group">
				 		<a href="https://www.facebook.com/ " class="list-group-item">페이스북</a>
				 		<a href="https://www.youtube.com/" class="list-group-item">유튜브</a>
				 		<a href="https://www.naver.com/" class="list-group-item">네이버</a>
				 	</div>
				 </div>
				 <div class="col-sm-2"><h4 style="text-align: center;"><span class="glyphicon glyphicon-ok"></span>&nbsp;by aa</h4></div>
			</div>
		</div>
	</footer>
	<div class="row">
		<div class="modal" id="modal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						우리 영화관의 특징
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" style="text-align: center;">
						저희 서비스의 특징은 바로 예매가 가능합니다.<br>
						어떤 영화관이든 직접 예매가 가능 합니다.<br><br>
						<img src="${pageContext.request.contextPath }/images/youtube.jpg" id="imagepreview" style="width: 256px; height: 256px;">
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
</body>
</html>