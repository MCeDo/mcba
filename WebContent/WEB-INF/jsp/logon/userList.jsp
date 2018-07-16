<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/blog-home.css" rel="stylesheet">
</head>
<body>
	<%@include file="/WEB-INF/jsp/include/header.jsp" %>
	<div class="container" style="margin-top: 20px;">
	<table class="table" border="1">
		<tr><th colspan="4">用户列表</th></tr>
		<tr>
			<td width="30px">ID</td>
			<td width="180px">账号</td>
			<td width="150px">密码</td>
			<td width="100px">昵称</td>
		</tr>
		<c:forEach var="u" items="${userList }">
		<tr>
			<td>${u.id }</td>
			<td>
				<a href="logon/Logon_detail.do?userId=${u.id }" name="userId">
					${u.userName }
				</a>
			</td>
			<td>${u.password }</td>
			<td>${u.nickName }</td>
		</tr>
		</c:forEach>
	</table>
	</div>
	<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
	<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath }/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>