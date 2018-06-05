package cn.edu.zucc.ems.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.ems.bean.UserBean;
import cn.edu.zucc.ems.model.UserDAO;
import cn.edu.zucc.ems.util.connectUtil;

/**
 * Servlet implementation class TeacherServlet
 */
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao = new connectUtil().getUserConnect();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result = null;
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("method").equals("addteacher")) {
			result = this.addTeacher(request);
		} else if(request.getParameter("method").equals("modifyTeacher")) {
			result = this.modifyTeacher(request);
		} else if(request.getParameter("method").equals("loadAllTeacher")) {
			result = this.loadAllTeacher(request);
		}
		
		RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher(result);
		if (dispatcher != null)
			dispatcher.forward(request, response);

	}

	/**增加教师
	 * @param request
	 * @return
	 */
	private String addTeacher(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		if (this.dao.readUser(userid) != null) {
			try {
				throw new Exception("用户已存在！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "/error.jsp";
		} else {
			UserBean test = this.dao.addTeacher(userid, username);
			System.out.println(test.getUser_id() + " " + test.getUser_name() + " " + test.getType());
			return "/teacher_list.jsp";
		}
	}
	
	private String modifyTeacher(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		String pwd2 = request.getParameter("password2");
		System.out.println(pwd);
		System.out.println(pwd2);
		if (this.dao.readUser(userid) == null) {
			try {
				throw new Exception("用户不存在！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "/error.jsp";
		} else if(!pwd.equals(pwd2)){
			try {
				throw new Exception("密码不相同！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "/error.jsp";
		} else {
			UserBean test = this.dao.modifyTeacher(userid, username, pwd);
			System.out.println(test.getUser_id() + " " + test.getUser_name() + " " + test.getType());
			return "/teacher_list.jsp";
		}
	}
	
	private String loadAllTeacher(HttpServletRequest request) {
		List<UserBean> list = this.dao.loadAllTeacher();
		request.setAttribute("userList", list);
		
		return "/teacher_list.jsp";
	}

}
