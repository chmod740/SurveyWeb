package com.imudges.survey.bean;

/**
 * Opt entity. @author MyEclipse Persistence Tools
 */

public class Opt implements java.io.Serializable {

	// Fields

	private Integer optId;
	private String secretId;
	private Integer surveyId;
	private String result;
	private Integer submit;

	// Constructors

	/** default constructor */
	public Opt() {
	}

	/** full constructor */
	public Opt(String secretId, Integer surveyId, String result, Integer submit) {
		this.secretId = secretId;
		this.surveyId = surveyId;
		this.result = result;
		this.submit = submit;
	}

	// Property accessors

	public Integer getOptId() {
		return this.optId;
	}

	public void setOptId(Integer optId) {
		this.optId = optId;
	}

	public String getSecretId() {
		return this.secretId;
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}

	public Integer getSurveyId() {
		return this.surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getSubmit() {
		return this.submit;
	}

	public void setSubmit(Integer submit) {
		this.submit = submit;
	}

}