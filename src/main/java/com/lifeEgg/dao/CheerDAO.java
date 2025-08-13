package com.lifeEgg.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lifeEgg.dto.CheerDTO;

@Mapper
public interface CheerDAO {
	
public void create(CheerDTO cheer) throws Exception;
    
    public CheerDTO read(Long id) throws Exception;

    public void update(CheerDTO cheer) throws Exception;

    public void delete(Long id) throws Exception;


}
