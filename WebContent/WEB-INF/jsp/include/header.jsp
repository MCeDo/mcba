<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="cn.maiba.model.User" %>
       <%
    	User user = (User)session.getAttribute("user");
    %>
<script type="text/javascript">
	function test() {
		var obj = document.getElementById("s");
		var selectedValue = obj.options[obj.selectedIndex].value;
		if(selectedValue==="user") {
			document.myForm.action = "User_list.do";
		}else if(selectedValue==="article") {
			document.myForm.action = "Article_list.do";
		}
	}
</script>
    
<!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath }/Article_list.do">麦吧</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="${pageContext.request.contextPath }/Article_list.do">首页
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <%
			if(user == null) {
			%>
			<li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath }/User_Logon.do">登录</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath }/User_Register.do">注册</a>
            </li>
			<%
				}else {
			%>
            <li class="nav-item">
              <!-- Single button -->
				 <div class="dropdown">
				  <a class="nav-link dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				    分类
				    <span class="caret"></span>
				  </a>
				  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				    <li><a href="${pageContext.request.contextPath }/Article_listByType.do?type=学习版">学习版</a></li>
				    <li><a href="${pageContext.request.contextPath }/Article_listByType.do?type=体育版">体育版</a></li>
				    <li><a href="${pageContext.request.contextPath }/Article_listByType.do?type=生活版">生活版</a></li>
				  </ul>
				</div>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath }/logon/Logon_detail.do?userId=<%=user.getId() %>">个人资料</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath }/User_logout.do">退出</a>
            </li>
            <%
			}
			%>
			<% 
				if(user!=null && user.getIsAdmin()) {
					%>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath }/logon/User_list.do">后台管理</a>
					</li>
					<%
				}
			%>
          </ul>
        </div>
      </div>
    </nav>