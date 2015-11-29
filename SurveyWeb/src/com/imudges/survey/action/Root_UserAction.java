package com.imudges.survey.action;

import org.apache.struts2.ServletActionContext;

import com.imudges.survey.bean.Loginlog;
import com.imudges.survey.model.Root_UserModel;
import com.imudges.survey.service.LoginlogService;
import com.opensymphony.xwork2.ActionSupport;

public class Root_UserAction extends ActionSupport{
	private SecurityAction securityAction;
	private Root_UserModel root_UserModel;
	private LoginlogService loginlogService;
	public Root_UserModel getRoot_UserModel() {
		return root_UserModel;
	}

	public void setRoot_UserModel(Root_UserModel root_UserModel) {
		this.root_UserModel = root_UserModel;
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
		/**验证用户身份*/
		securityAction = new SecurityAction();
		if (securityAction.checkRoot()==false) {
			return LOGIN;
		}
		
		//返回用户所需的对象
		Integer userId = securityAction.getUserId();
		Loginlog loginlog = loginlogService.getLastLoginlogByUserId(securityAction.getUserId());
		root_UserModel = new Root_UserModel(loginlogService.getLastLoginlogByUserId(securityAction.getUserId()));
		return SUCCESS;
	}
	
}
