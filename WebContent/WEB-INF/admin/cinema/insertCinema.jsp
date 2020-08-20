<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../top.jsp"%>
<script type="text/javascript">

function checkValue(){
	var f = document.f;

	if(!f.cinema_size.value){
		alert("상영관 수를 입력하세요");
		return false;
	}
	if(isNaN(f.cinema_size.value)){
		alert("상영관 수는 숫자만 입력가능합니다");
		return false;
	}
	if(!f.cinema_addr.value){
		alert("시네마 이름을 입력하세요");
		return false;
	}
	if(!f.cinema_admin.value){
		alert("관리자명을 입력하세요");
		return false;
	}
	if(!f.admin_id.value){
		alert("아이디를 입력하세요");
		return false;
	}
	if(f.idcheckcheck.value != "idCheck"){
		alert("아이디 중복체크를 하세요");
		return false;
	}
	if(!f.admin_passwd.value){
		alert("비밀번호를 입력하세요");
		return false;
	}
	if(f.admin_passwd.value != f.admin_passwdcheck.value){
		alert("비밀번호가 일치하지 않습니다");
		return false;
	}
	if(!f.tel1.value){
		alert("전화번호를 올바르게 입력하세요")
		return false;
	}
	if(!f.tel2.value){
		alert("전화번호를 올바르게 입력하세요")
		return false;
	}
	if(!f.tel3.value){
		alert("전화번호를 올바르게 입력하세요")
		return false;
	}
	if(isNaN(f.tel1.value)){
		alert("전화번호를 올바르게 입력하세요")
		return false;
	}
	if(isNaN(f.tel2.value)){
		alert("전화번호를 올바르게 입력하세요")
		return false;
	}
	if(isNaN(f.tel3.value)){
		alert("전화번호를 올바르게 입력하세요")
		return false;
	}
	return true;
}
function openCheck(){
    window.name = "parentForm";
    window.open('checkId.mo',"chkForm","width=500, height=300, resizable = no, scrollbars = no");    
}
function inputCheck(){
    document.f.idcheckcheck.value ="idUncheck";
}
</script>
<div align="center">
<form name="f" action="insertCinemaP.mo" method="post" onsubmit="return checkValue()">
<h2>영화관 등록</h2>
<div class="container">
<table class="table">
	<tr class="active" align="center">
		<th>지점명</th><td><input type="text" name="cinema_addr" /></td>
	</tr>
	<tr class="active" align="center">
		<th>상영관 수</th><td><input type="text" name="cinema_size" /></td>
	</tr>
	<tr class="active" align="center">
		<th>담당자</th><td><input type="text" name="cinema_admin" /></td>
	</tr>
	<tr class="active" align="center">
		<th>관리자ID</th><td><input type="text" name="admin_id" onkeydown="javascript:inputCheck()" onclick="openCheck()"/><input type="button" value="중복확인" onclick="openCheck()"/></td>
		<input type="hidden" name="idcheckcheck" value="idUncheck"/>
	</tr>
	<tr class="active" align="center">
		<th>비밀번호</th><td><input type="password" name="admin_passwd"/></td>
	</tr>
	<tr class="active" align="center">
		<th>비밀번호확인</th><td><input type="password" name="admin_passwdcheck"/></td>
	</tr>
	<tr class="active" align="center">
		<th>전화번호</th><td><input type="text" size="3" name="tel1"/>-<input type="text" size="4" name="tel2"/>-<input type="text" size="4" name="tel3"/></td>
	</tr>
	<tr class="active" align="right">
		<td colspan="2"><input type="submit" value="다음" /></td>
	</tr>
</table>
</div>
</form>
</div><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="../bottom.jsp"%>