package com.imudges.survey.action;

import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SecurityAction extends ActionSupport {
	private HttpSession session;
	public SecurityAction(){
		session = ServletActionContext.getRequest().getSession();
	}
	
	public boolean checkRoot(){
		//�õ��û���Ȩ�� 1:root 2:user
		if (checkIpAddress() == false) {
			return false;
		}
		Integer privilege = (Integer)session.getAttribute("privilege");
		if (privilege == null) {
			return false;
		}else {
			if (privilege == 1) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	
	public boolean checkUser(){
		if (checkIpAddress() == false) {
			return false;
		}
		Integer privilege = (Integer)session.getAttribute("privilege");
		if (privilege == null) {
			return false;
		}else {
			if (privilege == 1 || privilege == 2) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	public Integer getPrivilege(){
		return (Integer)session.getAttribute("privilege");
	}
	
	public String getUsername(){
		return (String)session.getAttribute("username");
	}
	
	/**
	 * �жϵ�¼ʱ��ip����ʵ�ǰҳ��ʱ���ip�ǲ���һ���ģ����Է�ֹxss����
	 * */
	public boolean checkIpAddress(){
		String loginIp = (String)session.getAttribute("ip");
		String nowIp = (String)ServletActionContext.getRequest().getRemoteAddr();
		if (loginIp == null || nowIp == null) {
			return false;
		}else {
			if (loginIp.equals(nowIp)) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	public Integer getUserId(){
		return (Integer)session.getAttribute("userId");
	}
}
