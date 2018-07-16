package cn.maiba.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {

	//�Զ���request�ж���url����ʽ��http://localhost:8080/JavaWeb/user/detail.do?userId=1
	public static String getURL(HttpServletRequest request) {
		String url = "";
		url = request.getRequestURI()+"?";
		url += param(request);
		url = url.replaceFirst("/JavaWeb", "");
		return url;
	}

	private static String param(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String url = "";
		Enumeration param = request.getParameterNames();
		while(param.hasMoreElements()) {
			String pname = param.nextElement().toString();
			url += pname + "=" + request.getParameter(pname) + "&";
		}
		if(url.endsWith("&")) {
			url = url.substring(0, url.lastIndexOf("&"));
		}
		return url;
	}
}
