package com.lifeEgg.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.lifeEgg.dto.CheerAlarmDTO;
import com.lifeEgg.dto.CheerDTO;


@Mapper 
public interface CheerDAO {
  
public void create(CheerDTO cheer) throws Exception;
    
    public List<CheerDTO> readByUserId(Long userId);
    
    public Optional<CheerDTO> readByUuid(String uuid);
   
    public Long readIdByUuid(String uuid); 
    
  //cheer uuid로 알림에 넣을 미리보기, cheer 아이디 불러오는 메서드
	
  	public CheerAlarmDTO readCheerAlarmDTOByUuid(String uuid); 

    public void delete(Long id) throws Exception;
    
    public void deleteByUuid(String uuid) throws Exception;

}