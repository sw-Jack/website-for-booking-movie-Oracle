<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp" %>
<script type="text/javascript">

	function checkValue(){
		
		var f = document.f;
		
		if (!f.passwd.value){
			alert("현재비밀번호를 입력해 주세요")
			f.passwd.focus()
			return false;
		}
		if (!f.newpasswd.value){
			alert("새비밀번호를 입력해 주세요")
			f.newpasswd.focus()
			return false;
		}
		if (!f.check_passwd.value){
			alert("새비밀번호를 확인해 주세요")
			f.check_passwd.focus()
			return false;
		}
		if (f.newpasswd.value != f.check_passwd.value){
			alert("입력하신 비밀 번호가 다릅니다")
			f.check_passwd.focus()
			return false;
		}
	}
</script>
<div class="container">
<form name="f" method="POST" action="member_passwdP.mo" onsubmit="return checkValue()">
<input type="hidden" name="id" value="${id}">
	<table class="table">
		<tr>
			<td colspan="2" align=center>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<비밀번호 변경></td>
		</tr>
 		<tr>
			<td width="150" class="m3"><br>현재 비밀번호</td>
			<td class="m3">
				<br><input type="password" name="passwd">
			</td>
  		</tr>
 		
 		<tr>
			<td width="150" class="m3">새 비밀번호</td>
			<td class="m3">
				<input type="password" name="newpasswd">
			</td>
  		</tr>
  		
  		<tr>
  			<td width="150" class="m3">새 비밀번호 확인</td>
  			<td class="m3">
  				<input type="password" name="check_passwd">
   		</tr>
   		
   		<tr>
			<td colspan="2" align="center">
				<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<input type="submit" value="확인">
			</td>
  		</tr>
	</table>
	</form>
</div>
 <%@ include file="../bottom.jsp"%>