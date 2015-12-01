package com.imudges.survey.action;

import org.apache.struts2.ServletActionContext;

import com.imudges.survey.service.SurveyService;
import com.opensymphony.xwork2.ActionSupport;

public class AddSurveyAction extends ActionSupport{
	private String surveyString;
	private SecurityAction securityAction;
	private SurveyService surveyService;
	public String getSurveyString() {
		return surveyString;
	}

	public void setSurveyString(String surveyString) {
		this.surveyString = surveyString;
	}

	public SurveyService getSurveyService() {
		return surveyService;
	}

	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		securityAction = new SecurityAction();
		//判断用户身份，若用户不是root用户，则重定向到登录界面
		if (securityAction.checkRoot() == false) {
			return LOGIN;
		}
		//判断为post还是get
		if (ServletActionContext.getRequest().getMethod().equals("GET")) {
			return "show";
		}else {
			if (surveyString!=null) {
				surveyService.addSurvey(surveyString);
			}
			return SUCCESS;
		}
	}
	
}
