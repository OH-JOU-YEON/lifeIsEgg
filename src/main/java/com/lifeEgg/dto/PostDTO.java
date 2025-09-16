package com.lifeEgg.dto;

import java.time.LocalDate;

import lombok.Data;

@Data 
public class PostDTO {
	private Long id; 
	private String content; 
	private boolean status; 
	private LocalDate created_at; 
	private Long user_id; 
	private String uuid; 
	private int age; 
	private String preview; 
	
	

}
