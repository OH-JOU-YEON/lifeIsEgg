package com.lifeEgg.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lifeEgg.dto.AlarmDTO;
import com.lifeEgg.dto.AlarmPageDTO;


@Mapper 
public interface AlarmDAO {
	
public void create(AlarmDTO alarm) throws Exception;
    
    
    public Optional<AlarmDTO> readByUuid(String uuid);
    
    public List<AlarmDTO> readByUserId(@Param("userId")Long userId,@Param("alarmPage") AlarmPageDTO<AlarmDTO> alarmPage);
    
    public Long getAlarmsCountByUserId(Long userId);

    public void deleteById(Long id) throws Exception;
    
    public void deleteByUuid(String uuid) throws Exception;

    public void deleteByPostId(Long postId) throws Exception;

}
