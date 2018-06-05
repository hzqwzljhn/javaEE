package cn.edu.zucc.ems.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.ems.model.StudentDAO;
import cn.edu.zucc.ems.util.connectUtil;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO dao = new connectUtil().getStudentConnect();
       
    public StudentServlet() {
        super();
    }

	/**
	 * 获取请求
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 获取请求
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		String result = "";
		if("addstudent".equals(method)){
			result = addStudent(request);
		}
		else if("addresult".equals(method)){
			result = addResult(request);
		}
		else if("modifystudent".equals(method)){
			result = modifyStudent(request);
		}
		else if("modifyresult".equals(method)){
			result = modifyResult(request);
		}
		else if("deletestudent".equals(method)){
			result = deleteStudent(request);
		}
		else{
			result = listStudent(request);
		}
		RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher(result);
		if(dispatcher != null){
			dispatcher.forward(request, response);
		}
	}
	
	/**
	 * 显示学生列表
	 * @param request
	 * @return
	 */
	private String listStudent(HttpServletRequest request){
		request.setAttribute("objlist", this.dao.listAllStudent());
		return "/student_list.jsp";
	}
	
	/**
	 * 删除学生
	 * @param request
	 * @return
	 */
	private String deleteStudent(HttpServletRequest request){
		String studentid = request.getParameter("studentid");
		this.dao.deleteStudent(Integer.valueOf(studentid));
		request.setAttribute("objlist", this.dao.listAllStudent());
		return "/student_list.jsp";
	}
	
	/**
	 * 修改学生信息
	 * @param request
	 * @return
	 */
	private String modifyResult(HttpServletRequest request){
		String studentid = request.getParameter("studentid");
		String studentname = request.getParameter("studentname");
		this.dao.modifyStudent(Integer.valueOf(studentid),studentname);
		request.setAttribute("objlist", this.dao.listAllStudent());
		return "/student_list.jsp";
	}
	
	/**
	 * 修改学生页面
	 * @param request
	 * @return
	 */
	private String modifyStudent(HttpServletRequest request){
		String studentid = request.getParameter("studentid");
		request.setAttribute("obj", this.dao.getStudent(Integer.valueOf(studentid)));
		return "/student_edit.jsp";
	}
	
	/**
	 * 添加学生信息
	 * @param request
	 * @return
	 */
	private String addResult(HttpServletRequest request){
		String studentid = request.getParameter("studentid");
		String studentname = request.getParameter("studentname");
		this.dao.addStudent(Integer.valueOf(studentid),studentname);
		request.setAttribute("objlist", this.dao.listAllStudent());
		return "/student_list.jsp";
	}
	
	/**
	 * 添加学生页面
	 * @param request
	 * @return
	 */
	private String addStudent(HttpServletRequest request){
		return "/student_add.jsp";
	}
}
