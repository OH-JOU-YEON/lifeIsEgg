package com.lifeEgg.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lifeEgg.dto.UserDTO;

@Mapper
public interface UserDAO {

    public void insertUser(UserDTO user) throws Exception;
    
    public UserDTO findUserById(int id) throws Exception;
    
    public int findUserIdByEmail(String email) throws Exception;

    public void updateUser(UserDTO user) throws Exception;

    public void deleteUser(int id) throws Exception;

}