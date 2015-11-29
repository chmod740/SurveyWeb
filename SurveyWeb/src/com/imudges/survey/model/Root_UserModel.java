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
	 * 默认的构造函数
	 * */
	public Root_UserModel(){
		
	}
	
	/**
	 * 自建的构造函数
	 * */
	public Root_UserModel(Loginlog loginlog){
		if (loginlog == null) {
			lastLoginAddress = "无";
			lastLoginIp = "无";
			lastLoginTime = "无";
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
				lastLoginAddress = "获取地理位置时发生异常";
				System.err.println("解析字符串时出现异常");
			}
			
		}else {
			lastLoginAddress = "无法确定其地址位置";
		}
		
		lastLoginTime = getStringTime(loginlog.getLoginTime());
	}
	
	
	private String getStringTime(String time){
		long t;
		try {
			t = Long.parseLong(time);
			Date date = new Date(t);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
			return simpleDateFormat.format(date);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
