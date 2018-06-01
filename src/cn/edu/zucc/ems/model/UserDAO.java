package cn.edu.zucc.ems.model;

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
	 * @param userid
	 * @return
	 */
	public UserBean readUser(String userid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		UserBean userBean=session.get(UserBean.class, userid);
		System.out.println(userBean.getUser_name());
		return userBean;
	}

}
