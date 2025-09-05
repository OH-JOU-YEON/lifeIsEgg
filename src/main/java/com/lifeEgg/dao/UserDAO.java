package com.lifeEgg.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.lifeEgg.dto.UserDTO;

@Mapper
public interface UserDAO {

    public void insertUser(UserDTO user);
    
    public Optional<UserDTO> findUserById(Long id);
    
    public Long findUserIdByEmail(String email);

    public void updateUser(UserDTO user);

    public void deleteUser(Long id);

}