package cn.edu.zucc.ems.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.ems.bean.CheckBean;
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
		if (request.getParameter("tab").equals("readCheck")) {
			result = this.readCheck(request);
		} else if(request.getParameter("tab").equals("checklist")) {
			result = this.loadAllCheckList(request);
		} else if(request.getParameter("tab").equals("addcheck")) {
			result = this.addCheck(request);
		} else if(request.getParameter("tab").equals("deletecheck")) {
			result = this.deleteCheck(request);
		} else if(request.getParameter("tab").equals("checkdetail")) {
			result = this.readCheckDetail(request);
		}
		
		RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher(result);
		if (dispatcher != null)
			dispatcher.forward(request, response);
	}
	
	private String readCheck(HttpServletRequest request) {
		String string = request.getParameter("courseid");
		int courseid = Integer.parseInt(string);
		request.getSession().setAttribute("tab","check" );
		request.getSession().setAttribute("course_id", courseid);
		return "/course_detail.jsp";
	}
	
	private String loadAllCheckList(HttpServletRequest request) {
		String string = request.getParameter("courseid");
		int classid = Integer.parseInt(string);
		List<CheckBean> list = this.dao.loadAllCheckList(classid);
		request.getSession().setAttribute("tab","checklist" );
		request.setAttribute("checklist", list);
		return "/course_detail.jsp";
	}
	
	private String addCheck(HttpServletRequest request) {
		
		return "course_detail.jsp";
	}
	
	private String deleteCheck(HttpServletRequest request) {
		
		return "/course_detail.jsp";
	}
	
	private String readCheckDetail(HttpServletRequest request) {
		
		return "/check_detail.jsp";
	}

}
