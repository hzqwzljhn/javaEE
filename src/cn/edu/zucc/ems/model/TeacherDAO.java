package cn.edu.zucc.ems.model;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.ems.bean.TeacherBean;

@Repository
@Transactional
public class TeacherDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<TeacherBean> test() {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<TeacherBean> list = session.createQuery("from TeacherBean").list();
		
		return list;
	}
}