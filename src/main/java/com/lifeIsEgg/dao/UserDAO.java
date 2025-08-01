package com.lifeIsEgg.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lifeIsEgg.dto.UserDTO;

@Mapper
public interface UserDAO {

    public void create(UserDTO user) throws Exception;
    
    public UserDTO read(Long userId) throws Exception;

    public void update(UserDTO user) throws Exception;

    public void delete(Long userId) throws Exception;

}