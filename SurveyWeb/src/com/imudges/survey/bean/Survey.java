package com.imudges.survey.bean;

/**
 * Survey entity. @author MyEclipse Persistence Tools
 */

public class Survey implements java.io.Serializable {

	// Fields

	private Integer surveyId;
	private String theme;
	private String createTime;
	private String startTime;
	private String endTime;
	private String description;

	// Constructors

	/** default constructor */
	public Survey() {
	}

	/** full constructor */
	public Survey(String theme, String createTime, String startTime,
			String endTime, String description) {
		this.theme = theme;
		this.createTime = createTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	}

	// Property accessors

	public Integer getSurveyId() {
		return this.surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}