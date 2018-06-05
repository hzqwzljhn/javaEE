package cn.edu.zucc.ems.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.ems.bean.StudentBean;

@Repository
@Transactional
public class StudentDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Object listAllStudent(){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql = "from StudentBean where removetime is null";
		List<StudentBean> list = session.createQuery(hql).list();
		return list;
	}
	
	public void addStudent(Integer studentid, String studentname){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		StudentBean bean = new StudentBean();
		bean.setStudent_id(studentid);
		bean.setStudent_name(studentname);
		bean.setCreatetime(new Date());
		session.saveOrUpdate(bean);
	}
	
	public void modifyStudent(Integer studentid, String studentname){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		StudentBean bean = getStudent(studentid);
		bean.setStudent_id(studentid);
		bean.setStudent_name(studentname);
		session.update(bean);
	}
	
	public StudentBean getStudent(Integer studentid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		StudentBean bean = (StudentBean)session.get(StudentBean.class,studentid);
		if(bean == null){
			System.out.println("null");
		}
		return bean;
	}
	
	public void deleteStudent(Integer studentid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		StudentBean bean = getStudent(studentid);
		bean.setRemovetime(new Date());
		session.update(bean);
	}
}
