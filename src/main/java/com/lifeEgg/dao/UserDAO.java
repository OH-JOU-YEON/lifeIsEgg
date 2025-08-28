package com.lifeEgg.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.lifeEgg.dto.UserDTO;

@Mapper
public interface UserDAO {

    public void insertUser(UserDTO user);
    
    public Optional<UserDTO> findUserById(int id);
    
    public Integer findUserIdByEmail(String email);

    public void updateUser(UserDTO user);

    public void deleteUser(int id);

}