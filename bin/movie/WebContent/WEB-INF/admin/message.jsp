<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
		String msg = (String)request.getAttribute("msg");

		String url = (String)request.getAttribute("url");
		String state = (String)request.getAttribute("state");
 		if (state != null && state.equals("checkMember")){
		request.setCharacterEncoding("EUC-KR");
		String name = request.getParameter("name");
		String ssn1 = request.getParameter("ssn1");
		String ssn2 = request.getParameter("ssn2");%>
		<form name="f" action="register_member.bo" method="post">
				<input type="hidden" name="name" value="<%=name%>"/>
				<input type="hidden" name="ssn1" value="<%=ssn1%>" />
				<input type="hidden" name="ssn2" value="<%=ssn2%>" />
		</form>	
		<script type="text/javascript">
			alert("<%=msg%>")
			document.f.submit()
		</script>
<%	}else {%>
		<script type="text/javascript">
			alert("<%=msg%>")
			location.href="<%=url%>"
		</script>
<%	} %>		 









