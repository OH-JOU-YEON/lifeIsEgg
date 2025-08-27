package com.lifeEgg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lifeEgg.dao.UserDAO;
import com.lifeEgg.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class UserService {
	
	private final UserDAO userDao;
	
    public void insertUser(UserDTO user) {
    	try {
    		userDao.insertUser(user);

    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	}
    }
    
    public UserDTO findUserById(int id) {
    	Optional<UserDTO> user = userDao.findUserById(id);
		return user.orElseThrow(() -> new IllegalArgumentException("id: "+ id + "user not exist"));
    }
    
    //로그인 시도 시 회원가입 여부 확인
    public Integer findUserIdByEmail(String email) { //Integer 이용하면 null 반환해도 오류 나지 않음
    	Integer userId = userDao.findUserIdByEmail(email);
    	return userId;
    }

    public void updateUser(UserDTO user) {
    	try {
    		userDao.updateUser(user);

    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	}
    }

    public void deleteUser(int id) {
    	try {
    		userDao.deleteUser(id);

    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	}
    }
	

}
