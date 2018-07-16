package cn.maiba.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.maiba.dao.Dao;
import cn.maiba.model.Notice;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NoticeControl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Notice> list = new Dao().list(Notice.class, null);
		try {
			PrintWriter out = response.getWriter();
			JSONArray noticeList = new JSONArray();
			JSONObject obj = null;
			for (Notice n : list) {
				obj = new JSONObject();
				obj.put("content", n.getContent());
				obj.put("author", n.getAuthor());
				noticeList.add(obj);
			}
			out.print(noticeList);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
