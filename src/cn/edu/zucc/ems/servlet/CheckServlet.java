package cn.edu.zucc.ems.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.ems.bean.StudentBean;
import cn.edu.zucc.ems.model.*;
import cn.edu.zucc.ems.util.connectUtil;

/**
 * Servlet implementation class CheckServlet
 */
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CheckDAO dao = new connectUtil().getCheckConnect();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result = null;
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("method").equals("readAllStudentByClass")) {
			result = this.readAllStudentByClass(request);
		} else if(request.getParameter("method").equals("homework")) {
			result = this.readAllStudentByClass(request);
		}
		
		RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher(result);
		if (dispatcher != null)
			dispatcher.forward(request, response);
	}
	
	private String readAllStudentByClass(HttpServletRequest request) {
		String string = request.getParameter("classid");
		//System.out.println(string);
		int classid = Integer.parseInt(string);
		List<StudentBean> studentBeans = this.dao.loadAllStudentByClass(classid);
		request.getSession().setAttribute("tab","homework" );
		request.getSession().setAttribute("class_id", studentBeans.get(0).getClass_id());
		request.setAttribute("studentList", studentBeans);
		return "/course_detail.jsp";
	}

}
