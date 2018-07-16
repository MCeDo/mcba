<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css">

</head>
<body>
	<jsp:include page="${pageContext.request.contextPath }/header.jsp" />
	<div align="center" style="padding:50px">
		<span>${Result_Message }</span>
		<br><br>
		<input type="button" value="返回" onclick="javascript:window.location.href='<%=request.getContextPath()%>/logon/success-UserLogon.jsp'"/>
	</div>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp" />
	<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>