<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp" %>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
function sendmail(){
	location.href='sendmail.mo?email='+document.getElementById('email').value;
}
function idcheck(){
	if(document.f.idnum.value==document.f.authNum.value){
		alert('인증성공!');
		document.f.checkmail.value="check";
	}else{
		alert('인증번호가 일치하지 않습니다.');
		docunemt.f.idnum.focus();
	}
}
	function checkValue(){
		regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		  
		var f = document.f;
		if(!regEmail.test(f.email.value)) {
		    alert('이메일 주소가 올바르지 않습니다.');
		    return;
		}
		if(f.checkmail.value!='check'){
			alert("이메일 인증을 완료해주세요.");
			return false;
		}
		if (f.id.value==""){
			alert("아이디를 입력해 주세요")
			f.id.focus()
			return false;
		}	
		if (!f.name.value){
			alert("이름을 입력해 주세요")
			f.name.focus()
			return false;
		}
		if (!f.passwd.value){
			alert("비밀번호를 입력해 주세요")
			f.passwd.focus()
			return false;
		}
		if (f.passwd.value != f.check_passwd.value){
			alert("입력하신 비밀 번호가 다릅니다")
			f.check_passwd.focus()
			return false;
		}
		if (!f.email.value){
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
			alert("전화번호를 입력해 주세요")
			f.hp2.focus()
			return false;
		}
		if (!f.hp3.value){
			alert("전화번호를 입력해 주세요")
			f.hp3.focus()
			return false;
		}
		if (!f.addr.value){
			alert("주소를 입력해 주세요")
			f.addr.focus()
			return false;
		}
		
			
	}
	function openCheck(){
	    window.name = "parentForm";
	    window.open('checkCId.mo',"chkForm","width=500, height=300, resizable = no, scrollbars = no");    
	}
	function inputCheck(){
	    document.f.idcheckcheck.value ="idUncheck";
	}
	
    function sample6_execDaumPostcode(){
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('adrr_code').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('adrr').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('addr_detail').focus();
            }
        }).open();
    }
</script>
<div class="container">
		<form name="f" method="POST" action="insertMember.mo" onsubmit="return checkValue()" enctype="multipart/form-data">
			<table class="table">
  				<tr>
					<td colspan="2" align=center>< 회원가입 ><p> </p></td>
 				</tr>
 				<tr>
					<td width="150" class="m3">이메일<p></p></td>
					<td class="m3">
						<input type="text" name="email" id="email" value="${email}" <c:if test="${not empty authNum}">readOnly</c:if>>
						&nbsp;<input type="button" onclick='sendmail()' value="인증번호전송">
						<input type="hidden" id="checkmail" value="nocheck"/>
						<input type="hidden" id="authNum" value="${authNum}"/>
					</td>
  				</tr>
  				<tr>
  					<td width="150" class="m3">인증번호<p> </p></td>
  					<td class="m3">
  						<input type="text" name="idnum">&nbsp;&nbsp;<input type="button" value="인증" onclick="idcheck()"/>
  					</td>
  				</tr>
  				<c:if test="${not empty authNum}">
				<tr>
					<td width="150">ID</td>
					<td>
						<input type="text" name="id" onkeydown="javascript:inputCheck()" onclick="openCheck()" readOlny/>
						<input type="button" value="중복확인" onclick="openCheck()"/>
						<input type="hidden" name="idcheckcheck" value="idUncheck"/>
					</td>
					
				</tr>
				<tr>
					<td width="150" class="m3">이름</td>
					<td>
						<input type="text" name="name">
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">비밀번호</td>
					<td class="m3">
						<input type="password" name="passwd">
					</td>
  				</tr>
  				<tr>
  					<td width="150" class="m3">비밀번호 확인</td>
  					<td class="m3">
  						<input type="password" name="check_passwd">
   				</tr>
  				
  				<tr>
						<th>생년월일</th>
						<td><select name="birth_year">
								<c:forEach var="i" begin="1900" end="2020" step="1">
									<option>${i}</option>
								</c:forEach>
						</select> 년 <select name="birth_month">
								<c:forEach var="j" begin="1" end="12" step="1">
									<option>${j}</option>
								</c:forEach>
						</select> 월 <select name="birth_day">
								<c:forEach var="k" begin="1" end="31" step="1">
									<option>${k}</option>
								</c:forEach>
						</select> 일</td>
					</tr>
  				
  				<tr>
					<td width="150">연락처</td>
					<td>
						<input type="text" name="hp1" size="3" maxlength="3"> -
						<input type="text" name="hp2" size="4" maxlength="4"> -
						<input type="text" name="hp3" size="4" maxlength="4">
					</td>
  				</tr>
  				<tr>
  					<td width="150">우편번호</td><td><input type="text" name="adrr_code" id="adrr_code" readOnly><input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"></td>
  				</tr>
  				<tr>
  					<td width="150">주소</td><td><input type="text" name="adrr" id="adrr" size="50" readOnly/></td>
  				</tr>
  				<tr>
  					<td width="150">상세주소</td><td><input type="text" name="addr_detail" id="addr_detail" size="50"/></td>
  				</tr>
  				<tr>
  					<td width="150">프로필사진</td><td><input type="file" name="member_image"></td>
  				</tr>
  				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원가입">
					</td>
  				</tr>
  				</c:if>
  			</table>
		</form>
</div>
 <%@ include file="../bottom.jsp"%>