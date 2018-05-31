package cn.edu.zucc.ems.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.zucc.ems.model.UserDAO;

public class connectUtil {
	ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
	public UserDAO getUserConnect() {
		return (UserDAO) this.context.getBean("userDAO");
	}

}
