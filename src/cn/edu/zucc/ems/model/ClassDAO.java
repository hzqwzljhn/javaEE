package cn.edu.zucc.ems.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.ems.bean.ClassBean;


@Repository
@Transactional
public class ClassDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 遍历所有班级
	 * @return
	 */
	public Object listAllClass() {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql="from ClassBean where removetime is null";
		List<ClassBean> list=session.createQuery(hql).list();
		return list;
	}


	/**
	 * 添加班级
	 * @param classid
	 * @param classname
	 */
	public void addClass(Integer classid, String classname) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ClassBean bean=new ClassBean();
		bean.setClass_id(classid);
		bean.setClass_name(classname);
		bean.setCreatetime(new Date());
		session.saveOrUpdate(bean);
		
	}

	/**
	 * 修改班级
	 * @param classid
	 * @param classname
	 */
	public void modifyClass(Integer classid, String classname) {
		this.setSessionFactory(sessionFactory);
		Session session=sessionFactory.getCurrentSession();
		ClassBean bean=getClasses(classid);
		bean.setClass_id(classid);
		bean.setClass_name(classname);
		
		session.update(bean);
		
	}
	/**获取某班级信息
	 * @param classid
	 * @return
	 */
	public ClassBean getClasses(Integer classid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ClassBean bean=(ClassBean)session.get(ClassBean.class, classid);
		if (bean==null) {
			System.out.println("null");
		}
		return bean;
		
	}

	/**
	 * 删除班级
	 * @param classid
	 */
	public void deleteClass(Integer classid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		ClassBean bean=getClasses(classid);
		bean.setRemovetime(new Date());
		session.update(bean);
	}

	/**
	 * 获取所有未导入的学生
	 * @return
	 */
	public Object listNoclass() {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql="from StudentBean where removetime is null and class_id =0";
		return session.createQuery(hql).list();
	}

	/**
	 * 获取所有已导入的学生
	 * @param classid
	 * @return
	 */
	public Object listMyclass(String classid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql="from StudentBean where removetime is null and class_id ='"+classid+"'";
		return session.createQuery(hql).list();
	}
}
