package cn.maiba.control;

import javax.servlet.http.HttpServletRequest;

import cn.maiba.dao.UserDao;
import cn.maiba.model.User;

public class LogonControl  {

	private HttpServletRequest request;
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public String detail() {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		String result = "";
		int userId = Integer.parseInt(request.getParameter("userId"));
		User user = (User) request.getSession().getAttribute("user");
		if(!user.getIsAdmin() && user.getId()!=userId) {
			request.setAttribute("failure_message", "您无此权限查看");
			return "result/failure.jsp";
		}
		User u = userDao.load(userId);
		request.setAttribute("u", u);
		result = "logon/userDetail.jsp";
		return result;
	}
	
	public String modify() {
		User user = (User) request.getSession().getAttribute("user");
		int id = Integer.parseInt(request.getParameter("userId"));
		if(!user.getIsAdmin() && user.getId()!=id) {
			request.setAttribute("failure_message", "您无此权限查看");
			return "result/failure.jsp";
		}
		String result = "";
		request.setAttribute("userId", id);
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		int age = Integer.parseInt(request.getParameter("age"));
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		UserDao userDao = new UserDao();
		User u = userDao.load(id);
		u.setUserName(userName);
		u.setPassword(password);
		u.setAge(age);
		u.setEmail(email);
		u.setNickName(nickName);
		result = "result/success-modify.jsp";
		if(userDao.update(u)) {
			request.setAttribute("Result_Message", "修改成功");
		}else {
			request.setAttribute("Result_Message", "修改失败");
		}
		return result;
	}
	
	public String delete(){
		User user = (User) request.getSession().getAttribute("user");
		if(!user.getIsAdmin()) {
			request.setAttribute("failure_message", "您无此权限查看");
			return "result/failure.jsp";
		}
		UserDao userDao = new  UserDao();
		String result = "result/success-modify.jsp";
		int userId = Integer.parseInt(request.getParameter("userId"));
		if(userDao.delete(userId)) {
			request.setAttribute("Result_Message", "删除用户成功！");
		}else {
			request.setAttribute("Result_Message", "删除用户失败！");
		}
		return result;
	}

}
