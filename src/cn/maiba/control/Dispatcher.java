package cn.maiba.control;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.maiba.handle.HandleMapping;

/**
 * Servlet implementation class Dispatcher
 */
public class Dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dispatcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 待改进
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//当前uri路径
		String uri = request.getRequestURI();
		//获得请求的类和方法名
		String[] clazz_method = (uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."))).split("_");
		//获得类名
		String clazz = clazz_method[0];
		//获得方法名字
		String method = clazz_method[1];
		try {
			//根据类名实例化对应的类，注入属性值
			Object control = HandleMapping.getControl(clazz, request, response);
			//通过反射执行方法
			Method ms = control.getClass().getDeclaredMethod(method);
			String result = (String) ms.invoke(control);
			//进行转发
			if(result != null || !"".equals(result)) {
				request.setAttribute("forward", result);
				request.getRequestDispatcher("/WEB-INF/jsp/"+result).forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
