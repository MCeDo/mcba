package cn.maiba.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflect {

	public static void main(String[] args) throws Exception{
		String clazzName = "cn.maiba.test.UserService";
		Object obj = Class.forName(clazzName).newInstance();
		//获取所有自定的方法
		Method[] ms = obj.getClass().getDeclaredMethods();
		//获取自定义的所有属性
		Field[] field = obj.getClass().getDeclaredFields();
		int fLen = field.length;
		int mLen = ms.length;
		String[] modelName = new String[fLen];
		String[] modelType = new String[fLen];
		
		for(int i=0; i<fLen; i++) {
			//获取属性的名字
			String name = field[i].getName();
			modelName[i] = name;
			//获取属性的类型
			String type = field[i].getType().toString();
			modelType[i] = type;
			field[i].setAccessible(true);
			name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
			if(type.equals("class java.lang.String")){
				String value = "cedo";
				Method m = obj.getClass().getMethod("set"+name, String.class);
				m.invoke(obj, value);
			}
		}
		
		for (Field f : field) {
			System.out.println(f.getType());
		}
		for(Method m: ms) {
			System.out.println(m.getName());
		}
		System.out.println(field[0].get(obj));
	}
}
