package com.lifeEgg.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lifeEgg.dto.UserDTO;

@Mapper
public interface UserDAO {

    public void create(UserDTO user) throws Exception;
    
    public UserDTO read(Long userId) throws Exception;

    public void update(UserDTO user) throws Exception;

    public void delete(Long userId) throws Exception;

}