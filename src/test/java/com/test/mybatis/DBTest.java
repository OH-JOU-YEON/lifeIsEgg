package com.test.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.lifeEgg.dao.UserDAO;
import com.lifeEgg.dto.UserDTO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml", 
    "classpath:database/config/mybatis-config.xml"
})
public class DBTest {
	
//	@Autowired
	private UserDAO userDao;
	
	@Test
    public void insertDBTest() throws Exception {
		UserDTO user = new UserDTO();
		user.setEmail("이메일");
		user.setUserName("테스트");
		user.setAge(99);
		userDao.insertUser(user);
    }
}