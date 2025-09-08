package com.lifeEgg.service.impl;

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
import com.lifeEgg.service.FeedService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor 
public class RecommendByAgeService implements FeedService {
	
	private final PostDAO postDAO; 
	
	@Override
	public FeedPageDTO<PostDTO> toFeedPageDTO(List<PostDTO> postList) {

		FeedPageDTO<PostDTO> feedPage = new FeedPageDTO<>();
		feedPage.setContentList(postList);
		feedPage.setTotalSize(postList.size());

		return feedPage;
	}
	
	
    public List<PostDTO> getFeedPosts(UserDTO user){ //나이별+나잇대별로 가져오기
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
        int minAge;
        int maxAge;

        for (int[] range : ranges) {
            if (posts.size() >= 10) break;
            
            minAge = age + range[0];
            maxAge = age + range[1];

            List<PostDTO> searchResult = postDAO.findByAgeRange(userId, minAge, maxAge);
            
            System.out.println("현재 나이 범위 min:" + minAge + " max:" + maxAge);
            System.out.println("검색 결과: " + searchResult.toString());
            
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
