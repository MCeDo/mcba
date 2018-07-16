<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的帖子</title>
<!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/blog-home.css" rel="stylesheet"></head>
<body>
	<%@include file="/WEB-INF/jsp/include/header.jsp" %>
	
	<div style="margin:20px">
		<a href="${pageContext.request.contextPath }/logon/Article_myList.do"><span style="color: blue; ">我发表的帖子</span></a>
		&nbsp;&nbsp;&nbsp;
		<a href="Article_myComment.do"><small>我评论的帖子</small></a>
	</div>
	
	<div class="container" style="margin-top: 20px;">
		<table class="table text-center table-bordered">
				<tr>
					<tr>
					<td colspan="4" style="background-color: #149BDF;">
						<span><big>我的文章列表</big></span>
					</td>
					<td style="background-color: #149BDF;">
						<a href="Article_preAdd.do">
							<span class="text-right">新建帖子</span>
						</a>
					</td>
				</tr>
				<tr>
					<td width="10%">点击数</td>
					<td width="10%">回复数</td>
					<td width="30%">标题	</td>
					<td width="10%">作者</td>
					<td width="10%">最后回复时间</td>
				</tr>
				<c:forEach items="${articleList }" var="list">
					<tr>
						<td>${list.article.hitNum }</td>
						<td>${list.article.remarkNum }</td>
						<td><a href="logon/Article_detail.do?articleId=${list.article.id }">${list.article.title }</a></td>
						<td>${list.user.userName }</td>
						<td>${list.article.createTime }</td>
					</tr>
				</c:forEach>
			</table>
			<ul class="pagination justify-content-center mb-4">
            <c:choose>
			  	<c:when test="${page.currentPage<=0 || page.currentPage==null }">
			  		<li class="page-item disabled">
				    	<a class="page-link">&larr;上一页</a>
				    </li>
			  	</c:when>
			  	<c:otherwise>
			  		<li>
				    	<a class="page-link" href="${pageContext.request.contextPath }/Article_myList.do?currentPage=${page.currentPage-1 }">&larr;上一页</a>
				    </li>
				</c:otherwise>
			  	</c:choose>
			  	
			   <c:choose>
			  	<c:when test="${page.currentPage>=page.totalPage-1 }">
			  		<li class="page-item disabled">
				    	<a class="page-link">下一页&rarr;</a>
				    </li>
			  	</c:when>
			  	<c:otherwise>
			  		<li class="page-item">
				    	<a class="page-link" href="${pageContext.request.contextPath }/Article_myList.do?currentPage=${page.currentPage+1 }">下一页&rarr;</a>
				    </li>
				</c:otherwise>
			  	</c:choose>
          </ul>
	</div>
	
	<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
		<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>