package cn.edu.zucc.ems.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// request.getCharacterEncoding("UTF-8");
		if (request.getParameter("method").equals("addteacher")) {
			this.addTeacher(request);
		}

	}

	private void addTeacher(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		if((userid)) {
			
		}else {
			
		}
	}

}
