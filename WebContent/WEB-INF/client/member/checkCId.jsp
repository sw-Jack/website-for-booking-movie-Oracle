<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<script type="text/javascript">
	function checkId(){
		var id = document.getElementById("f").id.value
		if(!id){
			alert("아이디를 입력하세요");
			return false;
		}else if((id < "0" || id > "9") && (id < "A" || id > "Z") && (id < "a" || id > "z")){ 
			alert("한글및 특수문자는 입력할수 없습니다");
			return false;
		}else{
			document.getElementById("f").submit();
		}
	}
	function sendCheck(){
		if(document.getElementById("f").checkcheck.value=="check"){
			opener.document.f.idcheckcheck.value ="idCheck";
			opener.document.f.id.value = document.getElementById("id").value;
	        
	        if (opener != null) {
	            opener.chkForm = null;
	            self.close();
	        }
		}else{
			alert("중복확인버튼을 누르세요.")
		}
	}
	function resetcheck(){
		document.getElementById("f").checkcheck.value ="nocheck";
	}
	</script>
	<title>중복체크</title>
</head>
<body>
	<div align="center">
	<br>
	<h4>아이디 중복체크</h4>
	<hr>
	<br>
		<form id="f" action="checkCIdP.mo" method="post">
			아이디<input type="text" name="id" id="id" value="${param.id}" onkeydown="resetcheck()"/> <input type="button" value="중복체크" onclick="checkId()"/>
			<input type="hidden" name="checkcheck" value="${param.check}"/>
		</form>
		<br><br><br><br><br>
		<input type="button" value="확인" onclick="sendCheck()"/>&nbsp;&nbsp;<input type="button" value="취소" onclick="window.close()"/>
	</div>
</body>
</html>