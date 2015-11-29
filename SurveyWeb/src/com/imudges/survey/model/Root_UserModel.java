package com.imudges.survey.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.imudges.survey.bean.Loginlog;
import com.imudges.survey.util.HttpRequest;

public class Root_UserModel {
	private String lastLoginIp;
	private String lastLoginAddress;
	private String lastLoginTime;
	
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginAddress() {
		return lastLoginAddress;
	}

	public void setLastLoginAddress(String lastLoginAddress) {
		this.lastLoginAddress = lastLoginAddress;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * Ĭ�ϵĹ��캯��
	 * */
	public Root_UserModel(){
		
	}
	
	/**
	 * �Խ��Ĺ��캯��
	 * */
	public Root_UserModel(Loginlog loginlog){
		if (loginlog == null) {
			lastLoginAddress = "��";
			lastLoginIp = "��";
			lastLoginTime = "��";
			return;
		}
		lastLoginIp = loginlog.getIp();
		String jsonString = HttpRequest.sendGet("http://int.dpool.sina.com.cn/iplookup/iplookup.php", "format=json&ip=" + lastLoginIp);
		if (jsonString.length() > 5) {
			try {
				Gson gson = new Gson();
				IpResult ipResult = gson.fromJson(jsonString, IpResult.class);
				lastLoginAddress = ipResult.getProvince() + " " + ipResult.getCity();
			} catch (Exception e) {
				// TODO: handle exception
				lastLoginAddress = "��ȡ����λ��ʱ�����쳣";
				System.err.println("�����ַ���ʱ�����쳣");
			}
			
		}else {
			lastLoginAddress = "�޷�ȷ�����ַλ��";
		}
		
		lastLoginTime = getStringTime(loginlog.getLoginTime());
	}
	
	
	private String getStringTime(String time){
		long t;
		try {
			t = Long.parseLong(time);
			Date date = new Date(t);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��");
			return simpleDateFormat.format(date);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
