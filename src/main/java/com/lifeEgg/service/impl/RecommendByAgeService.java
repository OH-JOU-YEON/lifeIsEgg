package com.lifeEgg.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.lifeEgg.dao.PostDAO;
import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.FeedService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor 
public class RecommendByAgeService implements FeedService {
	
	private final PostDAO postDAO; 
	
	
	@Override
    public List<PostDTO> getFeedPosts(UserDTO user){ //나잇대별로 가져오기
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
        int len = 2;

        for (int[] range : ranges) {
            if (posts.size() >= len) break;
            
            minAge = age + range[0];
            maxAge = age + range[1];

            List<PostDTO> searchResult = postDAO.findByAgeRange(userId, minAge, maxAge);
            
            // 이미 뽑은 결과 제외
            searchResult.removeAll(posts);

            if (searchResult.isEmpty()) continue;

            Collections.shuffle(searchResult, random);

            int needed = len - posts.size();
            if (searchResult.size() > needed) {
                posts.addAll(searchResult.subList(0, needed));
            } else {
                posts.addAll(searchResult);
            }
        }
        return posts;
    }
    
    @Override
    public List<PostDTO> getFeedPosts(UserDTO user, List<PostDTO> old){ //나잇대별로 가져오되 특정 post 제외
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
        int len = 2;

        for (int[] range : ranges) {
            if (posts.size() >= len) break;
            
            minAge = age + range[0];
            maxAge = age + range[1];

            List<PostDTO> searchResult = postDAO.findByAgeRangeExcludeOld(userId, minAge, maxAge, old);
            
            // 이미 뽑은 결과 제외
            searchResult.removeAll(posts);

            if (searchResult.isEmpty()) continue;

            Collections.shuffle(searchResult, random);

            int needed = len - posts.size();
            if (searchResult.size() > needed) {
                posts.addAll(searchResult.subList(0, needed));
            } else {
                posts.addAll(searchResult);
            }
        }
        return posts;
    }

}