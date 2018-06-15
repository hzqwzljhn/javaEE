package cn.edu.zucc.ems.bean;

import java.sql.Timestamp;


public class UserBean {
	private String user_id;
	private String user_name;
	private String password;
	private String type;
	private Timestamp createtime;
	private Timestamp removetime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
}
