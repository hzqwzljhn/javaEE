package cn.edu.zucc.ems.bean;

import java.util.Date;

public class CheckdetailBean {
	private int check_detail_id;
	private int check_id;
	private String state;
	private Date createtime;
	private Date removetime;

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
