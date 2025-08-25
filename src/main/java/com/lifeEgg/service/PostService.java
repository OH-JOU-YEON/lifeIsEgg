package com.lifeEgg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lifeEgg.dao.PostDAO;
import com.lifeEgg.dto.PostDTO;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor 
public class PostService {
	
	private final PostDAO postDAO; 
	
	
	
	public void createPost(PostDTO postDTO) {
		
		try {
			postDAO.create(postDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void deletePost(String uuid) {
		
		try {
			postDAO.delete(uuid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updatePost(PostDTO postDTO) {
		
		try {
			postDAO.update(postDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//uuid로 한 포스트 검색하는 메서드
	
	public PostDTO getPostByUuid(String uuid) {
		
	Optional<PostDTO> optionalPost = postDAO.readByUuid(uuid);
	
	return optionalPost.orElseThrow(() -> new IllegalArgumentException("post who has uuid doesn't exist"));
		
		
	}
	
	
	public List<PostDTO> getPostsByUserId(Long userId) {
		
		List<PostDTO> postList = postDAO.readByUserId(userId);
		
		return postList;
	}

	
	public List<PostDTO> getPostsByAge(int age) {
		
		List<PostDTO> postList = postDAO.readByAge(age);
		
		return postList; 
	}
}
