<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../top.jsp" %>
	<script type="text/javascript">
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
	</script>
	<div class="container">
		<div align="center">
			<h2 align="left">회원정보수정</h2>
			<hr color="black" width="100%">
			<form name="f" action="update_profileP.mo" method="post">
			<input type="hidden" name="user_num" value="${medto.user_num}">
				<table class="table" width="99%">
					<tr>
						<td rowspan="10" align="center">
						
						<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${medto.member_image}" width="70" height="70">
						<br>
						</td>
						
					</tr>
					<tr>
						<td><b>아이디</b></td>
						<td><input type="text" name="id" class="box" value="${medto.id}" disabled></td>
					</tr>
					<tr>
						<td><b>이름</b></td>
						<td><input type="text" name="name" class="box" value="${medto.name}" disabled></td>
					</tr>
					<tr>
						<td><b>이메일</b></td>
						<td><input type="text" name="email" class="box" value="${medto.email}"></td>
					</tr>
					<tr>
						<td><b>생년월일</b></td>
						<td><input type="text" name="birth_year" size="4" maxlength="4" value="${medto.birth_year}" disabled>년
						<input type="text" name="birth_month" size="2" maxlength="2" value="${medto.birth_month}" disabled>월
						<input type="text" name="birth_day" size="2" maxlength="2" value="${medto.birth_day}" disabled>일</td>
						</tr>
					<tr>
						<td><b>전화번호</b></td>
						<td><input type="text" name="hp1" size="3" maxlength="3" value="${medto.hp1}"> -
						<input type="text" name="hp2" size="4" maxlength="4" value="${medto.hp2}"> -
						<input type="text" name="hp3" size="4" maxlength="4" value="${medto.hp3}">
						</td>
					</tr>
					<tr>
	  					<td width="150">우편번호</td><td><input type="text" name="adrr_code" id="adrr_code" value="${medto.adrr_code}" readOnly><input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"></td>
	  				</tr>
	  				<tr>
	  					<td width="150">주소</td><td><input type="text" name="adrr" id="adrr" size="50" value="${medto.adrr}" readOnly/></td>
	  				</tr>
	  				<tr>
	  					<td width="150">상세주소</td><td><input type="text" name="addr_detail" id="addr_detail" value="${medto.addr_detail}" size="50"/></td>
	  				</tr>
	  				<tr>
		  				<td align="center">
		  					<input type="submit" value="정보 수정" width="70">
		  				</td>
	  				</tr>
				</table>
				
			</form>
		</div>
	</div>
<%@ include file="../bottom.jsp" %>