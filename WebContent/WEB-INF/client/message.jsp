<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
		String msg = (String)request.getAttribute("msg");
		String url = (String)request.getAttribute("url");
%>
<form name="f" action="<%=url%>" method="post">
	<input type="hidden" name="id" value="${id}"/>
	<input type="hidden" name="check" value="${check}"/>
	<input type="hidden" name="play_num" value="${play_num}"/>
	<input type="hidden" name="stickseat" value="${stickseat}"/>
	<input type="hidden" name="selectseat" value="${selectseat}"/>
	<input type="hidden" name="name" value="${name}"/>
	<input type="hidden" name="hp1" value="${hp1}"/>
	<input type="hidden" name="hp2" value="${hp2}"/>
	<input type="hidden" name="hp3" value="${hp3}"/>
	<input type="hidden" name="authNum" value="${authNum}"/>
	<input type="hidden" name="email" value="${email}"/>
</form>
<%if(msg != null){ %>
<script type="text/javascript">
	alert("<%=msg%>");
	document.f.submit();
</script>
<%}else{ %>
<script type="text/javascript">
	document.f.submit();
</script>
<%} %>










