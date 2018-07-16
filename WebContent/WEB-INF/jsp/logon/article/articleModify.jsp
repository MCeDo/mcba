<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改帖子</title>
<!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/blog-post.css" rel="stylesheet">
</head>
<body>
	<%@include file="/WEB-INF/jsp/include/header.jsp" %>
	
	<div class="container" style="margin-top: 20px;">
		<form action="Article_modify.do" method="post" >
		<input type="hidden" name="articleId" value="${articleUser.article.id }"/>
			<table class="table text-center table-bordered" >
				<tr>
					<td colspan="2" style="background-color: #149BDF;">
						<span><big>修改帖子</big></span>
					</td>
				</tr>
				<tr>
					<td width="20%">标题：</td>
					<td class="text-left">
						<input type="text" name="m_title" value="${articleUser.article.title }"/>
						<input type="checkbox" id="ii" name="isV" value="1" onblur="isVi()"/><small>游客可见</small>
						<input type="hidden" id="isVisable" name="isVisiable" value="0"/>
						<input type="checkbox" id="cc" name="isC" value="1" onblur="isCo()"/><small>可评论</small>
						<input type="hidden" id="isComment" name="isComment" value="0"/>
						<script type="text/javascript">
							function isVi() {
								if(document.getElementById("ii").checked) {
									document.getElementById("isVisable").value="1";
								}else {
									document.getElementById("isVisable").value="0";
								}
							}
							function isCo() {
								if(document.getElementById("cc").checked) {
									document.getElementById("isComment").value="1";
								}else {
									document.getElementById("isComment").value="0";
								}
							}
						</script>
					</td>
				</tr>
				<tr>
					<td>所属版块：</td>
					<td class="text-left">
						${articleUser.article.type }	
					</td>
				</tr>
				<tr>
					<td style="vertical-align: inherit;">文章内容：</td>
					<td height="200px">
						<textarea name="m_content" style="width:100%;height:100%" >${articleUser.article.content }
						</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit" class="btn btn-info">保存修改</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-danger" 
							onclick="javascript:window.location.href='Article_detail.do?articleId=${articleUser.article.id }'">取消</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
	<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath }/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>