package cn.maiba.test;

import cn.maiba.util.EmailSender;

public class Test {
	
	/*@org.junit.Test
	public void dbTest() throws Exception {
		Class clazz = Article.class;
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods = clazz.getDeclaredMethods();
		String sql = "select * from "+clazz.getField("TABLE_NAME").get(clazz);
		for(int i=0; i<fields.length; i++) {
			System.out.println(fields[i].getName());
			System.out.println(fields[i].getType().toString());
			Class clazz2 = fields[i].getType();
			Field[] fs = clazz2.getDeclaredFields();
			if(fields[i].getType().toString().contains("cn.maiba")) {
				sql += " left join "+clazz2.getField("TABLE_NAME").get(clazz2)+" on " + fields[i].getName() + "."
						+ fields[i].getName()+"Id=";
				for(int j=0; j<fs.length; j++) {
					System.out.println(fs[j].getName());
				}
			}
			
			System.out.println();
		}
		
		System.out.println(sql);
	}
	*/

	@org.junit.Test
	public void daoTest(){
		EmailSender sender = new EmailSender();
		sender.setTo("458509914@qq.com");
		sender.setMessage("测试用例子");
		sender.setTitle("a)从[开始时间~结束时间]在线用户数统计(session.isNew()), 如何实现");
		sender.send();
	}
}
