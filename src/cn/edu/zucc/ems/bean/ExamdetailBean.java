package cn.edu.zucc.ems.bean;

import java.util.Date;

public class ExamdetailBean {
	private int exam_detail_id;
	private int score;
	private int exam_id;
	private int student_id;
	private Date createtime;
	private Date removetime;

	public int getExam_detail_id() {
		return exam_detail_id;
	}

	public void setExam_detail_id(int exam_detail_id) {
		this.exam_detail_id = exam_detail_id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	
	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
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
