<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String forward = (String)request.getAttribute("forward");
	if(forward==null) {
		%>
		<jsp:forward page="/WEB-INF/jsp/Article_list.do"/>
		<%
	}else {
		%>
		<jsp:forward page="/WEB-INF/jsp/${forward }"/>
		<%
	}
%>
