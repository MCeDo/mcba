<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>错误</title>
<!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/blog-post.css" rel="stylesheet"></head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
<body>
	<%@include file="/WEB-INF/jsp/include/header.jsp" %>
	
	<div style="text-align:center; padding:100px">
		用户尚未登录，所以无法执行您所作的操作。请先<a href="${pageContext.request.contextPath }/User_Logon.do">登录</a>
		<br /><br>
	</div>
	
	<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
	<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>