package com.lifeEgg.dto;

import lombok.Data;

@Data 
public class AlarmDTO {
	private Long id;
	private String content; 
	private Long user_id; 
	private Long post_id;
	private Long cheer_id; 
	private String uuid; 

}
