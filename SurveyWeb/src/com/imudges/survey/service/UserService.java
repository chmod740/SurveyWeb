package com.imudges.survey.service;

import java.util.List;

import com.imudges.survey.DAO.UserDAO;
import com.imudges.survey.bean.User;
import com.imudges.survey.util.MD5;

public class UserService {
	private UserDAO userDAO;
	private User user;
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean login(String username,String password){
		user.setUsername(username);
		user.setPassword(new MD5().encryptPassword(password));
		List<User> list = userDAO.findByExample(user);
		if (list.isEmpty()) {
			return false;
		}else{
			user = list.get(0);
			return true;
		}
	}
	
	public boolean addUser(String username,String password,int privilege){
		//判断用户是不是已经存在了
		if (!userDAO.findByUsername(username).isEmpty()) {
			return false;
		}
		user.setUsername(username);
		user.setPassword(new MD5().encryptPassword(password));
		user.setPrivilege(privilege);
		userDAO.save(user);
		return true;
	}
	
	/* 获取已经正确登录的用户对象 */
	public User getCurrentUser(){
		return user;
	}
	
	/* 以用户名获取USER_ID */
	public int getUserIdByUsername(String username){
		List<User>list = userDAO.findByUsername(username);
		if (list.isEmpty()) {
			return 0;
		}
		return list.get(0).getUserId();
	}
	
	/**
	 * 修改用户密码
	 * 前提是用户已经正确登录，否则不会执行正确的操作修改密码操作
	 * */
	public void changePassword(String password){
		if (user.getUsername() != null && user.getUserId() != null) {
			user.setPassword(new MD5().encryptPassword(password));
			userDAO.save(user);
		}
	}
}
