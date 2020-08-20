<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp" %>
<script type="text/javascript">
	function gologin(){
		location.href="loginMemberP.mo";
	}
</script>
<div class="container">
<div align="center">
<h4><비회원 개인정보 입력></h4><br>
<form name="f" action="viewreser.mo" method="post">
	<table class="class">
		<tr>
			<th>임시아이디</th><td><input type="text" name="name"></td>
		</tr>
		<tr>
			<th>전화번호</th><td><input type="text" name="tel1" size="4" maxlength="3">-<input type="text" name="tel2" size="5" maxlength="4">-<input type="text" name="tel3" size="5" maxlength="4"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="로그인">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="gologin()" value="회원로그인"></td>
		</tr>
	</table>
</form>
</div>
</div>
<%@ include file="../bottom.jsp"%>