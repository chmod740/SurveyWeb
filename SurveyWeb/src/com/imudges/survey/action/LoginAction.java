package com.imudges.survey.action;

import java.io.PrintWriter;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.geetest.sdk.java.GeetestLib;
import com.imudges.survey.bean.User;
import com.imudges.survey.service.LoginlogService;
import com.imudges.survey.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends  ActionSupport{
	private String username;
	private String password;
	private UserService userService;
	private LoginlogService loginlogService;
	
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

	public LoginlogService getLoginlogService() {
		return loginlogService;
	}

	public void setLoginlogService(LoginlogService loginlogService) {
		this.loginlogService = loginlogService;
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
				loginlogService.addLoginLog(userService.getUserIdByUsername(username), ServletActionContext.getRequest().getRemoteAddr(), System.currentTimeMillis(), 0, "未正确移动滑块");
				return LOGIN;
			}
			
			if (!gtResult.equals(GeetestLib.success_res)) {
				// TODO handle the Success result
				this.addFieldError("errorInfo", "请先正确移动滑块完成验证");
				loginlogService.addLoginLog(userService.getUserIdByUsername(username), ServletActionContext.getRequest().getRemoteAddr(), System.currentTimeMillis(), 0, "未正确移动滑块");
				return LOGIN;
			}
			
			
			if (username == null || password == null) {
				loginlogService.addLoginLog(userService.getUserIdByUsername(username), ServletActionContext.getRequest().getRemoteAddr(), System.currentTimeMillis(), 0, "登录参数错误");
				this.addFieldError("errorInfo", "登录参数有误");
				return LOGIN;
			}
			if (username.equals("") || password.equals("")) {
				loginlogService.addLoginLog(userService.getUserIdByUsername(username), ServletActionContext.getRequest().getRemoteAddr(), System.currentTimeMillis(), 0, "用户名或者密码错误");
				this.addFieldError("errorInfo", "用户名或者密码不能为空");
				return LOGIN;
			}
			if (userService.login(username, password)) {
				//写入用户数据到session中
				User user = userService.getCurrentUser();
				Map map = ActionContext.getContext().getSession();
				map.put("userId", user.getUserId());
				map.put("username", user.getUsername());
				map.put("privilege", user.getPrivilege());
				map.put("ip", ServletActionContext.getRequest().getRemoteAddr());
				loginlogService.addLoginLog(userService.getUserIdByUsername(username), ServletActionContext.getRequest().getRemoteAddr(), System.currentTimeMillis(), 1, "");
				return SUCCESS;
			}else {
				loginlogService.addLoginLog(userService.getUserIdByUsername(username), ServletActionContext.getRequest().getRemoteAddr(), System.currentTimeMillis(), 0, "用户名或者密码错误");
				this.addFieldError("errorInfo", "用户名或者密码错误");
				return LOGIN;
			}
		}
	}
	
}
