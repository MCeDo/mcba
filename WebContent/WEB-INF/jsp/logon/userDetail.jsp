<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>用户信息</title>
 <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/blog-post.css" rel="stylesheet">
</head>
<body>
	<%@include file="/WEB-INF/jsp/include/header.jsp" %>
	<div class="col-md-8" style="margin: auto">
	<form id="myForm" action="logon/Logon_modify.do" method="post">
	<table class="table" border="1">
		<tr><th colspan="2">用户具体信息</th></tr>
		<tr>
			<td width="120px" style="background-color:#eeeeee;">ID:</td>
			<td><input class="form-control" type="text" id="userId" name="userId" value="${u.id }"/></td>
		</tr>
		<tr>
			<td style="background-color:#eeeeee;">账号：</td>
			<td><input class="form-control" type="text" name="userName" value="${u.userName }"/></td>
		</tr>
		<tr>
			<td style="background-color:#eeeeee;">密码：</td>
			<td><input class="form-control" type="text" name="password" value="${u.password }"/></td>
		</tr>
		<tr>
			<td style="background-color:#eeeeee;">昵称：</td>
			<td><input class="form-control" type="text" name="nickName" value="${u.nickName }" /></td>
		</tr>
		<tr>
			<td style="background-color:#eeeeee;">年龄：</td>
			<td><input class="form-control" type="text" name="age" value="${u.age }" /></td>
		</tr>
		<tr>
			<td style="background-color:#eeeeee;">电子邮箱：</td>
			<td><input class="form-control" type="text" name="email" value="${u.email }" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" class="btn btn-info" value="修改" onclick="handle_modify()"/>
				<input type="button" class="btn btn-danger" value="删除" onclick="handle_delete()"/>
				<c:if test="${user.isAdmin eq true}">
				<input class="btn btn-danger" type="button" value="返回" onclick="handle_list_user()"/>
				</c:if>
			</td>
		</tr>
	</table>
	</form>
	</div>
	<script type="text/javascript">
		function handle_modify() {
			document.getElementById("myForm").submit();
		}
		function handle_delete() {
			if(confirm("是否确定删除？")) {
				window.location.href="logon/Logon_delete.do?userId=${u.id}";
			}else {
				return;
			}
		}
		function handle_list_user() {
			window.location.href="logon/User_back.do";
		}
</script>
	<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
	<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>