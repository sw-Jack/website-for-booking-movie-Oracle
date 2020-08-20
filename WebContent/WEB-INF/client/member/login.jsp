<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../top.jsp" %>
<%
	if(id!=null||a_id!=null){
		response.sendRedirect("kgvindex.mo");
	}
%>
      <style type="text/css">
      body { 
        background: url('${pageContext.request.contextPath }/resources/images/4.jpg') no-repeat center center fixed; 
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
      }
      
      .panel-default {
      opacity: 0.9;
      margin-top:30px;
      }
      .form-group.last { margin-bottom:0px; 
      }
      </style>
<script type="text/javascript">

function openId(){
    window.name = "parentForm";
    window.open('find_id.mo',"chkForm","width=500, height=300, resizable = no, scrollbars = no");    
}
function openPasswd(){
	window.open('find_passwd.mo',"chkForm","width=500, height=300, resizable = no, scrollbars = no");
}
</script>
   <div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-lock"></span> 로그인</div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="memberlogin.mo" method="post">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">
                            아이디</label>
                        <div class="col-sm-9">
                            <input name="id" type="text" class="form-control" id="inputEmail3" placeholder="id" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-3 control-label">
                            비밀번호</label>
                        <div class="col-sm-9">
                            <input name="passwd" type="password" class="form-control" id="inputPassword3" placeholder="Password" required>
                        </div>
                    </div>
                    <div class="form-group last">
                        <div class="col-sm-offset-3 col-sm-9">
                            <button type="submit" class="btn btn-success btn-sm">로그인</button>
                        </div>
                    </div>
                    </form>
                </div>
                <div class="panel-footer">
                    <a href="register.mo">회원가입 | </a><a href="#" onclick="openId()">아이디찾기 | </a><a href="#" onclick="openPasswd()">비밀번호 찾기</a>
                    </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../bottom.jsp" %>