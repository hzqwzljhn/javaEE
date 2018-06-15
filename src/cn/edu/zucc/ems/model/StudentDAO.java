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
	
	/**查看所有学生
	 * @return
	 */
	public Object listAllStudent(){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql = "from StudentBean where removetime is null";
		List<StudentBean> list = session.createQuery(hql).list();
		System.out.println(list.get(0).getStudent_name());
		return list;
	}
	
	/**
	 * 添加学生
	 * @param studentid
	 * @param studentname
	 */
	public void addStudent(Integer studentid, String studentname){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		StudentBean bean = new StudentBean();
		bean.setStudent_id(studentid);
		bean.setStudent_name(studentname);
		bean.setCreatetime(new Date());
		session.saveOrUpdate(bean);
	}
	
	/**
	 * 修改学生信息
	 * @param studentid
	 * @param studentname
	 */
	public void modifyStudent(Integer studentid, String studentname){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		StudentBean bean = getStudent(studentid);
		bean.setStudent_id(studentid);
		bean.setStudent_name(studentname);
		session.update(bean);
	}
	
	/**
	 * 获取学生信息
	 * @param studentid
	 * @return
	 */
	public StudentBean getStudent(Integer studentid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		StudentBean bean = (StudentBean)session.get(StudentBean.class,studentid);
		if(bean == null){
			System.out.println("null");
		}
		return bean;
	}
	
	/**
	 * 删除学生
	 * @param studentid
	 */
	public void deleteStudent(Integer studentid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		StudentBean bean = getStudent(studentid);
		bean.setRemovetime(new Date());
		session.update(bean);
	}

	/**
	 * 导入学生
	 * @param stuid
	 * @param classid
	 */
	public void importstudent(Integer stuid, Integer classid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		StudentBean bean=getStudent(stuid);		
		bean.setClass_id(classid);
		session.update(bean);
		
	}

	/**
	 * 移除学生
	 * @param stuid
	 * @param classid
	 */
	public void exportStudent(Integer stuid, Integer classid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		StudentBean bean=getStudent(stuid);
		bean.setClass_id(0);
		session.update(bean);
		
	}
}
