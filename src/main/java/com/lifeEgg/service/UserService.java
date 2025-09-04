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
    
    public UserDTO findUserById(Long id) {
    	Optional<UserDTO> user = userDao.findUserById(id);
		return user.orElseThrow(() -> new IllegalArgumentException("id: "+ id + "user not exist"));
    }
    
    //로그인 시도 시 회원가입 여부 확인
    public Long findUserIdByEmail(String email) {
    	Long userId = userDao.findUserIdByEmail(email);
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

    public void deleteUser(Long id) {
    	try {
    		userDao.deleteUser(id);

    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	}
    }
	

}
