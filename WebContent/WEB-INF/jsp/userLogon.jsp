<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>maiba</title>
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/blog-home.css" rel="stylesheet">

<script type="text/javascript">
	function logon() {
		var name = document.getElementById("username").value;
		var pwd = document.getElementById("password").value;
		
		if(name==="" || pwd==="") {
			alert("账号或者密码不能为空");
		}else if(pwd.length < 6){
			alert("密码必须大于 六位");
		}else {
			document.getElementById("form").submit();
		}
	}
	function zhuce() {
		window.location.href = "UserRegister.jsp";
	}
	
</script>
</head>
<body>
	<%@include file="/WEB-INF/jsp/include/header.jsp" %>
	
	<div class="container" style="padding:10%">
		<form action="User_handleLogon.do" method="post" id="form">
		<table class="table text-center" border="1">
			<tr>
				<td colspan="2" ><h4>用户登陆</h4></td>
			</tr>
			<tr>
				<td width="174px">账号：</td>
				<td><input class="form-control" type="text" id="username" name="userName"/></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input class="form-control" type="password" id="password" name="password"/></td>
			</tr>
			<% 
				String errorPwd = "0";
				if(session.getAttribute("errorPwd")!=null) {
					errorPwd  = (String)session.getAttribute("errorPwd");
					System.out.println(errorPwd);
				}
				if(Integer.parseInt(errorPwd) >= 1) {
					%>
						<tr>
						<td><input class="form-control" type="text" name="imgID" value="" /></td>
						<td align="center">
							<img id="checkcodeimgID" src="<%=request.getContextPath()%>/servImageVal" alt="输入图中数字">
							<a href="javaScript:refreshImage()">看不清</a> <br>
						</td>
						</tr>
					<%
				}
			%>
			<tr>
				<td colspan="2" align="center">
					<input class="btn btn-info" type="button" value="登陆" onclick="logon()"/>
					<input class="btn btn-default" type="button" value="注册" onclick="javascript:window.location.href='User_Register.do'"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<span class="text-center" style="color: red">${failure_message } </span>
				</td>
			</tr>
		</table>
		</form>
	</div>
	
	<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
	
	<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath }/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		 function refreshImage(){
			 document.getElementById('checkcodeimgID').src="<%=request.getContextPath()%>/servImageVal?r="+Math.random();
		 }
	</script>
</body>
</html>