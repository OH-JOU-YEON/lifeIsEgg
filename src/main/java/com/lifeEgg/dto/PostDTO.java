package com.lifeEgg.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class PostDTO {
	
<<<<<<< Upstream, based on origin/master
	private Long id; 
	private String content; 
	private boolean status; 
	private LocalDateTime created_at; 
	private Long user_id; 
	private UUID uuid; 
	private int age; 
=======
	private String content; 
	private boolean status; 
	private LocalDateTime created_at; 
	private Long user_id; 
	private UUID uuid; 
	
>>>>>>> dac3a51 로그인 컨트롤러 작성

	

}
