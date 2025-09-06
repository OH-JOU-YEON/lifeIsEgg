package com.lifeEgg.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import lombok.Data;

@Data 
public class PostDTO {
	private Long id; 
	private String content; 
	private boolean status; 
	private LocalDateTime created_at; 
	private Long user_id; 
	private String uuid; 
	private int age; 
	
	

}
