package cn.edu.zucc.ems.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.ems.bean.CheckBean;
import cn.edu.zucc.ems.bean.StudentBean;
import cn.edu.zucc.ems.bean.ViewCheckDetail;
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
		if (request.getParameter("tab").equals("readCheck")) {
			result = this.readCheck(request);
		} else if (request.getParameter("tab").equals("checklist")) {
			result = this.loadAllCheckList(request);
		} else if (request.getParameter("tab").equals("addcheck")) {
			result = this.addCheck(request);
		} else if (request.getParameter("tab").equals("deletecheck")) {
			result = this.deleteCheck(request);
		} else if (request.getParameter("tab").equals("checkdetail")) {
			result = this.readCheckDetail(request);
		} else if (request.getParameter("tab").equals("checkin")) {
			result = this.addCheckDetail(request);
		} else if (request.getParameter("tab").equals("modifycheck")) {
			result = this.modifyCheckDetail(request);
		} else if (request.getParameter("tab").equals("final")) {
			System.out.println("final");
			result = this.listFinal(request);
		}

		RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher(result);
		if (dispatcher != null)
			dispatcher.forward(request, response);
	}
	private String listFinal(HttpServletRequest request) {
		String courseid=request.getParameter("courseid");
		request.setAttribute("listfinal", this.dao.listFinal(Integer.valueOf(courseid)));
		request.getSession().setAttribute("tab", "final");
		return "/course_detail.jsp";
	}

	private String readCheck(HttpServletRequest request) {
		String string = request.getParameter("courseid");
		int courseid = Integer.parseInt(string);
		String string2 = request.getParameter("classid");
		int classid = Integer.parseInt(string2);
		
		request.getSession().setAttribute("tab", "check");
		request.getSession().setAttribute("course_id", courseid);
		request.getSession().setAttribute("class_id", classid);

		return "/course_detail.jsp";
	}

	private String loadAllCheckList(HttpServletRequest request) {
		String string = request.getParameter("courseid");
		int courseid = Integer.parseInt(string);

		List<CheckBean> list = this.dao.loadAllCheckList(courseid);

		request.getSession().setAttribute("tab", "checklist");
		request.setAttribute("checklist", list);
		request.getSession().setAttribute("course_id", courseid);

		return "/course_detail.jsp";
	}

	private String addCheck(HttpServletRequest request) {
		String string = request.getParameter("courseid");
		int courseid = Integer.parseInt(string);
		String string2 = request.getParameter("classid");
		int classid = Integer.parseInt(string2);

		this.dao.addCheck(courseid);
		List<StudentBean> list = this.dao.readStudent(classid);
		List<CheckBean> list2 = this.dao.loadAllCheckList(courseid);

		int checkid = list2.get(list2.size() - 1).getCheck_id();

		request.setAttribute("studentlist", list);
		request.getSession().setAttribute("tab", "student");
		request.getSession().setAttribute("course_id", courseid);
		request.getSession().setAttribute("check_id", checkid);

		return "/course_detail.jsp";
	}

	private String deleteCheck(HttpServletRequest request) {
		String string = request.getParameter("courseid");
		int courseid = Integer.parseInt(string);
		String string2 = request.getParameter("checkid");
		int checkid = Integer.parseInt(string2);

		this.dao.deleteCheck(checkid);
		List<CheckBean> list = this.dao.loadAllCheckList(courseid);

		request.setAttribute("checklist", list);
		request.getSession().setAttribute("tab", "checklist");
		request.getSession().setAttribute("course_id", courseid);

		return "/course_detail.jsp";
	}

	private String readCheckDetail(HttpServletRequest request) {
		String string = request.getParameter("courseid");
		int courseid = Integer.parseInt(string);
		String string2 = request.getParameter("checkid");
		int checkid = Integer.parseInt(string2);

		List<ViewCheckDetail> list = this.dao.readCheckDetail(courseid, checkid);

		request.setAttribute("checkdetail", list);
		request.getSession().setAttribute("tab", "checkdetail");
		request.getSession().setAttribute("course_id", courseid);

		return "/course_detail.jsp";
	}
	
	private String modifyCheckDetail(HttpServletRequest request) {
		String string = request.getParameter("checkdetail_id");
		String string2 = request.getParameter("checkid");
		String string3 = request.getParameter("courseid");
		String state = request.getParameter("state");
		int checkdetail_id = Integer.parseInt(string);
		int checkid = Integer.parseInt(string2);
		int courseid = Integer.parseInt(string3);
		
		this.dao.modifyCheckDetail(state, checkdetail_id);
		List<ViewCheckDetail> list = this.dao.readCheckDetail(courseid, checkid);
		
		//System.out.println(list.get(0).getCheck_id());

		request.setAttribute("checkdetail", list);
		request.getSession().setAttribute("tab", "checkdetail");

		return "/course_detail.jsp";
	}

	private String addCheckDetail(HttpServletRequest request) {
		String state = request.getParameter("state");
		String string3 = request.getParameter("classid");
		String string = request.getParameter("checkid");
		String string2 = request.getParameter("studentid");
		int checkid = Integer.parseInt(string);
		int studentid = Integer.parseInt(string2);
		int classid = Integer.parseInt(string3);

		this.dao.addCheckDetail(state, checkid, studentid);
		List<ViewCheckDetail> list2 = this.dao.readCheckDetailByCheckID(checkid);

		List<StudentBean> list = this.dao.readStudent(classid);

		Iterator<StudentBean> iter = list.iterator();
		while (iter.hasNext()) {
			StudentBean item = iter.next();
			if (item.getStudent_id() == studentid) {
				iter.remove();
			}
		}
		if (list != null) {
			Iterator<StudentBean> iter2 = list.iterator();
			while (iter2.hasNext()) {
				StudentBean item = iter2.next();
				for (int i = 0; i < list2.size() - 1; i++) {
					if (item.getStudent_id() == list2.get(i).getStudent_id()) {
						iter2.remove();
					}
				}
			}
		}

		request.setAttribute("studentlist", list);
		request.getSession().setAttribute("tab", "student");

		return "/course_detail.jsp";
	}

}
