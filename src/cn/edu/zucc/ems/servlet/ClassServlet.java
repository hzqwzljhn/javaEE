package cn.edu.zucc.ems.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.ems.model.ClassDAO;
import cn.edu.zucc.ems.util.connectUtil;

/**
 * Servlet implementation class ClassServlet
 */
public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ClassDAO dao=new connectUtil().getClassConnect();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassServlet() {
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
		String act= request.getParameter("act");
		String result = "";
		if("addclass".equals(act)) {
			result=addClass(request);
		}else if ("addresult".equals(act)) {
			result=addresult(request);
		}else if("modifyclass".equals(act)){
			result=modifyclass(request);
		}else if ("modifyresult".equals(act)) {
			result=modifyresult(request);
		}else if ("deleteclass".equals(act)) {
			result=deleteclass(request);
		}
		else {
			result=listClass(request);
		}
		RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher(result);
		if (dispatcher != null)
			dispatcher.forward(request, response);
	}

	/**
	 * 删除班级
	 * @param request
	 * @return
	 */
	private String deleteclass(HttpServletRequest request) {
		String classid = request.getParameter("classid");
		this.dao.deleteClass(Integer.valueOf(classid));
		request.setAttribute("objlist", this.dao.listAllClass());
		return "/class_list.jsp";
	}

	/**
	 * 修改班级信息
	 * @param request
	 * @return
	 */
	private String modifyresult(HttpServletRequest request) {
		String classid = request.getParameter("classid");
		String classname = request.getParameter("classname");
		this.dao.modifyClass(Integer.valueOf(classid), classname);
		request.setAttribute("objlist", this.dao.listAllClass());
		return "/class_list.jsp";
	}

	/**
	 * 修改班级页面
	 * @param request
	 * @return
	 */
	private String modifyclass(HttpServletRequest request) {
		String classid = request.getParameter("classid");
		request.setAttribute("obj", this.dao.getClasses(Integer.valueOf(classid)));
		return "/class_edit.jsp";
	}

	/**
	 * 添加班级
	 * @param request
	 * @return
	 */
	private String addresult(HttpServletRequest request) {
		String classid = request.getParameter("classid");
		String classname = request.getParameter("classname");
		this.dao.addClass(Integer.valueOf(classid), classname);
		request.setAttribute("objlist", this.dao.listAllClass());
		return "/class_list.jsp";
	}

	/**
	 * 添加班级页面
	 * @param request
	 * @return
	 */
	private String addClass(HttpServletRequest request) {
		return "/class_add.jsp";
	}

	/**
	 * 遍历所有班级
	 * @param request
	 * @return
	 */
	private String listClass(HttpServletRequest request) {
		request.setAttribute("objlist", this.dao.listAllClass());
		return "/class_list.jsp";
	}

}
