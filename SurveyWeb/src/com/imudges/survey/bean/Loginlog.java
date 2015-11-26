package com.imudges.survey.bean;

/**
 * Loginlog entity. @author MyEclipse Persistence Tools
 */

public class Loginlog implements java.io.Serializable {

	// Fields

	private Integer loginId;
	private Integer userId;
	private String loginTime;
	private Integer result;
	private String ip;
	private String errorInfo;
	// Constructors

	/** default constructor */
	public Loginlog() {
	}

	/** full constructor */
	public Loginlog(Integer userId, String loginTime, Integer result, String ip) {
		this.userId = userId;
		this.loginTime = loginTime;
		this.result = result;
		this.ip = ip;
	}

	// Property accessors

	public Integer getLoginId() {
		return this.loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public Integer getResult() {
		return this.result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	
}