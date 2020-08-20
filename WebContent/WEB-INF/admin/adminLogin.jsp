<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 로그인</title>
<script type="text/javascript">
	function callnum(){
		alert("총 관리자에게 문의하세요");
	}
	function checkValue(){
		if(!f.admin_id.value){
			alert("아이디를 입력하세요");
			f.admin_id.focus();
			return false;
		}
		if(!f.admin_passwd.value){
			alert("비밀번호를 입력하세요");
			f.admin_passwd.focus();
			return false;
		}
	}
</script>
</head>
<body>
<div align="center">
<form name="f" action="adminLogin.mo" method="post" onsubmit="return checkValue()">
<table border="1">
<thead>
	<h4>관리자 로그인</h4>
</thead>
<tbody>
	<tr>
		<th>아이디</th><td><input type="text" name="admin_id"/></td>
	</tr>
	<tr>
		<th>비밀번호</th><td><input type="password" name="admin_passwd"/></td>
	</tr>
	<tr>
		<th colspan="2" align="center"><input type="submit" value="로그인"/></th>
	</tr>
	<tr>
		<th colspan="2" align="center"><input type="button" value="ID/PW찾기" onclick="callnum()"/></th>
	</tr>
	<!-- 임시용 삭제예정 -->
	<tr>
		<td><a href="adminIndex.mo">관리자 인덱스이동</a>
	</tr>
	<!-- 여기까지 -->
</tbody>
</table>
</form>
</div>
</body>
</html>