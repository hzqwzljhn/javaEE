package cn.edu.zucc.ems.util;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.zucc.ems.bean.TeacherBean;
import cn.edu.zucc.ems.model.TeacherDAO;

public class test1111111 {

	public static void main(String[] args) {
		ApplicationContext context= new ClassPathXmlApplicationContext("application.xml");  
		
		TeacherDAO teacherDAO = (TeacherDAO) context.getBean("teacherDAO");
		
		List<TeacherBean> list = teacherDAO.test();
		
		System.out.println("1234");
	}

}
