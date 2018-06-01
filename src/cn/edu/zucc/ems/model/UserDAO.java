package cn.edu.zucc.ems.model;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Data;

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
	 * @param userid
	 * @return
	 */
	public UserBean readUser(String userid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		UserBean userBean=session.get(UserBean.class, userid);
		return userBean;
	}
	
	public UserBean addTeacher(String userid, String username) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		UserBean userBean = new UserBean();
		Date time = new Date();
		userBean.setUser_id(userid);
		userBean.setUser_name(username);
		userBean.setPassword("123");
		userBean.setCreatetime(time);
		userBean.setType("教师");
		session.save(userBean);
		return userBean;
	}

}
