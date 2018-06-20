package cn.edu.zucc.ems.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.ems.bean.CheckBean;
import cn.edu.zucc.ems.bean.ExamdetailBean;
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
	private CourseDAO courseDAO = new connectUtil().getCourseConnect();

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
			result = this.listFinal(request);
		} else if (request.getParameter("tab").equals("exam")) {
			result = this.listExam(request);
		} else if (request.getParameter("tab").equals("addexam")) {
			result = this.addExam(request);
		} else if (request.getParameter("tab").equals("addexamresult")) {
			result = this.addExamResult(request);
		} else if (request.getParameter("tab").equals("modifyexam")) {
			result = this.modifyExam(request);
		} else if (request.getParameter("tab").equals("modifyexamresult") ){
			result=this.modifyExamResult(request);
		} else if (request.getParameter("tab").equals("deleteexam") ) {
			result=this.deleteExam(request);
		} else if (request.getParameter("tab").equals("listexam_detail")) {
			result=this.listExamDetail(request);
			request.getSession().setAttribute("tab","listexam_detail" );
		}  else if (request.getParameter("tab").equals("modifyscore")) {
			result = this.modifyScore(request);
		} else if (request.getParameter("tab").equals("modifyscoreresult")) {
			result = this.modifyScoreResult(request);
		} else if (request.getParameter("tab").equals("listall")) {
			result= this.listAlldetail(request);
		} else if (request.getParameter("tab").equals("modifyfinal")) {
			result=this.modifyfinal(request);
		}

		RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher(result);
		if (dispatcher != null)
			dispatcher.forward(request, response);
	}

	/**
	 * 修改最终成绩接口
	 * @param request
	 * @return
	 */
	private String modifyfinal(HttpServletRequest request) {
		int studentid=Integer.valueOf(request.getParameter("stuid"));
		int courseid=Integer.valueOf(request.getParameter("courseid"));
		String sc=request.getParameter("finalnum");
		int score;
		if (sc.equals("")||sc==null) {
			score=0;
		}else {
			score=Integer.valueOf(sc);
		}
		
		this.dao.modifyfinal(studentid, courseid,score);
		request.getSession().setAttribute("courseid", courseid);
		return listFinal(request);
	}

	/**
	 * 查看某人课程所有详情，包括点名，考试 的接口
	 * @param request
	 * @return
	 */
	private String listAlldetail(HttpServletRequest request) {
		String courseid=request.getParameter("courseid");
		String studentid=request.getParameter("stuid");
		request.getSession().setAttribute("stuid", studentid);
		request.getSession().setAttribute("courseid", courseid);
		request.setAttribute("listexam", this.dao.listExam(Integer.valueOf(studentid),Integer.valueOf(courseid)));
		request.setAttribute("listcheck", this.dao.listCheck(Integer.valueOf(studentid),Integer.valueOf(courseid)));		
		return "/list_alldetail.jsp";
	}

	/**
	 * 修改某次考试的成绩
	 * @param request
	 * @return
	 */
	private String modifyScoreResult(HttpServletRequest request) {
		String examdetailid = request.getParameter("examdetailid");
		String examid = request.getParameter("examid");
		String score = request.getParameter("score");
		this.dao.modifyScore(Integer.valueOf(examdetailid), Integer.valueOf(score));
		request.setAttribute("objlist", this.dao.loadAllExamDetail(Integer.valueOf(examid)));
		return "/course_detail.jsp";
	}

	/**
	 * 跳转修改某次考试成绩页面
	 * @param request
	 * @return
	 */
	private String modifyScore(HttpServletRequest request) {
		String examdetailid = request.getParameter("examdetailid");
				request.setAttribute("obj", this.dao.getExamDetail(Integer.valueOf(examdetailid)));			
				return "/score_edit.jsp";
		
	}

	/**
	 * 获取考试详情
	 * @param request
	 * @return
	 */
	private String listExamDetail(HttpServletRequest request) {

		String examid = request.getParameter("examid"); 
		request.getSession().setAttribute("examid",examid );
		request.setAttribute("objlist", this.dao.loadAllExamDetail(Integer.valueOf(examid)));
		return "/course_detail.jsp";
	}

	/**
	 * 删除考试
	 * @param request
	 * @return
	 */
	private String deleteExam(HttpServletRequest request) {
		String examid = request.getParameter("examid");
		this.dao.deleteExam(Integer.valueOf(examid));
		String courseid = request.getParameter("courseid");
		request.setAttribute("objlist", this.dao.loadAllExam(Integer.valueOf(courseid)));
		return "/course_detail.jsp";
		
	}

	/**
	 * 修改考试信息页面跳转
	 * @param request
	 * @return
	 */
	private String modifyExam(HttpServletRequest request) {
		String examid = request.getParameter("examid");
		request.setAttribute("obj", this.dao.getExam(Integer.valueOf(examid)));
		return "/exam_edit.jsp";
		
	}

	/**
	 * 修改考试信息接口
	 * @param request
	 * @return
	 */
	private String modifyExamResult(HttpServletRequest request) {
		String examid = request.getParameter("examid");
		String examname = request.getParameter("examname");
		String courseid = request.getParameter("courseid");
		this.dao.modifyExam(Integer.valueOf(examid),examname);
		request.setAttribute("objlist", this.dao.loadAllExam(Integer.valueOf(courseid)));
		return "/course_detail.jsp";
		
	}

	/**
	 * 添加考试接口
	 * @param request
	 * @return
	 */
	private String addExamResult(HttpServletRequest request) {
		String examname = request.getParameter("examname");
		String courseid = request.getParameter("courseid");
		this.dao.addExam(examname, Integer.valueOf(courseid));
		request.setAttribute("objlist", this.dao.loadAllExam(Integer.valueOf(courseid)));
		return "/course_detail.jsp";
	}

	/**
	 * 添加考试页面
	 * @param request
	 * @return
	 */
	private String addExam(HttpServletRequest request) {
		String courseid = request.getParameter("courseid");
		request.setAttribute("obj", this.courseDAO.getCourse(Integer.valueOf(courseid)));
		return "/exam_add.jsp";
	}

	/**
	 * 获取考试列表
	 * @param request
	 * @return
	 */
	private String listExam(HttpServletRequest request) {
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		request.getSession().setAttribute("tab", "exam");
		String userid = "";
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			if ((cookie.getName()).equals("userid")) {
				userid = cookie.getValue();
			}
		}
		String courseid = request.getParameter("courseid");
		request.getSession().setAttribute("course_id", courseid);
		request.setAttribute("objlistcourse", this.courseDAO.loadAllCourse(userid));
		request.setAttribute("objlist", this.dao.loadAllExam(Integer.valueOf(courseid)));
		return "/course_detail.jsp";
	}

	/**
	 * 获取最终成绩列表
	 * @param request
	 * @return
	 */
	private String listFinal(HttpServletRequest request) {
		String courseid = request.getParameter("courseid");
		request.setAttribute("listfinal", this.dao.listFinal(Integer.valueOf(courseid)));
		request.getSession().setAttribute("tab", "final");
		return "/course_detail.jsp";
	}

	/**
	 * 进入课程详情
	 * @param request
	 * @return
	 */
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

	/**
	 * 获取所有点名信息接口
	 * @param request
	 * @return
	 */
	private String loadAllCheckList(HttpServletRequest request) {
		String string = request.getParameter("courseid");
		int courseid = Integer.parseInt(string);

		List<CheckBean> list = this.dao.loadAllCheckList(courseid);

		request.getSession().setAttribute("tab", "checklist");
		request.setAttribute("checklist", list);
		request.getSession().setAttribute("course_id", courseid);

		return "/course_detail.jsp";
	}

	/**
	 * 添加点名页面
	 * @param request
	 * @return
	 */
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

	/**
	 * 删除点名接口
	 * @param request
	 * @return
	 */
	private String deleteCheck(HttpServletRequest request) {
		String string = request.getParameter("courseid");
		int courseid = Integer.parseInt(string);
		String string2 = request.getParameter("checkid");
		int checkid = Integer.parseInt(string2);

		this.dao.deleteCheck(checkid);
		this.dao.deleteCheckDetail(checkid);
		List<CheckBean> list = this.dao.loadAllCheckList(courseid);

		request.setAttribute("checklist", list);
		request.getSession().setAttribute("tab", "checklist");
		request.getSession().setAttribute("course_id", courseid);

		return "/course_detail.jsp";
	}

	/**
	 * 获取点名详情接口
	 * @param request
	 * @return
	 */
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

	/**
	 * 修改点名状态接口
	 * @param request
	 * @return
	 */
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
		request.setAttribute("checkdetail", list);
		request.getSession().setAttribute("tab", "checkdetail");

		return "/course_detail.jsp";
	}

	/**
	 * 添加考试详情
	 * @param request
	 * @return
	 */
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
