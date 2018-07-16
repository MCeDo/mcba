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

    <title>帖子详情</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/blog-post.css" rel="stylesheet">

  </head>

  <body>
	<%@include file="/WEB-INF/jsp/include/header.jsp" %>
    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <!-- Post Content Column -->
        <div class="col-lg-8">

          <!-- Title -->
          <h1 class="mt-4">${articleUserComm.article.title }</h1>
		  <c:if test="${user.id == articleUserComm.article.userId }">
			<input type="button" value="修改" class="btn btn-default" onclick="modify(${articleUserComm.article.id})"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="删除" class="btn btn-default" onclick="del(${articleUserComm.article.id})"/>
		  </c:if>
          <!-- Author -->
          <p class="lead">
            by
            <a href="#">${articleUserComm.user.userName }-${articleUserComm.user.starLevel }</a>
          </p>

          <hr>

          <!-- Date/Time -->
          <p>${articleUserComm.article.createTime }</p>

          <hr>

          <!-- Post Content -->
          <p class="lead">
          	${articleUserComm.article.content }
          </p>
          <hr>
		  
          <!-- Comments Form -->
          <div class="card my-4">
            <h5 class="card-header">留言</h5>
            <div class="card-body">
              <form action="Comment_add.do" method="post">
                <div class="form-group">
                  <textarea name="commContent" class="form-control" rows="3"></textarea>
                </div>
                <c:choose>
					<c:when test="${articleUserComm.article.isComment==0}">
						<input id="reply" type="button" class="btn btn-primary" value="不可评论"/>
					</c:when>
					<c:when test="${user != null }">
						<input id="reply" type="submit" class="btn btn-primary" value="发表评论"/>
					</c:when>
					<c:otherwise>
						<input id="reply" type="button" class="btn btn-primary" value="登录后可评论"/>
					</c:otherwise>
				</c:choose>
				<input type="hidden" name="articleId" value="${articleUserComm.article.id }" />
              </form>
            </div>
          </div>

          <!-- Single Comment -->
          <c:if test="${commentList.size() == 0}">
          <div class="media mb-4">
            <div class="media-body">
				暂无评论
            </div>
          </div>
          </c:if>
          <c:forEach items="${commentList }" var="list">
          <div class="media mb-4">
            <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
            <div class="media-body">
              <h5 class="mt-0">${list.user.userName }</h5>
            </div>
            <span>${list.comment.content }</span>
          </div>
		  </c:forEach>
        </div>

        <!-- right -->
        <%@include file="/WEB-INF/jsp/include/right.jsp" %>
      <!-- /.row -->

    </div>
   </div>
    <!-- /.container -->

    <!-- Footer -->
    <%@include file="/WEB-INF/jsp/include/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/jquery.liMarquee.js"></script>
	<script type="text/javascript">
	function del(id) {
		if(confirm("确定删除吗？")) {
			window.location.href="Article_delete.do?articleId="+id;
		}
	}
	function modify(id) {
		window.location.href="Article_preModify.do?articleId="+id;
	}
	</script>
  </body>

</html>
