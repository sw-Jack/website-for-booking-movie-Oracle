<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

	function checkValue(){
		
		var f = document.f;
		
		if (f.id.value==""){
			alert("아이디를 입력해 주세요")
			f.id.focus()
			return false;
		}	
		if (!f.emaili.value){
			alert("이메일을 입력해 주세요")
			f.email.focus()
			return false;
		}
		if (!f.hp1.value){
			alert("전화번호를 입력해 주세요")
			f.hp1.focus()
			return false;
		}
		if (!f.hp2.value){
			alert("전화번호를 끝까지 입력해 주세요")
			f.hp2.focus()
			return false;
		}
		if(!f.hp3.value){
			alert("전화번호를 끝까지 입력해 주세요")
			f.hp3.focus()
			return false;	
		}
		
	}
</script>
<div class="container">
	<div align="center">
		<form name="f" method="POST" action="find_passwdP.mo" onsubmit="return checkValue()">
		<input type="hidden" name="user_num" value="${user_num}">
			<table class="table">
  				  <tr>
					<td colspan="2" align=center><h2>< 비밀번호 찾기 ></h2><p> </p></td>
 				</tr>
				
				<tr>
					<td width="110" class="m3">아이디</td>
					<td>
						<input type="text" name="id" size="18">
					</td>
  				</tr>
  				<tr>
					<td width="110" class="m3">이메일</td>
					<td class="m3">
						<input type="text" name="email" size="18">
					</td>
  				</tr> <br>
  				<tr>
					<td width="110" class="m3">전화번호</td>
					<td>
						<input type="text" name="hp1" size="3">-
						<input type="text" name="hp2" size="4">-
						<input type="text" name="hp3" size="4">
					</td>
  				</tr>
  				<tr>
					<td colspan="2" align="center">
					<br>
						<input type="submit" value="비밀번호 찾기">
					</td>
  				</tr>
  			</table>
		</form>
	</div>
</div>