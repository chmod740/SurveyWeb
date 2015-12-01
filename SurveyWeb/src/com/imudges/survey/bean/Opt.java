package com.imudges.survey.bean;

/**
 * Opt entity. @author MyEclipse Persistence Tools
 */

public class Opt implements java.io.Serializable {

	// Fields

	private Integer optId;
	private String secretId;
	private String survey;
	private String result;
	private Integer submit;

	// Constructors

	/** default constructor */
	public Opt() {
	}

	/** full constructor */
	public Opt(String secretId, String survey, String result, Integer submit) {
		this.secretId = secretId;
		this.survey = survey;
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

	public String getSurvey() {
		return this.survey;
	}

	public void setSurvey(String survey) {
		this.survey = survey;
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