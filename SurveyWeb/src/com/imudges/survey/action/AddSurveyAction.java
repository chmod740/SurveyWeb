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
		//�ж��û���ݣ����û�����root�û������ض��򵽵�¼����
		if (securityAction.checkRoot() == false) {
			return LOGIN;
		}
		//�ж�Ϊpost����get
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
