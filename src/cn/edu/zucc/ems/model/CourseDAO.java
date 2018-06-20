package cn.edu.zucc.ems.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.ems.bean.CountBean;
import cn.edu.zucc.ems.bean.CourseBean;
import cn.edu.zucc.ems.bean.StudentBean;
import cn.edu.zucc.ems.bean.ViewCountBean;

@Repository
@Transactional
public class CourseDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Object loadAllCourse(String userid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql="from CourseBean where user_id='"+userid+"' and removetime is null";
		List<CourseBean> list=session.createQuery(hql).list();
		return list;
	}
	
	public void addCourse(Integer courseid, String coursename, Integer classid, String coursetime,String userid) throws ParseException{
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		CourseBean bean = new CourseBean();
		bean.setUser_id(userid);
		bean.setCourse_id(courseid);
		bean.setClass_id(classid);
		bean.setCourse_name(coursename);
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(coursetime);
		bean.setCourse_time(date); 
		bean.setCreatetime(new Date());
		session.saveOrUpdate(bean);
		List<StudentBean> sBeans=(List<StudentBean>) getStudentByClassid(classid);
		for(int i=0;i<sBeans.size();i++) {
			CountBean cBean=new CountBean();
			cBean.setCourse_id(courseid);
			cBean.setFinal_score(0);
			cBean.setStudent_id(sBeans.get(i).getStudent_id());
			cBean.setCreatetime(  new Date());
			session.saveOrUpdate(cBean);
			session.flush();
			session.clear();
		}
	}
	public Object getStudentByClassid(Integer classid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql = "from StudentBean where class_id = '" + classid + "' and removetime is null";
		List<StudentBean> list = session.createQuery(hql).list();
		System.out.println(list.get(0).getStudent_name());
		return list;
	}
	public void modifyCourse(Integer courseid, String coursename, String coursetime) throws ParseException{
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		CourseBean bean = getCourse(courseid);
		bean.setCourse_id(courseid);
		bean.setCourse_name(coursename);
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(coursetime);
		bean.setCourse_time(date); 
		session.update(bean);
	}
	
	public CourseBean getCourse(Integer courseid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		CourseBean bean = (CourseBean)session.get(CourseBean.class,courseid);
		if(bean == null){
			System.out.println("null");
		}
		return bean;
	}
	
	public void deleteCourse(Integer courseid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		CourseBean bean = getCourse(courseid);
		bean.setRemovetime(new Date());
		session.update(bean);
	}
}
