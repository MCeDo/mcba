<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发布帖子</title>

	<!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/css/blog-post.css" rel="stylesheet">
<script type="text/javascript">
	function check() {
		if($('.title').val() == "" || $('.title').val() == null) {
			alert("请输入标题");
			return false;
		}
		if($('.content').val() == "" || $('.content').val() == null) {
			alert("请输入内容");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<%@include file="/WEB-INF/jsp/include/header.jsp" %>
	
	<div class="container" style="margin-top: 20px;">
		<form action="Article_add.do" method="post" >
			<table class="table text-center table-bordered" >
				<tr>
					<td colspan="2" style="background-color: #149BDF;">
						<span><big>发表帖子</big></span>
					</td>
				</tr>
				<tr>
					<td width="20%">标题：</td>
					<td class="text-left">
						<input type="text" name="title"/>
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
						<select name="type">
							<c:forEach items="${typeList }" var="type">
								<option value="${type.type }">${type.type }</option>
							</c:forEach>
						</select>	
					</td>
				</tr>
				<tr>
					<td style="vertical-align: inherit;">文章内容：</td>
					<td height="200px"><textarea name="content" style="width:100%;height:100%"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit" class="btn btn-info" id="post">发表</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-danger" 
							onclick="javascript:window.location.href='Article_list.do'">取消</button>
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