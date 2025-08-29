package com.lifeEgg.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data 
public class UserAgeDTO {
	
	@JsonIgnore
    private int id;
	
	private List<Birthday> birthdays;

//    public List<Birthday> getBirthdays() {
//        return birthdays;
//    }
//
//    public void setBirthdays(List<Birthday> birthdays) {
//        this.birthdays = birthdays;
//    }
	
	@Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Birthday {
        private Date date;

//        public Date getDate() {
//            return date;
//        }
//
//        public void setDate(Date date) {
//            this.date = date;
//        }
    }

	@Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Date {
        private Integer year;

//        public Integer getYear() {
//            return year;
//        }
//
//        public void setYear(Integer year) {
//            this.year = year;
//        }
    }
}