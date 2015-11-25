package com.imudges.survey.action;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

import com.geetest.sdk.java.GeetestLib;
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
			GeetestLib geetest = GeetestLib.getGtSession(ServletActionContext.getRequest());
			int gt_server_status_code = GeetestLib
					.getGtServerStatusSession(ServletActionContext.getRequest());
			String gtResult = new String();
			if (gt_server_status_code == 1) {
				gtResult = geetest.enhencedValidateRequest(ServletActionContext.getRequest());
				
			}else {
				this.addFieldError("errorInfo", "请先正确移动滑块");
				return LOGIN;
			}
			
			if (!gtResult.equals(GeetestLib.success_res)) {
				// TODO handle the Success result
				this.addFieldError("errorInfo", "请先正确移动滑块完成验证");
				return LOGIN;
			}
			
			
			if (username == null || password == null) {
				this.addFieldError("errorInfo", "登录参数有误");
				return LOGIN;
			}
			if (username.equals("") || password.equals("")) {
				this.addFieldError("errorInfo", "用户名或者密码不能为空");
				return LOGIN;
			}
			if (userService.login(username, password)) {
				return SUCCESS;
			}else {
				this.addFieldError("errorInfo", "用户名或者密码错误");
				return LOGIN;
			}
		}
	}
	
}
