<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>帖子列表</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/blog-home.css" rel="stylesheet">

  </head>

  <body>
	<%@include file="/WEB-INF/jsp/include/header.jsp" %>

    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

          <h1 class="my-4">首页</h1>

          <!-- Blog Post -->
          <c:forEach items="${topList }" var="list">
          <div class="card mb-4">
            <div class="card-body">
              <h2 class="card-title" style="color:red">[置顶]${list.article.title }</h2>
              <p>( 
              	<a href="${pageContext.request.contextPath }/Article_listByType.do?type=${list.article.type }">${list.article.type }
              	</a>
              )</p>
              <c:choose>
				<c:when test="${user==null and list.article.isVisiable==0}">
					<a href="#" class="btn btn-primary" onClick="check()">Read More &rarr;</a>
				</c:when>
				<c:otherwise>
					<a href="Article_detail.do?articleId=${list.article.id }" class="btn btn-primary">Read More &rarr;</a>
				</c:otherwise>
			  </c:choose>
            </div>
            <div class="card-footer text-muted">
              	${list.article.createTime } 
              <a href="#">${list.user.userName }-${list.user.starLevel }</a>
              <span>->阅读数${list.article.hitNum };</span>
              <span>回复:${list.article.remarkNum }</span>
            </div>
          </div>
		  </c:forEach>
		  <hr><hr>
          <!-- Blog Post -->
          <c:forEach items="${articleList }" var="list">
          <div class="card mb-4">
            <div class="card-body">
              <h2 class="card-title">${list.article.title }</h2>
              <p>( 
              	<a href="${pageContext.request.contextPath }/Article_listByType.do?type=${list.article.type }">${list.article.type }
              	</a>
              )</p>
              <c:choose>
				<c:when test="${user==null and list.article.isVisiable==0}">
					<a href="#" onClick="check()" class="btn btn-primary">Read More &rarr;</a>
				</c:when>
				<c:otherwise>
					<a href="Article_detail.do?articleId=${list.article.id }" class="btn btn-primary">Read More &rarr;</a>
				</c:otherwise>
			  </c:choose>
            </div>
            <div class="card-footer text-muted">
              	${list.article.createTime } 
              <a href="#">${list.user.userName }-${list.user.starLevel }</a>
              <span>->阅读数${list.article.hitNum };</span>
              <span>回复:${list.article.remarkNum }</span>
            </div>
          </div>
		  </c:forEach>


          <!-- Pagination -->
          <ul class="pagination justify-content-center mb-4">
            <c:choose>
			  	<c:when test="${page.currentPage<=0 || page.currentPage==null }">
			  		<li class="page-item disabled">
				    	<a class="page-link">&larr;上一页</a>
				    </li>
			  	</c:when>
			  	<c:otherwise>
			  		<li>
				    	<a class="page-link" href="${pageContext.request.contextPath }/Article_list.do?currentPage=${page.currentPage-1 }">&larr;上一页</a>
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
				    	<a class="page-link" href="${pageContext.request.contextPath }/Article_list.do?currentPage=${page.currentPage+1 }">下一页&rarr;</a>
				    </li>
				</c:otherwise>
			  	</c:choose>
          </ul>

        </div>

        <!-- right -->
        <%@include file="/WEB-INF/jsp/include/right.jsp" %>
		</div>
	</div>
    <!-- Footer -->
    <%@include file="/WEB-INF/jsp/include/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/jquery.liMarquee.js"></script>
	<script type="text/javascript">
		function check() {
			alert("登录后可阅读");
			return false;
		}
	</script>
  </body>

</html>
