package com.imudges.survey.action;

import org.apache.struts2.ServletActionContext;

import com.imudges.survey.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends  ActionSupport{
	private String username;
	private String password;
	private UserService userService;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if (ServletActionContext.getRequest().getMethod().equals("GET")) {
			return LOGIN;
		}else {
			System.out.println(username);
			System.out.println(password);
			if (username == null || password == null) {
				this.addFieldError("errorinfo", "登录参数有误");
				return LOGIN;
			}
			if (username.equals("") || password.equals("")) {
				this.addFieldError("errorinfo", "用户名或者密码不能为空");
				return LOGIN;
			}
			if (userService.login(username, password)) {
				return SUCCESS;
			}else {
				this.addFieldError("errorinfo", "用户名或者密码错误");
				return LOGIN;
			}
		}
	}
	
}
