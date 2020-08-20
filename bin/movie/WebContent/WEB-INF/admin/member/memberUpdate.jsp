<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/top.jsp" %>
<html>
	<head>
	<title>Movie Member</title>
	<script type="text/javascript">
		/* function check(){
			
			document.f.submit()
		} */
		function checkUpdate(){
			if (f.hp1.value==""){
				alert("전화번호를 모두 입력하세요.")
				f.hp1.focus()
				return false;
			}
			if (f.hp2.value==""){
				alert("전화번호를 모두 입력하세요.")
				f.hp2.focus()
				return false;
			}
			if (f.hp3.value==""){
				alert("전화번호를 모두 입력하세요.")
				f.hp3.focus()
				return false;
			}
			if (f.point.value==""){
				alert("포인트를 입력하세요.")
				f.point.focus()
				return false;
			}
			return true;
		}
	</script>
	</head>
	<body>
		<form name="f" method="POST" action="memberUpdate.mo" onsubmit="return checkUpdate()">
			<input type="hidden" name="user_num" value="${getMember.user_num}">
			<table width="600" align="center" class="outline">
  				<tr>
					<td colspan="2" align=center class="m2">회원수정</td>
 				</tr>
				
				<tr>
					<td width="150" class="m3">이름</td>
					<td class="m3">
						<input type="text" name="name" class="box" 
						value="${getMember.name}" readOnly>
					</td>
				</tr>
				
				<tr>
					<td width="150" class="m3">아이디</td>
					<td class="m3">
						<input type="text" name="id" class="box"
						value="${getMember.id}" readOnly>
					</td>
  				</tr>
  				
  				<tr>
					<td width="150" class="m3">이메일</td>
					<td class="m3">
						<input type="text" name="email" class="box"
						value="${getMember.email}" readOnly>
					</td>
  				</tr>
  				
  				<tr>
					<td width="150" class="m3">전화번호</td>
					<td class="m3">
						<input type="text" name="hp1" class="box" 
						value="${getMember.hp1}" size="3" maxlength="3"> -
						<input type="text" name="hp2" class="box" 
						value="${getMember.hp2}" size="4" maxlength="4"> -
						<input type="text" name="hp3" class="box" 
						value="${getMember.hp3}" size="4" maxlength="4">
					</td>
  				</tr>
  				
  				<tr>
					<td width="150" class="m3">우편번호</td>
					<td class="m3">
						<input type="text" name="adrr_code" class="box" 
						value="${getMember.adrr_code}" size="10" maxlength="10" readOnly> 
					</td>
  				</tr>
  				
  				<tr>
					<td width="150" class="m3">주소</td>
					<td class="m3">
						<input type="text" name="adrr" class="box" 
						value="${getMember.adrr}" size="100" maxlength="100" readOnly> 
					</td>
  				</tr>
  				
  				<tr>
					<td width="150" class="m3">포인트</td>
					<td class="m3">
						<input type="text" name="point" class="box" 
						value="${getMember.point}" size="100" maxlength="100"> 
					</td>
  				</tr>
  				
  				<tr>
					<td colspan="2" align="center"><!-- 
						<a href="javascript:check()">[수정]</a> -->
						<input type="submit" value="수정"/>
						<input type="reset" value="재입력"/>
					</td>
  				</tr>
  			</table>
		</form>
	</body>
</html>
<%@ include file="/bottom.jsp" %>