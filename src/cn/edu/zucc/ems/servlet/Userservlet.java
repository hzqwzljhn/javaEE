package cn.edu.zucc.ems.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String result = "";
		if (method.equals("logincheck")) {
				result = this.logincheck(request);
		} else if (method.equals("logout")) {
				result=this.logout(request);
		} else {
			System.out.println("no");
		}
		RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher(result);
		if (dispatcher != null)
			dispatcher.forward(request, response);

	}

	/**
	 * 退出
	 * @param request
	 * @return
	 */
	private String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("userid");
		request.getSession().getAttribute("password");
		return "/login.jsp";
	}

	/**
	 * 登录接口
	 * @param request
	 * @return
	 */
	private String logincheck(HttpServletRequest request)  {
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
				return "/manager.jsp";
			} else {
				System.out.println("teacher");
				request.getSession().setAttribute("userid",userid );
				request.getSession().setAttribute("password",password );
				return "/teacher.jsp";
			}
		} catch (Exception e) {
			request.getSession().setAttribute("errormsg", e.getMessage());
			return "/login.jsp";
		}

	}

}
