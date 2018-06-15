package cn.edu.zucc.ems.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.ems.model.ClassDAO;
import cn.edu.zucc.ems.model.CourseDAO;
import cn.edu.zucc.ems.model.ExamDAO;
import cn.edu.zucc.ems.model.ExamDetailDAO;
import cn.edu.zucc.ems.model.StudentDAO;
import cn.edu.zucc.ems.util.connectUtil;

/**
 * Servlet implementation class CourseServlet
 */
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CourseDAO dao=new connectUtil().getCourseConnect();
    ExamDAO examdao=new connectUtil().getExamConnect();
    ClassDAO classdao=new connectUtil().getClassConnect();
    ExamDetailDAO examDetaildao=new connectUtil().getExamDetailConnect();
    StudentDAO studentdao=new connectUtil().getStudentConnect();
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
			result= listExam(request); 
			request.getSession().setAttribute("tab",tab );
		}else if ("final".equals(tab)) {
			result= "/course_detail.jsp";
			request.getSession().setAttribute("tab",tab );
		}
		else if("listexam_detail".equals(tab)){
			result = listExamDetail(request);
			request.getSession().setAttribute("tab",tab );
		}
		else if("addexam".equals(tab)){
			result = addExam(request);
		}
		else if("addexamresult".equals(tab)){
				result = addExamResult(request);
		}
		else if("modifyexam".equals(tab)){
			result = modifyExam(request);
		}
		else if("modifyexamresult".equals(tab)){
				result = modifyExamResult(request);
		}
		else if("deleteexam".equals(tab)){
			result = deleteExam(request);
		}
		else if("modifyscore".equals(tab)){
			result = modifyScore(request);
		}
		else if("modifyscoreresult".equals(tab)){
			result = modifyScoreResult(request);
		}
		else {
			result=listCourse(request);
		}
		RequestDispatcher dispatcher = request.getSession().getServletContext().getRequestDispatcher(result);
		if (dispatcher != null)
			dispatcher.forward(request, response);

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
		request.setAttribute("objlist", this.dao.loadAllCourse(courseid));
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
		String classid = request.getParameter("classid");
		this.dao.addCourse(Integer.valueOf(courseid),coursename,Integer.valueOf(classid),coursetime,userid);
		request.setAttribute("objlist", this.dao.loadAllCourse(userid));
		return "/course_list.jsp";
	}
	
	/**
	 * 添加课程页面
	 * @param request
	 * @return
	 */
	private String addCourse(HttpServletRequest request){
		request.setAttribute("objlist", this.classdao.listAllClass());
		return "/course_add.jsp";
	}
	
	/**
	 * 显示考试列表
	 * @param request
	 * @return
	 */
	private String listExam(HttpServletRequest request) {
		String userid=(String) request.getSession().getAttribute("userid");
		String courseid = request.getParameter("courseid");
		request.getSession().setAttribute("courseid",courseid );
		request.setAttribute("objlistcourse", this.dao.loadAllCourse(userid));
		request.setAttribute("objlist", this.examdao.loadAllExam(Integer.valueOf(courseid)));
		return "/course_detail.jsp";
	}
	
	/**
	 * 删除考试
	 * @param request
	 * @return
	 */
	private String deleteExam(HttpServletRequest request){
		String examid = request.getParameter("examid");
		this.examdao.deleteExam(Integer.valueOf(examid));
		String courseid = request.getParameter("courseid");
		request.setAttribute("objlist", this.examdao.loadAllExam(Integer.valueOf(courseid)));
		return "/course_detail.jsp";
	}
	
	/**
	 * 修改考试信息
	 * @param request
	 * @return
	 */
	private String modifyExamResult(HttpServletRequest request){
		String examid = request.getParameter("examid");
		String examname = request.getParameter("examname");
		String courseid = request.getParameter("courseid");
		this.examdao.modifyExam(Integer.valueOf(examid),examname);
		request.setAttribute("objlist", this.examdao.loadAllExam(Integer.valueOf(courseid)));
		return "/course_detail.jsp";
	}

	/**
	 * 修改考试页面
	 * @param request
	 * @return
	 */
	private String modifyExam(HttpServletRequest request){
		String examid = request.getParameter("examid");
		request.setAttribute("obj", this.examdao.getExam(Integer.valueOf(examid)));
		return "/exam_edit.jsp";
	}
	
	/**
	 * 添加考试信息
	 * @param request
	 * @return
	 */
	private String addExamResult(HttpServletRequest request){
		String examname = request.getParameter("examname");
		String courseid = request.getParameter("courseid");
		this.examdao.addExam(examname,Integer.valueOf(courseid));
		request.setAttribute("objlist", this.examdao.loadAllExam(Integer.valueOf(courseid)));
		return "/course_detail.jsp";
	}
	
	/**
	 * 添加考试页面
	 * @param request
	 * @return
	 */
	private String addExam(HttpServletRequest request){
		String courseid = request.getParameter("courseid");
		request.setAttribute("obj", this.dao.getCourse(Integer.valueOf(courseid)));
		return "/exam_add.jsp";
	}
	
//	/**
//	 * 显示考试详情列表
//	 * @param request
//	 * @return
//	 */
//	private String listExamDetail(HttpServletRequest request) {
//		String examid = request.getParameter("examid");
//		request.getSession().setAttribute("examid",examid);
//		request.setAttribute("objlist", this.examDetaildao.loadAllExamDetail(Integer.valueOf(examid)));
//		return "/course_detail.jsp";
//	}
	
	/**
	 * 显示考试详情列表
	 * @param request
	 * @return
	 */
	private String listExamDetail(HttpServletRequest request) {
		String classid = request.getParameter("classid"); 
		String examid = request.getParameter("examid"); 
		request.getSession().setAttribute("examid",examid );
		request.getSession().setAttribute("classid",classid );
		request.setAttribute("objlistexamdetail", this.examDetaildao.loadAllExamDetail(Integer.valueOf(examid)));
		request.setAttribute("objlist", this.studentdao.getStudentByClassid(Integer.valueOf(classid)));
		return "/course_detail.jsp";
	}
	
	/**
	 * 修改考试成绩
	 * @param request
	 * @return
	 */
	private String modifyScoreResult(HttpServletRequest request){
		String examdetailid = request.getParameter("examdetailid");
		String studentid = request.getParameter("studentid");
		String examid = request.getParameter("examid");
		String classid = request.getParameter("classid");
		String score = request.getParameter("score");
		this.examDetaildao.modifyScore(Integer.valueOf(examdetailid),Integer.valueOf(studentid), Integer.valueOf(score));
		request.setAttribute("objlistexamdetail", this.examDetaildao.loadAllExamDetail(Integer.valueOf(examid)));
		request.setAttribute("objlist", this.studentdao.getStudentByClassid(Integer.valueOf(classid)));
		return "/course_detail.jsp";
	}

	/**
	 * 修改成绩页面
	 * @param request
	 * @return
	 */
	private String modifyScore(HttpServletRequest request){
		String examdetailid = request.getParameter("examdetailid");
		String studentid = request.getParameter("studentid");
		String examid = request.getParameter("examid");
		String classid = request.getParameter("classid");
		request.setAttribute("obj", this.examDetaildao.getExamDetail(Integer.valueOf(examdetailid)));
		request.setAttribute("objclass", this.classdao.getClasses(Integer.valueOf(classid)));
		request.setAttribute("objexam", this.examdao.getExam(Integer.valueOf(examid)));
		request.setAttribute("objstudent", this.studentdao.getStudent(Integer.valueOf(studentid)));
		return "/score_edit.jsp";
	}
	
}
