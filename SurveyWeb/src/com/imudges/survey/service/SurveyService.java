package com.imudges.survey.service;

import com.imudges.survey.DAO.OptDAO;
import com.imudges.survey.DAO.QuestionDAO;
import com.imudges.survey.DAO.SurveyDAO;
import com.imudges.survey.bean.Opt;
import com.imudges.survey.bean.Question;
import com.imudges.survey.bean.Survey;

public class SurveyService {
	private SurveyDAO surveyDAO;
	private QuestionDAO questionDAO;
	private OptDAO optDAO;
	
	private Survey survey;
	private Question question;
	private Opt opt;
	
	public SurveyDAO getSurveyDAO() {
		return surveyDAO;
	}
	public void setSurveyDAO(SurveyDAO surveyDAO) {
		this.surveyDAO = surveyDAO;
	}
	public QuestionDAO getQuestionDAO() {
		return questionDAO;
	}
	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}
	public OptDAO getOptDAO() {
		return optDAO;
	}
	public void setOptDAO(OptDAO optDAO) {
		this.optDAO = optDAO;
	}
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Opt getOpt() {
		return opt;
	}
	public void setOpt(Opt opt) {
		this.opt = opt;
	}
	
	/**
	 * ���һ���µ��ʾ�
	 * */
	public boolean addSurvey(String surveyString){
		try {
			String theme;//�ʾ�����
			String startTimeString;
			String endTimeString;
			theme = surveyString.split("###")[0];
			startTimeString = surveyString.split("###")[1];
			endTimeString = surveyString.split("###")[2];
			String content = surveyString.split("###")[3];
			
			//��װ��Survey����������ݿ��
			survey.setTheme(theme);
			survey.setStartTime(startTimeString);
			survey.setEndTime(endTimeString);
			surveyDAO.save(survey);
			survey = (Survey)(surveyDAO.findByExample(survey).get(0));
			for (int i = 0; i < content.split("$$$").length; i++) {
				String questionString = content.split("$$$")[i];
				String title = questionString.split("***")[0];
				String questionType = questionString.split("***")[1];
				String questionText = questionString.split("***")[2];
				question.setTitle(title);
				if(questionType.equals(1)) {
					question.setType(1);//1����ѡ
				}else {
					question.setType(2);//2���ʴ�
				}
				question.setText(questionText);
				question.setSurveyId(survey.getSurveyId());
				questionDAO.save(question);//������������
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("log:����ʾ�ʧ��");
			return false;
		}
		return true;
	}
}
