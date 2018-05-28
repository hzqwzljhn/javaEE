package cn.edu.zucc.ems.bean;

import java.util.Date;

public class CountBean {
	private int count_id;
	private int final_score;
	private int course_id;
	private Date createtime;
	private Date removetime;

	public int getCount_id() {
		return count_id;
	}

	public void setCount_id(int count_id) {
		this.count_id = count_id;
	}

	public int getFinal_score() {
		return final_score;
	}

	public void setFinal_score(int final_score) {
		this.final_score = final_score;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getRemovetime() {
		return removetime;
	}

	public void setRemovetime(Date removetime) {
		this.removetime = removetime;
	}
}
