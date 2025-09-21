package com.lifeEgg.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.lifeEgg.dto.CheerDTO;


@Mapper 
public interface CheerDAO {
  
public void create(CheerDTO cheer) throws Exception;
    
    public List<CheerDTO> readByUserId(Long userId);
    
    public Optional<CheerDTO> readByUuid(String uuid);
   
    public Long readIdByUuid(String uuid); 
    
    public String getUuidById(Long id); 
   
  	public String getPreviewByUuid(String uuid); 

    public void delete(Long id) throws Exception;
    
    public void deleteByUuid(String uuid) throws Exception;

}