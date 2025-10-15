package com.lifeEgg.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data 
public class PostDTO {
	private Long id; 
	private String content; 
	private boolean status;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate created_at; 
	private Long user_id; 
	private String uuid; 
	private int age; 
	private String preview; 
	
	

}
