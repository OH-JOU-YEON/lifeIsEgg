package com.lifeEgg.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data 
public class UserAgeDTO {
	
	@JsonIgnore
    private Long id;
	
	private List<Birthday> birthdays;
	
	@Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Birthday {
        private Date date;
    }

	@Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Date {
        private Integer year;
    }
}