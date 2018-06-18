package cn.edu.zucc.ems.servlet;

import java.io.IOException;

import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.ems.bean.StudentBean;
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
		request.setCharacterEncoding("utf-8");
		String tab = request.getParameter("tab");
		String result = "";
		if("addcourse".equals(tab)){
			result = addCourse(request);
		}
		else if("addresult".equals(tab)){
			try {
				result = addResult(request);
			} catch (NumberFormatException | ParseException e) {
				e.printStackTrace();
			}
		}
		else if("modifycourse".equals(tab)){
			result = modifyCourse(request);
		}
		else if("modifyresult".equals(tab)){
			try {
				result = modifyResult(request);
			} catch (NumberFormatException | ParseException e) {
				e.printStackTrace();
			}
		}
		else if("deletecourse".equals(tab)){
			result = deleteCourse(request);
		}
		else if ("homework".equals(tab)) {
			result = "/course_detail.jsp";
			request.getSession().setAttribute("tab",tab );
		} else if ("exam".equals(tab)) {
			result= "/course_detail.jsp";
			request.getSession().setAttribute("tab",tab );
		}else if ("final".equals(tab)) {
			//result= "/course_detail.jsp";
			result=listFinal(request);
			request.getSession().setAttribute("tab",tab );
		}else if("detail".equals(tab)) {
			result="/course_detail.jsp";
			request.getSession().setAttribute("courseid",request.getParameter("courseid"));
			request.getSession().setAttribute("coursename",request.getParameter("coursename"));
		}
		else {
			result=listCourse(request);
		}
		RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher(result);
		if (dispatcher != null)
			dispatcher.forward(request, response);

	}

	private String listFinal(HttpServletRequest request) {
		String courseid=(String) request.getSession().getAttribute("courseid");
		System.out.println(courseid);
		request.setAttribute("listfinal", this.dao.listfinal(courseid));
		return "/course_detail.jsp";
	}

	/**
	 * 显示课程列表
	 * @param request
	 * @return
	 */
	private String listCourse(HttpServletRequest request) {
		String userid=(String) request.getSession().getAttribute("userid");
		request.setAttribute("objlist", this.dao.loadAllCourse(userid));
		return "/course_list.jsp";
	}
	
	/**
	 * 删除课程
	 * @param request
	 * @return
	 */
	private String deleteCourse(HttpServletRequest request){
		String userid=(String) request.getSession().getAttribute("userid");
		String courseid = request.getParameter("courseid");
		this.dao.deleteCourse(Integer.valueOf(courseid));
		request.setAttribute("objlist", this.dao.loadAllCourse(userid));
		return "/course_list.jsp";
	}
	
	/**
	 * 修改课程信息
	 * @param request
	 * @return
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	private String modifyResult(HttpServletRequest request) throws NumberFormatException, ParseException{
		String userid=(String) request.getSession().getAttribute("userid");
		String courseid = request.getParameter("courseid");
		String coursename = request.getParameter("coursename");
		String coursetime = request.getParameter("coursetime");
		this.dao.modifyCourse(Integer.valueOf(courseid),coursename,coursetime);
		request.setAttribute("objlist", this.dao.loadAllCourse(userid));
		return "/course_list.jsp";
	}
	
	/**
	 * 修改课程页面
	 * @param request
	 * @return
	 */
	private String modifyCourse(HttpServletRequest request){
		String courseid = request.getParameter("courseid");
		request.setAttribute("obj", this.dao.getCourse(Integer.valueOf(courseid)));
		return "/course_edit.jsp";
	}

	/**
	 * 添加课程信息
	 * @param request
	 * @return
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	private String addResult(HttpServletRequest request) throws NumberFormatException, ParseException{
		String userid=(String) request.getSession().getAttribute("userid");
		String courseid = request.getParameter("courseid");
		String coursename = request.getParameter("coursename");
		String coursetime = request.getParameter("coursetime");
		this.dao.addCourse(Integer.valueOf(courseid),coursename,coursetime,userid);
		request.setAttribute("objlist", this.dao.loadAllCourse(userid));
		return "/course_list.jsp";
	}
	
	/**
	 * 添加课程页面
	 * @param request
	 * @return
	 */
	private String addCourse(HttpServletRequest request){
		
		return "/course_add.jsp";
	}
}
