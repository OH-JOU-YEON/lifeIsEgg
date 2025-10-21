package com.lifeEgg.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lifeEgg.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomUserDetails implements UserDetails { //spring security 수동 로그인용
	
	@JsonIgnore
    private Long id;
    private String email;
    private String name;
    private int age;

    // 필수 오버라이드 메서드들
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() { return null; }

    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
    
    public UserDTO toUserDTO() {
    	UserDTO user = new UserDTO();
    	user.setId(id);
    	user.setAge(age);
    	user.setEmail(email);
    	user.setName(name);
    	return user;
    }

}