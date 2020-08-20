<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../top.jsp"%>

<div class="container">
		<div align="center">
			<table class="table" width="99%">
					<tr align="center">
						<div style="position: absolute;">
							<div style="position: relative; top: 116px; left: 236px;">
								<a href="store_list.mo?ccode=combo"><img src="images/comboset.PNG" width="150" height="100"></a>
							</div>
							<div style="position: relative; top: 18px; left: 408px;">
								<a href="store_list.mo?ccode=popcorn"><img src="images/popcorn.PNG" width="150" height="100"></a>
							</div>
							<div style="position: relative; top: -84px; left:580px;">
								<a href="store_list.mo?ccode=beverage"><img src="images/beverage.PNG" width="150" height="100"></a>
							</div>
							<div style="position: relative; top: -183px; left: 752px;">
								<a href="store_list.mo?ccode=snack"><img src="images/snack.PNG" width="150" height="100"></a>
							</div>
						</div>
						<td align="center">
							<img src="images/storemain.PNG" width="950" height="500">
						</td>
					</tr>
			</table>
		</div>
	</div>
<div class="container">
	<div align="center">
		<form name="f" action="movie_list.mo" method="get">
			<table class="class" width="99%">
				<h2 align="left">상품상세정보</h2>
				<hr color="black" width="100%">
					<tr>
						<td align="center" rowspan="3" width="5%">
						<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${getProd.pimage}" border="0" width="480" height="480">
						</td>
						<td width="35%" align="left"><h3>${getProd.pname}</h3><br>
						<hr color="black" width="600">
						판매가 <h4><fmt:formatNumber value="${getProd.pprice}" pattern="#,###"/>원</h4><br>
						상세설명&nbsp;&nbsp;&nbsp; <h4>${getProd.pcontent}</h4>
						</td>
					</tr>
					
					
			</table>
		
		</form>
	</div>
</div>
<%@ include file="../bottom.jsp"%>

