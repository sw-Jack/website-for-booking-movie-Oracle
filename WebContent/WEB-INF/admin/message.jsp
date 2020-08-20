<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
		String msg = (String)request.getAttribute("msg");
		String url = (String)request.getAttribute("url");
%>
<form name="f" action="<%=url%>" method="post">
	<input type="hidden" name="admin_id" value="${admin_id}"/>
	<input type="hidden" name="check" value="${check}"/>
</form>
<script type="text/javascript">
	alert("<%=msg%>");
	document.f.submit();
</script>










