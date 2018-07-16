<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Register</title>
<!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/blog-home.css" rel="stylesheet">

<script type="text/javascript">
	function registe() {
		var name = document.getElementById("username").value;
		var pwd = document.getElementById("password").value;
		var nickName = document.getElementById("nickname").value;
		var age = document.getElementById("age").value;
		var email = document.getElementById("email").value;
		var re = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g;
		
		if(name==="" || pwd==="") {
			alert("账号或者密码不能为空");
		}else if(pwd.length < 6){
			alert("密码必须大于 六位");
		}else if(nickName == ""){
			alert("昵称不能为空");
		}else if(!/^\d*$/.test(age)) {
			alert("年龄必须输入整数");
		}else if(parseInt(age)<0 || parseInt(age)>150) {
			alert("请输入合法年龄（0-150)");
		}else if(!re.test(email)){
			alert("请输入合法邮箱");
		}else {
			document.getElementById("form").submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
	
	<div class="container" style="padding:4%">
		<form action="User_handleRegister.do" method="post" id="form">
		<table class="table text-center" border="1">
			<tr>
				<th colspan="2">用户注册</th>
			</tr>
			<tr>
				<td>账号：</td>
				<td><input class="form-control" type="text" id="username" name="username"/></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input class="form-control" type="password" id="password" name="password"/></td>
			</tr>
			<tr>
				<td>昵称：</td>
				<td><input class="form-control" type="text" id="nickname" name="nickname"/></td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input class="form-control" type="text" id="age" name="age"/></td>
			</tr>
			<tr>
				<td>电子邮件</td>
				<td><input class="form-control" type="text" id="email" name="email"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input class="btn btn-info" type="button" value="注册" onclick="registe()"/></td>
			</tr>
		</table>
		</form>
	</div>
	
	<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
	<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath }/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>