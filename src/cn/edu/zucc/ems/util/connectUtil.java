package cn.edu.zucc.ems.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.zucc.ems.model.UserDAO;

public class connectUtil {
	public UserDAO getUserConnect() {
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		return (UserDAO) context.getBean("userDAO");
	}

}
