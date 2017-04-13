package edu.ql.dng.model;

import java.util.Date;

/**
 * 用户model
 * 
 * @author dxq
 *
 */
public class User {
	// 客户id
	private String id;
	// 客户名称
	private String userName;
	// 国家地区
	private String regin;
	// 电子邮件
	private String email;
	// 密码申请日期
	private String createDate;
	// 安装码
	private String installNum;
	// 计算机说明
	private String instruction;
	// 密码
	private String password;

	public String toString() {
		return "" + id + " " + userName + " " + regin + " " + email + " "
				+ createDate + " " + installNum + " " + instruction + " "
				+ password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRegin() {
		return regin;
	}

	public void setRegin(String regin) {
		this.regin = regin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getInstallNum() {
		return installNum;
	}

	public void setInstallNum(String installNum) {
		this.installNum = installNum;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
