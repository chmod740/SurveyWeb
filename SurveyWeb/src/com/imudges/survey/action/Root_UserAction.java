package com.imudges.survey.action;

import com.opensymphony.xwork2.ActionSupport;

public class Root_UserAction extends ActionSupport{
	private SecurityAction securityAction;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		securityAction = new SecurityAction();
		if (securityAction.checkRoot()) {
			return LOGIN;
		}
		return super.execute();
	}
	
}
