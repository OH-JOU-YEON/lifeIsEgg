package com.lifeEgg.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lifeEgg.dto.PostAlarmDTO;
import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.PostPageDTO;


@Mapper 
public interface PostDAO {
	
	//생성, 수정, 삭제 메서드는 예외 처리
	
	public void create(PostDTO post) throws Exception;
	 
	//uuid로 글 하나를 불러오는 메서드
	
	public Optional<PostDTO> readByUuid(String uuid);
	
	//날짜로 글 하나 불러오기
	public PostDTO readByCreated(@Param("user_id")Long userId, @Param("created_at")LocalDate created_at);
	    
	//글쓴이의 포스트 불러오는 메서드
	    
	public List<PostDTO> readByUserId(@Param("userId") Long userId,@Param("postPage") PostPageDTO<PostDTO> postPage);
	
	public List<PostDTO> readAllByUserId(@Param("userId") Long userId);

	public int getPostCountByUserId(@Param("userId") Long userId);
	
	//포스트 uuid로 아이디 불러오는 메서드 
	public Long readPostIdByUuid(String uuid); 
	
	//포스트 uuid로 알림에 넣을 미리보기, 포스트 아이디 불러오는 메서드
	
	public PostAlarmDTO readPostAlarmDTOByUuid(String uuid); 
	
	//나이별로 추천
	public List<PostDTO> findByAgeRange(
			@Param("user_id")Long userId, @Param("min_age")int minAge, @Param("max_age")int maxAge);
	
	public List<PostDTO> findByAgeRangeExcludeOld(@Param("user_id")Long userId, 
			@Param("min_age")int minAge, @Param("max_age")int maxAge, @Param("old") List<PostDTO> old);

	public void update(PostDTO post) throws Exception;

	public void delete(String uuid) throws Exception;

}
