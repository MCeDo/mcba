<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/bootstrap.min.css">

</head>
<body>
	<%@include file="/WEB-INF/jsp/include/header.jsp" %>
	<form id="myForm" action="logon/Logon_modify.do" method="post">
	<table class="userList-tb" border="1">
		<tr><th colspan="2">我的信息</th></tr>
		<tr>
			<td width="120px" style="background-color:#eeeeee;">ID:</td>
			<td><input type="text" id="userId" name="userId" value="${user.id }"/></td>
		</tr>
		<tr>
			<td style="background-color:#eeeeee;">账号：</td>
			<td><input type="text" name="userName" value="${u.userName }"/></td>
		</tr>
		<tr>
			<td style="background-color:#eeeeee;">密码：</td>
			<td><input type="text" name="password" value="${u.password }"/></td>
		</tr>
		<tr>
			<td style="background-color:#eeeeee;">昵称：</td>
			<td><input type="text" name="nickName" value="${u.nickName }" /></td>
		</tr>
		<tr>
			<td style="background-color:#eeeeee;">年龄：</td>
			<td><input type="text" name="age" value="${u.age }" /></td>
		</tr>
		<tr>
			<td style="background-color:#eeeeee;">电子邮箱：</td>
			<td><input type="text" name="email" value="${u.email }" /></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="button" value="修改" onclick="handle_modify()"/>
				<input type="button" value="删除" onclick="handle_delete()"/>
				<input type="button" value="返回" onclick="handle_list_user()"/>
			</th>
		</tr>
	</table>
	</form>
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
</body>
</html>