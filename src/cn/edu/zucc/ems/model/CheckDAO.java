package cn.edu.zucc.ems.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.ems.bean.CheckBean;
import cn.edu.zucc.ems.bean.StudentBean;

@Repository
@Transactional
public class CheckDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<CheckBean> loadAllCheckList(int courseid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<CheckBean> list = session.createQuery("from CheckBean where course_id = " + courseid).list();
		return list;
		
	}
	public List<CheckBean> addCheck(int courseid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<CheckBean> list = session.createQuery("from CheckBean where course_id = " + courseid).list();
		return list;
		
	}
	public List<CheckBean> deleteCheck(int courseid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<CheckBean> list = session.createQuery("from CheckBean where course_id = " + courseid).list();
		return list;
		
	}
	public List<CheckBean> readCheckDetail(int courseid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<CheckBean> list = session.createQuery("from CheckBean where course_id = " + courseid).list();
		return list;
		
	}
}
