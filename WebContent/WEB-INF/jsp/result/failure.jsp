<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/blog-post.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <br><br><br>
<div style="width: 100%;" align="center">
	操作失败：<%=request.getAttribute("failure_message") %>
	<p>请重新操作。
	<a href='#' onclick="history.back()">返回</a>
</div>
<br><br><br>
<jsp:include page="../include/footer.jsp" />
<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	