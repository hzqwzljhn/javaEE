package cn.edu.zucc.ems.bean;

import java.sql.Timestamp;

public class ViewCheckDetail {
	private int check_detail_id;
	
	private Timestamp createtime;
	private Timestamp coursetime;
	private Timestamp checktime;
	private Timestamp removetime;
	private String state;
	private int course_id;
	private int check_id;
	private String course_name;
	private int class_id;
	private String class_name;
	private int student_id;
	private String student_name;
	private String user_id;
	private String user_name;
	private String type;

	public int getCheck_detail_id() {
		return check_detail_id;
	}

	public void setCheck_detail_id(int check_detail_id) {
		this.check_detail_id = check_detail_id;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getCoursetime() {
		return coursetime;
	}

	public void setCoursetime(Timestamp coursetime) {
		this.coursetime = coursetime;
	}

	public Timestamp getChecktime() {
		return checktime;
	}

	public void setChecktime(Timestamp checktime) {
		this.checktime = checktime;
	}

	public Timestamp getRemovetime() {
		return removetime;
	}

	public void setRemovetime(Timestamp removetime) {
		this.removetime = removetime;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getCheck_id() {
		return check_id;
	}

	public void setCheck_id(int check_id) {
		this.check_id = check_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
