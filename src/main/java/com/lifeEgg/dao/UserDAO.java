package com.lifeEgg.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lifeEgg.dto.UserDTO;

@Mapper
public interface UserDAO {

    public void insertUser(UserDTO user) throws Exception;
    
    public UserDTO findUserById(Long userId) throws Exception;
    
    public UserDTO findUserByEmail(Long email) throws Exception;

    public void updateUser(UserDTO user) throws Exception;

    public void deleteUser(Long userId) throws Exception;

}