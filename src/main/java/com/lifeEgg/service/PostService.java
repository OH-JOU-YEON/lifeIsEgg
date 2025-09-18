package com.lifeEgg.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lifeEgg.dao.PostDAO;
import com.lifeEgg.dto.PostAlarmDTO;
import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.PostPageDTO;
import com.lifeEgg.dto.UserDTO;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor 
public class PostService {
	
	private final PostDAO postDAO; 
	
	
	
	public void createPost(PostDTO postDTO) {
		
		postDTO.getContent();
		String removeText = "<(/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>;]*)?(\s)*(/)?>";
		postDTO.setContent(postDTO.getContent().replaceAll(removeText, ""));
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
	
	//uuid로 포스트 id, 미리보기 검색하는 메서드 
	public PostAlarmDTO getPostAlarmByUuid(String uuid) {
		
		return postDAO.readPostAlarmDTOByUuid(uuid); 
	}
	
	public Long getPostIdByUuid(String uuid) {
		
		return postDAO.readPostIdByUuid(uuid); 
	}
	
	//date로 검색
	public PostDTO getPostByCreated(LocalDate created_at) {
		return postDAO.readByCreated(created_at);	
	}
	
	public PostPageDTO<PostDTO> getPostsByUserId(Long userId, long page) {
		
		
		
		PostPageDTO<PostDTO> postPage = new PostPageDTO<>();
		postPage.setPage(page);
		List<PostDTO> postList = postDAO.readByUserId(userId,postPage);
		postPage.setContentList(postList);
		postPage.setTotalSize(postDAO.getPostCountByUserId(userId));
		
		
		
		return postPage;
	}
	
	public void updatePostAge(UserDTO user) {
		List<PostDTO> postList = postDAO.readAllByUserId(user.getId());
		
		for (PostDTO post : postList) {
			post.setAge(user.getAge());
			updatePost(post);
		}
	}

}
