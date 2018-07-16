<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="cn.maiba.listener.OnlineUser"%>
		
<!-- Footer -->
    <footer class="py-10 bg-dark navbar-fixed-bottom">
    <div class="container">
      	<p class="text-white text-center">当前已登录用户数：<%=OnlineUser.onlineUsers %>;当前在线用户数：<%=OnlineUser.onlineAll %></p>
        <p class="m-0 text-center text-white">Copyright &copy; 邓伟驰 2017;</p>
      <!-- /.container -->
      </div>
    </footer>