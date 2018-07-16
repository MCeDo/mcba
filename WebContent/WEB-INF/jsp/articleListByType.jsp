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
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/blog-home.css" rel="stylesheet">

  </head>

  <body>
	<%@include file="/WEB-INF/jsp/include/header.jsp" %>

    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

          <h1 class="my-4">
			>${currentType }
			<c:forEach items="${typeUserList }" var="typeUser">
					<c:if test="${currentType eq typeUser.type.type && typeUser.type.userId!=0 }">
						<small>--版主：${typeUser.user.userName }</small>
					</c:if>
			</c:forEach>
		</h1>

          <!-- Blog Post -->
          <c:forEach items="${topList }" var="list">
          <div class="card mb-4">
            <div class="card-body">
              <h2 class="card-title" style="color:red">[置顶]${list.article.title }</h2>
              
              <c:choose>
				<c:when test="${user==null and list.article.isVisiable==0}">
					<a onClick="check()">Read More &rarr;</a>
				</c:when>
				<c:otherwise>
					<a href="Article_detail.do?articleId=${list.article.id }" class="btn btn-primary">Read More &rarr;</a>
				</c:otherwise>
			  </c:choose>
            </div>
            <div class="card-footer text-muted">
              	 ${list.article.createTime } 
              <a href="#">${list.user.userName }-${list.user.starLevel }</a>
              <span style="margin-left:auto">->阅读数${list.article.hitNum };</span>
              <span style="">回复:${list.article.remarkNum }</span>
            </div>
            <c:forEach items="${typeUserList }" var="typeUser">
					<c:if test="${typeUser.type.userId eq user.id && typeUser.type.type eq currentType}">
						<div>
						<input class="btn btn-danger" type="button" value="删除" onclick="del(${list.article.id})"/>
						<input class="btn btn-info" type="button" value="取置" onclick="toTop(${list.article.id})"/>
						</div>
					</c:if>
			</c:forEach>
          </div>
		  </c:forEach>
		  <hr><hr>
          <!-- Blog Post -->
          <c:forEach items="${articleList }" var="list">
          <div class="card mb-4">
            <div class="card-body">
              <h2 class="card-title">${list.article.title }</h2>
              
              <c:choose>
				<c:when test="${user==null and list.article.isVisiable==0}">
					<a onClick="check()">Read More &rarr;</a>
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
            <c:forEach items="${typeUserList }" var="typeUser">
					<c:if test="${typeUser.type.userId eq user.id && typeUser.type.type eq currentType}">
						<div>
						<input class="btn btn-danger" type="button" value="删除" onclick="del(${list.article.id})"/>
						<input class="btn btn-info" type="button" value="置顶" onclick="toTop(${list.article.id})"/>
						</div>
					</c:if>
			</c:forEach>
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
				    	<a class="page-link" href="${pageContext.request.contextPath }/Article_listByType.do?type=${currentType }&currentPage=${page.currentPage-1 }">&larr;上一页</a>
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
				    	<a class="page-link" href="${pageContext.request.contextPath }/Article_listByType.do?type=${currentType }&currentPage=${page.currentPage+1 }">下一页&rarr;</a>
				    </li>
				</c:otherwise>
			  	</c:choose>
          </ul>

        </div>

        <!-- right -->
        <%@include file="/WEB-INF/jsp/include/right.jsp" %>
        </div>
    </div>
    <!-- /.container -->

    <!-- Footer -->
    <%@include file="/WEB-INF/jsp/include/footer.jsp" %>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		function check() {
			alert("登录后可阅读");
			return false;
		}
		function del(id) {
			if(confirm("确定删除吗？")) {
				window.location.href = "Article_delete.do?articleId="+id;
			}else {
				return;
			}
		}
		function toTop(id) {
			window.location.href = "Article_toTop.do?articleId="+id;
		}
	</script>
  </body>

</html>
