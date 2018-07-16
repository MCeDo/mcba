<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="cn.maiba.model.User" %>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/liMarquee.css">
<%
    	User u = (User)session.getAttribute("user");
%>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery.liMarquee.js"></script>
<script type="text/javascript">
	function test() {
		var obj = document.getElementById("s");
		var selectedValue = obj.options[obj.selectedIndex].value;
		if(selectedValue==="user") {
			$('#seacrhForm').attr('action','User_list.do');
		}else if(selectedValue==="article") {
			document.myForm.action = "Article_search.do";
		}
	}
	$.post("notice","post", function(data){
		 var noticeList = eval("("+data+")");
		 var value = "";
		 for(var key in noticeList) {
			 value += "<span>";
			 for(var k in key) {
				 value += noticeList[key].content+"--"+noticeList[key].author;
			 }
			 value += "。</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
		 }
		 $('.notice').html(value);
		 $('.notice').liMarquee();
		});
</script>

<!-- Sidebar Widgets Column -->
        <div class="col-md-4">
          <!-- Search Widget -->
          <div class="card my-4">
            <h5 class="card-header">搜索</h5>
            <div class="card-body">
              <div class="input-group">
              	<form id="seacrhForm" name="myForm" action="Article_search.do" method="get">
                <input type="text" name="searchValue"/>
                <select id="s" name="selected" onblur="test()">
					<option value="user">用户</option>
					<option value="article" selected="selected">文章</option>
				</select>
				<input  class="btn btn-secondary" type="submit" value="GO" onclick="test()"/>
                </form>
              </div>
            </div>
          </div>

          <!-- Categories Widget -->
          <div class="card my-4">
            <h5 class="card-header">类型</h5>
            <div class="card-body">
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="${pageContext.request.contextPath }/Article_listByType.do?type=学习版">学习版</a>
                    </li>
                    <li>
                      <a href="${pageContext.request.contextPath }/Article_listByType.do?type=体育版">体育版</a>
                    </li>
                    <li>
                      <a href="#"></a>
                    </li>
                  </ul>
                </div>
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="${pageContext.request.contextPath }/Article_listByType.do?type=生活版">生活版</a>
                    </li>
                    <li>
                      <a href="#"></a>
                    </li>
                    <li>
                      <a href="#"></a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
			
          <!-- Side Widget -->
         <c:if test="${user != null }">
         	 <div class="card my-4">
	            <h5 class="card-header">欢迎你，<%=u.getNickName() %></h5>
	            
	            <div class="card-body">
	              	<h4>级别：<%=u.getStarLevel() %></h4>
	              	<span>经验值：<%=u.getExpe() %></span>
	              	<hr>
	              	<a href="${pageContext.request.contextPath }/logon/Article_myList.do"><small>我发表的帖子</small></a>
					&nbsp;&nbsp;&nbsp;
					<a href="Article_myComment.do"><small>我评论的帖子</small></a>
					<br />
					<a href="Article_preAdd.do">发表帖子</a>
	            </div>
	          </div>
         </c:if>
         

      </div>
      <!-- /.row -->
