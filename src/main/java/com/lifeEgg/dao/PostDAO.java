package com.lifeEgg.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lifeEgg.dto.PostDTO;


@Mapper 
public interface PostDAO {
	
	 public void create(PostDTO post) throws Exception;
	    
	    public PostDTO read(Long userId) throws Exception;

	    public void update(PostDTO post) throws Exception;

	    public void delete(Long userId) throws Exception;

}
