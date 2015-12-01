package com.imudges.survey.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	 * 添加一个新的问卷
	 * */
	public boolean addSurvey(String surveyString){
		try {
			String theme;//问卷主题
			String startTimeString;
			String endTimeString;
			String description;
			theme = surveyString.split("###")[0];
			description = surveyString.split("###")[1];
			startTimeString = surveyString.split("###")[2];
			endTimeString = surveyString.split("###")[3];
			
			String content = surveyString.split("###")[4];
			
			//封装成Survey对象存入数据库表
			survey.setTheme(theme);
			
			//生成开始时间
			SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
			String dateString=startTimeString+":00";
			try {
				Date date=format.parse(dateString);
				startTimeString = date.getTime()+"";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				startTimeString = "0";
			}
			//生成结束时间
			format=new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
			dateString=endTimeString+":00";
			try {
				Date date=format.parse(dateString);
				endTimeString = date.getTime()+"";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				endTimeString = "0";
			}
			
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
				if(questionType.equals("1")) {
					question.setType(1);//1：单选
				}
				if (questionType.equals("2")) {
					question.setType(2);
				}
				if (questionType.equals("3")) {
					question.setType(3);
				}
				question.setText(questionText);//包括所有的选项数据
				question.setSurveyId(survey.getSurveyId());
				questionDAO.save(question);//保存问题数据
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("log:添加问卷失败");
			return false;
		}
		return true;
	}
}
