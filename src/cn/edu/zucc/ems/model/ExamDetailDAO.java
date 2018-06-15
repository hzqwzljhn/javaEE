package cn.edu.zucc.ems.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.ems.bean.ExamBean;
import cn.edu.zucc.ems.bean.ExamdetailBean;

@Repository
@Transactional
public class ExamDetailDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Object loadAllExamDetail(Integer examid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ExamdetailBean where exam_id = '"+examid+"' and removetime is null";
		List<ExamdetailBean> list = session.createQuery(hql).list();
		System.out.println(list.get(0).getScore());
		return list;
	}
	
	public void addScore(Integer examid, Integer studentid, Integer score){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamdetailBean bean = new ExamdetailBean();
		bean.setExam_id(examid);
		bean.setStudent_id(studentid);
		bean.setScore(score);
		bean.setCreatetime(new Date());
		session.saveOrUpdate(bean);
	}
	
	public void modifyScore(Integer examdetailid, Integer studentid, Integer score){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamdetailBean bean = getExamDetail(examdetailid);
		bean.setStudent_id(studentid);
		bean.setScore(score);
		session.update(bean);
	}
	
	public ExamdetailBean getExamDetail(Integer examdetailid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamdetailBean bean = (ExamdetailBean)session.get(ExamdetailBean.class,examdetailid);
		if(bean == null){
			System.out.println("null");
		}
		return bean;
	}
	
	public void deleteExamDetail(Integer examdetailid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ExamdetailBean bean = getExamDetail(examdetailid);
		bean.setRemovetime(new Date());
		session.update(bean);
	}
	
}
