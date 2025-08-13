package com.lifeEgg.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lifeEgg.dto.AlarmDTO;


@Mapper 
public interface AlarmDAO {
	
public void create(AlarmDTO alarm) throws Exception;
    
    public AlarmDTO read(Long id) throws Exception;
    
    public AlarmDTO readByUuid(String uuid) throws Exception;

    public void delete(Long id) throws Exception;


}
