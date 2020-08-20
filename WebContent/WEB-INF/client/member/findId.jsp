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
	}
</script>
<div class="container">
	<div align="center">
		<form name="f" method="POST" action="find_idP.mo" onsubmit="return checkValue()">
			<table class="table">
  				  <tr>
					<td colspan="2" align=center><h2>< 아이디 찾기 ></h2><p> </p></td>
 				</tr>
 				<br>
				<tr>
					<td width="90" class="m3">이름</td>
					<td>
						<input type="text" name="name">
					</td>
  				</tr>
  				<tr>
					<td width="90" class="m3">이메일</td>
					<td class="m3">
						<input type="text" name="email"> 
					</td>
  				</tr>
  				<tr>
					<td colspan="2" align="center">
					<br>&nbsp;
						<input type="submit" value="아이디 찾기">
					</td>
  				</tr>
  			</table>
		</form>
	</div>
</div>
