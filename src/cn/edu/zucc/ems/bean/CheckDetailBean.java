package cn.edu.zucc.ems.bean;

import java.sql.Timestamp;

public class CheckDetailBean {
	private int check_detail_id;
	private int check_id;
	private int student_id;
	private String state;
	private Timestamp createtime;
	private Timestamp removetime;

	public int getCheck_detail_id() {
		return check_detail_id;
	}

	public void setCheck_detail_id(int check_detail_id) {
		this.check_detail_id = check_detail_id;
	}

	public int getCheck_id() {
		return check_id;
	}

	public void setCheck_id(int check_id) {
		this.check_id = check_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getRemovetime() {
		return removetime;
	}

	public void setRemovetime(Timestamp removetime) {
		this.removetime = removetime;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

}
