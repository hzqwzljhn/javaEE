package cn.edu.zucc.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import cn.edu.zucc.ems.bean.UserBean;
import cn.edu.zucc.ems.model.UserDAO;
import cn.edu.zucc.ems.util.connectUtil;

public class Userservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao = new connectUtil().getUserConnect();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Userservlet() {
		super();
	}

	/**
	 * 获取请求
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * 获取请求
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		String method = request.getParameter("method");
		
		if (method.equals("logincheck")) {
				 this.logincheck(request,response);
		} else if (method.equals("logout")) {
				this.logout(request, response);
		} else {
			System.out.println("no");
		}

	}

	/**
	 * 退出
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("userid");
		PrintWriter out = response.getWriter();
		out.println("111");
		out.flush();
		out.close();
	}

	/**
	 * 登录接口
	 * @param request
	 * @param response 
	 * @throws IOException 
	 */
	private void logincheck(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		 PrintWriter out = response.getWriter();
		try {
			String userid = request.getParameter("userid");
			
			if (userid == null || userid.equals(""))
				throw new Exception("请输入用户名");
			String password = request.getParameter("password");
			if (password == null)
				password = "";
			UserBean userBean = this.dao.readUser(userid);
			if (userBean == null) {
				throw new Exception("用户不存在");
			}
			
			if (!password.equals(userBean.getPassword())) {
				throw new Exception("密码错误");
			}
			
			request.getSession().setAttribute("username", userBean.getUser_name());
			if ("管理员".equals(userBean.getType())) {
				request.getSession().setAttribute("userid",userid );
				request.getSession().setAttribute("password",password );
				 Cookie cookie = new Cookie("userid",userid);
				 cookie.setMaxAge(60*60*24);
				 response.addCookie(cookie);
				 out.println("12");
				 
			} else {
				request.getSession().setAttribute("userid",userid );
				request.getSession().setAttribute("password",password );
				out.println("11");
				Cookie cookie = new Cookie("userid",userid);
				 cookie.setMaxAge(60*60*24);
				 response.addCookie(cookie);
			}
			
		} catch (Exception e) {
			request.getSession().setAttribute("errormsg", e.getMessage());
			out.print("13");
			 out.flush();
			 out.close();
		}
		out.flush();
		 out.close();

	}

}
