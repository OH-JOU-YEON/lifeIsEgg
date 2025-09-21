package com.lifeEgg.dto;

import lombok.Data;

@Data 
public class AlarmDTO {
	private Long id;
	private String content; 
	private Long user_id; 
	private String post_uuid;
	private String cheer_uuid; 
	private String uuid; 
	private boolean readOrNot; 

}
