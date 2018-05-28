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

			try {
				result = this.logincheck(request);
				System.out.println("logincheck");
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		} else {
			System.out.println("no");
		}
		RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher(result);
		if (dispatcher != null)
			dispatcher.forward(request, response);

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
				return "/manager.jsp";
			} else {
				return "/error.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("errormsg", e.getMessage());
			return "/error.jsp";
		}

	}

}
