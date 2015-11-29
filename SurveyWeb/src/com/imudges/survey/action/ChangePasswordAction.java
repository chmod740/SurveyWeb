package com.imudges.survey.action;

import org.apache.struts2.ServletActionContext;

import com.imudges.survey.service.UserService;
import com.imudges.survey.util.MD5;
import com.opensymphony.xwork2.ActionSupport;

public class ChangePasswordAction extends ActionSupport{
	private String oldPassword;
	private String newPassword;
	private SecurityAction securityAction;
	private UserService userService;
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
		securityAction = new SecurityAction();
		if (securityAction.checkUser() == false) {
			return LOGIN;
		}
		if (oldPassword == null || newPassword == null) {
			ServletActionContext.getResponse().setStatus(500);
		}else {
			//先验证用户的身份，身份验证成功以后执行修改密码操作
			if (userService.login(securityAction.getUsername(), new MD5().encryptPassword(oldPassword))) {
				userService.changePassword(newPassword);
			}else {
				ServletActionContext.getResponse().setStatus(500);
			}
		}
		return SUCCESS;
	}
	
}
