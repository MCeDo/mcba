package cn.maiba.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.maiba.dao.ExpeDao;
import cn.maiba.dao.ForBiddenUserDao;
import cn.maiba.dao.UserDao;
import cn.maiba.model.User;
import cn.maiba.util.EmailSender;

public class UserControl  {

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	UserDao userDao = new UserDao();
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public String list() {
		List<User> userList = null;
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("failure_message", "您无此权限查看");
			return "result/failure.jsp";
		}
		if(!user.getIsAdmin()) {
			request.setAttribute("failure_message", "您无此权限查看");
			return "result/failure.jsp";
		}
		String searchValue = request.getParameter("searchValue");
		if(searchValue != null) {
			userList = userDao.usersLike(searchValue);
		}else {
			userList = userDao.list();
		}
		request.setAttribute("userList", userList);
		return "logon/userList.jsp";
	}

	public String handleLogon() {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		String result = "";
		if(userName==null || password==null || "".equals(userName) || "".equals(password)) {
			result = "result/failure.jsp";
		}
		//密码是否输入错误
		if(session.getAttribute("errorPwd")!=null) {
			String error = (String) session.getAttribute("errorPwd");
			System.out.println(error);
			if(Integer.parseInt(error) <=1) {
				String imgID = request.getParameter("imgID");
				System.out.println(imgID+":"+(String)session.getAttribute("rand"));
				if(!imgID.equals((String)session.getAttribute("rand"))) {
					request.setAttribute("failure_message", "验证码错误！");
					return "userLogon.jsp";
				}
			}else {
				//密码错误三次，锁定账号一小时
				request.setAttribute("failure_message", "密码错误三次，将锁定账号一小时");
				System.out.println("锁定账号" + userName);
				//将账户锁定
				ForBiddenUserDao.addForbiddenUser(userName);
				request.getSession().setAttribute("forbiddenUser", userName);
				request.getSession().removeAttribute("errorPwd");
				return "userLogon.jsp";
			}
		}
		List<User> userList = userDao.list(userName);
			
		if(userList == null || userList.size()<=0) {
			request.setAttribute("failure_message", "你提交的用户不存在");
			result = "userLogon.jsp";
		}else {
			User user = (User)userList.get(0);
			if(user.getPassword().equals(password)) {
				session.setAttribute("user", user);
				session.removeAttribute("errorPwd");
				//增加登录次数
				ExpeDao.addLogon(user.getId()+"");
				String url = (String)session.getAttribute("forwardURL");
				if(url!=null) {
					session.removeAttribute("forwardURL");
					result = url;
				}else {
					try {
						response.sendRedirect("Article_list.do");
						return null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else {
				//若错误一次，则需要输入验证码
				String error = "1";
				if(session.getAttribute("errorPwd") != null) {
					error = (String)session.getAttribute("errorPwd");
					error = (Integer.parseInt(error) + 1) + "";
				}
				session.setAttribute("errorPwd", error);
				request.setAttribute("failure_message", "用户名或者密码错误");
				result = "userLogon.jsp";
			}
		}
		return result;
	}
	
	public String handleRegister() {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String nickName = request.getParameter("nickName");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setAge(Integer.parseInt(age));
		user.setNickName(nickName);
		user.setEmail(email);
		List userList;
		try {
			UserDao userDao = new UserDao();
			userList = userDao.list(userName);
			if(userList == null || userList.size()<=0) {
				userDao.save(user);
				request.setAttribute("success_message", "注册成功");
				//向注册用户发送通知
				EmailSender sender = new EmailSender(user.getEmail(), user.getUserName()+"，欢迎来到麦吧");
				sender.send();
			}else {
				request.setAttribute("failure_message", "注册失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "result/success.jsp";
	}
	
	public String back() {
		List userList = new UserDao().list();
		request.setAttribute("userList", userList);
		return "logon/userList.jsp";
	}
	
	public String Logon() {
		return "userLogon.jsp";
	}
	
	public String logout() {
		request.getSession().removeAttribute("user");
		return "userLogon.jsp";
	}
	
	public String Register() {
		// TODO Auto-generated method stub
		return "userRegister.jsp";
	}
}
