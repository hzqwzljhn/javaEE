package cn.edu.zucc.ems.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.ems.model.CourseDAO;
import cn.edu.zucc.ems.util.connectUtil;

/**
 * Servlet implementation class CourseServlet
 */
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CourseDAO dao=new connectUtil().getCourseConnect();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
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
		request.setCharacterEncoding("gbk");
		String tab = request.getParameter("tab");
		
		String result = "";
		if ("homework".equals(tab)) {
				result = "/course_detail.jsp";
				request.getSession().setAttribute("tab",tab );
		} else if ("exam".equals(tab)) {
				result= "/course_detail.jsp";
				request.getSession().setAttribute("tab",tab );
		}else if ("final".equals(tab)) {
			result= "/course_detail.jsp";
			request.getSession().setAttribute("tab",tab );
		}
		else {
			result=listCourse(request);
		}
		RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher(result);
		if (dispatcher != null)
			dispatcher.forward(request, response);

	}

	private String listCourse(HttpServletRequest request) {
		
		String userid=(String) request.getSession().getAttribute("userid");
		request.setAttribute("objlist", this.dao.loadAllCourse(userid));
		return "/course_list.jsp";
	}

}
