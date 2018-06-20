package cn.edu.zucc.ems.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.ems.bean.CheckBean;
import cn.edu.zucc.ems.bean.CheckDetailBean;
import cn.edu.zucc.ems.bean.CountBean;
import cn.edu.zucc.ems.bean.CourseBean;
import cn.edu.zucc.ems.bean.ExamBean;
import cn.edu.zucc.ems.bean.ExamdetailBean;
import cn.edu.zucc.ems.bean.StudentBean;
import cn.edu.zucc.ems.bean.ViewCheckDetail;
import cn.edu.zucc.ems.bean.ViewCountBean;
import cn.edu.zucc.ems.bean.ViewExamDetailBean;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

@Repository
@Transactional
public class CheckDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获取点名列表
	 * @param courseid
	 * @return
	 */
	public List<CheckBean> loadAllCheckList(int courseid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<CheckBean> list = session
				.createQuery("from CheckBean where course_id = " + courseid + " and removetime is null ").list();
		return list;

	}

	/**
	 * 添加点名
	 * @param courseid
	 */
	public void addCheck(int courseid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		CheckBean checkBean = new CheckBean();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		checkBean.setCourse_id(courseid);
		checkBean.setCheck_time(time);
		checkBean.setCreatetime(time);
		session.save(checkBean);
	}

	/**
	 * 读取学生信息
	 * @param classid
	 * @return
	 */
	public List<StudentBean> readStudent(int classid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<StudentBean> list = session.createQuery("from StudentBean where class_id = " + classid).list();
		return list;
	}

	/**
	 * 删除点名
	 * @param checkid
	 */
	public void deleteCheck(int checkid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		org.hibernate.query.Query query = session
				.createQuery("update CheckBean set removetime = '" + time + "' where check_id = " + checkid);
		query.executeUpdate();

	}

	/**
	 * 删除点名详情
	 * @param checkid
	 */
	public void deleteCheckDetail(int checkid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		org.hibernate.query.Query query = session
				.createQuery("update CheckDetailBean set removetime = '" + time + "' where check_id = " + checkid);
		query.executeUpdate();

	}

	/**
	 * 获取点名详情
	 * @param courseid
	 * @param checkid
	 * @return
	 */
	public List<ViewCheckDetail> readCheckDetail(int courseid, int checkid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<ViewCheckDetail> list = session
				.createQuery("from ViewCheckDetail where course_id = " + courseid + " and check_id = " + checkid)
				.list();
		return list;

	}

	/**
	 * 读取某点名详情
	 * @param checkid
	 * @return
	 */
	public List<ViewCheckDetail> readCheckDetailByCheckID(int checkid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<ViewCheckDetail> list = session.createQuery("from ViewCheckDetail where check_id = " + checkid).list();
		return list;

	}

	/**
	 * 添加点名详情
	 * @param state
	 * @param checkid
	 * @param studentid
	 */
	public void addCheckDetail(String state, int checkid, int studentid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		CheckDetailBean checkdetailBean = new CheckDetailBean();

		checkdetailBean.setCheck_id(checkid);
		checkdetailBean.setCreatetime(time);
		checkdetailBean.setState(state);
		checkdetailBean.setStudent_id(studentid);
		session.save(checkdetailBean);

	}

	/**
	 * 修改点名详情
	 * @param state
	 * @param checkdetail_id
	 */
	public void modifyCheckDetail(String state, int checkdetail_id) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		org.hibernate.query.Query query = session.createQuery(
				"update CheckDetailBean set state = '" + state + "' where check_detail_id = " + checkdetail_id);
		query.executeUpdate();

	}

	/**
	 * 查看最终成绩列表
	 *
	 * @param courseid
	 * @return
	 */
	public Object listFinal(Integer courseid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ViewCountBean where course_id=" + courseid;
		List<ViewCountBean> list = session.createQuery(hql).list();
		return list;
	}

	/**
	 * 获取所有考试列表
	 * @param courseid
	 * @return
	 */
	public Object loadAllExam(Integer courseid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ViewExamDetailBean where course_id = '" + courseid + "' and removetime is null group by exam_id";
		List<ExamBean> list = session.createQuery(hql).list();
		return list;
	}

	/**
	 * 添加考试
	 * @param examname
	 * @param courseid
	 */
	public void addExam(String examname, Integer courseid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamBean bean = new ExamBean();
		bean.setExam_name(examname);
		bean.setCourse_id(courseid);
		Date time=new Date();
		bean.setCreatetime(time);
		 DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String format = bf.format(time);
		session.saveOrUpdate(bean);
		String hql="from ExamBean where removetime is null and TIMESTAMPDIFF(SECOND,createtime,'"+format+"'	)=0";
		List<ExamBean> ebeans=(List<ExamBean>) session.createQuery(hql).list();
		ExamBean eBean=ebeans.get(0);
		int examid=eBean.getExam_id();
		CourseBean cBean=session.get(CourseBean.class, courseid);
		
		List<StudentBean> sBeans=(List<StudentBean>) getStudentByClassid(cBean.getClass_id());
		for(int i=0;i<sBeans.size();i++) {
			addScore(examid, sBeans.get(i).getStudent_id(), 0);
			session.flush();
			session.clear();
		}
	}

	/**
	 * 修改考试信息
	 * @param examid
	 * @param examname
	 */
	public void modifyExam(Integer examid, String examname) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamBean bean = getExam(examid);
		bean.setExam_id(examid);
		bean.setExam_name(examname);
		session.update(bean);
		
	}

	/**
	 * 获取某考试信息
	 * @param examid
	 * @return
	 */
	public ExamBean getExam(Integer examid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamBean bean = (ExamBean) session.get(ExamBean.class, examid);
		if (bean == null) {
			System.out.println("null");
		}
		return bean;
	}

	/**
	 * 删除考试
	 * @param examid
	 */
	public void deleteExam(Integer examid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamBean bean = getExam(examid);
		bean.setRemovetime(new Date());
		session.update(bean);
	}

	/**
	 * 获取学生列表
	 * @param classid
	 * @return
	 */
	public Object getStudentByClassid(Integer classid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql = "from StudentBean where class_id = '" + classid + "' and removetime is null";
		List<StudentBean> list = session.createQuery(hql).list();
		System.out.println(list.get(0).getStudent_name());
		return list;
	}

	/**
	 * 获取所有考试详情列表
	 * @param examid
	 * @return
	 */
	public Object loadAllExamDetail(Integer examid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ViewExamDetailBean where exam_id = '" + examid + "' and removetime is null";
		List<ViewExamDetailBean> list = session.createQuery(hql).list();
		return list;
	}

	/**
	 * 添加成绩
	 * @param examid
	 * @param studentid
	 * @param score
	 */
	public void addScore(Integer examid, Integer studentid, Integer score) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamdetailBean bean = new ExamdetailBean();
		bean.setExam_id(examid);
		bean.setStudent_id(studentid);
		bean.setScore(score);
		bean.setCreatetime(new Date());
		session.saveOrUpdate(bean);
	}

	/**
	 * 修改成绩
	 * @param examdetailid
	 * @param score
	 */
	public void modifyScore(Integer examdetailid, Integer score) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ViewExamDetailBean bean = getExamDetail(examdetailid);
		ExamdetailBean edBean = new ExamdetailBean();
		edBean.setExam_detail_id(bean.getExam_detail_id());
		edBean.setScore(score);
		edBean.setStudent_id(bean.getStudent_id());
		edBean.setExam_id(bean.getExam_id());
		edBean.setCreatetime(bean.getCreatetime());
		session.update(edBean);
	}

	/**
	 * 获得考试详情
	 * @param examdetailid
	 * @return
	 */
	public ViewExamDetailBean getExamDetail(Integer examdetailid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ViewExamDetailBean bean = (ViewExamDetailBean) session.get(ViewExamDetailBean.class, examdetailid);
		if (bean == null) {
			System.out.println("null");
		}
		return bean;
	}

	/**
	 * 删除考试详情
	 * @param examdetailid
	 */
	public void deleteExamDetail(Integer examdetailid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ViewExamDetailBean bean = getExamDetail(examdetailid);
		ExamdetailBean edBean = new ExamdetailBean();
		edBean.setExam_detail_id(bean.getExam_detail_id());
		edBean.setScore(bean.getScore());
		edBean.setStudent_id(bean.getStudent_id());
		edBean.setExam_id(bean.getExam_id());
		edBean.setCreatetime(bean.getCreatetime());
		edBean.setRemovetime(new Date());
		session.update(bean);
	}

	/**
	 * 获取某人的所有考试
	 * @param studentid
	 * @param courseid
	 * @return
	 */
	public Object listExam(Integer studentid, Integer courseid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql="from ViewExamDetailBean where student_id="+studentid+" and course_id="+courseid;
		List<ViewExamDetailBean> list = session.createQuery(hql).list();
		return list;
	}

	/**
	 * 获取某人的所有点名
	 * @param studentid
	 * @param courseid
	 * @return
	 */
	public Object listCheck(Integer studentid, Integer courseid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql="from ViewCheckDetail where student_id="+studentid+" and course_id="+courseid;
		List<ViewCheckDetail> list=session.createQuery(hql).list();
		return list;
	}

	/**
	 * 修改最终成绩
	 * @param studentid
	 * @param courseid
	 * @param score
	 */
	public void modifyfinal(int studentid, int courseid, int score) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql="from CountBean where student_id="+studentid+" and course_id="+courseid;
		List<CountBean> list=session.createQuery(hql).list();
		CountBean countBean=list.get(0);
		countBean.setFinal_score(score);
		session.update(countBean);
	}
}
