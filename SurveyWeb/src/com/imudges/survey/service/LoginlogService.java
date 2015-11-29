package com.imudges.survey.service;

import java.util.List;

import com.imudges.survey.DAO.LoginlogDAO;
import com.imudges.survey.bean.Loginlog;

public class LoginlogService {
	private LoginlogDAO loginlogDAO;
	private Loginlog loginlog;
	public LoginlogDAO getLoginlogDAO() {
		return loginlogDAO;
	}
	public void setLoginlogDAO(LoginlogDAO loginlogDAO) {
		this.loginlogDAO = loginlogDAO;
	}
	public Loginlog getLoginlog() {
		return loginlog;
	}
	public void setLoginlog(Loginlog loginlog) {
		this.loginlog = loginlog;
	}
	
	public Loginlog getLastLoginlogByUserId(int userId){
		List<Loginlog>list;
		list = loginlogDAO.getLastList(userId);
		if(list.size() < 2){
			return null;
		}else {
			return list.get(1);
		}
	}
	
	public void addLoginLog(int userId,String ip,Long time,int result){
		//result  1.µÇÂ¼³É¹¦
		//result  0.µÇÂ¼Ê§°Ü
		loginlog.setIp(ip);
		loginlog.setLoginTime(time + "");
		loginlog.setUserId(userId);
		loginlog.setResult(result);
		loginlogDAO.save(loginlog);
	}
	
	public void addLoginLog(int userId,String ip,Long time,int result,String errorInfo){
		//result  1.µÇÂ¼³É¹¦
		//result  0.µÇÂ¼Ê§°Ü
		loginlog.setIp(ip);
		loginlog.setLoginTime(time + "");
		loginlog.setUserId(userId);
		loginlog.setResult(result);
		loginlog.setErrorInfo(errorInfo);
		loginlogDAO.save(loginlog);
	}
}
