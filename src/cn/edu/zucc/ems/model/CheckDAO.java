package cn.edu.zucc.ems.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.ems.bean.StudentBean;

@Repository
@Transactional
public class CheckDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<StudentBean> loadAllStudentByClass(int classid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<StudentBean> list = session.createQuery("from StudentBean where class_id = " + classid).list();
		return list;
		
	}
}
