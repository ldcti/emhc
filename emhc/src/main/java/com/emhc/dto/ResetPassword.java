package com.emhc.dto;

public class ResetPassword {
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	private int userid;
	private String oldpassword;
	private String password;
	private String confirmpassword;

}
