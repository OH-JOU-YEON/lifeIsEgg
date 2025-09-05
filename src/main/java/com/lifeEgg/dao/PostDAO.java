package com.lifeEgg.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.PostPageDTO;


@Mapper 
public interface PostDAO {
	
	//생성, 수정, 삭제 메서드는 예외 처리
	
	 public void create(PostDTO post) throws Exception;
	 
	 	//uuid로 글 하나를 불러오는 메서드
	    
	    public Optional<PostDTO> readByUuid(String uuid);
	    
	    //글쓴이의 포스트 불러오는 메서드
	    
	    public List<PostDTO> readByUserId(@Param("userId") Long userId,@Param("postPage") PostPageDTO<PostDTO> postPage); 
	    
	    //동일한 나이 게시물들을 불러오는 메서드
	    
	    public List<PostDTO> readByAge(int age);
	    
	    public List<PostDTO> findByAgeRange(Map<String, Object> params);

	    public void update(PostDTO post) throws Exception;

	    public void delete(String uuid) throws Exception;

}
