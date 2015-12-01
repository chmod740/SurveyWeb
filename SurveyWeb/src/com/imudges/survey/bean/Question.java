package com.imudges.survey.bean;

/**
 * Question entity. @author MyEclipse Persistence Tools
 */

public class Question implements java.io.Serializable {

	// Fields

	private Integer questionId;
	private Integer surveyId;
	private String text;
	private Integer type;
	private String title;

	// Constructors

	/** default constructor */
	public Question() {
	}

	/** full constructor */
	public Question(Integer surveyId, String text, Integer type, String title) {
		this.surveyId = surveyId;
		this.text = text;
		this.type = type;
		this.title = title;
	}

	// Property accessors

	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getSurveyId() {
		return this.surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}