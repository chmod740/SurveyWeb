package com.imudges.survey.UnitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.imudges.survey.DAO.UserDAO;
import com.imudges.survey.bean.User;
import com.imudges.survey.service.UserService;
import com.imudges.survey.util.MD5;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath*:*/*Context.xml"})
@Transactional  
public class UserTest {
	
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Test
	public void addUser(){
		if (userService.addUser("admin", "123", 1)) {
			System.out.println("添加成功");
		}else {
			System.out.println("添加失败");
		}
	}
	
	@Test
	public void test(){
		System.out.println("123");
	}
	
	@Test
	public void testt(){
		UserDAO userDAO = new UserDAO();
		User user = new User();
		user.setUsername("admin");
		user.setPassword(new MD5().encryptPassword("123"));
		user.setPrivilege(1);
		userDAO.save(user);
	}
}
