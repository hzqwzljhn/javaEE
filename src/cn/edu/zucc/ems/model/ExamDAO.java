package cn.edu.zucc.ems.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.ems.bean.ExamBean;
import cn.edu.zucc.ems.bean.StudentBean;

@Repository
@Transactional
public class ExamDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Object loadAllExam(Integer courseid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ExamBean where course_id = '"+courseid+"' and removetime is null";
		List<ExamBean> list = session.createQuery(hql).list();
		System.out.println(list.get(0).getExam_name());
		return list;
	}
	
	public void addExam(String examname,Integer courseid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamBean bean = new ExamBean();
		bean.setExam_name(examname);
		bean.setCourse_id(courseid);
		bean.setCreatetime(new Date());
		session.saveOrUpdate(bean);
	}
	
	public void modifyExam(Integer examid, String examname){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamBean bean = getExam(examid);
		bean.setExam_id(examid);
		bean.setExam_name(examname);
		session.update(bean);
	}
	
	public ExamBean getExam(Integer examid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamBean bean = (ExamBean)session.get(ExamBean.class,examid);
		if(bean == null){
			System.out.println("null");
		}
		return bean;
	}
	
	public void deleteExam(Integer examid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamBean bean = getExam(examid);
		bean.setRemovetime(new Date());
		session.update(bean);
	}
	
}
