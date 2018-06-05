package cn.edu.zucc.ems.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import cn.edu.zucc.ems.model.CourseDAO;

import cn.edu.zucc.ems.model.StudentDAO;

import cn.edu.zucc.ems.model.UserDAO;

public class connectUtil {
	ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
	public UserDAO getUserConnect() {
		return (UserDAO) this.context.getBean("userDAO");
	}

	public CourseDAO getCourseConnect() {
		return (CourseDAO) this.context.getBean("courseDAO");
	}

	
	public StudentDAO getStudentConnect() {
		return (StudentDAO) this.context.getBean("studentDAO");

	}
}
