package com.lifeEgg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lifeEgg.dao.CheerDAO;
import com.lifeEgg.dto.CheerDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class CheerService {
	
	private final CheerDAO cheerDAO;
	
	public void createCheer(CheerDTO cheerDTO) {
		
		try {
			cheerDAO.create(cheerDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteCheerById(Long id) {
		
		try {
			cheerDAO.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<CheerDTO> getCheersByUserId(Long userId) {
		
		List<CheerDTO> cheerList = cheerDAO.readByUserId(userId);
		
		return cheerList;
	}
	
	public CheerDTO getCheerByUuid(String uuid) {
		
		Optional<CheerDTO> optionalCheer = cheerDAO.readByUuid(uuid);
		
		return optionalCheer.orElseThrow(() -> new IllegalArgumentException("alarm who has uuid doesn't exist"));
	}

}
