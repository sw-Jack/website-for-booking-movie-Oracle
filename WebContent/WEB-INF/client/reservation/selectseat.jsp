<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,movie.admin.theater.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../top.jsp" %>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<%	
	int play_num = Integer.parseInt(request.getParameter("play_num"));
	String name = (String)session.getAttribute("name");
	if(name == null){
		if(id==null){
			response.sendRedirect("nomemberlogin.mo?play_num="+play_num);
		}
	}
%>
<script type="text/javascript">
function selectsseat(){
	location.href='selectseat.mo?play_num='+${playdto.play_num}+'&selectseat='+selectseat.value;
}
function stick(){
	document.getElementById('f').submit();
}
function onemouseover(row,num){
	document.getElementById('seat'+row+'_'+num).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/click"+num+".png"
}
function onemousebye(row,num){
	document.getElementById('seat'+row+'_'+num).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/"+num+".png"
}
function oneclick(row,num,play_num,stickseat,selectseat){
	location.href='choice.mo?stickseat='+stickseat+'&selectseat='+selectseat+'&play_num='+play_num+'&seat_row='+row+'&seat_num='+num;
}

//한개짜리들

function twomouseover(row,num,end){
	if(num==end){
		document.getElementById('seat'+row+'_'+num).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/click"+num+".png";
		document.getElementById('seat'+row+'_'+(num-1)).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/click"+(num-1)+".png"
	}else if(document.getElementById(row+'_'+(num+1)).value=="click"){
		document.getElementById('seat'+row+'_'+num).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/click"+num+".png";
		document.getElementById('seat'+row+'_'+(num-1)).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/click"+(num-1)+".png"
	}else{
		document.getElementById('seat'+row+'_'+num).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/click"+num+".png";
		document.getElementById('seat'+row+'_'+(num+1)).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/click"+(num+1)+".png"
	}
}

function twomousebye(row,num,end){
	if(num==end){
		document.getElementById('seat'+row+'_'+num).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/"+num+".png";
		document.getElementById('seat'+row+'_'+(num-1)).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/select"+(num-1)+".png"
	}else if(document.getElementById(row+'_'+(num+1)).value=="click"){
		document.getElementById('seat'+row+'_'+num).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/"+num+".png";
		document.getElementById('seat'+row+'_'+(num-1)).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/select"+(num-1)+".png"
	}else{
		document.getElementById('seat'+row+'_'+num).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/"+num+".png";
		document.getElementById('seat'+row+'_'+(num+1)).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/select"+(num+1)+".png"
	}
}

