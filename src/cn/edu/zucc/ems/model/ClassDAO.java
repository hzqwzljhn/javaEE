package cn.edu.zucc.ems.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Data;

import cn.edu.zucc.ems.bean.ClassBean;

@Repository
@Transactional
public class ClassDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Object listAllClass() {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql="from ClassBean where removetime is null";
		List<ClassBean> list=session.createQuery(hql).list();
		return list;
	}


	public void addClass(Integer classid, String classname) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ClassBean bean=new ClassBean();
		bean.setClass_id(classid);
		bean.setClass_name(classname);
		bean.setCreatetime(new Date());
		session.saveOrUpdate(bean);
		
	}

	public void modifyClass(Integer classid, String classname) {
		this.setSessionFactory(sessionFactory);
		Session session=sessionFactory.getCurrentSession();
		ClassBean bean=getClasses(classid);
		bean.setClass_id(classid);
		bean.setClass_name(classname);
		
		session.update(bean);
		
	}
	public ClassBean getClasses(Integer classid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ClassBean bean=(ClassBean)session.get(ClassBean.class, classid);
		if (bean==null) {
			System.out.println("null");
		}
		return bean;
		
	}

	public void deleteClass(Integer classid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ClassBean bean=getClasses(classid);
		bean.setRemovetime(new Date());
		session.update(bean);
	}
	
}
