<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp"%>
<div align="center">
	<table class="table">
		<tr>
			<th>${cinema_addr}점</th>
		</tr>
	</table>
	<form name="f" action="insertCinemaP.mo" method="post" onsubmit="return checkValue()">
<div class="container">
<table class="table">
	<tr class="active" align="center">
		<th>담당자</th><td><input type="text" name="cinema_admin" value="${getAdmin.admin_name}"/></td>
	</tr>
	<tr class="active" align="center">
		<th>관리자ID</th><td><input type="text" name="admin_id" value="${getAdmin.admin_id}" onkeydown="javascript:inputCheck()" onclick="openCheck()"/><input type="button" value="중복확인" onclick="openCheck()"/></td>
		<input type="hidden" name="idcheckcheck" value="idUncheck"/>
	</tr>
	<tr class="active" align="center">
		<th>비밀번호</th><td><input type="password" name="admin_passwd"/></td>
	</tr>
	<tr class="active" align="center">
		<th>비밀번호확인</th><td><input type="password" name="admin_passwdcheck"/></td>
	</tr>
	<tr class="active" align="center">
		<th>전화번호</th><td><input type="text" size="3" name="tel1" value="${tel1}"/>-<input type="text" size="4" name="tel2" value="${tel2}"/>-<input type="text" size="4" name="tel3" value="${tel3}"/></td>
	</tr>
	<tr class="active" align="right">
		<td colspan="2"><input type="submit" value="다음" /></td>
	</tr>
</table>
</div>
</form>
</div>
<%@ include file="../bottom.jsp"%>