function reset(){
	location.href='selectseat.mo?play_num='+${playdto.play_num}+'&selectseat='+selectseat.value;
}
</script>
<div align="center">
<div class="container">
<h1>인원/좌석선택</h1>
	<table class="table">
		<tr>
			<td>일반&nbsp;&nbsp;&nbsp;
			<select id="selectseat" onchange="selectsseat()">
				<c:forEach var="i" begin="0" end="4" step="1">
					<option value="${i}" <c:if test="${i eq selectseat}">selected</c:if>>
						${i}명
					</option>
				</c:forEach>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="좌석선택초기화" onclick="reset()">
			</td>
			<td rowspan="3" bgcolor="gray">
				<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/${moviedto.image}" 
				border="0" width="180" height="280"><br>
				<font color="white">
				<div align="center">
				${moviedto.title}<br>${moviedto.state}
				</div>
				<ul>
					<li>${playdto.cinema_addr}점 &nbsp;${playdto.theater_stage}관</li>
					<li>${playdto.play_year}.${playdto.play_month}.${playdto.play_day}&nbsp;&nbsp;${playdto.start}
				</ul>
				<br>
				금액 : <fmt:formatNumber value="${selectseat*9000}" pattern="#,###"/>원</font>
				<br>
				<a href="credit.mo?play_num=${playdto.play_num}&selectseat=${selectseat}">
					<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/next1.png" 
						border="0" width="180" height="50">
				</a>
			</td>
		</tr>
		<!-- 인원선택 + 오른쪽 영화정보 -->
		<tr>
		<td>
			<c:if test="${selectseat eq null or selectseat eq 0}">
				<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/checkcount.png">
			</c:if>
			<!-- 인원선택을 하지않았거나 인원이 0일때 사진표시 -->
			<c:if test="${selectseat ne null and selectseat ne 0}">
				<table class="table">
					<tr>
						<td align="center">
							<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/SCREEN.png" width="300" height="28">
						</td>
					</tr>
				<!-- 예매창 스크린부분 -->
					<tr><td><br></td></tr>
					<tr>
						<td>
							<table class="table">
								<c:forEach var="row" begin="1" end="${theaterdto.seat_row}" step="1">
								<tr>
									<td>
									${row}열
									</td>
								<c:forEach var="num" begin="1" end="${theaterdto.seat_num}" step="1">
								<input type="hidden" id="${row}_${num}" value="non">
								<td>
									<c:if test="${stickseat eq 1}">
									<!-- 좌석붙임이 1일때 -->
										<c:if test="${theaterdto.seat_num%2 eq 1}">
										<!-- 줄이 홀수일때 -->
											<c:if test="${num%2 eq 1}">
											<!-- 자리가 홀수일때 -->
												<img id="seat${row}_${num}" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/${num}.png"
												onmouseover='onemouseover(${row},${num})' onmouseleave='onemousebye(${row},${num})' onclick="oneclick(${row},${num},${playdto.play_num},${stickseat},${selectseat})">
											</c:if>
											<c:if test="${num%2 eq 0}">
											<!-- 자리가 짝수일때 -->
												<img id="seat${row}_${num}" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/select${num}.png"
												onmouseover='xx' onmouseleave='xx'>
											</c:if>
										</c:if>
										
										<c:if test="${theaterdto.seat_num%2 eq 0}">
										<!-- 줄이 짝수일때 -->
											<c:if test="${num%2 eq 1}">
											<!-- 자리가 홀수일때 -->
											<c:if test="${num eq theaterdto.seat_num-1}">
											<!-- 마지막전줄 비활성화 -->
												<img id="seat${row}_${num}" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/select${num}.png"
												onmouseover='nono' onmouseleave='nono'>
											</c:if>
											<c:if test="${num ne theaterdto.seat_num-1}">
												<img id="seat${row}_${num}" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/${num}.png"
												onmouseover='onemouseover(${row},${num})' onmouseleave='onemousebye(${row},${num})' onclick='oneclick(${row},${num},${playdto.play_num},${stickseat},${selectseat})'>
											</c:if>
											</c:if>
											<c:if test="${num%2 eq 0}">
											<!-- 자리가 짝수일때 -->
												<c:if test="${num eq theaterdto.seat_num}">
											<!-- 마지막줄 활성화 -->
												<img id="seat${row}_${num}" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/${num}.png"
												onmouseover='onemouseover(${row},${num})' onmouseleave='onemousebye(${row},${num})' onclick='oneclick(${row},${num},${playdto.play_num},${stickseat},${selectseat})'>
											</c:if>
											<c:if test="${num ne theaterdto.seat_num}">
												<img id="seat${row}_${num}" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/select${num}.png"
												onmouseover='nono' onmouseleave='nono'>
											</c:if>
											</c:if>
										</c:if>
									</c:if>
									
									
									<c:if test="${stickseat eq 2}">
									<!-- 좌석붙임이 2일때 -->
										<c:if test="${theaterdto.seat_num%2 eq 1}">
										<!-- 줄이 홀수일때 -->
											<c:if test="${num%2 eq 1}">
											<!-- 자리가 홀수일때 -->
												<img id="seat${row}_${num}" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/${num}.png"
												onmouseover='twomouseover(${row},${num},${theaterdto.seat_num})' onmouseleave='twomousebye(${row},${num},${theaterdto.seat_num})' onclick="oneclick(${row},${num},${playdto.play_num},${stickseat},${selectseat})">
											</c:if>
											<c:if test="${num%2 eq 0}">
											<!-- 자리가 짝수일때 -->
												<img id="seat${row}_${num}" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/select${num}.png"
												onmouseover='xx' onmouseleave='xx'>
											</c:if>
										</c:if>
										
										<c:if test="${theaterdto.seat_num%2 eq 0}">
										<!-- 줄이 짝수일때 -->
											<c:if test="${num%2 eq 1}">
											<!-- 자리가 홀수일때 -->
											<c:if test="${num eq theaterdto.seat_num-1}">
											<!-- 마지막전줄 비활성화 -->
												<img id="seat${row}_${num}" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/select${num}.png"
												onmouseover='nono' onmouseleave='nono'>
											</c:if>
											<c:if test="${num ne theaterdto.seat_num-1}">
												<img id="seat${row}_${num}" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/${num}.png"
												onmouseover='twomouseover(${row},${num},${theaterdto.seat_num})' onmouseleave='twomousebye(${row},${num},${theaterdto.seat_num})' onclick="oneclick(${row},${num},${playdto.play_num},${stickseat},${selectseat})">
											</c:if>
											</c:if>
											
											<c:if test="${num%2 eq 0}">
											<!-- 자리가 짝수일때 -->
												<c:if test="${num eq theaterdto.seat_num}">
											<!-- 마지막줄 활성화 -->
												<img id="seat${row}_${num}" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/${num}.png"
												onmouseover='twomouseover(${row},${num},${theaterdto.seat_num})' onmouseleave='twomousebye(${row},${num},${theaterdto.seat_num})' onclick="oneclick(${row},${num},${playdto.play_num},${stickseat},${selectseat})">
											</c:if>
											<c:if test="${num ne theaterdto.seat_num}">
												<img id="seat${row}_${num}" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/select${num}.png"
												onmouseover='nono' onmouseleave='nono'>
											</c:if>
											</c:if>
										</c:if>
									</c:if>
									<!-- 클릭한부분 표시하는곳 -->
									<c:if test="${not empty seatlist }">
									<%
										List<Theater_seatDTO> seatlist = (List<Theater_seatDTO>)request.getAttribute("seatlist");
										TheaterDTO dto = (TheaterDTO)request.getAttribute("theaterdto");
										for(Theater_seatDTO click : seatlist){
									%>
										<script type="text/javascript">
										document.getElementById('seat'+<%=click.getSeat_row()%>
										+'_'+<%=click.getSeat_num()%>).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/click<%=click.getSeat_num()%>.png";
										
										document.getElementById('seat'+<%=click.getSeat_row()%>
										+'_'+<%=click.getSeat_num()%>).onmouseover='nono'
										
										document.getElementById('seat'+<%=click.getSeat_row()%>
										+'_'+<%=click.getSeat_num()%>).onmouseleave='nono'
										
										document.getElementById('seat'+<%=click.getSeat_row()%>
										+'_'+<%=click.getSeat_num()%>).onclick='nono'
										
										<%-- document.getElementById('seat'+<%=click.getSeat_row()%>
										+'_'+<%=click.getSeat_num()%>).id='xx' --%>
										
										document.getElementById(<%=click.getSeat_row()%>
										+'_'+<%=click.getSeat_num()%>).value="click";
										</script>
										
									<% 	int stickseat = (int)request.getAttribute("stickseat");
									if(stickseat!=1){
									if(click.getSeat_num()==dto.getSeat_num()-1){%>
										
										<script type="text/javascript">
										document.getElementById('seat'+<%=click.getSeat_row()%>
										+'_'+<%=dto.getSeat_num()%>).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/select<%=dto.getSeat_num()%>.png";
										
										document.getElementById('seat'+<%=click.getSeat_row()%>
										+'_'+<%=dto.getSeat_num()%>).onmouseover='nono';
										
										document.getElementById('seat'+<%=click.getSeat_row()%>
										+'_'+<%=dto.getSeat_num()%>).onmouseleave='nono';
										</script>
								<%						
													
												
											}
										}
										}
									%>
									</c:if>
									
									<!-- 팔린부분 표시하는곳 -->
									<c:if test="${not empty soldlist}">
										<%List<Theater_seatDTO> soldlist = (List<Theater_seatDTO>)request.getAttribute("soldlist");
										TheaterDTO dto = (TheaterDTO)request.getAttribute("theaterdto");
											for(Theater_seatDTO solddto : soldlist){%>
												<script type="text/javascript">
												document.getElementById('seat'+<%=solddto.getSeat_row()%>
												+'_'+<%=solddto.getSeat_num()%>).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/x.png";
												
												document.getElementById('seat'+<%=solddto.getSeat_row()%>
												+'_'+<%=solddto.getSeat_num()%>).onmouseover='nono';
												
												document.getElementById('seat'+<%=solddto.getSeat_row()%>
												+'_'+<%=solddto.getSeat_num()%>).onmouseleave='nono';
												
												document.getElementById('seat'+<%=solddto.getSeat_row()%>
												+'_'+<%=solddto.getSeat_num()%>).onclick='nono';
												
												document.getElementById(<%=solddto.getSeat_row()%>
												+'_'+<%=solddto.getSeat_num()%>).value="click";
												</script>
										<%	int stickseat = (int)request.getAttribute("stickseat");
											if(stickseat != 1){
											if(solddto.getSeat_num()==dto.getSeat_num()-1){%>
												<script type="text/javascript">
													document.getElementById('seat'+<%=solddto.getSeat_row()%>
													+'_'+<%=dto.getSeat_num()%>).src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/select<%=dto.getSeat_num()%>.png";
													
													document.getElementById('seat'+<%=solddto.getSeat_row()%>
													+'_'+<%=dto.getSeat_num()%>).onmouseover='nono';
													
													document.getElementById('seat'+<%=solddto.getSeat_row()%>
													+'_'+<%=dto.getSeat_num()%>).onmouseleave='nono';
												</script>
										<%			}
												}
											}
										%>
									</c:if>
									</td>
								</c:forEach>
								</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<!-- 좌석선택부분 -->
					<tr>
						<td>
						<b>좌석붙임설정</b>&nbsp;&nbsp;
						<form id="f" action="selectseat.mo" method="post">
						<input type="hidden" name="selectseat" value="${selectseat}">
						<input type="hidden" name="play_num" value="${playdto.play_num}">
							<c:forEach var="i" begin="1" end="2" step="1">
								<input type="radio" name="stickseat" id="stickseat" value="${i}" onclick="stick()"
								<c:if test="${i eq stickseat}">checked</c:if>
								<c:if test="${i > leftseat}">disabled</c:if> 
								>
									<c:forEach var="j" begin="1" end="${i}" step="1">
										<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/images/seat/tle.png">
									</c:forEach>
							</c:forEach>
						</form>
						</td>
					</tr>
					<!-- 좌석붙임설정부분 -->
				</table>
			</c:if>
		</td>
		</tr>
	</table>
</div>
</div>
<%@ include file="../bottom.jsp"%>
