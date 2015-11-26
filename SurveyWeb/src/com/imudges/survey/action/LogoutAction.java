package com.imudges.survey.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Map map = ActionContext.getContext().getSession();
		map.clear();
		this.addFieldError("errorInfo", "×¢Ïú³É¹¦");
		return SUCCESS;
	}
}
