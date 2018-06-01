package cn.edu.zucc.ems.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.ems.bean.CountBean;
import cn.edu.zucc.ems.bean.CourseBean;

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
		String hql="from CourseBean where user_id='"+userid+"'";
		List<CourseBean> list=session.createQuery(hql).list();
		System.out.println(list.get(0).getCourse_name());
		return list;
	}
	
}
