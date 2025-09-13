package com.lifeEgg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lifeEgg.dto.FeedPageDTO;
import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.UserDTO;


@Service
public interface FeedService {
	
    public List<PostDTO> getFeedPosts(UserDTO user); //나이별+나잇대별로 가져오기

	public List<PostDTO> getFeedPosts(UserDTO user, List<PostDTO> old);

}
