package com.lifeEgg.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.lifeEgg.dto.AlarmDTO;


@Mapper 
public interface AlarmDAO {
	
public void create(AlarmDTO alarm) throws Exception;
    
    
    public Optional<AlarmDTO> readByUuid(String uuid);
    
    public List<AlarmDTO> readByUserId(Long userId);

    public void deleteById(Long id) throws Exception;
    
    public void deleteByUuid(String uuid) throws Exception;


}
