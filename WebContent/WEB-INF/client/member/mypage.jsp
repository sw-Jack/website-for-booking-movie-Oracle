<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp" %>
<script type="text/javascript">
function submitt(){
	document.getElementById('files').submit();
}
</script>
	<div class="container">
		<div align="center">
			<h2 align="left">회원정보</h2>
			<hr color="black" width="100%">
				<form name="files" id="files" enctype="multipart/form-data" action="update_image.mo" method="post">
				<input type="hidden" name="user_num" value="${medto.user_num}">
							<div style="position: absolute;">
								<div style="position: relative; top: -4px; left: 120px;">
								<input type="file" name="file1" style='display:none'>
								<img src='images/sujung.PNG' width="30" height="15" onclick='document.files.file1.click(); submitt();'>
								</div>
							</div>
				</form>
			<form name="f" action="update_profile.mo?user_num=${medto.user_num}">
			<input type="hidden" name="user_num" value="${medto.user_num}">
				<table class="table" width="99%">
					<tr>
						<td rowspan="7" align="center">
						 
						<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${medto.member_image}" width="70" height="70">
						<br><input type="submit" value="정보 수정" width="70">
						</td>
					</tr>
					<tr>
						<td><b>아이디</b></td><td>${medto.id}</td>
					</tr>
					<tr>
						<td><b>이름</b></td><td>${medto.name}</td>
					</tr>
					<tr>
						<td><b>이메일</b></td><td>${medto.email}</td>
					</tr>
					<tr>
						<td><b>생년월일</b></td><td>${medto.birth_year}년 ${medto.birth_month}월 ${medto.birth_day}일</td>
					</tr>
					<tr>
						<td><b>전화번호</b></td><td>${medto.hp1}-${medto.hp2}-${medto.hp3}</td>
					</tr>
					<tr>
						<td><b>주소</b></td><td>${medto.adrr} ${medto.addr_detail}</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
<%@ include file="../bottom.jsp" %>