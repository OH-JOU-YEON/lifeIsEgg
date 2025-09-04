package com.lifeEgg.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.lifeEgg.dao.PostDAO;
import com.lifeEgg.dto.FeedPageDTO;
import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.dto.PostPageDTO;

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
	
	
	public PostPageDTO<PostDTO> getPostsByUserId(Long userId) {
		
		
		
		PostPageDTO<PostDTO> postPage = new PostPageDTO<>();
		List<PostDTO> postList = postDAO.readByUserId(userId,postPage);
		postPage.setContentList(postList);
		postPage.setTotalSize(postList.size());
		
		
		
		return postPage;
	}

	
//	public FeedPageDTO<PostDTO> getPostsByAge(UserDTO user) {
//		
//		List<PostDTO> postList = postDAO.readByAge(user.getAge());
//		
//		FeedPageDTO<PostDTO> postPages = new FeedPageDTO<>();
//		postPages.setContentList(postList);
//		postPages.setTotalSize(postList.size());
//		
//		return postPages;
//	}
	
    public List<PostDTO> getPostsByAgeRange(UserDTO user){
    	List<PostDTO> posts = new ArrayList<>();
        Random random = new Random();
        
        int[][] ranges = {
            {0, 0},     // age == age
            {-3, 3},    // age ±3
            {-6, 6},    // age ±6
            {-9, 9},    // age ±9
            {-10, 10}   // age ±10
        };
        
        Long userId = user.getId();
        int age = user.getAge();

        for (int[] range : ranges) {
            if (posts.size() >= 10) break;
            
            Map<String, Object> params = new HashMap<>();
            params.put("user_id", userId);
            params.put("minAge", age + range[0]);
            params.put("maxAge", age + range[1]);

            List<PostDTO> searchResult = postDAO.findByAgeRange(params);

            // 이미 뽑은 결과 제외
            searchResult.removeAll(posts);

            if (searchResult.isEmpty()) continue;

            Collections.shuffle(searchResult, random);

            int needed = 10 - posts.size();
            if (searchResult.size() > needed) {
                posts.addAll(searchResult.subList(0, needed));
            } else {
                posts.addAll(searchResult);
            }
        }

        return posts;
    }

}
