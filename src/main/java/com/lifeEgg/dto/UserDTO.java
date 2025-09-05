package com.lifeEgg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data 
public class UserDTO { //유저 정보를 받아올 시에만 필요하므로 id를 서버에 전달하지 않아도 괜찮을 듯(추후 관련 로직 필요해지면 변경)
	
	@JsonIgnore
    private Long id;
	
    private String name;
    private int age;
    private String email;
}