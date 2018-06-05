package cn.edu.zucc.ems.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.ems.bean.UserBean;

@Repository
@Transactional
public class UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 根据主键获取用户信息
	 * 
	 * @param userid
	 * @return
	 */
	public UserBean readUser(String userid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		UserBean userBean = session.get(UserBean.class, userid);
		return userBean;
	}

	public UserBean addTeacher(String userid, String username) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		UserBean userBean = new UserBean();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		userBean.setUser_id(userid);
		userBean.setUser_name(username);
		userBean.setPassword("123");
		userBean.setCreatetime(time);
		userBean.setType("教师");
		session.save(userBean);
		return userBean;
	}

	public UserBean modifyTeacher(String userid, String username, String pwd) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		org.hibernate.query.Query query = session.createQuery(
				"update user u set u.user_id = " + userid + " username = " + username + " password = " + pwd);
		return readUser(userid);
	}
	
	public List<UserBean> loadAllTeacher(){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		org.hibernate.query.Query query = session.createQuery("from UserBean where type = '教师' and remove_time is null ");
		
		List<UserBean> list = query.list();
		System.out.println(list.get(0).getUser_name());
		return list;
	}

}
