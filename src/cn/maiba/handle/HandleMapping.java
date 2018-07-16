package cn.maiba.handle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandleMapping {

	public static Object getControl(String className, 
			HttpServletRequest request, HttpServletResponse response) {
		Object obj = null;
		try {
			obj = Class.forName("cn.maiba.control."+className+"Control").newInstance();
			//获取属性
			Field[] field = obj.getClass().getDeclaredFields();
			String[] modelName = new String[field.length];
			String[] modelType = new String[field.length];
			for(int i=0; i<field.length; i++) {
				//获取属性名字
				String name = field[i].getName();
				//获取属性类型
				String type = field[i].getGenericType().toString();
				modelName[i] = name;
				modelType[i] = type;
				//设置素有属性为可访问
				field[i].setAccessible(true);
				//将属性首字符设置为大写，用于获得setter方法，如：setName(String name)
				name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
				//根据属性的类型为属性赋值，待改进
				if("interface javax.servlet.http.HttpServletRequest".equals(type)) {
					//获得setter方法
					Method m = obj.getClass().getMethod("set"+name, HttpServletRequest.class);
					//执行setter赋值
					m.invoke(obj, request);
				}
				if("interface javax.servlet.http.HttpServletResponse".equals(type)) {
					Method m = obj.getClass().getMethod("set"+name, HttpServletResponse.class);
					m.invoke(obj, response);
				}
			}
			return obj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
